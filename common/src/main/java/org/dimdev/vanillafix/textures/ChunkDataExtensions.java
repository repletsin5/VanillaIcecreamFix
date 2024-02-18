package org.dimdev.vanillafix.textures;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.Sprite;

import java.util.Set;

@Environment(EnvType.CLIENT)
public interface ChunkDataExtensions {
	Set<Sprite> getVisibleTextures();
}

