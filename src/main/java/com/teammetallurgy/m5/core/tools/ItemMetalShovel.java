package com.teammetallurgy.m5.core.tools;

import com.google.common.collect.Multimap;
import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemMetalShovel extends ItemSpade {

    private MetalDefinition metal;
    private float itemDamage;
    private float swingSpeed;
    
    public ItemMetalShovel(MetalDefinition metal) {
        super(ToolMaterial.WOOD);
        this.metal = metal;
        this.setMaxDamage(metal.toolDurability);
        this.efficiency = metal.miningSpeed;
        this.attackSpeed = 0;
        this.attackDamage = metal.shovelDamage;
        this.swingSpeed = metal.shovelSwingSpeed;
        this.setHarvestLevel("shovel", metal.harvestLevel);
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = new ItemStack(MetalRegistry.ingots.get(metal.name));
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
    
    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
        
        multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
        multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", - 4.0f + this.swingSpeed, 0));
        }

        return multimap;
    }
}
