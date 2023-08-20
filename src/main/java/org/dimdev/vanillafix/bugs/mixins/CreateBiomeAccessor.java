package org.dimdev.vanillafix.bugs.mixins;

import net.minecraft.sound.MusicSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomeCreator.class)

public interface CreateBiomeAccessor {
    @Invoker("createBiome")
    static Biome createBiome(boolean precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.LookupBackedBuilder generationSettings, @Nullable MusicSound music){
        throw new AssertionError();
    }
}
