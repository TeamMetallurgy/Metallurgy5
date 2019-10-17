package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.SifterTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class SifterContainer extends Container {
    private final SifterTileEntity sifter;

    public SifterContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.SIFTER, windowID);
        this.sifter = (SifterTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(sifter.getWorld()), sifter.getPos()), player, MetallurgyBlock.SIFTER);
    }
}
