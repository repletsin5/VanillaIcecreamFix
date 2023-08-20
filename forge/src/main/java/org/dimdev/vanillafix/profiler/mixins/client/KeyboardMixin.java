package org.dimdev.vanillafix.profiler.mixins.client;


import net.minecraft.client.Keyboard;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
@OnlyIn(Dist.CLIENT)
@Mixin(Keyboard.class)
public class KeyboardMixin {
	/**
	 * @reason Add the F3 + S help message to the F3 + Q debug help menu.
	 */
	@Inject(method = "processF3", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;addMessage(Lnet/minecraft/text/Text;)V", ordinal = 9), locals = LocalCapture.CAPTURE_FAILHARD)
	private void addF3SHelpMessage(int key, CallbackInfoReturnable<Boolean> cir, ChatHud chatGui) {
		// TODO
//        chatGui.addMessage(new TranslatableText("vanillafix.debug.switch_profiler.help"));
	}
}
