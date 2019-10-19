package com.teammetallurgy.metallurgy.base.crafting.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.teammetallurgy.metallurgy.base.crafting.RecipeSerializers;
import com.teammetallurgy.metallurgy.base.crafting.RecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AlloyerRecipe extends BlendingFurnaceRecipe {

    public AlloyerRecipe(ResourceLocation id, String group, int cookTime, NonNullList<Ingredient> ingredients, ItemStack catalyst, ItemStack result) {
        super(RecipeTypes.ALLOYER, RecipeSerializers.ALLOYER, id, group, cookTime, ingredients, catalyst, result);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        ItemStack catalyst = inv.getStackInSlot(4);
        if (!this.catalyst.isItemEqual(catalyst)) {
            return false;
        }
        for (int j = 0; j < 4; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (RecipeMatcher.findMatches(inputs, this.ingredients) != null);
    }

    public static class Serializer<T extends AlloyerRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        final IRecipeFactory<T> factory;

        public Serializer(IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            int cookTime = JSONUtils.getInt(json, "cookTime", 100);
            NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for alloyer recipe");
            } else if (ingredients.size() > 4) {
                throw new JsonParseException("Too many ingredients for shapeless recipe the max is 4");
            }
            ItemStack catalyst = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "catalyst"));
            ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            return this.factory.create(recipeId, s, cookTime, ingredients, catalyst, itemstack);
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray data) {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (int i = 0; i < data.size(); ++i) {
                Ingredient ingredient = Ingredient.deserialize(data.get(i));
                if (!ingredient.hasNoMatchingItems()) {
                    ingredients.add(ingredient);
                }
            }

            return ingredients;
        }

        @Nullable
        @Override
        public T read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            int cookTime = buffer.readVarInt();
            int i = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < ingredients.size(); ++j) {
                ingredients.set(j, Ingredient.read(buffer));
            }
            ItemStack catalyst = buffer.readItemStack();
            ItemStack itemstack = buffer.readItemStack();
            return this.factory.create(recipeId, s, cookTime, ingredients, catalyst, itemstack);
        }

        @Override
        public void write(PacketBuffer buffer, T recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.cookTime);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.catalyst);
            buffer.writeItemStack(recipe.result);
        }
    }

}
