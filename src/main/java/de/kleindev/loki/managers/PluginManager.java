package de.kleindev.loki.managers;

import de.kleindev.loki.Agent;
import de.kleindev.loki.Loki;
import de.kleindev.loki.logging.Logger;
import de.kleindev.loki.plugin.BotPlugin;
import de.kleindev.loki.plugin.PluginDescription;
import de.kleindev.loki.plugin.PluginLoadException;
import de.kleindev.loki.utils.DevTweaks;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginManager {
    private static HashMap<String, PluginEntry> pluginHashMap = new HashMap<>();

    @SneakyThrows
    public void loadPlugin(File file) {
        JarFile jarFile;
        JarEntry pluginDescriptionFile;
        PluginDescription pluginDescription;
        Class<?> pluginMainClass;

        if (!DevTweaks.isJAR(file)) {
            throw new PluginLoadException("File \"" + file.getName() + "\" isn't a valid jar file!");
        }

        jarFile = new JarFile(file);
        pluginDescriptionFile = jarFile.getJarEntry("plugin.yml");

        if (pluginDescriptionFile == null) {
            throw new PluginLoadException("File \"" + file.getName() + "\" doesn't contain the plugin.yml file!");
        }

        pluginDescription = new PluginDescription(jarFile.getInputStream(pluginDescriptionFile));

        if (pluginHashMap.containsKey(pluginDescription.getPluginName().toLowerCase())) {
            Logger.log("Plugin \"" + pluginDescription.getPluginName() + "\" already present! Skipping..");
            return;
        }

        Logger.log("Loading plugin \"" + pluginDescription.getPluginName() + "\"..");

        Agent.appendJarFile(jarFile);
        pluginMainClass = ClassLoader.getSystemClassLoader().loadClass(pluginDescription.getMain().replace("/", ".").replace(".class", ""));
        if (!pluginMainClass.getSuperclass().equals(BotPlugin.class)) {
            throw new PluginLoadException("Plugin \"" + pluginDescription.getPluginName() + "\" doesn't contain a class which extends EoBotPlugin!");
        }

        BotPlugin plugin = (BotPlugin) pluginMainClass.getConstructor().newInstance();
        UUID pluginID = UUID.randomUUID();

        String pack = plugin.getPluginDescription().getMain().replace(
                "." + plugin.getPluginDescription().getMain().split("\\.")[(plugin.getPluginDescription().getMain().split("\\.").length) - 1], "");

        PluginEntry pluginEntry = new PluginEntry();
        pluginEntry.botPlugin = plugin;
        pluginEntry.pluginID = pluginID;
        pluginEntry.packagePath = pack;
        pluginEntry.pluginFile = file;
        plugin.set(pluginID, file, new File(Loki.getInstance().getLokiConfiguration().pluginsFolder + "/" + pluginDescription.getPluginName()), pluginDescription);

        pluginHashMap.put(pluginDescription.getPluginName().toLowerCase(Locale.ROOT), pluginEntry);
        Logger.log("Enabling plugin \"" + pluginDescription.getPluginName() + "\"..");
        plugin.onEnable();
        Logger.log(pluginDescription.getPluginName() + " enabled!");
    }

    public BotPlugin getPlugin(String name) {
        return getPluginEntry(name.toLowerCase()).botPlugin;
    }

    public PluginDescription getPluginDescription(String name) {
        return getPluginEntry(name.toLowerCase()).botPlugin.getPluginDescription();
    }

    public void unloadPlugin(BotPlugin botPlugin) {
        Logger.log("Unloading plugin \"" + botPlugin.getPluginDescription().getPluginName() + "\"..");
        Loki.getInstance().getEventManager().unregisterListener(botPlugin);
        Loki.getInstance().getCommandManager().unregisterCommands(botPlugin);
        botPlugin.onDisable();
        pluginHashMap.remove(botPlugin.getPluginDescription().getPluginName().toLowerCase());
        //TODO Remove from classloader
        Logger.log(botPlugin.getPluginDescription().getPluginName() + " disabled!");
    }

    public Collection<BotPlugin> getActivePlugins() {
        List<BotPlugin> plugins = new ArrayList<>();
        for (Map.Entry<String, PluginEntry> entry : pluginHashMap.entrySet()) {
            plugins.add(entry.getValue().botPlugin);
        }
        return plugins;
    }

    public String getPluginNameByPackage(String pack) {
        for (Map.Entry<String, PluginEntry> entry : pluginHashMap.entrySet()) {
            if (entry.getValue().packagePath.equals(pack) || entry.getValue().packagePath.startsWith(pack)) {
                return entry.getValue().botPlugin.getPluginDescription().getPluginName();
            }
        }
        return "";
    }

    protected PluginEntry getPluginEntry(String pluginName) {
        return pluginHashMap.getOrDefault(pluginName, new PluginEntry());
    }

    public void reloadPlugin(BotPlugin botPlugin) {
        Logger.info("Reloading plugin " + botPlugin.getPluginDescription().getPluginName());
        if (!pluginHashMap.containsKey(botPlugin.getPluginDescription().getPluginName().toLowerCase())){
            Logger.error("Could not reload this plugin cause it doesn't exists in the pluginManager!?");
            return;
        }
        Agent.reloadClasses(pluginHashMap.get(botPlugin.getPluginDescription().getPluginName().toLowerCase()).packagePath);
        botPlugin.reload();
    }

    private static class PluginEntry {
        public UUID pluginID = null;
        public File pluginFile = null;
        public BotPlugin botPlugin = null;
        private String packagePath = null;
    }
}
