package org.dimdev.vanillafix.bugs.mixins.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.util.annotation.MixinConfigValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@MixinConfigValue(category = "clientOnly", value = "screenInNetherPortal")
@OnlyIn(Dist.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	/**
	 * @reason Enables opening GUIs in nether portals. (see https://bugs.mojang.com/browse/MC-2071)
	 * This works by making minecraft think that GUI pauses the game
	 */
	@Redirect(method = "updateNausea", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;shouldPause()Z"))
	private boolean onPauseCheck(Screen guiScreen) {
		return true;
	}
}
