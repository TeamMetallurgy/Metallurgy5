package com.teammetallurgy.m5.base;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.utils.MetallurgyConfig;

public class MetallurgyBaseConfig extends MetallurgyConfig {
    
    public static MetalDefinition COPPER = new MetalDefinition("copper", MetallurgyBaseSubmod.instance);
    public static MetalDefinition TIN = new MetalDefinition("tin", MetallurgyBaseSubmod.instance);
    public static MetalDefinition BRONZE = new MetalDefinition("bronze", MetallurgyBaseSubmod.instance);
    public static MetalDefinition LEAD = new MetalDefinition("lead", MetallurgyBaseSubmod.instance);
    public static MetalDefinition NICKEL = new MetalDefinition("nickel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition CUPRONICKEL = new MetalDefinition("cupronickel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition MONEL = new MetalDefinition("monel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition GRAPHITE = new MetalDefinition("graphite", MetallurgyBaseSubmod.instance);
    public static MetalDefinition PEWTER = new MetalDefinition("pewter", MetallurgyBaseSubmod.instance);
    public static MetalDefinition MOLYBDOCHALKOS = new MetalDefinition("molybdochalkos", MetallurgyBaseSubmod.instance);
    public static MetalDefinition MANGANESE = new MetalDefinition("manganese", MetallurgyBaseSubmod.instance);
    public static MetalDefinition HEAVY_STEEL = new MetalDefinition("heavy_steel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition DAMASCUS_STEEL = new MetalDefinition("damascus_steel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition MANGANESE_STEEL = new MetalDefinition("manganese_steel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition NICKEL_STEEL = new MetalDefinition("nickel_steel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition WOVEN_STEEL = new MetalDefinition("woven_steel", MetallurgyBaseSubmod.instance);
    public static MetalDefinition CROWN_GOLD = new MetalDefinition("crown_gold", MetallurgyBaseSubmod.instance);
    public static MetalDefinition WHITE_GOLD = new MetalDefinition("white_gold", MetallurgyBaseSubmod.instance);
    public static MetalDefinition HEPATIZON = new MetalDefinition("hepatizon", MetallurgyBaseSubmod.instance);
    public static MetalDefinition ANGMALLEN = new MetalDefinition("angmallen", MetallurgyBaseSubmod.instance);
    
    public static MetalDefinition[] metals = { COPPER, TIN, BRONZE, NICKEL, LEAD, CUPRONICKEL, MONEL, PEWTER, MOLYBDOCHALKOS, MANGANESE, HEAVY_STEEL, DAMASCUS_STEEL, MANGANESE_STEEL, NICKEL_STEEL, WOVEN_STEEL, CROWN_GOLD, WHITE_GOLD, HEPATIZON, ANGMALLEN, GRAPHITE };
    
    public static void load(String configPath) {
        for(MetalDefinition metal : metals)
            loadConfig(configPath, metal);
    }
    
    public static void register() {
        for(MetalDefinition metal : metals)
            MetalRegistry.registerMetal(metal);
    }
}
