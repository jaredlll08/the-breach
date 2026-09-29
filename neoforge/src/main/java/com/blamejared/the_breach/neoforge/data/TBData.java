package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.neoforge.data.tags.TBBiomeTagsProvider;
import com.blamejared.the_breach.neoforge.data.tags.TBBlockTagsProvider;
import com.blamejared.the_breach.neoforge.data.tags.TBTimelineTagsProvider;
import com.blamejared.the_breach.world.clock.TBWorldClocks;
import com.blamejared.the_breach.world.level.biome.TBBiomes;
import com.blamejared.the_breach.world.level.dimension.TBDimensionTypes;
import com.blamejared.the_breach.world.level.dimension.TBLevelStems;
import com.blamejared.the_breach.world.level.levelgen.TBNoiseGeneratorSettings;
import com.blamejared.the_breach.world.level.levelgen.feature.TBFeatures;
import com.blamejared.the_breach.world.level.levelgen.placement.TBPlacedFeatures;
import com.blamejared.the_breach.world.timeline.TBTimelines;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TBConstants.MODID)
public class TBData {
    
    private static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
            .add(Registries.DIMENSION_TYPE, TBDimensionTypes::bootstrap)
            .add(Registries.LEVEL_STEM, TBLevelStems::bootstrap)
            .add(Registries.NOISE_SETTINGS, TBNoiseGeneratorSettings::bootstrap)
            .add(Registries.BIOME, TBBiomes::bootstrap)
            .add(Registries.PLACED_FEATURE, TBPlacedFeatures::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, TBFeatures::bootstrap)
            .add(Registries.WORLD_CLOCK, TBWorldClocks::bootstrap)
            .add(Registries.TIMELINE, TBTimelines::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TBBiomeModifiers::bootstrap);
    
    @SubscribeEvent
    public static void gatherClient(final GatherDataEvent.Client event) {
        
        PackOutput output = event.getGenerator().getPackOutput();
        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = event.addProvider(new DatapackBuiltinEntriesProvider(output, event.getLookupProvider(), REGISTRIES, Set.of(TBConstants.MODID)));
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackBuiltinEntriesProvider.getRegistryProvider();
        
        event.addProvider(new TBTimelineTagsProvider(output, lookupProvider));
        event.addProvider(new TBBlockTagsProvider(output, lookupProvider));
        event.addProvider(new TBBiomeTagsProvider(output, lookupProvider));
        event.addProvider(new TBModelProvider(output));
        event.addProvider(new TBLangProvider(output));
        event.addProvider(new TBLootTables(output, lookupProvider));
        event.addProvider(new TBRecipeProvider.Runner(output, lookupProvider));
    }
    
}
