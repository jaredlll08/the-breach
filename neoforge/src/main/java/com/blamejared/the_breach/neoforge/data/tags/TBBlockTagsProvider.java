package com.blamejared.the_breach.neoforge.data.tags;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.tags.TBTags;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class TBBlockTagsProvider extends BlockTagsProvider {
    
    public TBBlockTagsProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, lookup, TBConstants.MODID);
    }
    
    @Override
    protected void addTags(final HolderLookup.Provider provider) {
        
        tag(TBTags.Blocks.BRIGHT_CAN_SPREAD_ON).add(
                TBBlocks.DISMAL.get(),
                TBBlocks.AMBIANT.get()
        );
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        TBBlocks.BRIGHT.get(),
                        TBBlocks.DISMAL_IRON_ORE.get(),
                        TBBlocks.DISMAL_COPPER_ORE.get(),
                        TBBlocks.DISMAL_DIAMOND_ORE.get(),
                        TBBlocks.DISMAL_EMERALD_ORE.get(),
                        TBBlocks.DISMAL_GOLD_ORE.get(),
                        TBBlocks.DISMAL_REDSTONE_ORE.get(),
                        TBBlocks.DISMAL_COAL_ORE.get(),
                        TBBlocks.DISMAL_LAPIS_ORE.get()
                );
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(TBBlocks.DISMAL_IRON_ORE.get(),
                        TBBlocks.DISMAL_LAPIS_ORE.get(),
                        TBBlocks.DISMAL_COPPER_ORE.get()
                );
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        TBBlocks.DISMAL_DIAMOND_ORE.get(),
                        TBBlocks.DISMAL_EMERALD_ORE.get(),
                        TBBlocks.DISMAL_GOLD_ORE.get(),
                        TBBlocks.DISMAL_REDSTONE_ORE.get()
                );
    }
    
}
