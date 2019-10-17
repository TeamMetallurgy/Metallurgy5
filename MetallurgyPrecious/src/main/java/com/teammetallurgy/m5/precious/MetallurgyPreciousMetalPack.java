//package com.teammetallurgy.m5.precious;
//
//import com.teammetallurgy.m5.core.MetallurgyMetalPack;
//import com.teammetallurgy.m5.base.utils.MetallurgyConfig;
//import cpw.mods.modlauncher.Environment;
//import cpw.mods.modlauncher.Launcher;
//import net.minecraft.block.Block;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemGroup;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Items;
//import net.minecraft.resources.ResourcePackType;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.loading.moddiscovery.ModFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Path;
//
//@Mod.EventBusSubscriber(modid = MetallurgyPrecious.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class MetallurgyPreciousMetalPack extends MetallurgyMetalPack {
//    private static MetallurgyPreciousMetalPack instance;
//    public static final boolean IS_DEV = Launcher.INSTANCE.environment().getProperty(Environment.Keys.VERSION.get()).filter(v -> v.equals("MOD_DEV")).isPresent();
//    private static ItemGroup ITEM_GROUP;
//
//    public static MetallurgyPreciousMetalPack getInstance() {
//        if (instance == null)
//            instance = new MetallurgyPreciousMetalPack();
//        return instance;
//    }
//
//    @Override
//    public String getPrefix() {
//        return MetallurgyPrecious.MOD_ID;
//    }
//
//    @Override
//    public ItemGroup getItemGroup() {
//        if (ITEM_GROUP == null) {
//            ITEM_GROUP = new ItemGroup(MetallurgyPrecious.MOD_ID) {
//                @Override
//                public ItemStack createIcon() {
//                    return new ItemStack(Items.PUMPKIN);
//                }
//            };
//        }
//        return ITEM_GROUP;
//    }
//
//    @SubscribeEvent
//    public static void init(RegistryEvent.NewRegistry event) {
//        String filePath = MetallurgyPrecious.MOD_ID + "/metals/";
//        InputStream fileReader;
//        if (IS_DEV) {
//            ModFile modFile = ModList.get().getModFileById(MetallurgyPrecious.MOD_ID).getFile();
//            Path root = modFile.getLocator().findPath(modFile, ResourcePackType.SERVER_DATA.getDirectoryName()).toAbsolutePath();
//            try {
//                fileReader = root.resolve(root.getFileSystem().getPath(filePath)).toUri().toURL().openStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        } else {
//            fileReader = getInstance().getClass().getResourceAsStream("/data/" + filePath);
//        }
//        MetallurgyConfig.loadAll(fileReader, getInstance());
//    }
//
//    @SubscribeEvent
//    public static void registerItems(RegistryEvent.Register<Item> event) {
//        getInstance().registry.registerItems(event);
//    }
//
//    @SubscribeEvent
//    public static void registerBlocks(RegistryEvent.Register<Block> event) {
//        getInstance().registry.registerBlocks(event);
//    }
//}
