package org.dimdev.vanillafix.textures.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.texture.Sprite;
import org.dimdev.vanillafix.textures.SpriteExtensions;
import org.spongepowered.asm.mixin.Mixin;

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
