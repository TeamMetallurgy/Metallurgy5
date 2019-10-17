package com.teammetallurgy.metallurgy.base.item.tools;

import com.teammetallurgy.metallurgy.base.registry.MetalDefinition;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class MetalPickaxeItem extends PickaxeItem {

    public MetalPickaxeItem(MetalDefinition metal, ItemGroup group) {
        super(metal.toolMaterial(), (int) metal.data.tools.pickaxe.damage, 0, new Item.Properties().group(group));
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
//            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", metal.data.tools.pickaxe.damage, AttributeModifier.Operation.ADDITION));
//            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -4.0f + metal.data.tools.pickaxe.swing_speed, AttributeModifier.Operation.ADDITION));
//        }
//
//        return multimap;
//    }
}
