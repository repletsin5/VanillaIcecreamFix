package org.dimdev.vanillafix.textures.mixins;

import org.dimdev.vanillafix.textures.SpriteExtensions;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.texture.Sprite;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@Mixin(Sprite.class)
public class SpriteMixin implements SpriteExtensions {
	private boolean animationUpdateRequired;

	@Override
	public void setAnimationUpdateRequired(boolean animationUpdateRequired) {
		this.animationUpdateRequired = animationUpdateRequired;
	}

	@Override
	public boolean isAnimationUpdateRequired() {
		return this.animationUpdateRequired;
	}
}
