package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.SifterContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SifterTileEntity extends AbstractFurnaceTileEntity {

    public SifterTileEntity() {
        super(MetallurgyTileEntities.SIFTER, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.sifter");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new SifterContainer(id, this.pos, player);
    }
}