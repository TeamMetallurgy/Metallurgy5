package com.teammetallurgy.metallurgy.base.crafting.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.teammetallurgy.metallurgy.base.crafting.RecipeSerializers;
import com.teammetallurgy.metallurgy.base.crafting.RecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
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

public class BlendingFurnaceRecipe implements IRecipe<IInventory> {
    private final IRecipeType<?> type;
    private final IRecipeSerializer<?> serializer;
    private final ResourceLocation id;
    protected final String group;
    protected final int cookTime;
    protected final NonNullList<Ingredient> ingredients;
    protected final ItemStack catalyst;
    protected final ItemStack result;

    public BlendingFurnaceRecipe(ResourceLocation id, String group, int cookTime, NonNullList<Ingredient> ingredients, ItemStack catalyst, ItemStack result) {
        this(RecipeTypes.BLENDING_FURNACE, RecipeSerializers.BLENDING_FURNACE, id, group, cookTime, ingredients, catalyst, result);
    }

    public BlendingFurnaceRecipe(IRecipeType<?> type, IRecipeSerializer<?> serializer, ResourceLocation id, String group, int cookTime, NonNullList<Ingredient> ingredients, ItemStack catalyst, ItemStack result) {
        this.type = type;
        this.serializer = serializer;
        this.id = id;
        this.group = group;
        this.cookTime = cookTime;
        this.ingredients = ingredients;
        this.catalyst = catalyst;
        this.result = result;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        ItemStack catalyst = inv.getStackInSlot(2);
        if (!this.catalyst.isItemEqual(catalyst)) {
            return false;
        }
        for (int j = 0; j < 2; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (RecipeMatcher.findMatches(inputs, this.ingredients) != null);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.result.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.result;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return this.serializer;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public IRecipeType<?> getType() {
        return this.type;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public static class Serializer<T extends BlendingFurnaceRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        final BlendingFurnaceRecipe.IRecipeFactory<T> factory;

        public Serializer(BlendingFurnaceRecipe.IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            int cookTime = JSONUtils.getInt(json, "cookTime", 100);
            NonNullList<Ingredient> ingredients = readIngredients(JSONUtils.getJsonArray(json, "ingredients"));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for recipe");
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

    public interface IRecipeFactory<T extends BlendingFurnaceRecipe> {
        T create(ResourceLocation id, String group, int cookTime, NonNullList<Ingredient> ingredient, ItemStack catalyst, ItemStack result);
    }
}
