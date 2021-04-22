package de.kleindev.loki.plugin;

import de.kleindev.loki.utils.configuration.file.FileConfiguration;
import de.kleindev.loki.utils.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;

public class PluginDescription {
    private String pluginName;
    private String version;
    private String[] authors;
    private String main;

    public PluginDescription(InputStream pluginYMLFile){
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(pluginYMLFile));
        load(configuration);
    }

    public PluginDescription(FileConfiguration configuration){
        load(configuration);
    }

    private void load(FileConfiguration configuration){
        try {
            if(configuration.get("name") == null)
                throw new PluginLoadException("plugin.yml file doesn't contain \"name\"");
            if(configuration.get("version") == null)
                throw new PluginLoadException("plugin.yml file doesn't contain \"version\"");
            if(configuration.get("authors") == null && configuration.get("author") == null)
                throw new PluginLoadException("plugin.yml file doesn't contain \"authors\"");
            if(configuration.get("main") == null)
                throw new PluginLoadException("plugin.yml file doesn't contain \"main\"");
            pluginName = configuration.getString("name");
            version = configuration.getString("version");
            if (configuration.get("authors") == null){
                authors = new String[]{configuration.getString("author")};
            } else {
                authors = configuration.getString("authors").replace(" ", "").split(",");
            }
            main = configuration.getString("main");
        }catch (PluginLoadException e) {
            Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
        }
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getVersion() {
        return version;
    }

    public String getMain() {
        return main;
    }

    public String[] getAuthors() {
        return authors;
    }
}
