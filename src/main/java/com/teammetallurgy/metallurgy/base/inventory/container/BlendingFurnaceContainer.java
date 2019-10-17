package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.BlendingFurnaceTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import com.teammetallurgy.metallurgy.base.inventory.container.slot.AlloyerOutputSlot;
import com.teammetallurgy.metallurgy.base.inventory.container.slot.FuelSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlendingFurnaceContainer extends RecipeBookContainer<IInventory> {
    private final BlendingFurnaceTileEntity blendingFurnace;
    private final World world;
    private final IIntArray furnaceData;

    public BlendingFurnaceContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        this(windowID, pos, playerInventory, new IntArray(4));
    }

    public BlendingFurnaceContainer(int windowID, BlockPos pos, PlayerInventory playerInventory, IIntArray furnaceData) {
        super(MetallurgyGuis.BLENDING_FURNACE, windowID);
        this.blendingFurnace = (BlendingFurnaceTileEntity) playerInventory.player.world.getTileEntity(pos);
        this.furnaceData = furnaceData;
        this.world = playerInventory.player.world;

        if (this.blendingFurnace != null) {
            // Input Slots
            this.addSlot(new Slot(this.blendingFurnace, 0, 21, 20));
            this.addSlot(new Slot(this.blendingFurnace, 1, 57, 20));
            this.addSlot(new Slot(this.blendingFurnace, 2, 39, 46));

            // Fuel
            this.addSlot(new FuelSlot(this.blendingFurnace, 3, 87, 59));

            // Output
            this.addSlot(new AlloyerOutputSlot(playerInventory.player, this.blendingFurnace, 4, 119, 21));

            // Player Inventory
            for (int row = 0; row < 3; ++row) {
                for (int slot = 0; slot < 9; ++slot) {
                    this.addSlot(new Slot(playerInventory, slot + row * 9 + 9, 8 + slot * 18, 84 + row * 18));
                }
            }
            // Player Hotbar
            for (int slot = 0; slot < 9; ++slot) {
                this.addSlot(new Slot(playerInventory, slot, 8 + slot * 18, 142));
            }
        }

        this.trackIntArray(furnaceData);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return this.blendingFurnace.isUsableByPlayer(player);
    }

    @Override
    public void func_201771_a(RecipeItemHelper p_201771_1_) {
        if (this.blendingFurnace instanceof IRecipeHelperPopulator) {
            ((IRecipeHelperPopulator) this.blendingFurnace).fillStackedContents(p_201771_1_);
        }
    }

    @Override
    public void clear() {
        this.blendingFurnace.clear();
    }

    @Override
    public boolean matches(IRecipe<? super IInventory> recipeIn) {
        return recipeIn.matches(this.blendingFurnace, this.world);
    }

    @Override
    public int getOutputSlot() {
        return 1;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @OnlyIn(Dist.CLIENT)
    public int getCookProgressionScaled() {
        int i = this.furnaceData.get(2);
        int j = this.furnaceData.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnLeftScaled() {
        int i = this.furnaceData.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.furnaceData.get(0) * 13 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isBurning() {
        return this.furnaceData.get(0) > 0;
    }
}
