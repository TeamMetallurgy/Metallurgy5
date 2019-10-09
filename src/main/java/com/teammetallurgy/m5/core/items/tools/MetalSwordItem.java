package com.teammetallurgy.m5.core.items.tools;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class MetalSwordItem extends SwordItem {

    private MetalDefinition metal;

    public MetalSwordItem(MetalDefinition metal, ItemGroup itemGroup) {
        super(metal.toolMaterial(), (int) metal.data.tools.sword.damage, metal.data.tools.sword.speed, new Item.Properties().group(itemGroup));
        this.metal = metal;
    }

    @Override
    public float getAttackDamage() {
        return metal.data.tools.sword.damage;
    }

    @Override
    public int getItemEnchantability() {
        return metal.data.tools.enchantability;
    }

}
