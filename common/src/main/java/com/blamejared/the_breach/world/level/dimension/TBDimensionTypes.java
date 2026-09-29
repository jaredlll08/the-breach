package com.blamejared.the_breach.world.level.dimension;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.tags.TBTags;
import com.blamejared.the_breach.world.clock.TBWorldClocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.timeline.Timeline;

import java.util.Optional;

public class TBDimensionTypes {
    
    public static final ResourceKey<DimensionType> THE_BREACH = TBConstants.rc(Registries.DIMENSION_TYPE, "the_breach");
    
    public static void bootstrap(final BootstrapContext<DimensionType> context) {
        
        HolderGetter<Timeline> timelines = context.lookup(Registries.TIMELINE);
        HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);
        context.register(THE_BREACH, new DimensionType(
                true,
                false,
                true,
                false,
                1,
                -64,
                384,
                384,
                TBTags.Blocks.INFINIBURN_THE_BREACH,
                0,
                new DimensionType.MonsterSettings(ConstantInt.of(15), 0),
                DimensionType.Skybox.NONE,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, -15199464)
                        .set(EnvironmentAttributes.SKY_LIGHT_COLOR, -5480243)
                        .set(EnvironmentAttributes.SKY_COLOR, -16777216)
                        .set(EnvironmentAttributes.SKY_LIGHT_FACTOR, 0.0F)
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -12630209)
                        .set(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.END))
                        .set(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
                        .set(EnvironmentAttributes.BED_RULE, BedRule.EXPLODES)
                        .set(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
                        .build(),
                timelines.getOrThrow(TBTags.Timelines.IN_THE_BREACH),
                Optional.of(clocks.getOrThrow(TBWorldClocks.THE_BREACH))
        ));
    }
    
}
