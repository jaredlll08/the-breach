package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.tags.TBTags;
import com.blamejared.the_breach.world.level.levelgen.placement.TBPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class TBBiomeModifiers {
    
    public static final ResourceKey<BiomeModifier> ADD_FEATURE_BREACH_PORTAL = ResourceKey.create(
            NeoForgeRegistries.Keys.BIOME_MODIFIERS, // The registry this key is for
            TBConstants.id("add_feature_breach_portal")
    );
    
    public static void bootstrap(final BootstrapContext<BiomeModifier> context) {
        // Lookup any necessary registries.
        // Static registries only need to be looked up if you need to grab the tag data.
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        
        // Register the biome modifiers.
        context.register(ADD_FEATURE_BREACH_PORTAL,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        // The biome(s) to generate within
                        biomes.getOrThrow(TBTags.Biomes.HAS_BREACH_PORTAL),
                        // The feature(s) to generate within the biomes
                        HolderSet.direct(placedFeatures.getOrThrow(TBPlacedFeatures.BREACH_PORTAL)),
                        // The generation step
                        GenerationStep.Decoration.UNDERGROUND_STRUCTURES
                )
        );
    }
    
}
