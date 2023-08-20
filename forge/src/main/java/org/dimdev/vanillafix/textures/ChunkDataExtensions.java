package org.dimdev.vanillafix.textures;


import net.minecraft.client.texture.Sprite;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

@OnlyIn(Dist.CLIENT)
public interface ChunkDataExtensions {
	Set<Sprite> getVisibleTextures();
}

