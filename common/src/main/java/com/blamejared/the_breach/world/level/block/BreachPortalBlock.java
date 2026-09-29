package com.blamejared.the_breach.world.level.block;

import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import com.blamejared.the_breach.world.level.dimension.TBLevelStems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Set;

public class BreachPortalBlock extends Block implements Portal {
    
    private static @Nullable BlockPattern portalShape;
    
    public BreachPortalBlock(Properties properties) {
        
        super(properties);
    }
    
    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
        
        if(entity instanceof Bleak) {
            level.explode(entity, pos.getX(), pos.getY(), pos.getZ(), 3, Level.ExplosionInteraction.BLOCK);
            return;
        }
        if(entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }
    
    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        
        LevelData.RespawnData respawnData = currentLevel.getRespawnData();
        ResourceKey<Level> currentDimension = currentLevel.dimension();
        boolean fromBreach = currentDimension == TBLevelStems.THE_BREACH_LEVEL;
        ResourceKey<Level> newDimension = fromBreach ? respawnData.dimension() : TBLevelStems.THE_BREACH_LEVEL;
        ServerLevel newLevel = currentLevel.getServer().getLevel(newDimension);
        if(newLevel == null) {
            return null;
        }
        
        Vec3 spawnPos;
        float yRot;
        float xRot;
        Set<Relative> relatives;
        if(!fromBreach) {
            
            int fallbackHeight = 128;
            BlockPos.MutableBlockPos portalPos = portalEntryPos.mutable().setY(fallbackHeight);
            boolean found = false;
            boolean lastWasAir = false;
            while(!found && portalPos.getY() > newLevel.getMinY() + 20) {
                if(newLevel.getBlockState(portalPos.move(Direction.DOWN)).isAir()) {
                    lastWasAir = true;
                } else if(lastWasAir) {
                    lastWasAir = false;
                    found = true;
                }
            }
            if(!found) {
                portalPos.setY(fallbackHeight);
            }
            
            spawnPos = portalPos.getBottomCenter();
            createPlatform(newLevel, BlockPos.containing(spawnPos).below());
            yRot = Direction.WEST.toYRot();
            xRot = 0.0F;
            relatives = Relative.union(Relative.DELTA, Set.of(Relative.X_ROT));
            if(entity instanceof ServerPlayer) {
                spawnPos = spawnPos.subtract(0.0, 1.0, 0.0);
            }
        } else {
            yRot = respawnData.yaw();
            xRot = respawnData.pitch();
            relatives = Relative.union(Relative.DELTA, Relative.ROTATION);
            if(entity instanceof ServerPlayer serverPlayer) {
                return serverPlayer.findRespawnPositionAndUseSpawnBlock(false, TeleportTransition.DO_NOTHING);
            }
            
            spawnPos = entity.adjustSpawnLocation(newLevel, respawnData.pos()).getBottomCenter();
        }
        
        return new TeleportTransition(newLevel, spawnPos, Vec3.ZERO, yRot, xRot, relatives, TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET));
    }
    
    public static void createPlatform(ServerLevelAccessor newLevel, BlockPos origin) {
        
        BlockPos.MutableBlockPos pos = origin.mutable();
        
        int width = 2;
        for(int dz = -width; dz <= width; dz++) {
            for(int dx = -width; dx <= width; dx++) {
                for(int dy = -1; dy < 3; dy++) {
                    BlockPos blockPos = pos.set(origin).move(dx, dy, dz);
                    Block block = dy == -1 ? TBBlocks.DISMAL.get() : Blocks.AIR;
                    if(!newLevel.getBlockState(blockPos).is(block)) {
                        newLevel.setBlock(blockPos, block.defaultBlockState(), Block.UPDATE_ALL);
                    }
                }
            }
        }
    }
    
    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        
        //TODO this causes the portal to only be a single block in fabric, so fabric is disabled for now
        for(Direction direction : Direction.Plane.HORIZONTAL) {
            if(level.getBlockState(pos.relative(direction)).isAir()) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }
    
    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        
        return neighborState.is(this) || super.skipRendering(state, neighborState, direction);
    }
    
    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        
        return ItemStack.EMPTY;
    }
    
    @Override
    protected boolean canBeReplaced(BlockState state, Fluid fluid) {
        
        return false;
    }
    
    public static BlockPattern getOrCreatePortalShape() {
        
        if(portalShape == null) {
            portalShape = BlockPatternBuilder.start()
                    .aisle(
                            "?bb?",
                            "bssb",
                            "bssb",
                            "?bb?")
                    .where('?', BlockInWorld.hasState(BlockStatePredicate.ANY))
                    .where('b', BlockInWorld.hasState(BlockStatePredicate.forBlock(TBBlocks.BRIGHT.get())))
                    .where('s', BlockInWorld.hasState(BlockStatePredicate.forBlock(TBBlocks.BRIGHT_SPREAD.get())))
                    .build();
        }
        
        return portalShape;
    }
    
}
