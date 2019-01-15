package com.teammetallurgy.m5.core;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teammetallurgy.m5.core.proxy.CommonProxy;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
import com.teammetallurgy.m5.core.registry.RegisterMetallurgySubmodEvent;
import com.teammetallurgy.m5.core.utils.Constants;
import com.teammetallurgy.m5.core.utils.MetallurgyCreativeTab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
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

    public static boolean createJson = false;
    public static boolean overrideConfigs = true;
    public static boolean createLang = true;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.post(new RegisterMetallurgySubmodEvent(event.getModConfigurationDirectory() + "/.."));
        List<IResourcePack> defaultResourcePacks = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "field_110449_ao");
        defaultResourcePacks.add(MetalResourceLoader.instance);
        Minecraft.getMinecraft().refreshResources();
        //FMLClientHandler.instance().refreshResources(VanillaResourceType.TEXTURES);
        //FMLClientHandler.instance().refreshResources(VanillaResourceType.MODELS);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        MetalRegistry.registerWorldGen();
        // NetworkRegistry.INSTANCE.registerGuiHandler(Atum.instance, new
        // AtumGuiHandler());
    }

}
