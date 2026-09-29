package com.blamejared.the_breach.neoforge.data.tags;

import com.blamejared.the_breach.tags.TBTags;
import com.blamejared.the_breach.world.timeline.TBTimelines;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TimelineTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TBTimelineTagsProvider extends TimelineTagsProvider {
    
    public TBTimelineTagsProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, lookup);
    }
    
    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        
        tag(TBTags.Timelines.IN_THE_BREACH).add(TBTimelines.THE_BREACH);
        
    }
    
}
