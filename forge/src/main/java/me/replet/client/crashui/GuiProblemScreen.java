package me.replet.client.crashui;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static me.replet.client.Mixins.ButtonWidgetAccessor.callConstructor;
import static me.replet.client.Mixins.ButtonWidgetAccessor.getDEFAULT_NARRATION_SUPPLIER;

@OnlyIn(Dist.CLIENT)
public class GuiProblemScreen extends Screen {

    public GuiProblemScreen() {
        super(Text.literal("Crash Screen"));
    }

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    protected void init() {
        addDrawableChild(callConstructor(width / 2 - 155 + 160, height / 4 + 120 + 12, 150, 20,Text.literal("Title Screen"),(button)-> {
            mc.setScreen(new TitleScreen());
        }, getDEFAULT_NARRATION_SUPPLIER()));
        addDrawableChild(callConstructor(width / 2 - 155, height / 4 + 120 + 12, 150, 20,Text.literal("Quit"),(button)-> {
            System.exit(0);
        }, getDEFAULT_NARRATION_SUPPLIER()));
        super.init();
    }
    public void drawCenteredText(DrawContext context,TextRenderer textRenderer, Text text, int centerX, int y, int color) {
        OrderedText orderedText = text.asOrderedText();
        context.drawText(textRenderer, orderedText, ((centerX - textRenderer.getWidth(orderedText) / 2)), y, color,false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context,mouseX,mouseY,delta);
       drawCenteredText(context,textRenderer, Text.literal("You have crashed"), width / 2, height / 4 - 40, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
