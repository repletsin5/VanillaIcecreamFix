package org.dimdev.vanillafix.profiler.mixins.client;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKeys;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
	@Shadow
	private ClientWorld world;

	@Shadow
	protected abstract void renderEntity(Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vcp);

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;renderEntity(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"))
	public void interceptRender(WorldRenderer worldRenderer, Entity entity, double cameraX, double cameraY, double cameraZ, float tickDelta, MatrixStack matrices, VertexConsumerProvider vcp) {
		this.world.getProfiler().push(Objects.requireNonNull(RegistryKeys.ENTITY_TYPE.getRegistry().toString()));
		this.renderEntity(entity, cameraX, cameraY, cameraZ, tickDelta, matrices, vcp);
		this.world.getProfiler().pop();
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/BlockEntityRenderDispatcher;render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"))
	public void interceptRender(BlockEntityRenderDispatcher dispatcher, BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vcp) {
		this.world.getProfiler().push(Objects.requireNonNull(RegistryKeys.BLOCK_ENTITY_TYPE.getRegistry().toString()));
		dispatcher.render(blockEntity, tickDelta, matrices, vcp);
		this.world.getProfiler().pop();
	}
}
