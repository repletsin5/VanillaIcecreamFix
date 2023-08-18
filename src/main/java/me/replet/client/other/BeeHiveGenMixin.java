package me.replet.client.other;
/*
import com.mojang.serialization.Codec;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import  net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(BeehiveTreeDecorator.class)
public abstract class BeeHiveGenMixin  extends TreeDecorator{
    @Final
    @Shadow public static Codec<BeehiveTreeDecorator> CODEC;
    @Final
    @Shadow private static  Direction BEE_NEST_FACE;
    @Final
    @Shadow private static  Direction[] GENERATE_DIRECTIONS;
    @Final
    @Shadow private  float probability;

    // @author replet
    // @reason makes it easier to allow a random orientation of the beesnets.


    @Overwrite
    public void generate(TreeDecorator.Generator generator){
        Random random = generator.getRandom();
        if (!(random.nextFloat() >= this.probability)) {
            List<BlockPos> list = generator.getLeavesPositions();
            List<BlockPos> list2 = generator.getLogPositions();
            int i = !list.isEmpty() ? Math.max(((BlockPos)list.get(0)).getY() - 1, (list2.get(0)).getY() + 1) : Math.min((list2.get(0)).getY() + 1 + random.nextInt(3), ((BlockPos)list2.get(list2.size() - 1)).getY());
            List<BlockPos> list3 = (List)list2.stream().filter((pos) -> {
                return pos.getY() == i;
            }).flatMap((pos) -> {
                Stream var10000 = Stream.of(GENERATE_DIRECTIONS);
                Objects.requireNonNull(pos);
                return var10000.map(a -> {
                    LogManager.getLogger("VanillIcecreamFix").warn(a.getClass().getName());
                    return pos.offset((Direction)a);
                });
            }).collect(Collectors.toList());
            if (!list3.isEmpty()) {

                int b = random.nextBetween(0,3);
                BlockPos pos = (BlockPos) list3.stream().filter((pos1)->generator.isAir(pos1));
                switch (b) {
                    case 0:
                        generator.replace(pos,  Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, Direction.NORTH));
                        break;
                    case 1:
                        generator.replace(pos, Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING,  Direction.SOUTH));
                        break;
                    case 2:
                        generator.replace(pos, Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING,  Direction.EAST));

                        break;
                    case 3:
                        generator.replace(pos, Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING,  Direction.WEST));

                        break;
                }


                Collections.shuffle(list3);
                Optional<BlockPos> optional = list3.stream().filter((pos1) -> generator.isAir(pos1) && generator.isAir(pos1.offset(BEE_NEST_FACE))).findFirst();



                    generator.getWorld().getBlockEntity(optional.get(), BlockEntityType.BEEHIVE).ifPresent((blockEntity) -> {

                        for(int j = 0; j < b; ++j) {
                            NbtCompound nbtCompound = new NbtCompound();
                            nbtCompound.putString("id", Registry.ENTITY_TYPE.getId(EntityType.BEE).toString());
                            blockEntity.addBee(nbtCompound, random.nextInt(599), false);
                        }

                    });
                }

        }
    }


}
*/