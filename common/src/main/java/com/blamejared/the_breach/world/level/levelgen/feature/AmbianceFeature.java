package com.blamejared.the_breach.world.level.levelgen.feature;

import com.blamejared.the_breach.world.level.block.TBBlocks;
import com.blamejared.the_breach.world.level.levelgen.feature.configurations.AmbianceConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Collection;

public class AmbianceFeature extends Feature<AmbianceConfiguration> {
    
    public AmbianceFeature(Codec<AmbianceConfiguration> codec) {
        
        super(codec);
    }
    
    @Override
    public boolean place(FeaturePlaceContext<AmbianceConfiguration> context) {
        
        boolean placed = false;
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        AmbianceConfiguration config = context.config();
        BlockPos origin = context.origin();
        
        Iterable<BlockPos> potentials = BlockPos.randomInCube(random, 8, origin, 16);
        for(BlockPos working : potentials) {
            if(!isAirOrWater(level.getBlockState(working))) {
                return false;
            }
            Collection<SearchArea> areas = SearchArea.allShuffled(random);
            BlockPos.MutableBlockPos pos = origin.mutable();
            step:
            for(Integer step : config.searchAreas()) {
                for(SearchArea area : areas) {
                    pos.set(origin);
                    area.movePos(pos, 1 + step);
                    if(!isAirOrWater(level.getBlockState(pos)) && !isAdjacentToAir(level::getBlockState, pos)) {
                        level.setBlock(pos, TBBlocks.AMBIANT.get().defaultBlockState(), Block.UPDATE_CLIENTS);
                        placed = true;
                        break step;
                    }
                }
            }
        }
        
        
        return placed;
    }
    
    private static boolean isAirOrWater(BlockState state) {
        
        return state.isAir() || state.is(Blocks.WATER);
    }
    
    
    private static enum SearchArea {
        NORTH_UP(Direction.NORTH, Direction.UP),
        NORTH(Direction.NORTH),
        NORTH_DOWN(Direction.NORTH, Direction.DOWN),
        
        NORTH_EAST_UP(Direction.NORTH, Direction.EAST, Direction.UP),
        NORTH_EAST(Direction.NORTH, Direction.EAST),
        NORTH_EAST_DOWN(Direction.NORTH, Direction.EAST, Direction.DOWN),
        
        NORTH_WEST_UP(Direction.NORTH, Direction.WEST, Direction.UP),
        NORTH_WEST(Direction.NORTH, Direction.WEST),
        NORTH_WEST_DOWN(Direction.NORTH, Direction.WEST, Direction.DOWN),
        
        SOUTH_UP(Direction.SOUTH, Direction.UP),
        SOUTH(Direction.SOUTH),
        SOUTH_DOWN(Direction.SOUTH, Direction.DOWN),
        
        SOUTH_EAST_UP(Direction.SOUTH, Direction.EAST, Direction.UP),
        SOUTH_EAST(Direction.SOUTH, Direction.EAST),
        SOUTH_EAST_DOWN(Direction.SOUTH, Direction.EAST, Direction.DOWN),
        
        SOUTH_WEST_UP(Direction.SOUTH, Direction.WEST, Direction.UP),
        SOUTH_WEST(Direction.SOUTH, Direction.WEST),
        SOUTH_WEST_DOWN(Direction.SOUTH, Direction.WEST, Direction.DOWN),
        
        EAST_UP(Direction.EAST, Direction.UP),
        EAST(Direction.EAST),
        EAST_DOWN(Direction.EAST, Direction.DOWN),
        
        WEST_UP(Direction.WEST, Direction.UP),
        WEST(Direction.WEST),
        WEST_DOWN(Direction.WEST, Direction.DOWN),
        ;
        
        private final Direction[] directions;
        
        SearchArea(Direction... directions) {
            
            this.directions = directions;
        }
        
        public static Collection<SearchArea> allShuffled(RandomSource random) {
            
            return Util.shuffledCopy(values(), random);
        }
        
        public void movePos(BlockPos.MutableBlockPos pos, int step) {
            
            for(Direction direction : this.directions) {
                pos.move(direction, step);
            }
        }
    }
    
}
