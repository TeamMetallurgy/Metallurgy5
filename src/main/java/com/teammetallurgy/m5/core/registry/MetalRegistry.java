package com.teammetallurgy.m5.core.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teammetallurgy.m5.core.MetalResourceLoader;
import com.teammetallurgy.m5.core.MetallurgySubmod;
import com.teammetallurgy.m5.core.blocks.BlockCatalystOre;
import com.teammetallurgy.m5.core.items.armor.ItemMetalArmor;
import com.teammetallurgy.m5.core.items.tools.ItemMetalAxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalHoe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalPickaxe;
import com.teammetallurgy.m5.core.items.tools.ItemMetalShovel;
import com.teammetallurgy.m5.core.items.tools.ItemMetalSword;
import com.teammetallurgy.m5.core.utils.JSONMaker;
import com.teammetallurgy.m5.core.utils.MetallurgyUtils;
import com.teammetallurgy.m5.core.utils.RecipeHelper;
import com.teammetallurgy.m5.core.world.WorldGenMetalOre;

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

    public static List<MetalDefinition> registry = new ArrayList<>();

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
    public static Map<String, Item> catalysts = new HashMap<>();

    public static Map<String, ?>[] blockMaps = new Map[] { oreBlocks, metalBlocks, metalLargeBricks };
    public static Map<String, ?>[] itemMaps = new Map[] { ingots, nuggets, dusts, swords, axes, shovels, pickaxes, hoes, helmets, chestplates, leggings, boots, catalysts };
    
    public static void registerMetal(MetalDefinition metal) {
        registry.add(metal);
        MetalResourceLoader.instance.registerDomain(metal.mod.getPrefix());
    }

    public static MetalDefinition getMetal(String name) {
        for(MetalDefinition metal : registry) {
            if(name.equals(metal.name))
                return metal;
        }
        return null;
    }
    
    public static void init() {
        for(MetalDefinition metal : registry) {
            if(metal.ORE != null)
                oreBlocks.put(metal.name, metal.ORE);
            if(metal.METAL_BLOCK != null)
                metalBlocks.put(metal.name, metal.METAL_BLOCK);
            if(metal.METAL_LARGE_BRICKS != null)
                metalLargeBricks.put(metal.name, metal.METAL_LARGE_BRICKS);
            if(metal.INGOT != null)
                ingots.put(metal.name, metal.INGOT);
            if(metal.DUST != null)
                dusts.put(metal.name, metal.DUST);
            if(metal.NUGGET != null)
                nuggets.put(metal.name, metal.NUGGET);
            if(metal.SWORD != null)
                swords.put(metal.name, metal.SWORD);
            if(metal.PICKAXE != null)
                pickaxes.put(metal.name, metal.PICKAXE);
            if(metal.AXE != null)
                axes.put(metal.name, metal.AXE);
            if(metal.SHOVEL != null)
                shovels.put(metal.name, metal.SHOVEL);
            if(metal.HOE != null)
                hoes.put(metal.name, metal.HOE);
            if(metal.HELMET != null)
                helmets.put(metal.name, metal.HELMET);
            if(metal.CHESTPLATE != null)
                chestplates.put(metal.name, metal.CHESTPLATE);
            if(metal.LEGGINGS != null)
                leggings.put(metal.name, metal.LEGGINGS);
            if(metal.BOOTS != null)
                boots.put(metal.name, metal.BOOTS);
            if(metal.CATALYST != null)
                catalysts.put(metal.name, metal.CATALYST);
        }
    }
    
    public static void registerOreDict() {
        for(MetalDefinition metal : registry)
        {
            if(metal.type == MetalDefinition.Type.CATALYST || metal.type == MetalDefinition.Type.CATALYST)
                OreDictionary.registerOre("ore" + MetallurgyUtils.capitalize(metal.name, false), Item.getItemFromBlock(oreBlocks.get(metal.name)));
            
            if(metal.type == MetalDefinition.Type.ALLOY || metal.type == MetalDefinition.Type.ORE)
            {
                OreDictionary.registerOre("block" + MetallurgyUtils.capitalize(metal.name, false), Item.getItemFromBlock(metalBlocks.get(metal.name)));
                OreDictionary.registerOre("largeBrick" + MetallurgyUtils.capitalize(metal.name, false), Item.getItemFromBlock(metalLargeBricks.get(metal.name)));
                OreDictionary.registerOre("ingot" + MetallurgyUtils.capitalize(metal.name, false), ingots.get(metal.name));
                OreDictionary.registerOre("nugget" + MetallurgyUtils.capitalize(metal.name, false), nuggets.get(metal.name));
                OreDictionary.registerOre("dust" + MetallurgyUtils.capitalize(metal.name, false), dusts.get(metal.name));
                OreDictionary.registerOre("itemDust" + MetallurgyUtils.capitalize(metal.name, false), dusts.get(metal.name));
                OreDictionary.registerOre("sword" + MetallurgyUtils.capitalize(metal.name, false), swords.get(metal.name));
                OreDictionary.registerOre("axe" + MetallurgyUtils.capitalize(metal.name, false), axes.get(metal.name));
                OreDictionary.registerOre("shovel" + MetallurgyUtils.capitalize(metal.name, false), shovels.get(metal.name));
                OreDictionary.registerOre("pickaxe" + MetallurgyUtils.capitalize(metal.name, false), pickaxes.get(metal.name));
                OreDictionary.registerOre("hoe" + MetallurgyUtils.capitalize(metal.name, false), hoes.get(metal.name));
                OreDictionary.registerOre("helmet" + MetallurgyUtils.capitalize(metal.name, false), helmets.get(metal.name));
                OreDictionary.registerOre("chestplate" + MetallurgyUtils.capitalize(metal.name, false), chestplates.get(metal.name));
                OreDictionary.registerOre("leggings" + MetallurgyUtils.capitalize(metal.name, false), leggings.get(metal.name));
                OreDictionary.registerOre("boots" + MetallurgyUtils.capitalize(metal.name, false), boots.get(metal.name));
            }
            
            if(metal.type == MetalDefinition.Type.CATALYST) {
                OreDictionary.registerOre("item" + MetallurgyUtils.capitalize(metal.name, false), catalysts.get(metal.name));
            }
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

        for (MetalDefinition metal : registry) {
            String metalName = metal.name;
            String modid = metal.mod.getPrefix();
            
            Block metalBlock = metalBlocks.get(metalName);
            Item ingot = ingots.get(metalName);
            Item nugget = nuggets.get(metalName);
            Item dust = dusts.get(metalName);
            String oreIngot = "ingot" + MetallurgyUtils.capitalize(metalName, false);
            String oreNugget = "nugget" + MetallurgyUtils.capitalize(metalName, false);
            String oreDust = "nugget" + MetallurgyUtils.capitalize(metalName, false);
            String oreBlock = "block" + MetallurgyUtils.capitalize(metalName, false);

            if (metal.type == MetalDefinition.Type.ORE) {
                Block ore = oreBlocks.get(metalName);
                GameRegistry.addSmelting(ore, new ItemStack(ingot), 0);
            }
            else if (metal.type == MetalDefinition.Type.ALLOY) {
                List<Object> recipe = new ArrayList<>();
                for(String ingredient : metal.ingredients.keySet()) {
                    for(int i = 0; i < metal.ingredients.get(ingredient); i++)
                        recipe.add("dust" + MetallurgyUtils.capitalize(ingredient, false));
                }
                int craftingAmount = Math.round(recipe.size() * metal.alloyEfficiency);
                recipe.add(Item.getByNameOrId(metal.alloyCatalyst));
                
                RecipeHelper.shapelessRecipe(event.getRegistry(), metalName + "_alloy", new ItemStack(dust, craftingAmount), recipe.toArray(new Object[recipe.size()]));
                
            }
            
            if (metal.type != MetalDefinition.Type.CATALYST) {
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
    }
    
    public static void registerWorldGen() {
        for(MetalDefinition metal : registry) {
            if(!oreBlocks.containsKey(metal.name))
                continue;
            Block oreBlock = oreBlocks.get(metal.name);
            GameRegistry.registerWorldGenerator(new WorldGenMetalOre(metal, oreBlock.getDefaultState(), metal.orePerVeinMin, metal.orePerVeinMax), 1);
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
