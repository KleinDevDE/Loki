package de.kleindev.loki.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DevTweaks {
    private static final char[] numbers = "0123456789".toCharArray();

    public static boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNumber(char c) {
        for (char n : numbers) {
            if (n == c)
                return true;
        }
        return false;
    }

    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBoolean(String string) {
        try {
            Boolean.parseBoolean(string);
            return true;
        } catch (UnsupportedOperationException e) {
            return false;
        }
    }

    public static boolean isValidURL(String url) {
        try {
            URL u = new URL(url);
            if (u.openConnection() != null)
                return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean isUUID(String string) {
        try {
            UUID.fromString(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean methodExists(Method obj) {
        boolean methodExists = false;
        try {
            obj.invoke("", (Object[]) null);
            methodExists = true;
        } catch (Exception ignored) {
        }
        return methodExists;
    }

    /* https://dzone.com/articles/generate-random-alpha-numeric */
    public static String randomAlphaNumeric(int count, String chars) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * chars.length());
            builder.append(chars.charAt(character));
        }
        return builder.toString();
    }

    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static String readInputStream(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    public static String listToString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            if (builder.toString().equals(""))
                builder.append(s);
            else
                builder.append("\n").append(s);
        }
        return builder.toString();
    }

    public static FilenameFilter getFileNameFilter(String... extensions) {
        List<String> extensionslist = Arrays.asList(extensions);
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.lastIndexOf('.') > 0) {
                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');
                    // get extension
                    String str = name.substring(lastIndex);
                    // match path name extension
                    return extensionslist.contains(str);
                }
                return false;
            }
        };
        return fileNameFilter;
    }

    public static boolean listContainsKeyOfArray(Set<String> list, String[] array) {
        for (String s : array) {
            if (list.contains(s))
                return true;
        }
        return false;
    }

    public static boolean isZip(File f) {
        int fileSignature = 0;
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            fileSignature = raf.readInt();
        } catch (IOException e) {
            // handle if you like
        }
        return fileSignature == 0x504B0304 || fileSignature == 0x504B0506 || fileSignature == 0x504B0708;
    }

    public static boolean isJAR(File f) {
        int fileSignature = 0;
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            fileSignature = raf.readInt();
        } catch (IOException e) {
            // handle if you like
        }
        return fileSignature == 0x504B0304 || fileSignature == 0x504B0506 || fileSignature == 0x504B0708;
    }

    public static BufferedImage base64ToBufferedImage(String base64) {
        try {
            return ImageIO.read(
                    new ByteArrayInputStream(
                            Base64.getDecoder().decode(base64)
                    )
            );
        } catch (IOException e) {
            return null;
        }
    }

}
