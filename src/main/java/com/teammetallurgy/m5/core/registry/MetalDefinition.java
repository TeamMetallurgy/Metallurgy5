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
import com.teammetallurgy.m5.core.blocks.BlockCatalystOre;
import com.teammetallurgy.m5.core.items.armor.ItemMetalArmor;
import com.teammetallurgy.m5.core.items.tools.ItemMetalAxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalHoe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalPickaxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalShovel;
import com.teammetallurgy.m5.core.items.tools.ItemMetalSword;
import com.teammetallurgy.m5.core.utils.JSONMaker;
import com.teammetallurgy.m5.core.utils.LangWriter;
import com.teammetallurgy.m5.core.utils.MetallurgyUtils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class MetalDefinition {
    public enum Type {
        ORE, CATALYST, OTHER, ALLOY
    }
    
    public Block ORE;
    public Block METAL_BLOCK;
    public Block METAL_LARGE_BRICKS;
    public Item INGOT;
    public Item DUST;
    public Item NUGGET;
    public ItemMetalSword SWORD;
    public ItemMetalPickaxe PICKAXE;
    public ItemMetalAxe AXE;
    public ItemMetalShovel SHOVEL;
    public ItemMetalHoe HOE;
    public ItemMetalArmor HELMET;
    public ItemMetalArmor CHESTPLATE;
    public ItemMetalArmor LEGGINGS;
    public ItemMetalArmor BOOTS;
    public Item CATALYST;
    

    public MetallurgySubmod mod;
    public String name;
    public Type type;
    public int tier;
    
    //World Gen
    public int veinsPerChunk;
    public int orePerVeinMin;
    public int orePerVeinMax;
    public int minY;
    public int maxY;
    
    // Tools
    public int harvestLevel;
    public int toolDurability;
    public int toolEnchantability;
    public float efficiency = 2.0f;
    
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
    
    // Armors
    private ArmorMaterial armorMaterial;
    public int armorEnchantability;
    public int armorDurability;
    public int[] armorDamageReduction = { 0, 0, 0, 0 };
    public float armorToughness = 0;
    
    // Alloy Info
    public float alloyEfficiency;
    public String alloyCatalyst;
    public Map<String, Integer> ingredients = new HashMap<>();
    
    public MetalDefinition(String name, MetallurgySubmod mod) {
        this.name = name;
        this.mod = mod;
    }

    public void loadFromJson(String json) {
        JsonObject root = new JsonParser().parse(json).getAsJsonObject();

        if(this.name == null)
            this.name = root.get("name").getAsString();
        String typeString = root.get("type").getAsString();
        this.type = MetalDefinition.Type.valueOf(typeString);
        
        if (type == Type.CATALYST || type == Type.ORE) {
            this.veinsPerChunk = root.getAsJsonObject("world").get("veins_per_chunk").getAsInt();
            this.orePerVeinMin = root.getAsJsonObject("world").get("ore_per_vein").getAsJsonArray().get(0).getAsInt();
            this.orePerVeinMax = root.getAsJsonObject("world").get("ore_per_vein").getAsJsonArray().get(1).getAsInt();
            this.minY = root.getAsJsonObject("world").get("height_min").getAsInt();
            this.maxY = root.getAsJsonObject("world").get("height_max").getAsInt();
        }
        
        if (type != Type.CATALYST) {
            // TOOLS
            this.harvestLevel = root.getAsJsonObject("tools").get("harvest_level").getAsInt();
            this.toolDurability = root.getAsJsonObject("tools").get("durability").getAsInt();
            this.efficiency = root.getAsJsonObject("tools").get("efficiency").getAsFloat();
            
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
        
        if (type == Type.ALLOY) {
            this.alloyEfficiency = root.getAsJsonObject("alloy").get("efficiency").getAsFloat();
            JsonObject alloyIngredientJson = root.getAsJsonObject("alloy").getAsJsonObject("ingredients");
            for(Entry<String, JsonElement> entry : alloyIngredientJson.entrySet()) {
                ingredients.put(entry.getKey(), entry.getValue().getAsInt());
            }
            this.alloyCatalyst = root.getAsJsonObject("alloy").get("catalyst").getAsString();
        }
        
        createItems();
    }
    
    private void createItems() {
        if (type == Type.ORE || type == Type.CATALYST) {
            if(type == MetalDefinition.Type.CATALYST)
                ORE = new BlockCatalystOre(this);
            else
                ORE = new Block(Material.ROCK);
            ORE.setHardness(3.0F)
               .setResistance(5.0F)
               .setCreativeTab(mod.getCreativeTab())
               .setRegistryName(mod.getPrefix(), name + "_ore")
               .setTranslationKey(name + "_ore")
               .setHarvestLevel("pickaxe", 1);
            JSONMaker.createBlockJson(mod.getPrefix(), name + "_ore");
            LangWriter.addItem(mod.getPrefix(), ORE.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Ore");
        }

        if (type == MetalDefinition.Type.CATALYST) {
            CATALYST = new Item().setRegistryName(mod.getPrefix(), name + "_item")
                                 .setTranslationKey(name + "_item")
                                 .setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_item");
            LangWriter.addItem(mod.getPrefix(), CATALYST.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true));
        }

        if (type != MetalDefinition.Type.CATALYST) {
            METAL_BLOCK = new Block(Material.ROCK);
            METAL_BLOCK.setHardness(5.0F).setResistance(10.0F)
                .setCreativeTab(mod.getCreativeTab())
                .setRegistryName(mod.getPrefix(), name + "_block")
                .setTranslationKey(name + "_block")
                .setHarvestLevel("pickaxe", 1);
            JSONMaker.createBlockJson(mod.getPrefix(), name + "_block");
            LangWriter.addItem(mod.getPrefix(), METAL_BLOCK.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Block");
    
            METAL_LARGE_BRICKS = new Block(Material.ROCK);
            METAL_LARGE_BRICKS.setHardness(5.0F).setResistance(10.0F)
                .setCreativeTab(mod.getCreativeTab())
                .setRegistryName(mod.getPrefix(), name + "_large_bricks")
                .setTranslationKey(name + "_large_bricks")
                .setHarvestLevel("pickaxe", 1);
            JSONMaker.createBlockJson(mod.getPrefix(), name + "_large_bricks");
            LangWriter.addItem(mod.getPrefix(), METAL_LARGE_BRICKS.getTranslationKey() + ".name", "Large " + MetallurgyUtils.capitalize(name, true) + " Bricks");
    
            // CREATE ITEM INGOT
            INGOT = new Item().setRegistryName(mod.getPrefix(), name + "_ingot").setTranslationKey(name + "_ingot").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_ingot");
            LangWriter.addItem(mod.getPrefix(), INGOT.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Ingot");
    
            // CREATE ITEM NUGGET
            NUGGET = new Item().setRegistryName(mod.getPrefix(), name + "_nugget").setTranslationKey(name + "_nugget").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_nugget");
            LangWriter.addItem(mod.getPrefix(), NUGGET.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Nugget");
    
            // CREATE ITEM DUST
            DUST = new Item().setRegistryName(mod.getPrefix(), name + "_dust").setTranslationKey(name + "_dust").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_dust");
            LangWriter.addItem(mod.getPrefix(), DUST.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Dust");
    
            // CREATE ITEM SWORD
            SWORD = (ItemMetalSword) new ItemMetalSword(this).setRegistryName(mod.getPrefix(), name + "_sword").setTranslationKey(name + "_sword").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_sword");
            LangWriter.addItem(mod.getPrefix(), SWORD.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Sword");
    
            // CREATE ITEM AXE
            AXE = (ItemMetalAxe) new ItemMetalAxe(this).setRegistryName(mod.getPrefix(), name + "_axe").setTranslationKey(name + "_axe").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_axe");
            LangWriter.addItem(mod.getPrefix(), AXE.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Axe");
    
            // CREATE ITEM SHOVEL
            SHOVEL = (ItemMetalShovel) new ItemMetalShovel(this).setRegistryName(mod.getPrefix(), name + "_shovel").setTranslationKey(name + "_shovel").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_shovel");
            LangWriter.addItem(mod.getPrefix(), SHOVEL.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Shovel");
    
            // CREATE ITEM PICKAXE
            PICKAXE = (ItemMetalPickaxe) new ItemMetalPickaxe(this).setRegistryName(mod.getPrefix(), name + "_pickaxe").setTranslationKey(name + "_pickaxe").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_pickaxe");
            LangWriter.addItem(mod.getPrefix(), PICKAXE.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Pickaxe");
            
            // CREATE ITEM HOE
            HOE = (ItemMetalHoe) new ItemMetalHoe(this).setRegistryName(mod.getPrefix(), name + "_hoe").setTranslationKey(name + "_hoe").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_hoe");
            LangWriter.addItem(mod.getPrefix(), HOE.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Hoe");
    
            // CREATE ITEM HELMET
            HELMET = (ItemMetalArmor) new ItemMetalArmor(this, EntityEquipmentSlot.HEAD).setRegistryName(mod.getPrefix(), name + "_helmet").setTranslationKey(name + "_helmet").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_helmet");
            LangWriter.addItem(mod.getPrefix(), HELMET.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Helmet");
    
            // CREATE ITEM CHESTPLATE
            CHESTPLATE = (ItemMetalArmor) new ItemMetalArmor(this, EntityEquipmentSlot.CHEST).setRegistryName(mod.getPrefix(), name + "_chestplate").setTranslationKey(name + "_chestplate").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_chestplate");
            LangWriter.addItem(mod.getPrefix(), CHESTPLATE.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Chestplate");
    
            // CREATE ITEM LEGS
            LEGGINGS = (ItemMetalArmor) new ItemMetalArmor(this, EntityEquipmentSlot.LEGS).setRegistryName(mod.getPrefix(), name + "_leggings").setTranslationKey(name + "_leggings").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_leggings");
            LangWriter.addItem(mod.getPrefix(), LEGGINGS.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Leggings");
    
            // CREATE ITEM BOOTS
            BOOTS = (ItemMetalArmor) new ItemMetalArmor(this, EntityEquipmentSlot.FEET).setRegistryName(mod.getPrefix(), name + "_boots").setTranslationKey(name + "_boots").setCreativeTab(mod.getCreativeTab());
            JSONMaker.createItemJson(mod.getPrefix(), name + "_boots");
            LangWriter.addItem(mod.getPrefix(), BOOTS.getTranslationKey() + ".name", MetallurgyUtils.capitalize(name, true) + " Boots");
        }
    }
    
    public ArmorMaterial armorMaterial() {
        if(armorMaterial == null) {
            armorMaterial = EnumHelper.addArmorMaterial("m5_" + name, mod.getPrefix() + ":" + name, armorDurability, armorDamageReduction, armorEnchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, armorToughness);
        }
        return armorMaterial;
    }
}
