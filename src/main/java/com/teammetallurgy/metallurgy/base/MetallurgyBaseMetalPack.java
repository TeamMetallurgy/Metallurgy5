package com.teammetallurgy.metallurgy.base;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetallurgyBaseMetalPack extends MetalSet {
    private static ItemGroup ITEM_GROUP;

    @Override
    public String getPrefix() {
        return MetallurgyBase.MOD_ID;
    }

    @Override
    public ItemGroup getItemGroup() {
        if (ITEM_GROUP == null) {
            ITEM_GROUP = new ItemGroup(MetallurgyBase.MOD_ID) {
                @Override
                public ItemStack createIcon() {
                    return new ItemStack(registry.getMetal("copper").INGOT);
                }
            };
        }
        return ITEM_GROUP;
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
        this.registry.registerWorldGen();
    }
}
