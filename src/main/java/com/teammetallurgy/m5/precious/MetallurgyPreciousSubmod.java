package com.teammetallurgy.m5.precious;

import com.teammetallurgy.m5.precious.utils.Constants;
import com.teammetallurgy.m5.core.MetallurgySubmod;

import net.minecraft.creativetab.CreativeTabs;

public class MetallurgyPreciousSubmod extends MetallurgySubmod {

    public static MetallurgyPreciousSubmod instance = new MetallurgyPreciousSubmod();

    @Override
    public String getPrefix() {
        return Constants.MOD_ID;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return MetallurgyPrecious.CREATIVE_TAB;
    }

}
