package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.ForgeFurnaceTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ForgeFurnaceContainer extends Container {
    private final ForgeFurnaceTileEntity forgeFurnace;

    public ForgeFurnaceContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.FORGE_FURNACE, windowID);
        this.forgeFurnace = (ForgeFurnaceTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(forgeFurnace.getWorld()), forgeFurnace.getPos()), player, MetallurgyBlock.FORGE_FURNACE);
    }
}
