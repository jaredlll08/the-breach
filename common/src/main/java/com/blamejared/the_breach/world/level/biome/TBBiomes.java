package com.blamejared.the_breach.world.level.biome;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.world.attribute.TBEnvironmentAttributes;
import com.blamejared.the_breach.world.entity.TBEntityTypes;
import com.blamejared.the_breach.world.level.levelgen.placement.TBPlacedFeatures;
import com.google.auto.service.AutoService;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;

@AutoService(IRegister.class)
public class TBBiomes implements IRegister {
    
    public static final ResourceKey<Biome> THE_BREACH = TBConstants.rc(Registries.BIOME, "the_breach");
    
    public static void bootstrap(BootstrapContext<Biome> context) {
        
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        generationSettings.addCarver(Carvers.NETHER_CAVE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.BRIGHT_TREE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.BREACH_PORTAL_FRAME);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.DISMAL_AMBIANCE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_IRON_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_COPPER_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_DIAMOND_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_EMERALD_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_GOLD_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_REDSTONE_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_COAL_ORE);
        generationSettings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TBPlacedFeatures.THE_BREACH_LAPIS_ORE);
        
        Biome.BiomeBuilder builder = new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(1)
                .downfall(0)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x020202)
                .setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, 0x080808)
                .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, 0x3da6e4)
                .setAttribute(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES)
                .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 32f)
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.3f), new AmbientParticle(ParticleTypes.ASH, 0.15f)))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x030303)
                .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0f)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.EMPTY)
                .setAttribute(TBEnvironmentAttributes.EXHAUSTION_MODIFIER.get(), 2f)
                .setAttribute(TBEnvironmentAttributes.GRAVITY_MODIFIER.get(), 0.9f)
                
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xFF000000)
                        .grassColorOverride(0xFF000000)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(TBEntityTypes.BLEAK.get(), 4, 4))
                        .build())
                .generationSettings(generationSettings.build());
        
        context.register(THE_BREACH, builder.build());
    }
    
}
