package com.teammetallurgy.metallurgy.base.crafting.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.teammetallurgy.metallurgy.base.crafting.RecipeSerializers;
import com.teammetallurgy.metallurgy.base.crafting.RecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class SlagPotRecipe implements IRecipe<IInventory> {
    private final IRecipeType<?> type;
    private final IRecipeSerializer<?> serializer;
    private final ResourceLocation id;
    protected final String group;
    protected final int meltTime;
    protected final Ingredient ingredient;
    protected final int points;

    public SlagPotRecipe(ResourceLocation id, String group, int meltTime, Ingredient ingredient, int points) {
        this.type = RecipeTypes.SLAG_POT;
        this.serializer = RecipeSerializers.SLAG_POT;
        this.id = id;
        this.group = group;
        this.meltTime = meltTime;
        this.ingredient = ingredient;
        this.points = points;
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return this.ingredient.test(inv.getStackInSlot(0));
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
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
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public IRecipeType<?> getType() {
        return this.type;
    }

    public int getCookTime() {
        return this.meltTime;
    }

    public int getPoints() {
        return this.points;
    }

    public static class Serializer<T extends SlagPotRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        final IRecipeFactory<T> factory;

        public Serializer(IRecipeFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json) {
            String s = JSONUtils.getString(json, "group", "");
            int meltTime = JSONUtils.getInt(json, "meltTime", 100);
            if (!JSONUtils.hasField(json, "ingredient"))
                throw new JsonParseException("No ingredients for slag pot recipe");

            Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "ingredient"));
            if (ingredient.hasNoMatchingItems()) {
                throw new JsonParseException("No matching ingredient");
            }
            int points = JSONUtils.getInt(json, "points", 1);
            return this.factory.create(recipeId, s, meltTime, ingredient, points);
        }

        @Nullable
        @Override
        public T read(ResourceLocation recipeId, PacketBuffer buffer) {
            String s = buffer.readString(32767);
            int meltTime = buffer.readVarInt();
            Ingredient ingredient = Ingredient.read(buffer);
            int points = buffer.readVarInt();
            return this.factory.create(recipeId, s, meltTime, ingredient, points);
        }

        @Override
        public void write(PacketBuffer buffer, T recipe) {
            buffer.writeString(recipe.group);
            buffer.writeVarInt(recipe.meltTime);
            recipe.ingredient.write(buffer);
            buffer.writeVarInt(recipe.points);
        }
    }

    public interface IRecipeFactory<T extends SlagPotRecipe> {
        T create(ResourceLocation id, String group, int meltTime, Ingredient ingredient, int points);
    }
}
