package me.replet.client.Mixins;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
@OnlyIn(Dist.CLIENT)
@Mixin(ButtonWidget.class)
public class ButtonWidgetAccessor {

    @Invoker(value = "<init>")
   public static ButtonWidget callConstructor(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress, ButtonWidget.NarrationSupplier narrationSupplier){
        throw new AssertionError();
    }

    @Accessor
    public static ButtonWidget.NarrationSupplier getDEFAULT_NARRATION_SUPPLIER() {
        throw new AssertionError();
    }
}
