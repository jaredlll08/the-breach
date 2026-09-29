package com.blamejared.the_breach.world.level.levelgen.placement;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.level.levelgen.feature.TBFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class TBPlacedFeatures {
    
    public static final ResourceKey<PlacedFeature> DISMAL_AMBIANCE = TBConstants.rc(Registries.PLACED_FEATURE, "dismal_ambiance");
    public static final ResourceKey<PlacedFeature> BREACH_PORTAL = TBConstants.rc(Registries.PLACED_FEATURE, "breach_portal");
    public static final ResourceKey<PlacedFeature> BREACH_PORTAL_FRAME = TBConstants.rc(Registries.PLACED_FEATURE, "breach_portal_frame");
    public static final ResourceKey<PlacedFeature> BRIGHT_TREE = TBConstants.rc(Registries.PLACED_FEATURE, "bright_tree");
    
    public static final ResourceKey<PlacedFeature> THE_BREACH_IRON_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "iron_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_COPPER_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "copper_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_DIAMOND_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "diamond_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_EMERALD_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "emerald_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_GOLD_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "gold_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_REDSTONE_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "redstone_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_COAL_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "coal_ore");
    public static final ResourceKey<PlacedFeature> THE_BREACH_LAPIS_ORE = TBConstants.rc(Registries.PLACED_FEATURE, "lapis_ore");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        
        HolderGetter<ConfiguredFeature<?, ?>> lookup = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(DISMAL_AMBIANCE, new PlacedFeature(lookup.getOrThrow(TBFeatures.DISMAL_AMBIANCE), List.of(CountPlacement.of(30), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()))));
        context.register(BREACH_PORTAL, new PlacedFeature(lookup.getOrThrow(TBFeatures.BREACH_PORTAL), List.of(CountPlacement.of(3), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()))));
        
        context.register(BREACH_PORTAL_FRAME, new PlacedFeature(lookup.getOrThrow(TBFeatures.BREACH_PORTAL_FRAME), List.of(CountPlacement.of(10), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()))));
        context.register(BRIGHT_TREE, new PlacedFeature(lookup.getOrThrow(TBFeatures.BRIGHT_TREE), List.of(RarityFilter.onAverageOnceEvery(90), CountOnEveryLayerPlacement.of(1))));
        context.register(THE_BREACH_IRON_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_IRON_ORE), List.of(CountPlacement.of(7), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_COAL_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_COAL_ORE), List.of(CountPlacement.of(25), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_GOLD_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_GOLD_ORE), List.of(CountPlacement.of(3), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_REDSTONE_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_REDSTONE_ORE), List.of(CountPlacement.of(3), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_DIAMOND_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_DIAMOND_ORE), List.of(CountPlacement.of(5), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_COPPER_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_COPPER_ORE), List.of(CountPlacement.of(13), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_LAPIS_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_LAPIS_ORE), List.of(CountPlacement.of(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        context.register(THE_BREACH_EMERALD_ORE, new PlacedFeature(lookup.getOrThrow(TBFeatures.THE_BREACH_EMERALD_ORE), List.of(CountPlacement.of(30), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()))));
        
    }
    
}
