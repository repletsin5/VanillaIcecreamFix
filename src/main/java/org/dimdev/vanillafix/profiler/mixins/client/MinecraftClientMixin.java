package org.dimdev.vanillafix.profiler.mixins.client;

import org.dimdev.vanillafix.profiler.MinecraftClientExtensions;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.server.integrated.IntegratedServer;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly()
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin   {

	@Inject(method = "handleProfilerKeyPress", at = @At("HEAD"))
	private void getKeyCountForProfilerNameUpdate(int digit, CallbackInfo ci) {
		digit =  (Screen.hasControlDown() ? digit + 10 : digit);
	}

}
