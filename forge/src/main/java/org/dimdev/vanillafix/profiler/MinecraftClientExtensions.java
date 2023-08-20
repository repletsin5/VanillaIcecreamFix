package org.dimdev.vanillafix.profiler;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface MinecraftClientExtensions {
	void toggleProfiler();
}
