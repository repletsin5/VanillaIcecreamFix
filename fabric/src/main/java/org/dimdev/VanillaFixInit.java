package org.dimdev;

import com.google.common.collect.ImmutableMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Jankson;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.api.SyntaxError;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.vanillafix.util.ConfigPair;
import org.dimdev.vanillafix.util.config.ModConfig;
import org.dimdev.vanillafix.util.serialization.JanksonOps;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public class VanillaFixInit implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final ModContainer MOD = FabricLoader.getInstance().getModContainer("vanillafix").orElseThrow(IllegalStateException::new);
	//private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("vanillafix.json5");
	//public static final Jankson JANKSON = Jankson.builder().build();
	//public static final ConfigHolder<ModConfig> CONFIG = AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

	@Override
	public void onInitialize() {
		if (MOD.getMetadata().getVersion().getFriendlyString().contains("beta")) {
			LOGGER.warn("================================================");
			LOGGER.warn("You are running a beta version of VanillaIcecreamFix!");
			LOGGER.warn("VanillaIcecreamFix Version: {}", MOD.getMetadata().getVersion().getFriendlyString());
			LOGGER.warn("We can not guarantee that this version is compatible");
			LOGGER.warn("with all mods. Please report issues to github if found.");
			LOGGER.warn("================================================");
		}
	}



}
