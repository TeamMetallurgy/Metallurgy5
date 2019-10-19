package com.teammetallurgy.metallurgy.base.utils;

import net.minecraft.item.ItemStack;

public class RecipeUtils {
    public static boolean canSmelt(ItemStack slotStack, ItemStack outputStack) {
        return RecipeUtils.canSmelt(slotStack, outputStack, 64);
    }

    public static boolean canSmelt(ItemStack slotStack, ItemStack outputStack, int stackLimit) {
        if (slotStack.isEmpty()) {
            return true;
        } else if (!slotStack.isItemEqual(outputStack)) {
            return false;
        } else if (slotStack.getCount() + outputStack.getCount() <= stackLimit) { // Forge fix: make furnace respect stack sizes in furnace recipes
            return true;
        } else {
            return slotStack.getCount() + outputStack.getCount() <= outputStack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
        }
    }
}
