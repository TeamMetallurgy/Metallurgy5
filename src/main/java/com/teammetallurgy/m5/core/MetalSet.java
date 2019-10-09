package com.teammetallurgy.m5.core;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import net.minecraft.item.ItemGroup;

public abstract class MetalSet {

    protected final MetalRegistry registry;

    public MetalSet() {
        this.registry = new MetalRegistry(this);
    }

    public abstract String getPrefix();

    public abstract ItemGroup getItemGroup();

    public void registerMetal(MetalDefinition metal) {
        registry.registerMetal(metal);
    }
}
