package com.teammetallurgy.metallurgy.base.crafting;

import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.crafting.recipe.AlloyerRecipe;
import com.teammetallurgy.metallurgy.base.crafting.recipe.BlendingFurnaceRecipe;
import com.teammetallurgy.metallurgy.base.crafting.recipe.SlagPotRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeSerializers {
    public static final IRecipeSerializer<AlloyerRecipe> ALLOYER = new AlloyerRecipe.Serializer<>(AlloyerRecipe::new);
    public static final IRecipeSerializer<BlendingFurnaceRecipe> BLENDING_FURNACE = new BlendingFurnaceRecipe.Serializer<>(BlendingFurnaceRecipe::new);
    public static final IRecipeSerializer<SlagPotRecipe> SLAG_POT = new SlagPotRecipe.Serializer<>(SlagPotRecipe::new);

    @SubscribeEvent
    public static void onSerializerRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().registerAll(
                ALLOYER.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, "alloyer")),
                BLENDING_FURNACE.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, "blending_furnace")),
                SLAG_POT.setRegistryName(new ResourceLocation(MetallurgyBase.MOD_ID, "slag_pot")));
    }
}