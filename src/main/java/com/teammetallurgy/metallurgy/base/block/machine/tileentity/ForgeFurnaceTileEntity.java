package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.ForgeFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ForgeFurnaceTileEntity extends AbstractFurnaceTileEntity {

    public ForgeFurnaceTileEntity() {
        super(MetallurgyTileEntities.FORGE_FURNACE, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.forge_furnace");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new ForgeFurnaceContainer(id, this.pos, player);
    }
}
