package com.teammetallurgy.m5.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.MetallurgyCore;
import com.teammetallurgy.m5.core.registry.MetalDefinition;

public class MetallurgyConfig {

    public static void loadConfig(String name, String configPath, MetalDefinition metal) {
        try {
            File dir = new File(configPath);
            if(!dir.exists())
                dir.mkdirs();
            
            File file = new File(configPath + "/" + name + ".json");
            if(!file.exists() || MetallurgyCore.overrideConfigs) {
                URL inputUrl = metal.mod.getClass().getClassLoader().getResource("assets/" + Constants.MOD_ID + "/config/" + name + ".json");
                try { FileUtils.copyURLToFile(inputUrl, file); }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            String configJson = FileUtils.readFileToString(file);
            metal.loadFromJson(configJson);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
