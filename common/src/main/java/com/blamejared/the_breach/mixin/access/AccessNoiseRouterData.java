package com.blamejared.the_breach.mixin.access;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.gen.Invoker;

@org.spongepowered.asm.mixin.Mixin(net.minecraft.world.level.levelgen.NoiseRouterData.class)
public interface AccessNoiseRouterData {
    
    @Invoker
    static NoiseRouter callOverworld(
            HolderGetter<DensityFunction> functions, HolderGetter<NormalNoise.NoiseParameters> noises, boolean largeBiomes, boolean amplified
    ) {throw new UnsupportedOperationException();}
    
    @Invoker
    static NoiseRouter callNether(HolderGetter<DensityFunction> functions, HolderGetter<NormalNoise.NoiseParameters> noises) {throw new UnsupportedOperationException();}
    
}
