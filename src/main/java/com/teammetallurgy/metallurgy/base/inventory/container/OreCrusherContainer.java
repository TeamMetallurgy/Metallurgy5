package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.OreCrusherTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBaseBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class OreCrusherContainer extends Container {
    private final OreCrusherTileEntity oreCrusher;

    public OreCrusherContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.ORE_CRUSHER, windowID);
        this.oreCrusher = (OreCrusherTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(oreCrusher.getWorld()), oreCrusher.getPos()), player, MetallurgyBaseBlock.ORE_CRUSHER);
    }
}
