package org.dimdev.vanillafix.bugs.mixins;

/**
 * Disables running commands that are longer than 255 characters from a sign
 * when command blocks are disabled on the server.
 * <p>
 * Bugs Fixed:
 * - https://bugs.mojang.com/browse/MC-190478
 */
/*
@MixinConfigValue(category = "bugFixes", value = "fixSignCommands")
@Mixin(SignBlockEntity.class)
public class SignBlockEntityMixin {
	@Redirect(method = "onActivate", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/CommandManager;executeWithPrefix(Lnet/minecraft/server/command/ServerCommandSource;Ljava/lang/String;)I"))
	public int checkActivate(CommandManager commandManager, ServerCommandSource commandSource, String command) {
		if (!commandSource.getServer().areCommandBlocksEnabled()) {
			if (command.length() > 255) {
				return 0;
			}
		}
		return 0;
	}
}

*/
