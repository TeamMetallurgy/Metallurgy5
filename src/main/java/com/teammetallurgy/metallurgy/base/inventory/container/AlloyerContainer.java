package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.AlloyerTileEntity;
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

public class AlloyerContainer extends RecipeBookContainer<IInventory> {
    private final AlloyerTileEntity alloyer;
    private final World world;
    private final IIntArray furnaceData;

    public AlloyerContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        this(windowID, pos, playerInventory, new IntArray(4));
    }

    public AlloyerContainer(int windowID, BlockPos pos, PlayerInventory playerInventory, IIntArray furnaceData) {
        super(MetallurgyGuis.ALLOYER, windowID);
        this.alloyer = (AlloyerTileEntity) playerInventory.player.world.getTileEntity(pos);
        this.furnaceData = furnaceData;
        this.world = playerInventory.player.world;
        if (this.alloyer != null) {
            // Input Slots
            for (int row = 0; row < 2; ++row) {
                for (int slot = 0; slot < 2; ++slot) {
                    this.addSlot(new Slot(this.alloyer, slot + row * 2, 45 + slot * 18, 11 + row * 18));
                }
            }
            // Catalyst
            this.addSlot(new Slot(this.alloyer, 4, 54, 58));

            // Fuel
            this.addSlot(new FuelSlot(this.alloyer, 5, 89, 58));

            // Output
            for (int slot = 0; slot < 2; ++slot) {
                this.addSlot(new AlloyerOutputSlot(playerInventory.player, this.alloyer, 6 + slot, 114, 11 + slot * 18));
            }
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
        return this.alloyer.isUsableByPlayer(player);
    }

    @Override
    public void func_201771_a(RecipeItemHelper itemHelper) {
        if (this.alloyer instanceof IRecipeHelperPopulator) {
            ((IRecipeHelperPopulator) this.alloyer).fillStackedContents(itemHelper);
        }
    }

    @Override
    public void clear() {
        this.alloyer.clear();
    }

    @Override
    public boolean matches(IRecipe<? super IInventory> recipeIn) {
        return recipeIn.matches(this.alloyer, this.world);
    }

    @Override
    public int getOutputSlot() {
        return 2;
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
        return this.alloyer.getSizeInventory();
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
