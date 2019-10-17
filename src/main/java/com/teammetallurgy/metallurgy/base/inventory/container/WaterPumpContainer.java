package com.teammetallurgy.metallurgy.base.inventory.container;

import com.teammetallurgy.metallurgy.base.block.machine.tileentity.WaterPumpTileEntity;
import com.teammetallurgy.metallurgy.base.init.MetallurgyBlock;
import com.teammetallurgy.metallurgy.base.init.MetallurgyGuis;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class WaterPumpContainer extends Container {
    private final WaterPumpTileEntity waterPump;

    public WaterPumpContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(MetallurgyGuis.WATER_PUMP, windowID);
        this.waterPump = (WaterPumpTileEntity) playerInventory.player.world.getTileEntity(pos);
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(waterPump.getWorld()), waterPump.getPos()), player, MetallurgyBlock.WATER_PUMP);
    }
}
