package com.teammetallurgy.m5.core.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.teammetallurgy.m5.core.MetallurgySubmod;
import com.teammetallurgy.m5.core.items.armor.ItemMetalArmor;
import com.teammetallurgy.m5.core.items.tools.ItemMetalAxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalHoe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalPickaxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalShovel;
import com.teammetallurgy.m5.core.items.tools.ItemMetalSword;
import com.teammetallurgy.m5.core.utils.JSONMaker;
import com.teammetallurgy.m5.core.utils.MetallurgyUtils;
import com.teammetallurgy.m5.core.utils.RecipeHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber(modid = "metallurgy5core")
public class MetalRegistry {

    private static List<MetalRegistryEntry> registry = new ArrayList<>();

    public static Map<String, Block> oreBlocks = new HashMap<>();
    public static Map<String, Block> metalBlocks = new HashMap<>();
    public static Map<String, Block> metalLargeBricks = new HashMap<>();
    public static Map<String, Item> ingots = new HashMap<>();
    public static Map<String, Item> nuggets = new HashMap<>();
    public static Map<String, Item> dusts = new HashMap<>();
    public static Map<String, Item> swords = new HashMap<>();
    public static Map<String, Item> axes = new HashMap<>();
    public static Map<String, Item> shovels = new HashMap<>();
    public static Map<String, Item> pickaxes = new HashMap<>();
    public static Map<String, Item> hoes = new HashMap<>();
    public static Map<String, Item> helmets = new HashMap<>();
    public static Map<String, Item> chestplates = new HashMap<>();
    public static Map<String, Item> leggings = new HashMap<>();
    public static Map<String, Item> boots = new HashMap<>();

    public static Map<String, ?>[] blockMaps = new Map[] { oreBlocks, metalBlocks, metalLargeBricks };
    public static Map<String, ?>[] itemMaps = new Map[] { ingots, nuggets, dusts, swords, axes, shovels, pickaxes, hoes, helmets, chestplates, leggings, boots };

    public static void registerMetal(MetalDefinition metal) {
        MetallurgySubmod mod = metal.mod;
        MetalRegistry.registry.add(new MetalRegistryEntry(metal, mod));
        String name;
        Item item;

        if (metal.type == MetalDefinition.Type.ORE) {
            Block oreBlock = new Block(Material.ROCK).setHardness(3.0F).setResistance(5.0F);
            oreBlock.setCreativeTab(mod.getCreativeTab());
            oreBlock.setRegistryName(mod.getPrefix(), metal.name + "_ore");
            oreBlock.setTranslationKey(metal.name + "_ore");
            oreBlock.setHarvestLevel("pickaxe", 1);
            oreBlocks.put(metal.name, oreBlock);
            JSONMaker.createBlockJson(mod.getPrefix(), metal.name + "_ore");
        }

        Block metalBlock = new Block(Material.ROCK).setHardness(5.0F).setResistance(10.0F);
        metalBlock.setCreativeTab(mod.getCreativeTab());
        metalBlock.setRegistryName(mod.getPrefix(), metal.name + "_block");
        metalBlock.setTranslationKey(metal.name + "_block");
        metalBlock.setHarvestLevel("pickaxe", 1);
        metalBlocks.put(metal.name, metalBlock);
        JSONMaker.createBlockJson(mod.getPrefix(), metal.name + "_block");

        Block metalLargeBrick = new Block(Material.ROCK).setHardness(5.0F).setResistance(10.0F);
        metalLargeBrick.setCreativeTab(mod.getCreativeTab());
        metalLargeBrick.setRegistryName(mod.getPrefix(), metal.name + "_large_bricks");
        metalLargeBrick.setTranslationKey(metal.name + "_large_bricks");
        metalLargeBrick.setHarvestLevel("pickaxe", 1);
        metalLargeBricks.put(metal.name, metalLargeBrick);
        JSONMaker.createBlockJson(mod.getPrefix(), metal.name + "_large_bricks");

        // CREATE ITEM INGOT
        name = metal.name + "_ingot";
        item = new Item().setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        ingots.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM NUGGET
        name = metal.name + "_nugget";
        item = new Item().setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        nuggets.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM DUST
        name = metal.name + "_dust";
        item = new Item().setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        dusts.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM SWORD
        name = metal.name + "_sword";
        item = new ItemMetalSword(metal).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        swords.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM AXE
        name = metal.name + "_axe";
        item = new ItemMetalAxe(metal).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        axes.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM SHOVEL
        name = metal.name + "_shovel";
        item = new ItemMetalShovel(metal).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        shovels.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM PICKAXE
        name = metal.name + "_pickaxe";
        item = new ItemMetalPickaxe(metal).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        pickaxes.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);
        
