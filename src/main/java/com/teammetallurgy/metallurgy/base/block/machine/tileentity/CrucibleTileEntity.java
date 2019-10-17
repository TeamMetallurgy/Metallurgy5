package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.CrucibleContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CrucibleTileEntity extends AbstractFurnaceTileEntity {

    public CrucibleTileEntity() {
        super(MetallurgyTileEntities.CRUCIBLE, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.crucible");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new CrucibleContainer(id, this.pos, player);
    }
}