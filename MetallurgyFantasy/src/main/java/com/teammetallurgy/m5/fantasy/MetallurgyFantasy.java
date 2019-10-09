package com.teammetallurgy.m5.fantasy;

import com.teammetallurgy.m5.base.utils.MetallurgyCreativeTab;
import com.teammetallurgy.m5.fantasy.proxy.ClientProxy;
import com.teammetallurgy.m5.fantasy.proxy.CommonProxy;
import com.teammetallurgy.m5.fantasy.utils.Constants;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = Constants.MOD_ID)
public class MetallurgyFantasy {

    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final Logger LOG = LogManager.getLogger(Constants.MOD_NAME);
    public static final MetallurgyCreativeTab CREATIVE_TAB = new MetallurgyCreativeTab(Constants.MOD_ID);

//    @Mod.EventHandler
//    public void preInit(FMLPreInitializationEvent event) {
//
//    }
//
//    @Mod.EventHandler
//    public void init(FMLInitializationEvent event) {
//        proxy.init();
//        // NetworkRegistry.INSTANCE.registerGuiHandler(Atum.instance, new
//        // AtumGuiHandler());
//    }
}
