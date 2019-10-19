package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.WellTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBaseBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class WellContainer extends Container {
    private final WellTileEntity well;

    public WellContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.WELL, windowID);
        this.well = (WellTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(well.getWorld()), well.getPos()), player, MetallurgyBaseBlock.WELL);
    }
}
