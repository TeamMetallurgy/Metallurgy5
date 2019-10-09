package com.teammetallurgy.m5.core.items.armor;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class MetalArmorItem extends ArmorItem {

    private MetalDefinition metal;

    public MetalArmorItem(MetalDefinition metal, EquipmentSlotType equipmentSlotIn, ItemGroup itemGroup) {
        super(metal.armorMaterial(), equipmentSlotIn, new Item.Properties().group(itemGroup));
        this.metal = metal;
    }
}
