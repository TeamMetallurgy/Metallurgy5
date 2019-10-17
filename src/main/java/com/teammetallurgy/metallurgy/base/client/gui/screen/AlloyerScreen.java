package com.teammetallurgy.metallurgy.base.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.inventory.container.AlloyerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloyerScreen extends ContainerScreen<AlloyerContainer> {
    private static final ResourceLocation ALLOYER_GUI = new ResourceLocation(MetallurgyBase.MOD_ID, "textures/gui/container/alloyer.png");

    public AlloyerScreen(AlloyerContainer alloyerContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(alloyerContainer, playerInventory, title);
        this.ySize = 162;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(ALLOYER_GUI);
        }
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);

    }
}