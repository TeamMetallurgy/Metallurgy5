package com.teammetallurgy.metallurgy.base.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(MetallurgyBase.MOD_ID)
public class MetallurgyItem {
    public static List<Item> ITEMS = Lists.newArrayList();

    /**
     * Registers an item
     *
     * @param item The item to be registered
     * @param name The name to register the item with
     * @return The Item that was registered
     */
    public static Item register(@Nonnull Item item, @Nonnull String name) {
        return register(item, new ResourceLocation(MetallurgyBase.MOD_ID, name));
    }

    public static Item register(@Nonnull Item item, @Nonnull ResourceLocation registryName) {
        item.setRegistryName(registryName);
        ITEMS.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }
}