        // CREATE ITEM HOE
        name = metal.name + "_hoe";
        item = new ItemMetalHoe(metal).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        hoes.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM HELMET
        name = metal.name + "_helmet";
        item = new ItemMetalArmor(metal, EntityEquipmentSlot.HEAD).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        helmets.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM CHESTPLATE
        name = metal.name + "_chestplate";
        item = new ItemMetalArmor(metal, EntityEquipmentSlot.CHEST).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        chestplates.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM LEGS
        name = metal.name + "_leggings";
        item = new ItemMetalArmor(metal, EntityEquipmentSlot.LEGS).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        leggings.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);

        // CREATE ITEM BOOTS
        name = metal.name + "_boots";
        item = new ItemMetalArmor(metal, EntityEquipmentSlot.FEET).setRegistryName(mod.getPrefix(), name).setTranslationKey(name).setCreativeTab(mod.getCreativeTab());
        boots.put(metal.name, item);
        JSONMaker.createItemJson(mod.getPrefix(), name);
    }
    
    public static void registerOreDict() {
        for(MetalRegistryEntry entry : registry)
        {
            MetalDefinition metal = entry.metal;
            OreDictionary.registerOre("ore" + MetallurgyUtils.capitalize(metal.name), Item.getItemFromBlock(oreBlocks.get(metal.name)));
            OreDictionary.registerOre("block" + MetallurgyUtils.capitalize(metal.name), Item.getItemFromBlock(metalBlocks.get(metal.name)));
            OreDictionary.registerOre("largeBrick" + MetallurgyUtils.capitalize(metal.name), Item.getItemFromBlock(metalLargeBricks.get(metal.name)));
            OreDictionary.registerOre("ingot" + MetallurgyUtils.capitalize(metal.name), ingots.get(metal.name));
            OreDictionary.registerOre("nugget" + MetallurgyUtils.capitalize(metal.name), nuggets.get(metal.name));
            OreDictionary.registerOre("dust" + MetallurgyUtils.capitalize(metal.name), dusts.get(metal.name));
            OreDictionary.registerOre("itemDust" + MetallurgyUtils.capitalize(metal.name), dusts.get(metal.name));
            OreDictionary.registerOre("sword" + MetallurgyUtils.capitalize(metal.name), swords.get(metal.name));
            OreDictionary.registerOre("axe" + MetallurgyUtils.capitalize(metal.name), axes.get(metal.name));
            OreDictionary.registerOre("shovel" + MetallurgyUtils.capitalize(metal.name), shovels.get(metal.name));
            OreDictionary.registerOre("pickaxe" + MetallurgyUtils.capitalize(metal.name), pickaxes.get(metal.name));
            OreDictionary.registerOre("hoe" + MetallurgyUtils.capitalize(metal.name), hoes.get(metal.name));
            OreDictionary.registerOre("helmet" + MetallurgyUtils.capitalize(metal.name), helmets.get(metal.name));
            OreDictionary.registerOre("chestplate" + MetallurgyUtils.capitalize(metal.name), chestplates.get(metal.name));
            OreDictionary.registerOre("leggings" + MetallurgyUtils.capitalize(metal.name), leggings.get(metal.name));
            OreDictionary.registerOre("boots" + MetallurgyUtils.capitalize(metal.name), boots.get(metal.name));
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Map<String, ?> blocks : blockMaps) {
            registerItemBlocks((Map<String, Block>) blocks, event.getRegistry());
        }
        for (Map<String, ?> items : itemMaps) {
            registerItems((Map<String, Item>) items, event.getRegistry());
        }

        registerOreDict();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Map<String, ?> blocks : blockMaps) {
            registerBlocks((Map<String, Block>) blocks, event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Map<String, ?> blocks : blockMaps) {
            registerBlockRenderers((Map<String, Block>) blocks);
        }
        for (Map<String, ?> items : itemMaps) {
            registerItemRenderers((Map<String, Item>) items);
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable<IRecipe> recipes = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();

        for (MetalRegistryEntry entry : registry) {
            String metalName = entry.metal.name;
            String modid = entry.mod.getPrefix();

            Block metalBlock = metalBlocks.get(metalName);
            Item ingot = ingots.get(metalName);
            Item nugget = nuggets.get(metalName);
            Item dust = dusts.get(metalName);
            String oreIngot = "ingot" + MetallurgyUtils.capitalize(metalName);
            String oreNugget = "nugget" + MetallurgyUtils.capitalize(metalName);
            String oreDust = "nugget" + MetallurgyUtils.capitalize(metalName);
            String oreBlock = "block" + MetallurgyUtils.capitalize(metalName);

            if (entry.metal.type == MetalDefinition.Type.ORE) {
                Block ore = oreBlocks.get(metalName);
                GameRegistry.addSmelting(ore, new ItemStack(ingot), 0);
            }
            else if (entry.metal.type == MetalDefinition.Type.ALLOY) {
                List<String> recipe = new ArrayList<>();
                for(String ingredient : entry.metal.ingredients.keySet()) {
                    for(int i = 0; i < entry.metal.ingredients.get(ingredient); i++)
                        recipe.add("dust" + MetallurgyUtils.capitalize(ingredient));
                }
                
                int craftingAmount = Math.round(recipe.size() * entry.metal.alloyEfficiency);
                RecipeHelper.shapelessRecipe(event.getRegistry(), metalName + "_alloy", new ItemStack(dust, craftingAmount), recipe.toArray(new Object[recipe.size()]));
                
            }
            
            GameRegistry.addSmelting(dust, new ItemStack(ingot), 0);
            
            RecipeHelper.shapelessRecipe(event.getRegistry(), metalName + "_nugget", new ItemStack(nugget, 9), oreIngot);
            RecipeHelper.shapelessRecipe(event.getRegistry(), metalName + "_block_to_ingot", new ItemStack(ingot, 9), oreBlock);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_ingot", ingot, "NNN", "NNN", "NNN", 'N', oreNugget);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_block", metalBlock, "III", "III", "III", 'I', oreIngot);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_sword", swords.get(metalName), "I", "I", "S", 'I', oreIngot, 'S', "stickWood");
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_axe", axes.get(metalName), "II", "SI", "S ", 'I', oreIngot, 'S', "stickWood");
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_shovel", shovels.get(metalName), "I", "S", "S", 'I', oreIngot, 'S', "stickWood");
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_pickaxe", pickaxes.get(metalName), "III", " S ", " S ", 'I', oreIngot, 'S', "stickWood");
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_hoe", hoes.get(metalName), "II", "S ", "S ", 'I', oreIngot, 'S', "stickWood");

            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_helmet", helmets.get(metalName), "III", "I I", 'I', oreIngot);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_chestplate", chestplates.get(metalName), "I I", "III", "III", 'I', oreIngot);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_leggings", leggings.get(metalName), "III", "I I", "I I", 'I', oreIngot);
            RecipeHelper.shapedRecipe(event.getRegistry(), metalName + "_boots", boots.get(metalName), "I I", "I I", 'I', oreIngot);
        }
    }

    // ********************
    // * HELPER FUNCTIONS *
    // ********************
    public static void registerItems(Map<String, Item> items, IForgeRegistry<Item> registry) {
        for (Item item : items.values()) {
            registry.register(item);
        }
    }

    public static void registerItemBlocks(Map<String, Block> items, IForgeRegistry<Item> registry) {
        for (Block block : items.values()) {
            Item itemblock = new ItemBlock(block).setRegistryName(block.getRegistryName());
            registry.register(itemblock);
        }
    }

    public static void registerBlocks(Map<String, Block> blocks, IForgeRegistry<Block> registry) {
        for (Block block : blocks.values()) {
            registry.register(block);
        }
    }

    public static void registerItemRenderers(Map<String, Item> items) {
        for (Item item : items.values()) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }

    public static void registerBlockRenderers(Map<String, Block> blocks) {
        for (Block block : blocks.values()) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
