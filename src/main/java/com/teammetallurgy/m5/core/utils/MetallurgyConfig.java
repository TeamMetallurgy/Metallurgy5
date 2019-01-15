package com.teammetallurgy.m5.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.MetallurgySubmod;
import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;

public class MetallurgyConfig {
    
    public static void loadAll(String configPath, MetallurgySubmod mod) {
        try {
            List<String> files = IOUtils.readLines(mod.getClass().getClassLoader().getResourceAsStream("assets/" + Constants.MOD_ID + "/config/"), Charsets.UTF_8);
            
            for (String configFilePath : files) {
                URL configUrl = mod.getClass().getClassLoader().getResource("assets/" + Constants.MOD_ID + "/config/" + configFilePath);
                String configJson = FileUtils.readFileToString(FileUtils.toFile(configUrl), Charset.defaultCharset());
                MetalDefinition metal = MetalDefinition.createFromJson(configJson, mod);
                metal.createItems();
                MetalRegistry.registerMetal(metal);
                File file = new File(configPath + "/" + metal.name + ".json");
                if (!file.exists()) {
                    FileUtils.writeStringToFile(file, "{\n}", Charset.defaultCharset());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File folder = new File(configPath);
        for (File configFile : folder.listFiles()) {
            try {
                String configJson = FileUtils.readFileToString(configFile, Charset.defaultCharset());
                String name = configFile.getName().split("\\.")[0];                
                MetalDefinition metal = MetalRegistry.getMetal(name);
                if (metal == null) {
                    metal = MetalDefinition.createFromJson(configJson, mod);
                } else {
                    metal.updateFromJson(configJson);
                }
                metal.createItems();
                MetalRegistry.registerMetal(metal);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
