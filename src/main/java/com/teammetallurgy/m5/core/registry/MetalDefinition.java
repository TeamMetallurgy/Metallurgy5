package com.teammetallurgy.m5.core.registry;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teammetallurgy.m5.core.MetalSet;
import com.teammetallurgy.m5.core.blocks.CatalystOreBlock;
import com.teammetallurgy.m5.core.gson.RequiredKeyAdapterFactory;
import com.teammetallurgy.m5.core.items.armor.MetalArmorItem;
import com.teammetallurgy.m5.core.items.tools.*;
import com.teammetallurgy.m5.core.registry.data.Metal;
import com.teammetallurgy.m5.core.registry.data.TypeData;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class MetalDefinition {
    public static Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new RequiredKeyAdapterFactory())
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public List<Block> blocks = new ArrayList<>();
    public List<Item> items = new ArrayList<>();

    public final Metal data;
    public final MetalSet mod;
    public IItemTier toolMaterial;
    private IArmorMaterial armorMaterial;

    public Block ORE;
    public Block METAL_BLOCK;
    public Item INGOT;
    public Item DUST;
    public Item NUGGET;
    public Item SWORD;
    public Item PICKAXE;
    public Item AXE;
    public Item SHOVEL;
    public Item HOE;
    public Item HELMET;
    public Item CHESTPLATE;
    public Item LEGGINGS;
    public Item BOOTS;
    public Item CATALYST;

    public MetalDefinition(Metal data, MetalSet mod) {
        this.data = data;
        this.mod = mod;
    }

    public static MetalDefinition createFromJson(String json, MetalSet mod) {
        return new MetalDefinition(GSON.fromJson(json, Metal.class), mod);
    }

    public void createItems() {
        if (this.data.type == TypeData.CATALYST) {
            ORE = this.registerBlock(new CatalystOreBlock(this)
                    .setRegistryName(mod.getPrefix(), this.data.name + "_ore"));

            CATALYST = this.registerItem(new Item(new Item.Properties().group(mod.getItemGroup()))
                    .setRegistryName(mod.getPrefix(), this.data.name + "_item"));
            return;
        }

        if (this.data.type == TypeData.ORE || this.data.type == TypeData.SOLUTE) {
            ORE = this.registerBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 5.0F))
                    .setRegistryName(mod.getPrefix(), this.data.name + "_ore"));
        }

        METAL_BLOCK = this.registerBlock(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0F, 10.0F))
                .setRegistryName(mod.getPrefix(), this.data.name + "_block"));

        // CREATE ITEM INGOT
        INGOT = this.registerItem(new Item(new Item.Properties().group(mod.getItemGroup()))
                .setRegistryName(mod.getPrefix(), this.data.name + "_ingot"));

        // CREATE ITEM NUGGET
        NUGGET = this.registerItem(new Item(new Item.Properties().group(mod.getItemGroup()))
                .setRegistryName(mod.getPrefix(), this.data.name + "_nugget"));

        // CREATE ITEM DUST
        DUST = this.registerItem(new Item(new Item.Properties().group(mod.getItemGroup()))
                .setRegistryName(mod.getPrefix(), this.data.name + "_dust"));

        if (this.data.tools != null) {
            if (this.data.tools.sword != null) {
                // CREATE ITEM SWORD
                SWORD = this.registerItem(new MetalSwordItem(this, mod.getItemGroup())
                        .setRegistryName(mod.getPrefix(), this.data.name + "_sword"));
                // CREATE ITEM AXE
                AXE = this.registerItem(new MetalAxeItem(this, mod.getItemGroup())
                        .setRegistryName(mod.getPrefix(), this.data.name + "_axe"));

                // CREATE ITEM SHOVEL
                SHOVEL = this.registerItem(new MetalShovelItem(this, mod.getItemGroup())
                        .setRegistryName(mod.getPrefix(), this.data.name + "_shovel"));
                // CREATE ITEM PICKAXE
                PICKAXE =this.registerItem( new MetalPickaxeItem(this, mod.getItemGroup())
                        .setRegistryName(mod.getPrefix(), this.data.name + "_pickaxe"));
                // CREATE ITEM HOE
                HOE = this.registerItem(new MetalHoeItem(this, mod.getItemGroup())
                        .setRegistryName(mod.getPrefix(), this.data.name + "_hoe"));
            } else {
                System.out.println("TODO Add the data for the tools: " + this.data.name);
            }
        }
        if (this.data.armor != null) {
            // CREATE ITEM HELMET
            HELMET = this.registerItem(new MetalArmorItem(this, EquipmentSlotType.HEAD, mod.getItemGroup())
                    .setRegistryName(mod.getPrefix(), this.data.name + "_helmet"));

            // CREATE ITEM CHESTPLATE
            CHESTPLATE = this.registerItem(new MetalArmorItem(this, EquipmentSlotType.CHEST, mod.getItemGroup())
                    .setRegistryName(mod.getPrefix(), this.data.name + "_chestplate"));

            // CREATE ITEM LEGS
            LEGGINGS = this.registerItem(new MetalArmorItem(this, EquipmentSlotType.LEGS, mod.getItemGroup())
                    .setRegistryName(mod.getPrefix(), this.data.name + "_leggings"));

            // CREATE ITEM BOOTS
            BOOTS = this.registerItem(new MetalArmorItem(this, EquipmentSlotType.FEET, mod.getItemGroup())
                    .setRegistryName(mod.getPrefix(), this.data.name + "_boots"));
        }
    }

    private Item registerItem(Item item) {
        this.items.add(item);
        return item;
    }

    private Block registerBlock(Block block) {
        this.blocks.add(block);
        return block;
    }

    public IItemTier toolMaterial() {
        if (toolMaterial == null) {
            this.toolMaterial = new IItemTier() {
                @Override
                public int getMaxUses() {
                    return data.tools.durability;
                }

                @Override
                public float getEfficiency() {
                    return data.tools.efficiency;
                }

                @Override
                public float getAttackDamage() {
                    return 0;
                }

                @Override
                public int getHarvestLevel() {
                    return data.tools.harvestLevel;
                }

                @Override
                public int getEnchantability() {
                    return data.tools.enchantability;
                }

                @Override
                public Ingredient getRepairMaterial() {
                    return Ingredient.fromItems(INGOT);
                }
            };
        }
        return this.toolMaterial;
    }

    public IArmorMaterial armorMaterial() {
        if (armorMaterial == null) {
            this.armorMaterial = new IArmorMaterial() {

                @Override
                public int getDurability(EquipmentSlotType type) {
                    return data.armor.durability[type.getIndex()];
                }

                @Override
                public int getDamageReductionAmount(EquipmentSlotType type) {
                    return data.armor.damageReduction[type.getIndex()];
                }

                @Override
                public int getEnchantability() {
                    return data.armor.enchantability;
                }

                @Override
                public SoundEvent getSoundEvent() {
                    return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
                }

                @Override
                public Ingredient getRepairMaterial() {
                    return Ingredient.fromItems(INGOT);
                }

                @Override
                public String getName() {
                    return mod.getPrefix() + ":" + data.name;
                }

                @Override
                public float getToughness() {
                    return data.armor.toughness;
                }
            };
        }
        return armorMaterial;
    }
}
