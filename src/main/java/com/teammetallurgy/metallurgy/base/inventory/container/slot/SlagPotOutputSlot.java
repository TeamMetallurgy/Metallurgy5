package com.teammetallurgy.metallurgy.base.inventory.container.slot;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.SlagPotTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;

import javax.annotation.Nonnull;

public class SlagPotOutputSlot extends Slot {
    private final PlayerEntity player;
    private int removeCount;

    public SlagPotOutputSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }

    @Override
    @Nonnull
    public ItemStack decrStackSize(int amount) {
        if (this.getHasStack()) {
            this.removeCount += Math.min(amount, this.getStack().getCount());
        }
        return super.decrStackSize(amount);
    }

    @Override
    @Nonnull
    public ItemStack onTake(PlayerEntity thePlayer, @Nonnull ItemStack stack) {
        this.onCrafting(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    @Override
    protected void onCrafting(@Nonnull ItemStack stack, int amount) {
        this.removeCount += amount;
        this.onCrafting(stack);
    }

    @Override
    protected void onCrafting(@Nonnull ItemStack stack) {
        stack.onCrafting(this.player.world, this.player, this.removeCount);
        if (!this.player.world.isRemote && this.inventory instanceof AbstractFurnaceTileEntity) {
            ((SlagPotTileEntity) this.inventory).func_213995_d(this.player);
        }

        this.removeCount = 0;
    }
}