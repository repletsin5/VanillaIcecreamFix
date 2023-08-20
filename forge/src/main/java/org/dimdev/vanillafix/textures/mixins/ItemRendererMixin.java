package org.dimdev.vanillafix.textures.mixins;


import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dimdev.vanillafix.textures.SpriteExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
	@Inject(method = "renderBakedItemQuads", at = @At("HEAD"))
	private void beforeRenderItem(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay, CallbackInfo ci) {
		for (BakedQuad quad : quads) {
			Sprite sprite = ((BakedQuadAccessor) quad).getSprite();
			if (sprite == null) {
				continue;
			}
			((SpriteExtensions) sprite).setAnimationUpdateRequired(true);
		}
	}
}
