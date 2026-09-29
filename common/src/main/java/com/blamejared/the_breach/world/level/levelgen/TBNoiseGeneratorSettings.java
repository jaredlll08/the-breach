package com.blamejared.the_breach.world.level.levelgen;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.mixin.access.AccessNoiseRouterData;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;

import java.util.List;

public class TBNoiseGeneratorSettings {
    
    public static final ResourceKey<NoiseGeneratorSettings> THE_BREACH = TBConstants.rc(Registries.NOISE_SETTINGS, "the_breach");
    
    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> context) {
        
        context.register(THE_BREACH, new NoiseGeneratorSettings(
                NoiseSettings.create(-64, 384, 1, 2),
                TBBlocks.DISMAL.get().defaultBlockState(),
                Blocks.TNT.defaultBlockState(),
                
                AccessNoiseRouterData.callNether(context.lookup(Registries.DENSITY_FUNCTION), context.lookup(Registries.NOISE)),
                SurfaceRules.state(TBBlocks.DISMAL.get().defaultBlockState()),
                List.of(),
                0,
                false,
                false,
                false,
                true
        ));
    }
    
}
