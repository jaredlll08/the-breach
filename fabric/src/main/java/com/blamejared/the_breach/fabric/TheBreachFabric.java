package com.blamejared.the_breach.fabric;

import com.blamejared.the_breach.TheBreach;
import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.tags.TBTags;
import com.blamejared.the_breach.world.entity.TBSpawnPlacements;
import com.blamejared.the_breach.world.entity.ai.attributes.TBDefaultAttributes;
import com.blamejared.the_breach.world.level.levelgen.placement.TBPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TheBreachFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        TheBreach.init();
        
        TBDefaultAttributes.init(FabricDefaultAttributeRegistry::register);
        TBSpawnPlacements.init((entityType, data) -> SpawnPlacements.register(Util.uncheck(entityType), data.placement(), data.heightMap(), Util.uncheck(data.predicate())));
        BiomeModifications.addFeature(BiomeSelectors.tag(TBTags.Biomes.HAS_BREACH_PORTAL), GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TBPlacedFeatures.BREACH_PORTAL);
    }
    
}