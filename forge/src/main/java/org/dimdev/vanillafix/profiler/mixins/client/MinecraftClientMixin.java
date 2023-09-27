package org.dimdev.vanillafix.profiler.mixins.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.profiler.MinecraftClientExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements MinecraftClientExtensions {
	@Inject(method = "handleProfilerKeyPress", at = @At("HEAD"))
	private void getKeyCountForProfilerNameUpdate(int digit, CallbackInfo ci) {
		digit =  (Screen.hasControlDown() ? digit + 10 : digit);
	}
}
