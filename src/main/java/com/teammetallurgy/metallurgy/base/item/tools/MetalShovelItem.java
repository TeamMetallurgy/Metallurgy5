package com.teammetallurgy.metallurgy.base.item.tools;

import com.teammetallurgy.metallurgy.base.registry.MetalDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class MetalShovelItem extends ShovelItem {

    public MetalShovelItem(MetalDefinition metal, ItemGroup itemGroup) {
        super(metal.toolMaterial(), metal.data.tools.shovel.damage, metal.data.tools.shovel.speed, new Item.Properties().group(itemGroup));
    }

//    /**
//     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
//     */
//    @Override
//    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
//        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
//
//        multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
//        multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());
//
//        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
//            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
//            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -4.0f + this.metal.data.tools.shovel.swing_speed, AttributeModifier.Operation.ADDITION));
//        }
//
//        return multimap;
//    }
}
