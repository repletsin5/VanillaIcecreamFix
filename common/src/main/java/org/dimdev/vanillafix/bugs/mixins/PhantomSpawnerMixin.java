package org.dimdev.vanillafix.bugs.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.spawner.PhantomSpawner;
import org.dimdev.vanillafix.VanillaFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/**
 * Adds a physical mob cap to phantoms.
 * Bugs Fixes:
 * - https://bugs.mojang.com/browse/MC-198101
 */
@Mixin(PhantomSpawner.class)
public class PhantomSpawnerMixin {

	@Inject(method = "spawn", at = @At(value = "HEAD", remap = false), cancellable = true)
	public void preventSpawn(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
		if (VanillaFix.config().bugFixes.phantomMobCap >= 0) {
			if (world.getEntitiesByType(EntityType.PHANTOM, e -> true).size() >= VanillaFix.config().bugFixes.phantomMobCap) {
				cir.setReturnValue(0);
			}
		}
	}

}
