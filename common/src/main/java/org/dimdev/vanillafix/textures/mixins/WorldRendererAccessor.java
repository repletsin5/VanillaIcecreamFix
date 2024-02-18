package org.dimdev.vanillafix.textures.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public interface WorldRendererAccessor {
//	@Accessor("chunks")
//    BuiltChunkStorage getVisibleChunks();
}
