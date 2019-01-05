package com.teammetallurgy.m5.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.teammetallurgy.m5.base.proxy.CommonProxy;
import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.registry.MetalDefinition;
import com.teammetallurgy.m5.core.registry.MetalRegistry;
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
        MetalDefinition copper = new MetalDefinition().name("copper").resource_name("copper").tier(1).type(MetalDefinition.Type.ORE);
        copper.toolDurability = 180;
        copper.enchantability = 8;
        copper.miningSpeed = 6.0f;
        copper.harvestLevel = 2;
        copper.swordDamage = 4;
        copper.pickaxeDamage = 3;
        copper.shovelDamage = 3.5f;
        
        MetalDefinition tin = new MetalDefinition().name("tin").resource_name("tin").tier(1).type(MetalDefinition.Type.ORE);
        MetalDefinition bronze = new MetalDefinition().name("bronze").resource_name("bronze").tier(1).type(MetalDefinition.Type.ALLOY);
        MetalDefinition lead = new MetalDefinition().name("lead").resource_name("lead").tier(1).type(MetalDefinition.Type.ORE);

        MetalRegistry.registerMetal(copper, MetallurgyBaseSubmod.instance);
        MetalRegistry.registerMetal(tin, MetallurgyBaseSubmod.instance);
        MetalRegistry.registerMetal(bronze, MetallurgyBaseSubmod.instance);
        MetalRegistry.registerMetal(lead, MetallurgyBaseSubmod.instance);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        // NetworkRegistry.INSTANCE.registerGuiHandler(Atum.instance, new
        // AtumGuiHandler());
    }
}
