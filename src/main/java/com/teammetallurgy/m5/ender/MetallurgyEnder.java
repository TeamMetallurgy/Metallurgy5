package com.teammetallurgy.m5.ender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teammetallurgy.m5.core.utils.MetallurgyCreativeTab;
import com.teammetallurgy.m5.ender.proxy.CommonProxy;
import com.teammetallurgy.m5.ender.utils.Constants;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES)
public class MetallurgyEnder {
    @Mod.Instance(Constants.MOD_ID)
    public static MetallurgyEnder instance;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static final Logger LOG = LogManager.getLogger(Constants.MOD_NAME);
    public static final CreativeTabs CREATIVE_TAB = new MetallurgyCreativeTab(Constants.MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        //NetworkRegistry.INSTANCE.registerGuiHandler(Atum.instance, new AtumGuiHandler());
    }
}
