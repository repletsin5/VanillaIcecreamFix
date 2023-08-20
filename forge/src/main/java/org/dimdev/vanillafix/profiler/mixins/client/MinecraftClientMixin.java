package org.dimdev.vanillafix.profiler.mixins.client;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.profiler.MinecraftClientExtensions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@OnlyIn(Dist.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements MinecraftClientExtensions {
	@Shadow
	public abstract boolean isIntegratedServerRunning();

	@Shadow
	private @Nullable IntegratedServer server;
	@Unique
	private boolean useIntegratedServerProfiler = false;

	@ModifyVariable(method = "handleProfilerKeyPress", at = @At("HEAD"), ordinal = 0)
	private int getKeyCountForProfilerNameUpdate(int digit) {
		return Screen.hasControlDown() ? digit + 10 : digit;
	}

	@Override
	public void toggleProfiler() {
		this.useIntegratedServerProfiler = this.isIntegratedServerRunning() && !this.useIntegratedServerProfiler;
	}
}
