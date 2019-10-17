package com.teammetallurgy.metallurgy.base.utils;

public class MetallurgyUtils {

    public static String capitalize(String string, boolean useSpaces) {
        String[] split = string.split("_");
        for (int i = 0; i < split.length; i++) {
            split[i] = capitalizeWord(split[i]);
        }
        if (useSpaces)
            return String.join(" ", split);
        else
            return String.join("", split);
    }

    private static String capitalizeWord(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
