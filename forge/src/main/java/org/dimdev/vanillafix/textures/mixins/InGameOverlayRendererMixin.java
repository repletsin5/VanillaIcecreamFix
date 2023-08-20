package org.dimdev.vanillafix.textures.mixins;


import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.textures.SpriteExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {


	@Inject(method = "renderInWallOverlay", at = @At("HEAD"))
	private static void beforeRenderFireInFirstPerson(Sprite sprite, MatrixStack matrices, CallbackInfo ci) {
		((SpriteExtensions) sprite).setAnimationUpdateRequired(true);
	}
}
