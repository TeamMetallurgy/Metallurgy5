package com.teammetallurgy.m5.core.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.MetallurgyCore;
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
public class VanillaItems {

    public static Item ironDust = new Item();
    
    public static void registerOreDict() {
        OreDictionary.registerOre("dustIron", ironDust);
        OreDictionary.registerOre("itemDustIron", ironDust);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ironDust = new Item().setRegistryName("metallurgy5base", "iron_dust").setTranslationKey("iron_dust").setCreativeTab(MetallurgyCore.CREATIVE_TAB);
        JSONMaker.createItemJson("metallurgy5base", "iron_dust");
        LangWriter.addItem("metallurgy5base", ironDust.getTranslationKey() + ".name", "Iron Dust");
        
        event.getRegistry().register(ironDust);
        registerOreDict();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ironDust, 0, new ModelResourceLocation(ironDust.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    }
}
