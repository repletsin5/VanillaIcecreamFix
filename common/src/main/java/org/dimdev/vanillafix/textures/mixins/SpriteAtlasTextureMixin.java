package org.dimdev.vanillafix.textures.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(SpriteAtlasTexture.class)
public abstract class SpriteAtlasTextureMixin extends AbstractTexture {

	@Shadow
	@Final
	private List<Sprite> animatedSprites;

	/**
	 * @reason Replaces the tickAnimatedSprites method to only tick animated textures
	 * that are in one of the loaded BuiltChunks. This can lead to an FPS more than
	 * three times higher on large modpacks with many textures.
	 * <p>
	 * Also breaks down the "root.tick.textures" profiler by texture name.
	 * @author Runemoro
	 */
//	@Overwrite
//	public void tickAnimatedSprites() {
//		Profiler profiler = MinecraftClient.getInstance().getProfiler();
//		profiler.push("determineVisibleTextures");
//		for (Object e : ((WorldRendererAccessor) Objects.requireNonNull(MinecraftClient.getInstance().worldRenderer)).getVisibleChunks().chunks) {
//			ChunkBuilder.BuiltChunk builtChunk = ((ChunkInfoAccessor) e).getChunk();
//			for (Sprite sprite : ((ChunkDataExtensions) builtChunk.getData()).getVisibleTextures()) {
//				((SpriteExtensions) sprite).setAnimationUpdateRequired(true);
//			}
//		}
//		profiler.pop();
//
//		RenderSystem.bindTexture(this.getGlId());
//		for (Sprite animatedSprite : this.animatedSprites) {
//			if (((SpriteExtensions) animatedSprite).isAnimationUpdateRequired()) {
//				profiler.push(animatedSprite.getId().toString());
//				animatedSprite.getAnimation().tick();
//				((SpriteExtensions) animatedSprite).setAnimationUpdateRequired(false);
//				profiler.pop();
//			}
//		}
//	}
}
