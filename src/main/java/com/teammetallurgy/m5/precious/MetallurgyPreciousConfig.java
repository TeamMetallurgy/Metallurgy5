package com.teammetallurgy.m5.precious;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.utils.MetallurgyConfig;

public class MetallurgyPreciousConfig extends MetallurgyConfig {
    
    public static MetalDefinition[] metals = { };
    
    public static void load(String configPath) {
        for(MetalDefinition metal : metals)
            loadConfig(configPath, metal);
    }
    
    public static void register() {
        for(MetalDefinition metal : metals)
            MetalRegistry.registerMetal(metal);
    }
}
