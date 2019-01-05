package com.teammetallurgy.m5.core.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class RecipeHelper {
    public static void shapedRecipe(IForgeRegistry<IRecipe> registry, String name, Block output, Object... recipe) {
        shapedRecipe(registry, name, new ItemStack(output), recipe);
    }
    
    public static void shapedRecipe(IForgeRegistry<IRecipe> registry, String name, Item output, Object... recipe) {
        shapedRecipe(registry, name, new ItemStack(output), recipe);
    }
    
    public static void shapedRecipe(IForgeRegistry<IRecipe> registry, String name, ItemStack output, Object... recipe) {
        registry.register(new ShapedOreRecipe(new ResourceLocation(name), output, recipe).setRegistryName(name));
    }
    
    public static void shapelessRecipe(IForgeRegistry<IRecipe> registry, String name, Block output, Object... recipe) {
        shapelessRecipe(registry, name, new ItemStack(output), recipe);
    }
    
    public static void shapelessRecipe(IForgeRegistry<IRecipe> registry, String name, Item output, Object... recipe) {
        shapelessRecipe(registry, name, new ItemStack(output), recipe);
    }
    
    public static void shapelessRecipe(IForgeRegistry<IRecipe> registry, String name, ItemStack output, Object... recipe) {
        registry.register(new ShapelessOreRecipe(new ResourceLocation(name), output, recipe).setRegistryName(name));
    }
}
