package com.teammetallurgy.m5.base;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = MetallurgyBase.MOD_ID)
public class MetallurgyBase {
    public final static String MOD_ID = "metallurgy5base";

    public MetallurgyBase() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(MetallurgyBaseMetalPack.getInstance()::setup);
    }
}
