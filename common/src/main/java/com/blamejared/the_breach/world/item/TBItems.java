package com.blamejared.the_breach.world.item;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.blamejared.the_breach.platform.Services;
import com.blamejared.the_breach.world.entity.TBEntityTypes;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import com.google.auto.service.AutoService;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ItemLore;

import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

@AutoService(IRegister.class)
public class TBItems implements IRegister {
    
    private static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, TBConstants.MODID);
    private static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, TBConstants.MODID);
    
    public static final RegistryObject<CreativeModeTab> TAB = TABS.register(TBConstants.MODID, () -> Services.PLATFORM.creativeTabBuilder()
            .displayItems((_, output) -> TBItems.registerCreativeTabs(output))
            .icon(() -> TBItems.DISMAL.get().getDefaultInstance())
            .title(Component.translatable("itemGroup." + TBConstants.MODID))
            .build());
    
    public static final RegistryObject<BlockItem> DISMAL = ITEMS.blockItem("dismal", TBBlocks.DISMAL, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> BRIGHT = ITEMS.blockItem("bright", TBBlocks.BRIGHT, UnaryOperator.identity());
    
    public static final RegistryObject<BlockItem> DISMAL_IRON_ORE = ITEMS.blockItem("dismal_iron_ore", TBBlocks.DISMAL_IRON_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_COPPER_ORE = ITEMS.blockItem("dismal_copper_ore", TBBlocks.DISMAL_COPPER_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_DIAMOND_ORE = ITEMS.blockItem("dismal_diamond_ore", TBBlocks.DISMAL_DIAMOND_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_EMERALD_ORE = ITEMS.blockItem("dismal_emerald_ore", TBBlocks.DISMAL_EMERALD_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_GOLD_ORE = ITEMS.blockItem("dismal_gold_ore", TBBlocks.DISMAL_GOLD_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_REDSTONE_ORE = ITEMS.blockItem("dismal_redstone_ore", TBBlocks.DISMAL_REDSTONE_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_COAL_ORE = ITEMS.blockItem("dismal_coal_ore", TBBlocks.DISMAL_COAL_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> DISMAL_LAPIS_ORE = ITEMS.blockItem("dismal_lapis_ore", TBBlocks.DISMAL_LAPIS_ORE, UnaryOperator.identity());
    public static final RegistryObject<BlockItem> BRIGHT_SPREAD = ITEMS.blockItem("bright_spread", TBBlocks.BRIGHT_SPREAD, UnaryOperator.identity());
    
    public static final RegistryObject<Item> BLEAK_SPAWN_EGG = ITEMS.item("bleak_spawn_egg", SpawnEggItem::new, () -> new Item.Properties().spawnEgg(TBEntityTypes.BLEAK.get()));
    
    public static final RegistryObject<Item> MUMBLES = ITEMS.item("mumbles", MumblesItem::new, () -> new Item.Properties().component(DataComponents.LORE, new ItemLore(List.of(Component.translatable(TBConstants.sid("bleak.mumble.lore"))))));
    
    public static void registerCreativeTabs(final CreativeModeTab.Output registrar) {
        
        registrar.accept(TBItems.DISMAL.get());
        registrar.accept(TBItems.BRIGHT.get());
        registrar.accept(TBItems.DISMAL_IRON_ORE.get());
        registrar.accept(TBItems.DISMAL_COPPER_ORE.get());
        registrar.accept(TBItems.DISMAL_DIAMOND_ORE.get());
        registrar.accept(TBItems.DISMAL_EMERALD_ORE.get());
        registrar.accept(TBItems.DISMAL_GOLD_ORE.get());
        registrar.accept(TBItems.DISMAL_REDSTONE_ORE.get());
        registrar.accept(TBItems.DISMAL_COAL_ORE.get());
        registrar.accept(TBItems.DISMAL_LAPIS_ORE.get());
        registrar.accept(TBItems.BLEAK_SPAWN_EGG.get());
        registrar.accept(TBItems.BRIGHT_SPREAD.get());
        registrar.accept(TBItems.MUMBLES.get());
    }
    
    
    public static Collection<RegistryObject<Item>> getEntries() {
        
        return ITEMS.getEntries();
    }
    
}
