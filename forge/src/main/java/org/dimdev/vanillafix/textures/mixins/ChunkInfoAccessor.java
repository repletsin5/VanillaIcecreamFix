package org.dimdev.vanillafix.textures.mixins;


import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@OnlyIn(Dist.CLIENT)
@Mixin(targets = "net.minecraft.client.render.WorldRenderer$ChunkInfo")
public interface ChunkInfoAccessor {
	@Accessor
	ChunkBuilder.BuiltChunk getChunk();
}
