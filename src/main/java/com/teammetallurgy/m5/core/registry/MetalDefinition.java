package com.teammetallurgy.m5.core.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teammetallurgy.m5.base.MetallurgyBaseSubmod;
import com.teammetallurgy.m5.core.MetallurgySubmod;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
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
    
    // Alloy Info
    public float alloyEfficiency;
    public String alloyCatalyst;
    public Map<String, Integer> ingredients = new HashMap<>();
    
    public MetalDefinition(MetallurgyBaseSubmod mod) {
        this.mod = mod;
    }

    public void loadFromJson(String json) {
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();

        this.name = root.get("name").getAsString();
        String typeString = root.get("type").getAsString();
        this.type = MetalDefinition.Type.valueOf(typeString);
        
        if(type != Type.CATALYST)
        {
            // TOOLS
            this.harvestLevel = root.getAsJsonObject("tools").get("harvest_level").getAsInt();
            this.toolDurability = root.getAsJsonObject("tools").get("durability").getAsInt();
            this.swordDamage = root.getAsJsonObject("tools").getAsJsonObject("sword").get("damage").getAsFloat();
            this.swordSwingSpeed = root.getAsJsonObject("tools").getAsJsonObject("sword").get("swing_speed").getAsFloat();
            this.pickaxeDamage = root.getAsJsonObject("tools").getAsJsonObject("pickaxe").get("damage").getAsFloat();
            this.pickaxeSwingSpeed = root.getAsJsonObject("tools").getAsJsonObject("pickaxe").get("swing_speed").getAsFloat();
            this.axeDamage = root.getAsJsonObject("tools").getAsJsonObject("axe").get("damage").getAsFloat();
            this.axeSwingSpeed = root.getAsJsonObject("tools").getAsJsonObject("axe").get("swing_speed").getAsFloat();
            this.shovelDamage = root.getAsJsonObject("tools").getAsJsonObject("shovel").get("damage").getAsFloat();
            this.shovelSwingSpeed = root.getAsJsonObject("tools").getAsJsonObject("shovel").get("swing_speed").getAsFloat();
            this.hoeDamage = root.getAsJsonObject("tools").getAsJsonObject("hoe").get("damage").getAsFloat();
            this.hoeSwingSpeed = root.getAsJsonObject("tools").getAsJsonObject("hoe").get("swing_speed").getAsFloat();
    
            // ARMOR
            this.armorEnchantability = root.getAsJsonObject("armor").get("enchantability").getAsInt();
            this.armorDurability = root.getAsJsonObject("armor").get("durability_multiplier").getAsInt();
            JsonArray array = root.getAsJsonObject("armor").getAsJsonArray("damage_reduction");
            this.armorDamageReduction = new int[] { array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt(), array.get(3).getAsInt() };
            this.armorToughness = root.get("armor").getAsJsonObject().get("toughness").getAsInt();
        }
        
        if(type == Type.ALLOY)
        {
            this.alloyEfficiency = root.getAsJsonObject("alloy").get("efficiency").getAsFloat();
            JsonObject alloyIngredientJson = root.getAsJsonObject("alloy").getAsJsonObject("ingredients");
            for(Entry<String, JsonElement> entry : alloyIngredientJson.entrySet()) {
                ingredients.put(entry.getKey(), entry.getValue().getAsInt());
            }
            this.alloyCatalyst = root.getAsJsonObject("alloy").get("catalyst").getAsString();
        }
    }
    
    public ArmorMaterial armorMaterial() {
        if(armorMaterial == null) {
            armorMaterial = EnumHelper.addArmorMaterial("m5_" + name, mod.getPrefix() + ":" + name, armorDurability, armorDamageReduction, armorEnchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, armorToughness);
        }
        return armorMaterial;
    }
}
