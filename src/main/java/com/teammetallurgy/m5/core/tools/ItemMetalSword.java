package com.teammetallurgy.m5.core.tools;

import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.utils.MetalDefinition;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemMetalSword extends ItemSword {

    private MetalDefinition metal;
    
    public ItemMetalSword(MetalDefinition metal) {
        super(ToolMaterial.IRON);
        this.setMaxDamage(metal.toolDurability);
    }

    public float getAttackDamage()
    {
        return metal.swordDamage;
    }

    public int getItemEnchantability()
    {
        return metal.enchantability;
    }

    public String getToolMaterialName()
    {
        return metal.name;
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = new ItemStack(MetalRegistry.ingots.get(metal.name));
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
    
    
}
