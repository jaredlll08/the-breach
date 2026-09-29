package com.blamejared.the_breach.world.level.block;

import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.world.entity.TBEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;

public class AmbientBlock extends Block {
    
    public AmbientBlock(Properties properties) {
        
        super(properties);
    }
    
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        
        super.randomTick(state, level, pos, random);
        if(random.nextInt(10) == 1) {
            switch(random.nextInt(4)) {
                case 0 ->
                        level.playSound(null, pos, SoundEvents.TUFF_STEP, SoundSource.AMBIENT, random.nextInt(5, 10), random.nextFloat());
                case 1 -> {
                    for(BlockPos blockPos : BlockPos.randomInCube(random, 64, pos, 6)) {
                        BlockState checking = level.getBlockState(blockPos);
                        if(checking.isAir() && Util.isAdjacentTo(level::getBlockState, blockPos, TBBlocks.DISMAL.get())) {
                            level.setBlock(blockPos, TBBlocks.DISMAL.get().defaultBlockState(), Block.UPDATE_CLIENTS);
                            break;
                        } else if(TBBlocks.DISMAL.get() == checking.getBlock() && Util.isAdjacentToAir(level::getBlockState, blockPos)) {
                            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                            level.levelEvent(null, LevelEvent.PARTICLES_DESTROY_BLOCK, blockPos, getId(checking));
                            break;
                        }
                    }
                }
                case 2 ->
                        level.playSound(null, pos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.AMBIENT, random.nextInt(5, 10), random.nextFloat());
                case 3 -> {
                    for(BlockPos blockPos : BlockPos.randomInCube(random, 64, pos, 6)) {
                        BlockState checking = level.getBlockState(blockPos);
                        if(checking.isAir() && level.getBlockState(blockPos.below()).is(TBBlocks.DISMAL.get())) {
                            TBEntityTypes.BLEAK.get().spawn(level, blockPos, EntitySpawnReason.SPAWNER);
                            break;
                        }
                    }
                }
            }
        }
    }
    
}
