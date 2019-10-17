package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.SubsurfaceDrillContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SubsurfaceDrillTileEntity extends AbstractFurnaceTileEntity {

    public SubsurfaceDrillTileEntity() {
        super(MetallurgyTileEntities.SUBSURFACE_DRILL, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.subsurface_drill");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new SubsurfaceDrillContainer(id, this.pos, player);
    }
}