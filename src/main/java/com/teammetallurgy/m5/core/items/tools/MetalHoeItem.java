package com.teammetallurgy.m5.core.items.tools;

import com.teammetallurgy.m5.core.registry.MetalDefinition;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class MetalHoeItem extends HoeItem {

    public MetalHoeItem(MetalDefinition metal, ItemGroup itemGroup) {
        super(metal.toolMaterial(), metal.data.tools.hoe.speed, new Item.Properties().group(itemGroup));
    }

    //TODO Find out about damage
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
//            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 0, AttributeModifier.Operation.ADDITION));
//            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -4.0f + this.metal.data.tools.hoe.swing_speed, AttributeModifier.Operation.ADDITION));
//        }
//
//        return multimap;
//    }
}
