package com.teammetallurgy.m5.base;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.utils.MetallurgyConfig;

public class MetallurgyBaseConfig extends MetallurgyConfig {
    
    public static MetalDefinition COPPER = new MetalDefinition("copper", MetallurgyBaseSubmod.instance);
    public static MetalDefinition TIN = new MetalDefinition("tin", MetallurgyBaseSubmod.instance);
    public static MetalDefinition BRONZE = new MetalDefinition("bronze", MetallurgyBaseSubmod.instance);
    public static MetalDefinition NICKEL = new MetalDefinition("nickel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition LEAD = new MetalDefinition("lead", MetallurgyBaseSubmod.instance);
    public static MetalDefinition GRAPHITE = new MetalDefinition("graphite", MetallurgyBaseSubmod.instance);
    
    public static MetalDefinition[] metals = { COPPER, TIN, BRONZE, NICKEL, LEAD, GRAPHITE };
    
    public static void load(String configPath) {
        for(MetalDefinition metal : metals)
            loadConfig(configPath, metal);
    }
    
    public static void register() {
        for(MetalDefinition metal : metals)
            MetalRegistry.registerMetal(metal);
    }
}
