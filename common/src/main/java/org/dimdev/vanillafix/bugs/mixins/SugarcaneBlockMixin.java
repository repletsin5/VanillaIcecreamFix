package org.dimdev.vanillafix.bugs.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.apache.logging.log4j.LogManager;
import org.dimdev.vanillafix.VanillaFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Fixes a bug where sugarcane can be placed underwater
 * when there is water adjacent to them.
 * Bugs Fixed:
 * - https://bugs.mojang.com/browse/MC-929
 */
@Mixin(SugarCaneBlock.class)
public abstract class SugarcaneBlockMixin extends Block {

	private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("VanillaFix");
	public SugarcaneBlockMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "canPlaceAt",at =@At("HEAD"),cancellable = true)
	public void test(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
		FluidState fluidState2 = world.getFluidState(pos);
		if(fluidState2.isIn(FluidTags.WATER) && VanillaFix.config().bugFixes.underwaterSugarcaneFix) {
			cir.setReturnValue(false);
		}
	}
	/**
	 * @author
	 * @reason

	@Overwrite
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.down());
		if (blockState.isOf(this)) {
			return true;
		} else {

			FluidState fluidState2 = world.getFluidState(pos);
			if(fluidState2.isIn(FluidTags.WATER) && VanillaFix.config().bugFixes.underwaterSugarcaneFix) {
				return false;
			}
			if (blockState.isIn(BlockTags.DIRT) || blockState.isIn(BlockTags.SAND)) {
				BlockPos blockPos = pos.down();
				Iterator var6 = Direction.Type.HORIZONTAL.iterator();

				while(var6.hasNext()) {
					Direction direction = (Direction)var6.next();
					BlockState blockState2 = world.getBlockState(blockPos.offset(direction));
					FluidState fluidState = world.getFluidState(blockPos.offset(direction));
					if (fluidState.isIn(FluidTags.WATER) || blockState2.isOf(Blocks.FROSTED_ICE)) {
						return true;
					}
				}
			}

			return false;
		}

	}
	 */
}
