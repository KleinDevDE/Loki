package de.kleindev.loki.plugin;


import de.kleindev.loki.Main;
import de.kleindev.loki.utils.configuration.file.FileConfiguration;
import de.kleindev.loki.utils.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class InternalPlugin extends BotPlugin {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public InternalPlugin() {
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.set("name", "Internal");
        fileConfiguration.set("version", Main.VERSION);
        fileConfiguration.set("main", Main.class.getName());
        fileConfiguration.set("authors", "KleinDev");
        set(UUID.randomUUID(), new File("./"), new File("./"), new PluginDescription(fileConfiguration));
    }
}
