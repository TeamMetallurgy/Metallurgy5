package com.teammetallurgy.metallurgy.base.block.machine.tileentity;

import com.teammetallurgy.metallurgy.base.init.MetallurgyTileEntities;
import com.teammetallurgy.metallurgy.base.inventory.container.OreBreakerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OreBreakerTileEntity extends AbstractFurnaceTileEntity {

    public OreBreakerTileEntity() {
        super(MetallurgyTileEntities.ORE_BREAKER, null);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.ore_breaker");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new OreBreakerContainer(id, this.pos, player);
    }
}