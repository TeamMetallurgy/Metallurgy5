package com.teammetallurgy.m5.core.utils;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.teammetallurgy.m5.core.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;

public class MetallurgyRegistry {
    private static final NonNullList<EntityEntry> MOBS = NonNullList.create();
    private static final NonNullList<EntityEntry> ENTITIES = NonNullList.create();
    private static final NonNullList<SoundEvent> SOUNDS = NonNullList.create();
    public static final NonNullList<ItemStack> HIDE_LIST = NonNullList.create();
    //Entity tracking values
    private static HashMap<ResourceLocation, Integer> trackingRange = new HashMap<>();
    private static HashMap<ResourceLocation, Integer> updateFrequency = new HashMap<>();
    private static HashMap<ResourceLocation, Boolean> sendsVelocityUpdates = new HashMap<>();

    /**
     * Same as {@link AtumRegistry#registerItem(Item, String, CreativeTabs, String)}, but have CreativeTab set null by default and easy way to set OreDictionary name
     */
    public static Item registerItem(@Nonnull Item item, @Nonnull String name, @Nullable String oreDictName) {
        return registerItem(item, name, null, oreDictName);
    }

    /**
     * Same as {@link AtumRegistry#registerItem(Item, String, CreativeTabs, String)}, but have CreativeTab set null by default
     */
    public static Item registerItem(@Nonnull Item item, @Nonnull String name) {
        return registerItem(item, name, null, null);
    }

    /**
     * Registers an item
     *
     * @param item The item to be registered
     * @param name The name to register the item with
     * @param tab  The creative tab for the item. Set to null for no CreativeTab
     * @return The Item that was registered
     */
    public static Item registerItem(@Nonnull Item item, @Nonnull String name, @Nullable CreativeTabs tab, @Nullable String oreDictName) {
        item.setRegistryName(new ResourceLocation(Constants.MOD_ID, AtumUtils.toRegistryName(name)));
        item.setTranslationKey(Constants.MOD_ID + "." + AtumUtils.toUnlocalizedName(name));
        ForgeRegistries.ITEMS.register(item);

        if (tab != null) {
            item.setCreativeTab(tab);
        }

        if (oreDictName != null) {
            OreDictHelper.add(item, oreDictName);
        }

        if (item instanceof IOreDictEntry) {
            IOreDictEntry entry = (IOreDictEntry) item;
            OreDictHelper.entries.add(entry);
        }

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Constants.MOD_ID, AtumUtils.toRegistryName(name)), "inventory"));
        }
        return item;
    }

    /**
     * Same as {@link AtumRegistry#registerBlock(Block, Item, String, CreativeTabs)}, but have a basic ItemBlock and CreativeTab set null by default
     */
    public static Block registerBlock(@Nonnull Block block, @Nonnull String name) {
        return registerBlock(block, new ItemBlock(block), name, null);
    }

    /**
     * Same as {@link AtumRegistry#registerBlock(Block, Item, String, CreativeTabs)}, but have a basic ItemBlock set by default
     */
    public static Block registerBlock(@Nonnull Block block, @Nonnull String name, @Nullable CreativeTabs tab) {
        return registerBlock(block, new ItemBlock(block), name, tab);
    }

    /**
     * Same as {@link AtumRegistry#registerBlock(Block, Item, String, CreativeTabs)}, but have CreativeTab set by default
     */
    public static Block registerBlock(@Nonnull Block block, @Nonnull Item itemBlock, @Nonnull String name) {
        return registerBlock(block, itemBlock, name, null);
    }

    /**
     * Registers a block
     *
     * @param block The block to be registered
     * @param name  The name to register the block with
     * @param tab   The creative tab for the block. Set to null for no CreativeTab
     * @return The Block that was registered
     */
    public static Block registerBlock(@Nonnull Block block, @Nonnull Item itemBlock, @Nonnull String name, @Nullable CreativeTabs tab) {
        block.setRegistryName(new ResourceLocation(Constants.MOD_ID, AtumUtils.toRegistryName(name)));
        block.setTranslationKey(Constants.MOD_ID + "." + AtumUtils.toUnlocalizedName(name));
        ForgeRegistries.BLOCKS.register(block);
        registerItem(itemBlock, AtumUtils.toRegistryName(name));

        if (tab != null) {
            block.setCreativeTab(tab);
        }

        if (block instanceof IOreDictEntry) {
            IOreDictEntry entry = (IOreDictEntry) block;
            OreDictHelper.entries.add(entry);
        }

        //if (block instanceof IRenderMapper && FMLCommonHandler.instance().getSide() == Side.CLIENT) {
        //    ClientProxy.ignoreRenderProperty(block);
        //}
        return block;
    }
    
    public static SoundEvent registerSound(String name) {
        ResourceLocation resourceLocation = new ResourceLocation(Constants.MOD_ID, name);
        SoundEvent sound = new SoundEvent(resourceLocation);
        sound.setRegistryName(resourceLocation);
        SOUNDS.add(sound);
        return sound;
    }

    /**
     * Makes it easier to register a new recipe. Should be called in the RegistryEvent.Register event
     *
     * @param registryName the unique name for the recipe
     * @param entry        the recipe
     * @param event        the RegistryEvent.Register event
     */
    public static <T extends IForgeRegistryEntry<T>> T registerRecipe(String registryName, T entry, RegistryEvent.Register<T> event) {
        entry.setRegistryName(new ResourceLocation(Constants.MOD_ID, registryName));
        event.getRegistry().register(entry);
        return entry;
    }

    /**
     * Used to register a new registry
     *
     * @param registryName the unique string to register the registry as
     * @param type         the class that the registry is for
     * @return a new registry
     */
    public static <T extends IForgeRegistryEntry<T>> IForgeRegistry<T> makeRegistry(String registryName, Class<T> type) {
        return new RegistryBuilder<T>().setName(new ResourceLocation(Constants.MOD_ID, registryName)).setType(type).setMaxID(Integer.MAX_VALUE >> 5).allowModification().create();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        //AtumItems.registerItems();
        //AtumItems.setItemInfo();
        //OreDictHelper.register();
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        //AtumBlocks.registerBlocks();
        //AtumBlocks.setBlockInfo();
        //AtumBlocks.registerTileEntities();
    }
}
