package com.teammetallurgy.m5.core.utils;

public class MetallurgyUtils {

    public static String capitalize(String string) {
        String[] split = string.split("_");
        for(int i = 0; i < split.length; i++) {
            split[i] = capitalizeWord(split[i]);
        }
        return String.join(" ", split);
    }
    
    private static String capitalizeWord(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
