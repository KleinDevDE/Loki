package de.kleindev.loki.plugin;


import de.kleindev.loki.Main;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

public class InternalPlugin extends BotPlugin {
    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public InternalPlugin(){
        Properties properties = new Properties();
        properties.setProperty("name", "Internal");
        properties.setProperty("version", Main.VERSION);
        properties.setProperty("main", Main.class.getName());
        properties.setProperty("authors", "KleinDev");

        set(UUID.randomUUID(), new File("./"), new File("./"), new PluginDescription(properties));
    }
}
