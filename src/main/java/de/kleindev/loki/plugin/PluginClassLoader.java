package de.kleindev.loki.plugin;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginClassLoader extends URLClassLoader {

    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginClassLoader get(URL url) {
        return new PluginClassLoader(new URL[]{url}, this);
    }

}