package org.dimdev.vanillafix.textures.mixins;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.render.BuiltChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.render.WorldRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public interface WorldRendererAccessor {
//	@Accessor("chunks")
//    BuiltChunkStorage getVisibleChunks();
}
