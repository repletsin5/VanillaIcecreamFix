package org.dimdev.vanillafix.textures;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface SpriteExtensions {
	void setAnimationUpdateRequired(boolean animationUpdateRequired);

	boolean isAnimationUpdateRequired();
}
