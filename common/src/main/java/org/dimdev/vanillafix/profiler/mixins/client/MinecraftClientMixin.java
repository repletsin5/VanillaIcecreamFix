package org.dimdev.vanillafix.profiler.mixins.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin   {

	@Inject(method = "handleProfilerKeyPress", at = @At("HEAD"))
	private void getKeyCountForProfilerNameUpdate(int digit, CallbackInfo ci) {
		digit =  (Screen.hasControlDown() ? digit + 10 : digit);
	}

}
