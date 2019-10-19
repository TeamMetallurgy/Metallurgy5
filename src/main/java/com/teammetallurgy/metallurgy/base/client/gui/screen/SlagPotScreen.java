package com.teammetallurgy.metallurgy.base.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.metallurgy.base.MetallurgyBase;
import com.teammetallurgy.metallurgy.base.inventory.container.SlagPotContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SlagPotScreen extends ContainerScreen<SlagPotContainer> {
    private static final ResourceLocation SLAG_POT_GUI = new ResourceLocation(MetallurgyBase.MOD_ID, "textures/gui/container/slag_pot.png");
    private final int top = 31;
    private final int bottom = 87;
    private final int size = bottom - top;

    public SlagPotScreen(SlagPotContainer slagPotContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(slagPotContainer, playerInventory, title);
        this.ySize = 166;
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
            this.minecraft.getTextureManager().bindTexture(SLAG_POT_GUI);
        }
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (this.container.isBurning()) {
            int k = this.container.getBurnLeftScaled();
            this.blit(i + 41, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.container.getCookProgressionScaled();
        this.blit(i + 62, j + 34, 176, 14, l + 1, 16);

        int p = this.container.getPoints();
        float x = p / 1000f;
        final float sizePercentage = bottom - (size) * x;
        final int offset = (int) (bottom - sizePercentage);
        this.blit(i + 92, j + 70 - offset, 176, (int) sizePercentage, 11, offset);
    }
}