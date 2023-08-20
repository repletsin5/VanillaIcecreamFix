package org.dimdev.vanillafix.textures.mixins;


import net.minecraft.client.render.WorldRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@OnlyIn(Dist.CLIENT)
@Mixin(WorldRenderer.class)
public interface WorldRendererAccessor {
//	@Accessor("chunks")
//    BuiltChunkStorage getVisibleChunks();
}
