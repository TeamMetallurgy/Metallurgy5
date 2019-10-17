package com.teammetallurgy.metallurgy.base.client;

import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.client.gui.screen.*;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MetallurgyBase.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static void setupClient() {
        ScreenManager.registerFactory(MetallurgyGuis.ALLOYER, AlloyerScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.BLENDING_FURNACE, BlendingFurnaceScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.CRUCIBLE, CrucibleScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.FORGE_FURNACE, ForgeFurnaceScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.ORE_BREAKER, OreBreakerScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.ORE_CRUSHER, OreCrusherScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.SIFTER, SifterScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.SIFTING_TABLE, SiftingTableScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.SLAG_POT, SlagPotScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.SUBSURFACE_DRILL, SubsurfaceDrillScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.WATER_PUMP, WaterPumpScreen::new);
        ScreenManager.registerFactory(MetallurgyGuis.WELL, WellScreen::new);
    }
}
