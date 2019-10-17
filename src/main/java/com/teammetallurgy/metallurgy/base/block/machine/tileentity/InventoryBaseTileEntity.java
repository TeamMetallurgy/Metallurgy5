package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public abstract class InventoryBaseTileEntity extends LockableLootTileEntity {
    protected NonNullList<ItemStack> inventory;

    public InventoryBaseTileEntity(TileEntityType<?> typeIn, int slots) {
        super(typeIn);
        this.inventory = NonNullList.withSize(slots, ItemStack.EMPTY);
    }

    @Override
    public int getSizeInventory() {
        return this.inventory.size();
    }


//    @Override
//    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull BlockState oldState, @Nonnull BlockState newSate) {
//        return oldState.getBlock() != newSate.getBlock();
//    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    @Nonnull
    protected NonNullList<ItemStack> getItems() {
        return inventory;
    }

//    @Override
//    @Nonnull
//    public String getName() {
//        return this.hasCustomName() ? this.customName : this.getBlockType().getTranslationKey() + ".name";
//    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.inventory = itemsIn;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

//    @Override
//    public void readFromNBT(CompoundNBT compound) {
//        super.readFromNBT(compound);
//        this.inventory = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
//        if (!this.checkLootAndRead(compound)) {
//            ItemStackHelper.loadAllItems(compound, this.getItems());
//        }
//
//        if (compound.contains("CustomName", 8)) {
//            this.customName = compound.getString("CustomName");
//        }
//    }
//
//    @Override
//    @Nonnull
//    public CompoundNBT write(CompoundNBT compound) {
//        super.write(compound);
//        if (!this.checkLootAndWrite(compound)) {
//            ItemStackHelper.saveAllItems(compound, this.getItems());
//        }
//
//        if (this.hasCustomName()) {
//            compound.putString("CustomName", this.customName);
//        }
//        return compound;
//    }
}