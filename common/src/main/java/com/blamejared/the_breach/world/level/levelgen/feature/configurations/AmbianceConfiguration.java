package com.blamejared.the_breach.world.level.levelgen.feature.configurations;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public record AmbianceConfiguration(List<TargetBlockState> targetStates,
                                    List<Integer> searchAreas) implements FeatureConfiguration {
    
    public static final Codec<AmbianceConfiguration> CODEC = RecordCodecBuilder.create(
            i -> i.group(
                            Codec.list(TargetBlockState.CODEC)
                                    .fieldOf("targets")
                                    .forGetter(c -> c.targetStates),
                            Codec.list(Codec.INT).fieldOf("search_areas").forGetter(c -> c.searchAreas)
                    )
                    .apply(i, AmbianceConfiguration::new)
    );
    
    public static TargetBlockState target(RuleTest rule, BlockState state) {
        
        return new TargetBlockState(rule, state);
    }
    
    public static class TargetBlockState {
        
        public static final Codec<TargetBlockState> CODEC = RecordCodecBuilder.create(
                i -> i.group(RuleTest.CODEC.fieldOf("target")
                                .forGetter(c -> c.target), BlockState.CODEC.fieldOf("state").forGetter(c -> c.state))
                        .apply(i, TargetBlockState::new)
        );
        public final RuleTest target;
        public final BlockState state;
        
        private TargetBlockState(RuleTest target, BlockState state) {
            
            this.target = target;
            this.state = state;
        }
        
    }
    
}
