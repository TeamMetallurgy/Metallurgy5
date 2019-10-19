package com.teammetallurgy.metallurgy.base;

import com.teammetallurgy.metallurgy.base.registry.MetalDefinition;
import com.teammetallurgy.metallurgy.base.registry.MetalRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract class MetalSet {

    protected final MetalRegistry registry;

    public MetalSet() {
        this.registry = new MetalRegistry(this);
    }

    public abstract String getPrefix();

    public abstract ItemGroup getItemGroup();

    public abstract void setup(FMLCommonSetupEvent event);

    public MetalRegistry getRegistry() {
        return this.registry;
    }

    public void registerMetal(MetalDefinition metal) {
        registry.registerMetal(metal);
    }
}
