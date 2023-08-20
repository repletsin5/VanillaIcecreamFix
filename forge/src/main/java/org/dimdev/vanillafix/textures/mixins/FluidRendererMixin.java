package org.dimdev.vanillafix.textures.mixins;


import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@OnlyIn(Dist.CLIENT)
@Mixin(FluidRenderer.class)
public class FluidRendererMixin {
	@Shadow
	@Final
	private Sprite[] lavaSprites;

	@Shadow
	@Final
	private Sprite[] waterSprites;

//	@Inject(method = "render", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/tag/Tag;)Z"), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
//	private void afterTextureDetermined(BlockRenderView world, BlockPos pos, VertexConsumer vertexConsumer, FluidState state, CallbackInfoReturnable<Boolean> cir, boolean bl) {
//		Sprite[] sprites = bl ? this.lavaSprites : this.waterSprites;
//		ChunkBuilder.ChunkData chunkData = TemporaryStorage.CURRENT_CHUNK_DATA.get();
//		if (chunkData != null) {
//			((ChunkDataExtensions) chunkData).getVisibleTextures().add(sprites[0]);
//			((ChunkDataExtensions) chunkData).getVisibleTextures().add(sprites[1]);
//		} else {
//			// Called from non-chunk render thread. Unfortunately, the best we can do
//			// is assume it's only going to be used once:
//			((SpriteExtensions) sprites[0]).setAnimationUpdateRequired(true);
//			((SpriteExtensions) sprites[1]).setAnimationUpdateRequired(true);
//		}
//	}
}
