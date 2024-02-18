package org.dimdev.vanillafix;

import com.mojang.logging.LogUtils;
import org.objectweb.asm.tree.ClassNode;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {
	private static Logger LOGGER = LogUtils.getLogger();
	@Override
	public void onLoad(String mixinPackage) {
	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
//		LogManager.getLogger("VanillIcecreamFix").warn(mixinClassName.compareTo("org.dimdev.vanillafix.bugs.mixins.step.ServerPlayerEntityMixin")==0);
//
//		if(mixinClassName.compareTo("org.dimdev.vanillafix.bugs.mixins.step.ServerPlayerEntityMixin")==0){
//			try {
//				Class<?> clazz = Class.forName("org.quiltmc.loader.impl.game.minecraft.Hooks");
//
//				return false;
//
//			} catch (ClassNotFoundException e) {
//				LogManager.getLogger("VanillIcecreamFix").error(e);
//			}
//		}
		for (var b: VanillaFix.MIXIN_CONFIGS.values()){
			for (var a: VanillaFix.MIXIN_CONFIGS.keySet()){
				//LOGGER.warn("Hello: {}, {}",a,b.getValue());
			}
			// LOGGER.warn("Hello: {}, {}",b.getValue(),b.getCategory());
		}
		boolean ret = Optional.ofNullable(VanillaFix.MIXIN_CONFIGS.get(mixinClassName)).map(pair -> {
			try {

				Object e = VanillaFix.config()
						.getClass()
						.getField(pair.getCategory())
						.get(VanillaFix.config());
				return e.getClass().getField(pair.getValue()).getBoolean(e);
			} catch (IllegalAccessException | NoSuchFieldException e) {
				LOGGER.warn("Can't get value of: \"{}\" defaulting to false",e.getMessage());
				return false;
			}
		}).orElse(Boolean.TRUE);
		if(!ret)
			LOGGER.warn("Not applying \"{}\" mixin",mixinClassName);
		//else
		//	LOGGER.warn("Applying \"{}\" mixin",mixinClassName);

		return ret;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
	}
}
