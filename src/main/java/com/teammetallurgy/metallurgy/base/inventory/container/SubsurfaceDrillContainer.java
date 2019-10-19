package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.SubsurfaceDrillTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBaseBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class SubsurfaceDrillContainer extends Container {
    private final SubsurfaceDrillTileEntity subsurfaceDrill;

    public SubsurfaceDrillContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.SUBSURFACE_DRILL, windowID);
        this.subsurfaceDrill = (SubsurfaceDrillTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(subsurfaceDrill.getWorld()), subsurfaceDrill.getPos()), player, MetallurgyBaseBlock.SUBSURFACE_DRILL);
    }
}
