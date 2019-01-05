package com.teammetallurgy.m5.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teammetallurgy.m5.base.proxy.CommonProxy;
import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.utils.MetallurgyCreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES)
public class MetallurgyBase {
    @Mod.Instance(Constants.MOD_ID)
    public static MetallurgyBase instance;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static final Logger LOG = LogManager.getLogger(Constants.MOD_NAME);
    public static final CreativeTabs CREATIVE_TAB = new MetallurgyCreativeTab(Constants.MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MetallurgyBaseConfig.load(event.getModConfigurationDirectory() + "/metallurgy/base/");
        MetallurgyBaseConfig.register();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        // NetworkRegistry.INSTANCE.registerGuiHandler(Atum.instance, new
        // AtumGuiHandler());
    }
}
