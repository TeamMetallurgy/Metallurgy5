package com.teammetallurgy.m5.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teammetallurgy.m5.core.proxy.CommonProxy;
import com.teammetallurgy.m5.core.utils.Constants;
import com.teammetallurgy.m5.core.utils.MetallurgyCreativeTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = Constants.DEPENDENCIES)
public class MetallurgyCore {
    @Mod.Instance(Constants.MOD_ID)
    public static MetallurgyCore instance;

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
