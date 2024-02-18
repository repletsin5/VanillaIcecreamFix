package org.dimdev.vanillafix;

import com.google.common.collect.ImmutableMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.JsonObject;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.api.SyntaxError;
import org.dimdev.vanillafix.util.ConfigPair;
import org.dimdev.vanillafix.util.config.ModConfig;
import org.dimdev.vanillafix.util.serialization.JanksonOps;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Jankson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;


public class VanillaFix {

    public static final Map<String, ConfigPair> MIXIN_CONFIGS;
    public static final Jankson JANKSON = Jankson.builder().build();
    public static final ConfigHolder<ModConfig> CONFIG = AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

    static {
        ImmutableMap.Builder<String, ConfigPair> builder = ImmutableMap.builder();
        try (InputStream stream = VanillaFix.class.getResourceAsStream("/data/vanillafix/mixin_configs.json")) {
            JsonObject jsonObject = JANKSON.load(stream);
            //noinspection UnstableApiUsage
            builder.putAll(jsonObject.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() instanceof JsonObject)
                    .map(entry -> {
                        return new Map.Entry<String, ConfigPair>() {
                            @Override
                            public String getKey() {
                                return entry.getKey();
                            }

                            @Override
                            public ConfigPair getValue() {
                                return ConfigPair.CODEC.parse(JanksonOps.INSTANCE, entry.getValue()).getOrThrow(false, System.err::println);
                            }

                            @Override
                            public ConfigPair setValue(ConfigPair value) {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }).collect(Collectors.toSet()));
        } catch (IOException | SyntaxError e) {
            throw new AssertionError(e);
        }
        MIXIN_CONFIGS = builder.build();
    }
    public static ModConfig config() {
        return CONFIG.get();
    }
}
