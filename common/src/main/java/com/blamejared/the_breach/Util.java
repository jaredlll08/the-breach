package com.blamejared.the_breach;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;
import java.util.function.Predicate;

public class Util {
    
    @SuppressWarnings("unchecked")
    public static <T> T uncheck(final Object o) {
        
        return (T) o;
    }
    
    public static boolean checkNeighbors(Function<BlockPos, BlockState> blockGetter, BlockPos pos, Predicate<BlockState> predicate) {
        
        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        
        for(Direction direction : Direction.values()) {
            neighborPos.setWithOffset(pos, direction);
            if(predicate.test(blockGetter.apply(neighborPos))) {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isAdjacentToAir(Function<BlockPos, BlockState> blockGetter, BlockPos pos) {
        
        return checkNeighbors(blockGetter, pos, BlockBehaviour.BlockStateBase::isAir);
    }
    
    
    public static boolean isAdjacentTo(Function<BlockPos, BlockState> blockGetter, BlockPos pos, Block block) {
        
        return checkNeighbors(blockGetter, pos, blockState -> blockState.getBlock() == block);
    }
    
}
