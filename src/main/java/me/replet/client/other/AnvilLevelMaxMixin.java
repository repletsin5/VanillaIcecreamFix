package me.replet.client.other;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.dimdev.vanillafix.VanillaFix;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.client.gui.screen.ingame.AnvilScreen.class)
public abstract class AnvilLevelMaxMixin extends ForgingScreen<AnvilScreenHandler> {
    public AnvilLevelMaxMixin(AnvilScreenHandler handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title, texture);
    }
    @Shadow @Final
    PlayerEntity player;
    /**
     * @author replet
     * @reason ability to change anvil max level
     */
    @Overwrite
    protected void drawForeground(DrawContext matrices, int mouseX, int mouseY){
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
        int i = this.handler.getLevelCost();
        if (i > 0) {
            int j = 8453920;
            Object text;
            if (i >= VanillaFix.config().clientOnly.maxAnvilLevel && !this.client.player.getAbilities().creativeMode) {
                text =  Text.translatable("container.repair.expensive");
                j = 16736352;
            } else if (!this.handler.getSlot(2).hasStack()) {
                text = null;
            } else {
                text = Text.translatable("container.repair.cost", new Object[]{i});
                if (!this.handler.getSlot(2).canTakeItems(this.player) ){
                    j = 16736352;
                }
            }

            if (text != null) {
                int k = this.backgroundWidth - 8 - this.textRenderer.getWidth((StringVisitable)text) - 2;
                matrices.fill( k - 2, 67, this.backgroundWidth - 8, 79, 1325400064);
                matrices.drawTextWithShadow(textRenderer, (Text) text, k, 69, j);
            }
        }
    }
}
