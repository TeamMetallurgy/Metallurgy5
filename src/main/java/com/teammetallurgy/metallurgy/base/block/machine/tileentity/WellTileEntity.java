package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.WellContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class WellTileEntity extends AbstractFurnaceTileEntity {

    public WellTileEntity() {
        super(MetallurgyTileEntities.WELL, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.well");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new WellContainer(id, this.pos, player);
    }
}