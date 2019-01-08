package com.teammetallurgy.m5.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.teammetallurgy.m5.core.MetallurgyCore;

public class LangWriter {
    public static String langString;
    
    public static void addItem(String modid, String key, String translation) {
        if(!MetallurgyCore.createLang)
            return;
        
        File file = new File("../src/main/resources/assets/" + modid + "/lang/en_us.lang");
        try {
            if(langString == null) {
                langString = FileUtils.readFileToString(file, Charset.defaultCharset());
            }
            if(langString.contains(key)) {
                return;
            }
            langString += "\r\n" + key + "=" + translation;
            FileUtils.write(file, langString, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
