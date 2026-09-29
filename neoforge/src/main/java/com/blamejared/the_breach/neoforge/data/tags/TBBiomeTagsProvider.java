package com.blamejared.the_breach.neoforge.data.tags;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.tags.TBTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;

import java.util.concurrent.CompletableFuture;

public class TBBiomeTagsProvider extends BiomeTagsProvider {
    
    public TBBiomeTagsProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, lookup, TBConstants.MODID);
    }
    
    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        
        tag(TBTags.Biomes.HAS_BREACH_PORTAL).addTag(BiomeTags.IS_OVERWORLD);
    }
    
}
