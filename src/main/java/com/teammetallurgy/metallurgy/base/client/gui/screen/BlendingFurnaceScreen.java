package com.teammetallurgy.metallurgy.base.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.inventory.container.BlendingFurnaceContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BlendingFurnaceScreen extends ContainerScreen<BlendingFurnaceContainer> {
    private static final ResourceLocation BLENDING_FURNACE_GUI = new ResourceLocation(MetallurgyBase.MOD_ID, "textures/gui/container/blending_furnace.png");

    public BlendingFurnaceScreen(BlendingFurnaceContainer blendingFurnaceContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(blendingFurnaceContainer, playerInventory, title);
        this.ySize = 172;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(BLENDING_FURNACE_GUI);
        }
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);

        if (this.container.isBurning()) {
            int k = this.container.getBurnLeftScaled();
            this.blit(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.container.getCookProgressionScaled();
        this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
    }
}