package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.CrucibleTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBaseBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class CrucibleContainer extends Container {
    private final CrucibleTileEntity crucible;

    public CrucibleContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.CRUCIBLE, windowID);
        this.crucible = (CrucibleTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(crucible.getWorld()), crucible.getPos()), player, MetallurgyBaseBlock.CRUCIBLE);
    }
}
