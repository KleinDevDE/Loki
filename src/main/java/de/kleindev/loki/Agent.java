package de.kleindev.loki;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.jar.JarFile;

public class Agent {
    public static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void agentmain(String args, Instrumentation instrumentation) {
        Agent.instrumentation = instrumentation;
    }

    public static void appendJarFile(JarFile file) throws IOException {
        if (instrumentation != null) {
            instrumentation.appendToSystemClassLoaderSearch(file);
        }
    }

    public static void reloadClasses(String packagePath) {
        Class<?>[] classes = instrumentation.getAllLoadedClasses();
        for (Class<?> c : classes) {
            if (!c.getName().startsWith(packagePath)) {
                continue;
            }

            try {
                instrumentation.retransformClasses(c);
            } catch (UnmodifiableClassException e) {
                // ILB
            }
        }
    }
}