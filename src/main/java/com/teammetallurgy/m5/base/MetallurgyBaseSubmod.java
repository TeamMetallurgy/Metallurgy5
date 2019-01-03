package com.teammetallurgy.m5.base;

import com.teammetallurgy.m5.base.utils.Constants;
import com.teammetallurgy.m5.core.MetallurgySubmod;

import net.minecraft.creativetab.CreativeTabs;

public class MetallurgyBaseSubmod extends MetallurgySubmod {

    public static MetallurgyBaseSubmod instance = new MetallurgyBaseSubmod();

    @Override
    public String getPrefix() {
        return Constants.MOD_ID;
    }

    @Override
    public CreativeTabs getCreativeTab() {
        return MetallurgyBase.CREATIVE_TAB;
    }

}
