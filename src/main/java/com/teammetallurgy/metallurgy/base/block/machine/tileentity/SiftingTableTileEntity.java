package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.SiftingTableContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SiftingTableTileEntity extends AbstractFurnaceTileEntity {

    public SiftingTableTileEntity() {
        super(MetallurgyTileEntities.SIFTING_TABLE, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.sifting_table");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new SiftingTableContainer(id, this.pos, player);
    }
}