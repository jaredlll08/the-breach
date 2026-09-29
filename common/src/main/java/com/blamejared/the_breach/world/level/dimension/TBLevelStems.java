package com.blamejared.the_breach.world.level.dimension;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.level.biome.TBBiomes;
import com.blamejared.the_breach.world.level.levelgen.TBNoiseGeneratorSettings;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class TBLevelStems {
    
    public static final ResourceKey<LevelStem> THE_BREACH = TBConstants.rc(Registries.LEVEL_STEM, "the_breach");
    public static final ResourceKey<Level> THE_BREACH_LEVEL = ResourceKey.create(Registries.DIMENSION, TBConstants.id("the_breach"));
    
    public static void bootstrap(final BootstrapContext<LevelStem> context) {
        
        HolderGetter<DimensionType> dimensions = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<NoiseGeneratorSettings> noiseSettings = context.lookup(Registries.NOISE_SETTINGS);
        
        Holder.Reference<DimensionType> theBreachDimensionType = dimensions.getOrThrow(TBDimensionTypes.THE_BREACH);
        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(new FixedBiomeSource(biomes.getOrThrow(TBBiomes.THE_BREACH)), noiseSettings.getOrThrow(TBNoiseGeneratorSettings.THE_BREACH));
        context.register(THE_BREACH, new LevelStem(theBreachDimensionType, noiseBasedChunkGenerator));
    }
    
}
