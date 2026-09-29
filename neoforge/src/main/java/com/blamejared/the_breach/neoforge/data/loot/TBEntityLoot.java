package com.blamejared.the_breach.neoforge.data.loot;

import com.blamejared.the_breach.world.entity.TBEntityTypes;
import com.blamejared.the_breach.world.item.TBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.stream.Stream;

public class TBEntityLoot extends EntityLootSubProvider {
    
    public TBEntityLoot(final HolderLookup.Provider provider) {
        
        super(FeatureFlags.REGISTRY.allFlags(), provider);
    }
    
    @Override
    public void generate() {
        
        this.add(TBEntityTypes.BLEAK.get(), LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(
                                        LootItem.lootTableItem(TBItems.MUMBLES.get())
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                ).when(LootItemKilledByPlayerCondition.killedByPlayer())
                ));
    }
    
    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        
        return Stream.of(TBEntityTypes.BLEAK.get());
    }
    
}
