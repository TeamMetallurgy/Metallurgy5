package com.teammetallurgy.metallurgy.base;

import com.teammetallurgy.metallurgy.base.client.ClientHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = MetallurgyBase.MOD_ID)
public class MetallurgyBase {
    public final static String MOD_ID = "metallurgy5base";

    public MetallurgyBase() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(MetallurgyBaseMetalPack.getInstance()::setup);
    }

    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
}
