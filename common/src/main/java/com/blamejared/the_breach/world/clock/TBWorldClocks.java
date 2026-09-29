package com.blamejared.the_breach.world.clock;

import com.blamejared.the_breach.TBConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.clock.WorldClock;

public class TBWorldClocks {
    
    public static final ResourceKey<WorldClock> THE_BREACH = TBConstants.rc(Registries.WORLD_CLOCK, "the_breach");
    
    public static void bootstrap(BootstrapContext<WorldClock> context) {
        
        context.register(THE_BREACH, new WorldClock());
    }
    
}
