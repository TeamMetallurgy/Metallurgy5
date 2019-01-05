package com.teammetallurgy.m5.core.items.armor;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemMetalArmor extends ItemArmor {

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {13, 15, 16, 11};
    private MetalDefinition metal;
    
    public ItemMetalArmor(MetalDefinition metal, EntityEquipmentSlot equipmentSlotIn) {
        super(metal.armorMaterial(), 0, equipmentSlotIn);
        this.metal = metal;
        metal.armorMaterial().repairMaterial = new ItemStack(MetalRegistry.ingots.get(metal.name));
    }

}
