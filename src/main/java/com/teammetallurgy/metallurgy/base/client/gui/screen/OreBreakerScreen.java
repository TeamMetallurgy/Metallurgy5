package com.teammetallurgy.metallurgy.base.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.inventory.container.OreBreakerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class OreBreakerScreen extends ContainerScreen<OreBreakerContainer> {
    private static final ResourceLocation ORE_BREAKER_GUI = new ResourceLocation(MetallurgyBase.MOD_ID, "textures/gui/container/ore_breaker.png");

    public OreBreakerScreen(OreBreakerContainer oreBreakerContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(oreBreakerContainer, playerInventory, title);
        this.ySize = 172;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 100.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(ORE_BREAKER_GUI);
        }
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);

    }
}