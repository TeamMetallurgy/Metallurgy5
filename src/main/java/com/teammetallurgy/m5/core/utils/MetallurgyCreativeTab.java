package com.teammetallurgy.m5.core.utils;

import javax.annotation.Nonnull;

import com.teammetallurgy.m5.base.utils.Constants;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetallurgyCreativeTab extends CreativeTabs {

    private Item icon;

    public MetallurgyCreativeTab(String id) {
        super(id);
        this.setBackgroundImageName("item_search.png");
    }

    public void setIcom(Item item) {
        this.icon = item;
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return new ItemStack(icon);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

}
