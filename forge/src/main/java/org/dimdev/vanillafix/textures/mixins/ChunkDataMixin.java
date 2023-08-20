package org.dimdev.vanillafix.textures.mixins;

import com.google.common.collect.Sets;
import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraft.client.texture.Sprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.textures.ChunkDataExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
@Mixin(ChunkBuilder.ChunkData.class)
public class ChunkDataMixin implements ChunkDataExtensions {
	@Unique
	private final Set<Sprite> visibleTextures = Sets.newHashSet();

	@Override
	public Set<Sprite> getVisibleTextures() {
		return this.visibleTextures;
	}
}
