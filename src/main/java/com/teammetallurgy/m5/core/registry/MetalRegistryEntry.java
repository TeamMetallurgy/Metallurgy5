package com.teammetallurgy.m5.core.registry;

import com.teammetallurgy.m5.core.MetallurgySubmod;

public class MetalRegistryEntry {
    public MetalDefinition metal;
    public MetallurgySubmod mod;

    public MetalRegistryEntry(MetalDefinition ore, MetallurgySubmod mod) {
        this.metal = ore;
        this.mod = mod;
    }
}