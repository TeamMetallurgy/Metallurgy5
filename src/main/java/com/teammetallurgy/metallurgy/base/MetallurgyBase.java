package com.teammetallurgy.metallurgy.base;

import com.teammetallurgy.metallurgy.base.client.ClientHandler;
import com.teammetallurgy.metallurgy.base.utils.MetallurgyConfig;
import cpw.mods.modlauncher.Environment;
import cpw.mods.modlauncher.Launcher;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.resources.ResourcePackType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(value = MetallurgyBase.MOD_ID)
public class MetallurgyBase {
    public static final String MOD_ID = "metallurgy5base";
    public static final Map<String, MetalSet> METAL_SET = new HashMap<>();

    public static final boolean IS_DEV = Launcher.INSTANCE.environment().getProperty(Environment.Keys.VERSION.get()).filter(v -> v.equals("MOD_DEV")).isPresent();

    public MetallurgyBase() {
        METAL_SET.put(MOD_ID, new MetallurgyBaseMetalPack());

        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(MetallurgyBase.METAL_SET.get(MetallurgyBase.MOD_ID)::setup);
    }

    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        MetallurgyBase.METAL_SET.get(MetallurgyBase.MOD_ID).registry.registerItems(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        MetallurgyBase.METAL_SET.get(MetallurgyBase.MOD_ID).registry.registerBlocks(event);
    }

    @SubscribeEvent
    public static void init(RegistryEvent.NewRegistry event) {
        String filePath = MetallurgyBase.MOD_ID + "/metals/";
        InputStream fileReader;
        if (IS_DEV) {
            ModFile modFile = ModList.get().getModFileById(MetallurgyBase.MOD_ID).getFile();
            Path root = modFile.getLocator().findPath(modFile, ResourcePackType.SERVER_DATA.getDirectoryName()).toAbsolutePath();
            try {
                fileReader = root.resolve(root.getFileSystem().getPath(filePath)).toUri().toURL().openStream();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            fileReader = MetallurgyBase.class.getResourceAsStream("/data/" + filePath);
        }
        MetallurgyConfig.loadAll(fileReader, MetallurgyBase.METAL_SET.get(MetallurgyBase.MOD_ID));
    }
}
