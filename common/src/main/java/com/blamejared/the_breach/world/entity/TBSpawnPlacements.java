package com.blamejared.the_breach.world.entity;

import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.BiConsumer;

public class TBSpawnPlacements {
    
    public static void init(final BiConsumer<EntityType<?>, Data> map) {
        
        TBSpawnPlacements.register(map, TBEntityTypes.BLEAK.get(), Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnPlacementTypes.ON_GROUND, Bleak::checkMobSpawnRules);
    }
    
    private static <T extends Entity> void register(final BiConsumer<EntityType<?>, Data> map, EntityType<T> type, Heightmap.Types heightMap, SpawnPlacementType placement, SpawnPlacements.SpawnPredicate<T> predicate) {
        
        map.accept(type, new Data(heightMap, placement, predicate));
    }
    
    public record Data(Heightmap.Types heightMap, SpawnPlacementType placement,
                       SpawnPlacements.SpawnPredicate<?> predicate) {}
    
}
