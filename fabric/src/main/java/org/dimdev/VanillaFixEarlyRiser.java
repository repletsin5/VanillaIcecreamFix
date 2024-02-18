package org.dimdev;

import net.fabricmc.loader.api.FabricLoader;
import org.dimdev.vanillafix.VanillaFix;
import org.spongepowered.asm.mixin.Mixins;

public class VanillaFixEarlyRiser implements Runnable {
	@Override
	public void run() {
		if (VanillaFix.config().clientOnly.optimizedAnimatedTextures ||
				// sodium already does this
				!(FabricLoader.getInstance().isModLoaded("sodium"))) {
			VanillaFixInit.LOGGER.debug("Registering Animated Texture Optimization Mixins");
			Mixins.addConfiguration("vanillafix.textures.mixins.json");
		}
		if (VanillaFix.config().clientOnly.cullParticles ||
				// sodium already does this too
				!(FabricLoader.getInstance().isModLoaded("sodium"))) {
			VanillaFixInit.LOGGER.debug("Registering Particle Optimization Mixins");
			Mixins.addConfiguration("vanillafix.particles.mixins.json");
		}
		if (VanillaFix.config().general.profilerImprovements) {
			VanillaFixInit.LOGGER.debug("Registering Profiler Improvements Mixins");
			Mixins.addConfiguration("vanillafix.profiler.mixins.json");
		}

		// haha asm go brr
//		String clazzName = FabricLoader.getInstance().getMappingResolver().mapClassName("intermediary", "net.minecraft.class_4584");
//		ClassTinkerers.addTransformation(clazzName, node -> {
//			node.methods.stream().map(node -> {
//
//			})
//		});
	}
}
