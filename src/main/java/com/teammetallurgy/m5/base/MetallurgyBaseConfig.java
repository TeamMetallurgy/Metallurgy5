package com.teammetallurgy.m5.base;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.utils.MetallurgyConfig;

public class MetallurgyBaseConfig extends MetallurgyConfig {
    
    public static MetalDefinition COPPER = new MetalDefinition(MetallurgyBaseSubmod.instance);
    public static MetalDefinition TIN = new MetalDefinition(MetallurgyBaseSubmod.instance);
    public static MetalDefinition BRONZE = new MetalDefinition(MetallurgyBaseSubmod.instance);
    public static MetalDefinition LEAD = new MetalDefinition(MetallurgyBaseSubmod.instance);
    
    public static void load(String configPath) {
        loadConfig("copper", configPath, COPPER);
        loadConfig("tin", configPath, TIN);
        loadConfig("bronze", configPath, BRONZE);
        loadConfig("lead", configPath, LEAD);
    }
    
    public static void register() {
        MetalRegistry.registerMetal(MetallurgyBaseConfig.BRONZE);
        MetalRegistry.registerMetal(MetallurgyBaseConfig.COPPER);
        MetalRegistry.registerMetal(MetallurgyBaseConfig.LEAD);
        MetalRegistry.registerMetal(MetallurgyBaseConfig.TIN);
    }
}
