package com.blamejared.the_breach.world.level.block;

import com.blamejared.the_breach.tags.TBTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;

public class BrightSpreadingBlock extends Block {
    
    public BrightSpreadingBlock(Properties properties) {
        
        super(properties);
    }
    
    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        
        if(level instanceof ServerLevel sl) {
            RandomSource random = level.getRandom();
            Set<BlockPos> candidates = new HashSet<>();
            for(Direction direct : Util.shuffledCopy(Direction.values(), random)) {
                BlockPos directPos = pos.relative(direct, random.nextInt(1, 4));
                if(level.getBlockState(directPos).is(TBTags.Blocks.BRIGHT_CAN_SPREAD_ON)) {
                    candidates.add(directPos);
                    for(Direction adj : Util.shuffledCopy(Direction.values(), random)) {
                        BlockPos relativePos = directPos.relative(adj, random.nextInt(1, 4));
                        if(level.getBlockState(relativePos).is(TBTags.Blocks.BRIGHT_CAN_SPREAD_ON)) {
                            candidates.add(relativePos);
                        }
                    }
                }
            }
            
            for(BlockPos candidate : candidates) {
                if(random.nextDouble() >= 0.25) {
                    continue;
                }
                for(Direction dir : Util.shuffledCopy(Direction.values(), random)) {
                    
                    BlockPos neighbourPos = candidate.relative(dir);
                    BlockState neighbourState = level.getBlockState(neighbourPos);
                    Direction opposite = dir.getOpposite();
                    if(neighbourState.isAir()) {
                        neighbourState = TBBlocks.BRIGHT_SPREAD.get().defaultBlockState();
                    } else if(neighbourState.is(Blocks.WATER) && neighbourState.getFluidState().isSource()) {
                        neighbourState = TBBlocks.BRIGHT_SPREAD.get().defaultBlockState()
                                .setValue(MultifaceBlock.WATERLOGGED, true);
                    }
                    if(neighbourState.is(TBBlocks.BRIGHT_SPREAD.get()) && !MultifaceBlock.hasFace(neighbourState, opposite)) {
                        level.playSound(null, neighbourPos, SoundEvents.BUBBLE_POP, SoundSource.BLOCKS, 1.0F, 1.0F);
                        level.gameEvent(GameEvent.BLOCK_PLACE, neighbourPos, GameEvent.Context.of(state));
                        Vec3 center = neighbourPos.getCenter();
                        sl.sendParticles(ParticleTypes.BUBBLE_POP, false, true, center.x(), center.y(), center.z(), 1, 0, 0.04, 0, 0);
                        level.setBlock(neighbourPos, neighbourState.setValue(MultifaceBlock.getFaceProperty(opposite), true), Block.UPDATE_ALL);
                    }
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
    
}
