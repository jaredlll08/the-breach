package com.blamejared.the_breach.world.level.levelgen.feature;

import com.blamejared.the_breach.world.level.block.TBBlocks;
import com.blamejared.the_breach.world.level.levelgen.feature.configurations.BreachPortalConfiguration;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.slf4j.Logger;

import java.util.function.Predicate;

public class BreachPortalRoomFeature extends Feature<BreachPortalConfiguration> {
    
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();
    
    public BreachPortalRoomFeature(Codec<BreachPortalConfiguration> codec) {
        
        super(codec);
    }
    
    @Override
    public boolean place(FeaturePlaceContext<BreachPortalConfiguration> context) {
        
        Predicate<BlockState> replaceableTag = Feature.isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        int hr = 3;
        int xr = 4;
        int minX = -xr - 1;
        int maxX = xr + 2;
        int minY = -1;
        int maxY = 4;
        int zr = 4;
        int minZ = -zr - 1;
        int maxZ = zr + 2;
        int holeCount = 0;
        
        for(int dx = minX; dx <= maxX; dx++) {
            for(int dy = minY; dy <= maxY; dy++) {
                for(int dz = minZ; dz <= maxZ; dz++) {
                    BlockPos holePos = origin.offset(dx, dy, dz);
                    boolean solid = level.getBlockState(holePos).isSolid();
                    if(dy == minY && !solid) {
                        return false;
                    }
                    
                    if(dy == maxY && !solid) {
                        return false;
                    }
                    
                    if((dx == minX || dx == maxX || dz == minZ || dz == maxZ) && dy == 0 && level.isEmptyBlock(holePos) && level.isEmptyBlock(holePos.above())) {
                        holeCount++;
                    }
                }
            }
        }
        
        if(holeCount >= 1 && holeCount <= 5) {
            for(int dx = minX; dx <= maxX; dx++) {
                for(int dy = hr; dy >= -1; dy--) {
                    for(int dz = minZ; dz <= maxZ; dz++) {
                        BlockPos wallBlock = origin.offset(dx, dy, dz);
                        BlockState wallState = level.getBlockState(wallBlock);
                        if(dx == minX || dy == -1 || dz == minZ || dx == maxX || dy == 4 || dz == maxZ) {
                            if(wallBlock.getY() >= level.getMinY() && !level.getBlockState(wallBlock.below())
                                    .isSolid()) {
                                level.setBlock(wallBlock, AIR, Block.UPDATE_CLIENTS);
                            } else if(wallState.isSolid()) {
                                this.safeSetBlock(level, wallBlock, TBBlocks.DISMAL.get()
                                        .defaultBlockState(), replaceableTag);
                            }
                        } else {
                            this.safeSetBlock(level, wallBlock, AIR, replaceableTag);
                        }
                    }
                }
            }
            
            if(context.config().complete()) {
                this.safeSetBlock(level, origin, TBBlocks.BREACH_PORTAL.get().defaultBlockState(), replaceableTag);
                this.safeSetBlock(level, origin.east(), TBBlocks.BREACH_PORTAL.get()
                        .defaultBlockState(), replaceableTag);
                this.safeSetBlock(level, origin.south(), TBBlocks.BREACH_PORTAL.get()
                        .defaultBlockState(), replaceableTag);
                this.safeSetBlock(level, origin.south().east(), TBBlocks.BREACH_PORTAL.get()
                        .defaultBlockState(), replaceableTag);
            }
            
            this.safeSetBlock(level, origin.north(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.east().north(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.west(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.west().south(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.east().east(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.east().east().south(), TBBlocks.BRIGHT.get()
                    .defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.east().south().south(), TBBlocks.BRIGHT.get()
                    .defaultBlockState(), replaceableTag);
            this.safeSetBlock(level, origin.south().south(), TBBlocks.BRIGHT.get().defaultBlockState(), replaceableTag);
            return true;
        } else {
            return false;
        }
    }
    
}
