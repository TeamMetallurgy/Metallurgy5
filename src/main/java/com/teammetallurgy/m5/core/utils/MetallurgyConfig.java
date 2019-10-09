package com.teammetallurgy.m5.core.utils;

import com.teammetallurgy.m5.core.MetalSet;
import com.teammetallurgy.m5.core.registry.MetalDefinition;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MetallurgyConfig {
    public static void loadAll(InputStream fileReader, MetalSet mod) {
        String filePath = "/data/" + mod.getPrefix() + "/metals/";
        // TODO Add more checking
        try {
            List<String> files = IOUtils.readLines(fileReader, StandardCharsets.UTF_8);

            for (String configFilePath : files) {
                URL configUrl = mod.getClass().getClassLoader().getResource(filePath + configFilePath);
                String configJson = IOUtils.toString(configUrl.openStream(), StandardCharsets.UTF_8);
                MetalDefinition metal = MetalDefinition.createFromJson(configJson, mod);
                mod.registerMetal(metal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
