package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.OreBreakerTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class OreBreakerContainer extends Container {
    private final OreBreakerTileEntity oreBreaker;

    public OreBreakerContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.ORE_BREAKER, windowID);
        this.oreBreaker = (OreBreakerTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(oreBreaker.getWorld()), oreBreaker.getPos()), player, MetallurgyBlock.ORE_BREAKER);
    }
}
