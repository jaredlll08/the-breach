package com.blamejared.the_breach.neoforge.data.loot;

import com.blamejared.the_breach.world.level.block.TBBlocks;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;
import java.util.function.Supplier;

public class TBBlockLoot extends BlockLootSubProvider {
    
    private final Set<Block> knownBlocks = new ReferenceOpenHashSet<>();
    
    public TBBlockLoot(final HolderLookup.Provider provider) {
        
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }
    
    @Override
    protected void generate() {
        
        this.dropSelf(TBBlocks.BRIGHT.get());
        this.add(TBBlocks.BRIGHT_SPREAD.get(), this::createShearsOrSilkTouchOnlyDrop);
        this.dismalOre(TBBlocks.DISMAL_IRON_ORE, Items.RAW_IRON);
        this.dismalOre(TBBlocks.DISMAL_COPPER_ORE, Items.RAW_COPPER);
        this.dismalOre(TBBlocks.DISMAL_DIAMOND_ORE, Items.DIAMOND);
        this.dismalOre(TBBlocks.DISMAL_EMERALD_ORE, Items.EMERALD);
        this.dismalOre(TBBlocks.DISMAL_GOLD_ORE, Items.RAW_GOLD);
        this.dismalOre(TBBlocks.DISMAL_REDSTONE_ORE, Items.REDSTONE);
        this.dismalOre(TBBlocks.DISMAL_COAL_ORE, Items.COAL);
        this.dismalOre(TBBlocks.DISMAL_LAPIS_ORE, Items.LAPIS_LAZULI);
    }
    
    private void dismalOre(Supplier<Block> block, Item item) {
        
        this.add(block.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(item))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 4f)))
                ));
    }
    
    @Override
    protected void add(final Block block, final LootTable.Builder table) {
        
        super.add(block, table);
        knownBlocks.add(block);
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        
        return knownBlocks;
    }
    
}
