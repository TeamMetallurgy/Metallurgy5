package com.teammetallurgy.m5.core.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teammetallurgy.m5.core.MetallurgySubmod;
import com.teammetallurgy.m5.core.utils.JSONMaker;
import com.teammetallurgy.m5.core.utils.MetalDefinition;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber(modid = "metallurgy5core")
public class MetalRegistry {

	private static List<MetalRegistryEntry> registry = new ArrayList<>();

	public static Map<String, Block> oreBlocks = new HashMap<>();
	public static Map<String, Block> metalBlocks = new HashMap<>();
	public static Map<String, Block> metalLargeBricks = new HashMap<>();
	public static Map<String, Item> ingots = new HashMap<>();
	public static Map<String, Item> swords = new HashMap<>();
	public static Map<String, Item> axes = new HashMap<>();

	public static void registerMetal(MetalDefinition metal, MetallurgySubmod mod) {
		MetalRegistry.registry.add(new MetalRegistryEntry(metal, mod));
		
		if(metal.type == MetalDefinition.Type.ORE) {
			Block oreBlock = new Block(Material.ROCK);
			oreBlock.setCreativeTab(mod.getCreativeTab());
			oreBlock.setRegistryName(mod.getPrefix(), metal.name + "_ore");
			oreBlock.setTranslationKey(metal.name + "_ore");
			oreBlocks.put(metal.name, oreBlock);
		}
		
		Block metalBlock = new Block(Material.ROCK);
		metalBlock.setCreativeTab(mod.getCreativeTab());
		metalBlock.setRegistryName(mod.getPrefix(), metal.name + "_block");
		metalBlock.setTranslationKey(metal.name + "_block");
		metalBlocks.put(metal.name, metalBlock);
		
		Block metalLargeBrick = new Block(Material.ROCK);
		metalLargeBrick.setCreativeTab(mod.getCreativeTab());
		metalLargeBrick.setRegistryName(mod.getPrefix(), metal.name + "_large_bricks");
		metalLargeBrick.setTranslationKey(metal.name + "_large_bricks");
		metalLargeBricks.put(metal.name, metalLargeBrick);
		
		Item ingot = new Item().setRegistryName(mod.getPrefix(), metal.name + "_ingot").setTranslationKey(metal.name + "_ingot").setCreativeTab(mod.getCreativeTab());
		ingots.put(metal.name, ingot);
		
		Item sword = new Item().setRegistryName(mod.getPrefix(), metal.name + "_sword").setTranslationKey(metal.name + "_sword").setCreativeTab(mod.getCreativeTab());
		swords.put(metal.name, sword);
		JSONMaker.createItemJson(mod.getPrefix(), metal.name + "_sword");
		
		Item axe = new Item().setRegistryName(mod.getPrefix(), metal.name + "_axe").setTranslationKey(metal.name + "_axe").setCreativeTab(mod.getCreativeTab());
		axes.put(metal.name, axe);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		registerItemBlocks(oreBlocks, event.getRegistry());
		registerItemBlocks(metalBlocks, event.getRegistry());
		registerItemBlocks(metalLargeBricks, event.getRegistry());
		
		registerItems(ingots, event.getRegistry());
		registerItems(swords, event.getRegistry());
		registerItems(axes, event.getRegistry());
		
		OreDictionary.registerOre("copper_axe", axes.get("copper"));
		OreDictionary.registerOre("copper_block", metalBlocks.get("copper"));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		registerBlocks(oreBlocks, event.getRegistry());
		registerBlocks(metalBlocks, event.getRegistry());
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerBlockRenderers(oreBlocks);
		registerBlockRenderers(metalBlocks);
		registerBlockRenderers(metalLargeBricks);
		
		registerItemRenderers(ingots);
		registerItemRenderers(swords);
		registerItemRenderers(axes);
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		IForgeRegistryModifiable<IRecipe> recipes = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();

		for (MetalRegistryEntry entry : registry) {
			if (entry.metal.type == MetalDefinition.Type.ORE) {
				Block ore = oreBlocks.get(entry.metal.name);
				Item ingot = ingots.get(entry.metal.name);
				GameRegistry.addSmelting(ore, new ItemStack(ingot), 0);
			}
			
			Block metalBlock = metalBlocks.get(entry.metal.name);
			event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation(entry.metal.name + "_block"), metalBlock, "III", "III", "III", 'I', ingots.get(entry.metal.name)).setRegistryName(entry.metal.name + "_block"));
			event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation(entry.metal.name + "_axe"), axes.get(entry.metal.name), "III", " S ", " S ", 'I', ingots.get(entry.metal.name), 'S', Items.STICK).setRegistryName(entry.metal.name + "_axe"));
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
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}

	public static void registerBlockRenderers(Map<String, Block> blocks) {
		for (Block block : blocks.values()) {
			Item item = Item.getItemFromBlock(block);
			ModelLoader.setCustomModelResourceLocation(item, 0,
					new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}
