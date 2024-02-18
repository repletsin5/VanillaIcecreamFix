package org.dimdev.vanillafix.particles.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import org.dimdev.vanillafix.particles.WorldRendererExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
//import net.minecraft.util.math.Matrix4f;


@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public class WorldRendererMixin implements WorldRendererExtensions {
	@Unique
	private Frustum frustum;

//	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Frustum;setPosition(DDD)V", ordinal = 1), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
//	public void intercept(MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo ci) {
//		//this.frustum = frustum2;
//	}

	@Override
	public Frustum getFrustum() {
		return this.frustum;
	}
}
