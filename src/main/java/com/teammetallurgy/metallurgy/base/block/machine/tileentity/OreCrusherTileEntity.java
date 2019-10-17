package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.OreCrusherContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OreCrusherTileEntity extends AbstractFurnaceTileEntity {

    public OreCrusherTileEntity() {
        super(MetallurgyTileEntities.ORE_CRUSHER, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.ore_crusher");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new OreCrusherContainer(id, this.pos, player);
    }
}
