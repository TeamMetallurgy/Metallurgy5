package com.teammetallurgy.m5.core.registry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.teammetallurgy.m5.base.MetallurgyBaseSubmod;
import com.teammetallurgy.m5.core.MetallurgySubmod;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MetalDefinition {
    public enum Type {
        ORE, CATALYST, OTHER, ALLOY
    }

    public MetallurgySubmod mod;
    public String name;
    public Type type;
    public int tier;

    public int harvestLevel;
    public int toolDurability;
    public int toolEnchantability;
    public float miningSpeed = 2.0f;
    
    public float swordDamage = 1;
    public float swordSwingSpeed = 1.6f;
    
    public float pickaxeDamage = 1;
    public float pickaxeSwingSpeed = 1.2f;
    public float shovelDamage = 1;
    public float shovelSwingSpeed = 1f;
    public float hoeDamage = 1;
    public float hoeSwingSpeed = 1;
    public float axeDamage = 7;
    public float axeSwingSpeed = 0.8f;
    
    private ArmorMaterial armorMaterial;
    public int armorEnchantability;
    public int armorDurability;
    public int[] armorDamageReduction = { 0, 0, 0, 0 };
    public float armorToughness = 0;
    
    public MetalDefinition(MetallurgyBaseSubmod mod) {
        this.mod = mod;
    }

    public void loadFromJson(String json) {
        JsonElement root = new JsonParser().parse(json);

        this.name = root.getAsJsonObject().get("name").getAsString();
        String typeString = root.getAsJsonObject().get("type").getAsString();
        this.type = MetalDefinition.Type.valueOf(typeString);
        this.harvestLevel = root.getAsJsonObject().get("tools").getAsJsonObject().get("harvest_level").getAsInt();
        this.toolDurability = root.getAsJsonObject().get("tools").getAsJsonObject().get("durability").getAsInt();
        this.swordDamage = root.getAsJsonObject().get("tools").getAsJsonObject().get("sword").getAsJsonObject().get("damage").getAsFloat();
        this.swordSwingSpeed = root.getAsJsonObject().get("tools").getAsJsonObject().get("sword").getAsJsonObject().get("swing_speed").getAsFloat();
        this.pickaxeDamage = root.getAsJsonObject().get("tools").getAsJsonObject().get("pickaxe").getAsJsonObject().get("damage").getAsFloat();
        this.pickaxeSwingSpeed = root.getAsJsonObject().get("tools").getAsJsonObject().get("pickaxe").getAsJsonObject().get("swing_speed").getAsFloat();
        this.axeDamage = root.getAsJsonObject().get("tools").getAsJsonObject().get("axe").getAsJsonObject().get("damage").getAsFloat();
        this.axeSwingSpeed = root.getAsJsonObject().get("tools").getAsJsonObject().get("axe").getAsJsonObject().get("swing_speed").getAsFloat();
        this.shovelDamage = root.getAsJsonObject().get("tools").getAsJsonObject().get("shovel").getAsJsonObject().get("damage").getAsFloat();
        this.shovelSwingSpeed = root.getAsJsonObject().get("tools").getAsJsonObject().get("shovel").getAsJsonObject().get("swing_speed").getAsFloat();
        this.hoeDamage = root.getAsJsonObject().get("tools").getAsJsonObject().get("hoe").getAsJsonObject().get("damage").getAsFloat();
        this.hoeSwingSpeed = root.getAsJsonObject().get("tools").getAsJsonObject().get("hoe").getAsJsonObject().get("swing_speed").getAsFloat();

        this.armorEnchantability = root.getAsJsonObject().get("armor").getAsJsonObject().get("enchantability").getAsInt();
        this.armorDurability = root.getAsJsonObject().get("armor").getAsJsonObject().get("durability_multiplier").getAsInt();
        JsonArray array = root.getAsJsonObject().get("armor").getAsJsonObject().get("damage_reduction").getAsJsonArray();
        this.armorDamageReduction = new int[] { array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt(), array.get(3).getAsInt() };
        this.armorToughness = root.getAsJsonObject().get("armor").getAsJsonObject().get("toughness").getAsInt();        
    }
    
    public ArmorMaterial armorMaterial() {
        if(armorMaterial == null) {
            armorMaterial = EnumHelper.addArmorMaterial("m5_" + name, mod.getPrefix() + ":" + name, armorDurability, armorDamageReduction, armorEnchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, armorToughness);
        }
        return armorMaterial;
    }
}
