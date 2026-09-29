package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.item.MumblesItem;
import com.blamejared.the_breach.world.item.TBItems;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TBLangProvider extends LanguageProvider {
    
    public TBLangProvider(final PackOutput output) {
        
        super(output, TBConstants.MODID, "en_us");
    }
    
    @Override
    protected void addTranslations() {
        
        this.add("itemGroup." + TBConstants.MODID, "The Breach");
        
        this.addBlock(TBBlocks.BREACH_PORTAL, "Breach Portal");
        this.blockItem(TBBlocks.DISMAL, TBItems.DISMAL, "Dismal");
        this.blockItem(TBBlocks.BRIGHT, TBItems.BRIGHT, "Bright");
        
        this.addBlock(TBBlocks.AMBIANT, "Ambiant");
        
        this.blockItem(TBBlocks.DISMAL_IRON_ORE, TBItems.DISMAL_IRON_ORE, "Dismal Iron Ore");
        this.blockItem(TBBlocks.DISMAL_COPPER_ORE, TBItems.DISMAL_COPPER_ORE, "Dismal Copper Ore");
        this.blockItem(TBBlocks.DISMAL_DIAMOND_ORE, TBItems.DISMAL_DIAMOND_ORE, "Dismal Diamond Ore");
        this.blockItem(TBBlocks.DISMAL_EMERALD_ORE, TBItems.DISMAL_EMERALD_ORE, "Dismal Emerald Ore");
        this.blockItem(TBBlocks.DISMAL_GOLD_ORE, TBItems.DISMAL_GOLD_ORE, "Dismal Gold Ore");
        this.blockItem(TBBlocks.DISMAL_REDSTONE_ORE, TBItems.DISMAL_REDSTONE_ORE, "Dismal Redstone Ore");
        this.blockItem(TBBlocks.DISMAL_COAL_ORE, TBItems.DISMAL_COAL_ORE, "Dismal Coal Ore");
        this.blockItem(TBBlocks.DISMAL_LAPIS_ORE, TBItems.DISMAL_LAPIS_ORE, "Dismal Lapis Ore");
        this.blockItem(TBBlocks.BRIGHT_SPREAD, TBItems.BRIGHT_SPREAD, "Bright Spread");
        
        this.addItem(TBItems.BLEAK_SPAWN_EGG, "Bleak Spawn Egg");
        this.addItem(TBItems.MUMBLES, "Bleak Mumbles");
        
        IntStream.range(0, MumblesItem.MUMBLES.size()).forEach(value -> {
            this.add(TBConstants.sid("bleak.mumble." + value), MumblesItem.MUMBLES.get(value));
        });
        this.add(TBConstants.sid("item.mumble.lore"), "What were they mumbling about...");
    }
    
    
    private void blockItem(Supplier<? extends Block> block, Supplier<? extends Item> item, String name) {
        
        this.addBlock(block, name);
        this.addItem(item, name);
    }
    
}
