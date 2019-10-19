package com.teammetallurgy.metallurgy.base.utils;

import com.teammetallurgy.metallurgy.base.MetalSet;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.registry.MetalDefinition;
import net.minecraft.item.Item;

public class MetalUtil {

    public static Item getIngot(String metalName) {
        for (MetalSet metalSet : MetallurgyBase.METAL_SET.values()) {
            MetalDefinition metal = metalSet.getRegistry().getMetal(metalName);
            if (metal != null) {
                return metal.INGOT;
            }
        }
        return null;
    }
}
