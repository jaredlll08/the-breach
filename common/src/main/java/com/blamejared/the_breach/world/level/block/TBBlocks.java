package com.blamejared.the_breach.world.level.block;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Collection;

@AutoService(IRegister.class)
public class TBBlocks implements IRegister {
    
    private static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, TBConstants.MODID);
    public static final RegistryObject<Block> DISMAL = BLOCKS.block("dismal", Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .strength(0.1F, 0.0F)
    );
    
    public static final RegistryObject<Block> BRIGHT = BLOCKS.block("bright", Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .lightLevel(_ -> 5)
                    .strength(1F, 0.0F)
    );
    
    public static final RegistryObject<Block> AMBIANT = BLOCKS.block("ambiant", AmbientBlock::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.TUFF)
                    .randomTicks()
                    .strength(0.1F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_IRON_ORE = BLOCKS.block("dismal_iron_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_GOLD_ORE = BLOCKS.block("dismal_gold_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_DIAMOND_ORE = BLOCKS.block("dismal_diamond_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_EMERALD_ORE = BLOCKS.block("dismal_emerald_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_COPPER_ORE = BLOCKS.block("dismal_copper_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> DISMAL_REDSTONE_ORE = BLOCKS.block("dismal_redstone_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    public static final RegistryObject<Block> DISMAL_COAL_ORE = BLOCKS.block("dismal_coal_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    public static final RegistryObject<Block> DISMAL_LAPIS_ORE = BLOCKS.block("dismal_lapis_ore", BrightSpreadingBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_GRAY)
                    .sound(SoundType.TUFF) //TODO
                    .requiresCorrectToolForDrops()
                    .strength(3F, 0.0F)
    );
    
    public static final RegistryObject<Block> BRIGHT_SPREAD = BLOCKS.block(
            "bright_spread",
            BrightSpread::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GLOW_LICHEN)
                    .replaceable()
                    .noCollision()
                    .strength(0F)
                    .sound(SoundType.GLOW_LICHEN)
                    .lightLevel(BrightSpread.emission(3))
                    .pushReaction(PushReaction.DESTROY)
    );
    
    public static final RegistryObject<Block> BREACH_PORTAL = BLOCKS.block(
            "breach_portal",
            BreachPortalBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .randomTicks()
                    .strength(-1.0F)
                    .sound(SoundType.GLASS)
                    .lightLevel(_ -> 5)
                    .pushReaction(PushReaction.BLOCK)
    );
    
    public static Collection<RegistryObject<Block>> getEntries() {
        
        return BLOCKS.getEntries();
    }
    
}
