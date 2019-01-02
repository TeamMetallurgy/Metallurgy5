package com.teammetallurgy.m5.core.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teammetallurgy.m5.core.MetallurgySubmod;
import com.teammetallurgy.m5.core.utils.MetalDefinition;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
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
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber(modid = "metallurgy5core")
public class MetalRegistry {

	private static List<MetalRegistryEntry> registry = new ArrayList<>();

	public static Map<String, Block> oreBlocks = new HashMap<>();
	public static Map<String, Item> oreItemBlocks = new HashMap<>();
	public static Map<String, Block> metalBlocks = new HashMap<>();
	public static Map<String, Item> metalItemBlocks = new HashMap<>();
	public static Map<String, Item> ingots = new HashMap<>();

	public static void registerMetal(MetalDefinition metal, MetallurgySubmod mod) {
		MetalRegistry.registry.add(new MetalRegistryEntry(metal, mod));
		
		if(metal.type == MetalDefinition.Type.ORE) {
			Block oreBlock = new Block(Material.ROCK);
			oreBlock.setCreativeTab(mod.getCreativeTab());
			oreBlock.setRegistryName(mod.getPrefix(), metal.name + "_ore");
			oreBlock.setTranslationKey(metal.name + "_ore");
			oreBlocks.put(metal.name, oreBlock);
			Item oreItemBlock = new ItemBlock(oreBlock).setRegistryName(mod.getPrefix(), metal.name + "_ore");
			oreItemBlocks.put(metal.name, oreItemBlock);
		}
		
		Block metalBlock = new Block(Material.ROCK);
		metalBlock.setCreativeTab(mod.getCreativeTab());
		metalBlock.setRegistryName(mod.getPrefix(), metal.name + "_block");
		metalBlock.setTranslationKey(metal.name + "_block");
		metalBlocks.put(metal.name, metalBlock);
		Item metalItemBlock = new ItemBlock(metalBlock).setRegistryName(mod.getPrefix(), metal.name + "_block");
		metalItemBlocks.put(metal.name, metalItemBlock);
		
		Item ingot = new Item().setRegistryName(mod.getPrefix(), metal.name + "_ingot").setTranslationKey(metal.name + "_ingot").setCreativeTab(mod.getCreativeTab());
		ingots.put(metal.name, ingot);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		registerItems(oreItemBlocks, event.getRegistry());
		registerItems(metalItemBlocks, event.getRegistry());
		registerItems(ingots, event.getRegistry());
	}

	public static void registerItems(Map<String, Item> items, IForgeRegistry<Item> registry) {
		for (Item item : items.values()) {
			registry.register(item);
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		registerBlocks(oreBlocks, event.getRegistry());
		registerBlocks(metalBlocks, event.getRegistry());
	}

	public static void registerBlocks(Map<String, Block> blocks, IForgeRegistry<Block> registry) {
		for (Block block : blocks.values()) {
			registry.register(block);
		}
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
			
			Item metalBlock = metalItemBlocks.get(entry.metal.name);
			
			//event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation(entry.metal.name + "_block"), new ItemStack(metalBlock), "III", "III", "III", 'I', new ItemStack(ingots.get(entry.metal.name))));
		}
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerBlockRenderers(oreBlocks);
		registerBlockRenderers(metalBlocks);
		registerItemRenderers(ingots);
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
