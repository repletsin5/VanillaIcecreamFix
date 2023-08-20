package org.dimdev.vanillafix.textures;

import net.minecraft.client.render.chunk.ChunkBuilder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Temporarily stores objects to pass between methods, to avoid
 * having to change method parameters.
 */
@OnlyIn(Dist.CLIENT)
public final class TemporaryStorage {
	public static final ThreadLocal<ChunkBuilder.ChunkData> CURRENT_CHUNK_DATA = new ThreadLocal<>(); // Thread ID -> Chunk Data being rebuilt
}
