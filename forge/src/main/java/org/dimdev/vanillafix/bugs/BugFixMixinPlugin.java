package org.dimdev.vanillafix.bugs;

import org.dimdev.vanillafix.VanillaFix;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BugFixMixinPlugin implements IMixinConfigPlugin {
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

		return Optional.ofNullable(VanillaFix.MIXIN_CONFIGS.get(targetClassName)).map(pair -> {
			try {
				Object e = VanillaFix.config()
						.getClass()
						.getField(pair.getCategory())
						.get(VanillaFix.config());
				return e.getClass().getField(pair.getValue()).getBoolean(e);
			} catch (IllegalAccessException | NoSuchFieldException ignored) {
				throw new AssertionError();
			}
		}).orElse(Boolean.TRUE);
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
