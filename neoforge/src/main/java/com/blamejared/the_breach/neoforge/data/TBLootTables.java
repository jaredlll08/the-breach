package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.neoforge.data.loot.TBBlockLoot;
import com.blamejared.the_breach.neoforge.data.loot.TBEntityLoot;
import com.blamejared.the_breach.world.level.storage.loot.TBLoot;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TBLootTables extends LootTableProvider {
    
    public TBLootTables(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookup) {
        
        super(output, TBLoot.getEntries(), List.of(
                new SubProviderEntry(TBBlockLoot::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(TBEntityLoot::new, LootContextParamSets.ENTITY)
        ), lookup);
    }
    
}
