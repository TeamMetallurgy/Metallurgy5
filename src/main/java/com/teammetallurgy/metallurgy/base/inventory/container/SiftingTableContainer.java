package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.SiftingTableTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBaseBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class SiftingTableContainer extends Container {
    private final SiftingTableTileEntity siftingTable;

    public SiftingTableContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.SIFTING_TABLE, windowID);
        this.siftingTable = (SiftingTableTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(siftingTable.getWorld()), siftingTable.getPos()), player, MetallurgyBaseBlock.SIFTING_TABLE);
    }
}
