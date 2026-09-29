package com.blamejared.the_breach.world.timeline;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.clock.TBWorldClocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.EasingType;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.timeline.Timeline;

import java.util.List;

public final class TBTimelines {
    
    public static final ResourceKey<Timeline> THE_BREACH = TBConstants.rc(Registries.TIMELINE, "the_breach");
    
    public static void bootstrap(final BootstrapContext<Timeline> context) {
        
        HolderGetter<WorldClock> clocks = context.lookup(Registries.WORLD_CLOCK);
        Holder.Reference<WorldClock> theBreachClock = clocks.getOrThrow(TBWorldClocks.THE_BREACH);
        context.register(THE_BREACH, Timeline.builder(theBreachClock)
                .setPeriodTicks(1200)
                .addTrack(EnvironmentAttributes.BLOCK_LIGHT_TINT, track -> {
                    int start = 0xff3da6e4;
                    int end = 0xff11567e;
                    track.setEasing(EasingType.IN_OUT_QUAD);
                    
                    int i = 0;
                    track.addKeyframe(i, start);
                    boolean in = false;
                    while(i < 1200) {
                        if(in) {
                            i += 40;
                            track.addKeyframe(i, start);
                        } else {
                            i += 60;
                            track.addKeyframe(i, end);
                        }
                        in = !in;
                    }
                })
                .addTrack(EnvironmentAttributes.FOG_END_DISTANCE, track -> {
                    track.setEasing(EasingType.IN_OUT_QUAD);
                    track.addKeyframe(0, 32f);
                    track.addKeyframe(400, 28f);
                    track.addKeyframe(600, 24f);
                    track.addKeyframe(800, 28f);
                    track.addKeyframe(1200, 32f);
                })
                
                .addTrack(EnvironmentAttributes.AMBIENT_PARTICLES, track -> {
                    track.addKeyframe(0, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.3f), new AmbientParticle(ParticleTypes.ASH, 0.15f)));
                    track.addKeyframe(200, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.15f), new AmbientParticle(ParticleTypes.ASH, 0.3f)));
                    track.addKeyframe(400, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.3f), new AmbientParticle(ParticleTypes.ASH, 0.15f)));
                    track.addKeyframe(600, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.15f), new AmbientParticle(ParticleTypes.ASH, 0.3f)));
                    track.addKeyframe(800, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.3f), new AmbientParticle(ParticleTypes.ASH, 0.15f)));
                    track.addKeyframe(1000, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.15f), new AmbientParticle(ParticleTypes.ASH, 0.3f)));
                    track.addKeyframe(1200, List.of(new AmbientParticle(ParticleTypes.MYCELIUM, 0.3f), new AmbientParticle(ParticleTypes.ASH, 0.15f)));
                })
                .build());
    }
    
}
