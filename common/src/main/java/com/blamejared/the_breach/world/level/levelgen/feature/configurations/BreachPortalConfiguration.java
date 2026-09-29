package com.blamejared.the_breach.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record BreachPortalConfiguration(boolean complete) implements FeatureConfiguration {
    
    public static final Codec<BreachPortalConfiguration> CODEC = RecordCodecBuilder.create(
            i -> i.group(
                            Codec.BOOL.fieldOf("complete").forGetter(BreachPortalConfiguration::complete)
                    )
                    .apply(i, BreachPortalConfiguration::new)
    );
    
    
}
