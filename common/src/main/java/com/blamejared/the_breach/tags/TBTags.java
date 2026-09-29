package com.blamejared.the_breach.tags;

import com.blamejared.the_breach.TBConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.timeline.Timeline;

public class TBTags {
    
    public static class Biomes {
        
        public static final TagKey<Biome> HAS_BREACH_PORTAL = TBConstants.tk(Registries.BIOME, "has_breach_portal");
        
    }
    
    public static class Blocks {
        
        public static final TagKey<Block> INFINIBURN_THE_BREACH = TBConstants.tk(Registries.BLOCK, "infiniburn_the_breach");
        public static final TagKey<Block> BRIGHT_CAN_SPREAD_ON = TBConstants.tk(Registries.BLOCK, "bright_can_spread_on");
        
    }
    
    public static class Timelines {
        
        public static final TagKey<Timeline> IN_THE_BREACH = TBConstants.tk(Registries.TIMELINE, "in_the_breach");
        
    }
    
}
