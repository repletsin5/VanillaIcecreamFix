package me.replet.vanillafix.forge;

import me.replet.vanillafix.Vanillaicecreamfix;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.dimdev.vanillafix.util.config.ModConfig;

@Mod(Vanillaicecreamfix.MOD_ID)
public class VanillaicecreamfixForge {
    public VanillaicecreamfixForge() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> AutoConfig.getConfigScreen(ModConfig.class, parent).get()));
    }
}