package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.WaterPumpContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WaterPumpTileEntity extends AbstractFurnaceTileEntity {

    public WaterPumpTileEntity() {
        super(MetallurgyTileEntities.WATER_PUMP, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.water_pump");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new WaterPumpContainer(id, this.pos, player);
    }
}