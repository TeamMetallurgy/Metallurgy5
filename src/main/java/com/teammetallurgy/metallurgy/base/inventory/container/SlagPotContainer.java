package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.SlagPotTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import com.teammetallurgy.metallurgy.base.inventory.container.slot.FuelSlot;
import com.teammetallurgy.metallurgy.base.inventory.container.slot.SlagPotOutputSlot;
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

public class SlagPotContainer extends RecipeBookContainer<IInventory> {
    private final SlagPotTileEntity slagPot;
    private final World world;
    private final IIntArray furnaceData;

    public SlagPotContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        this(windowID, pos, playerInventory, new IntArray(4));
    }

    public SlagPotContainer(int windowID, BlockPos pos, PlayerInventory playerInventory, IIntArray furnaceData) {
        super(MetallurgyGuis.SLAG_POT, windowID);
        this.slagPot = (SlagPotTileEntity) playerInventory.player.world.getTileEntity(pos);
        this.furnaceData = furnaceData;
        this.world = playerInventory.player.world;
        if (this.slagPot != null) {
            // Input Slots
            this.addSlot(new Slot(this.slagPot, 0, 40, 16));

            // Fuel
            this.addSlot(new FuelSlot(this.slagPot, 1, 40, 52));

            // Output
            this.addSlot(new SlagPotOutputSlot(playerInventory.player, this.slagPot, 2, 121, 34));

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
        return this.slagPot.isUsableByPlayer(player);
    }

    @Override
    public void func_201771_a(RecipeItemHelper itemHelper) {
        if (this.slagPot instanceof IRecipeHelperPopulator) {
            ((IRecipeHelperPopulator) this.slagPot).fillStackedContents(itemHelper);
        }
    }

    @Override
    public void clear() {
        this.slagPot.clear();
    }

    @Override
    public boolean matches(IRecipe<? super IInventory> recipeIn) {
        return recipeIn.matches(this.slagPot, this.world);
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
        return this.slagPot.getSizeInventory();
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
