package org.dimdev.vanillafix.textures;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.chunk.ChunkBuilder;

/**
 * Temporarily stores objects to pass between methods, to avoid
 * having to change method parameters.
 */
@Environment(EnvType.CLIENT)
public final class TemporaryStorage {
	public static final ThreadLocal<ChunkBuilder.ChunkData> CURRENT_CHUNK_DATA = new ThreadLocal<>(); // Thread ID -> Chunk Data being rebuilt
}
