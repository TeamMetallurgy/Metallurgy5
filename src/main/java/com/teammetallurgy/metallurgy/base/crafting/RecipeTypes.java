package com.teammetallurgy.metallurgy.base.crafting;

import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.crafting.recipe.AlloyerRecipe;
import com.teammetallurgy.metallurgy.base.crafting.recipe.BlendingFurnaceRecipe;
import com.teammetallurgy.metallurgy.base.crafting.recipe.SlagPotRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class RecipeTypes {

    public static IRecipeType<AlloyerRecipe> ALLOYER = registerRecipeType("alloyer");
    public static IRecipeType<BlendingFurnaceRecipe> BLENDING_FURNACE = registerRecipeType("blending_furnace");
    public static IRecipeType<SlagPotRecipe> SLAG_POT = registerRecipeType("slag_pot");

    private static <T extends IRecipe<?>> IRecipeType<T> registerRecipeType(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MetallurgyBase.MOD_ID, key), new IRecipeType<T>() {
            @Override
            public String toString() {
                return key;
            }
        });
    }
}
