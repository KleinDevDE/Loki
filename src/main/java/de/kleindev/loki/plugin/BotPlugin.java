package de.kleindev.loki.plugin;

import lombok.Getter;

import java.io.File;
import java.util.UUID;

public abstract class BotPlugin {
    private static PluginDescription pluginDescription;
    private static File dataFolder;
    private static File pluginFile;
    @Getter
    private UUID pluginID;

    public static File getDataFolder() {
        dataFolder.mkdir();
        return dataFolder;
    }

    public abstract void onEnable();

    /**
     * Every listener will be automatically unregistered while disabling the plugin
     */
    public abstract void onDisable();

    public static File getPluginFile() {
        return pluginFile;
    }

    public void reload() {

    }

    public PluginDescription getPluginDescription() {
        return pluginDescription;
    }

    public final void set(final UUID pluginID, final File pluginFile, final File dataFolder, final PluginDescription pluginDescription) {
        BotPlugin.pluginDescription = pluginDescription;
        BotPlugin.dataFolder = dataFolder;
        BotPlugin.pluginFile = pluginFile;
    }
}
