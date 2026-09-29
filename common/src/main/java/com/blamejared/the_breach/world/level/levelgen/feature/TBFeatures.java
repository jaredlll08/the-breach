package com.blamejared.the_breach.world.level.levelgen.feature;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import com.blamejared.the_breach.world.level.levelgen.feature.configurations.AmbianceConfiguration;
import com.blamejared.the_breach.world.level.levelgen.feature.configurations.BreachPortalConfiguration;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

@AutoService(IRegister.class)
public class TBFeatures implements IRegister {
    
    private static final RegistrationProvider<Feature<?>> FEATURES = RegistrationProvider.get(Registries.FEATURE, TBConstants.MODID);
    
    public static final RegistryObject<Feature<AmbianceConfiguration>> AMBIANCE_FEATURE = FEATURES.register("dismal_ambiance", () -> new AmbianceFeature(AmbianceConfiguration.CODEC));
    public static final RegistryObject<Feature<BreachPortalConfiguration>> BREACH_PORTAL_FEATURE = FEATURES.register("breach_room", () -> new BreachPortalRoomFeature(BreachPortalConfiguration.CODEC));
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISMAL_AMBIANCE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "dismal_ambiance");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BREACH_PORTAL = TBConstants.rc(Registries.CONFIGURED_FEATURE, "breach_portal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BREACH_PORTAL_FRAME = TBConstants.rc(Registries.CONFIGURED_FEATURE, "breach_portal_frame");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIGHT_TREE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "bright_tree");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_IRON_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_COPPER_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_DIAMOND_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "diamond_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_EMERALD_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_GOLD_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_REDSTONE_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "redstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_COAL_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THE_BREACH_LAPIS_ORE = TBConstants.rc(Registries.CONFIGURED_FEATURE, "lapis_ore");
    
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        
        RuleTest dismalReplaceable = new BlockMatchTest(TBBlocks.DISMAL.get());
        
        FeatureUtils.register(context, BRIGHT_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(TBBlocks.BRIGHT.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(TBBlocks.BRIGHT.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        )
                .ignoreVines()
                .belowTrunkProvider(BlockStateProvider.simple(TBBlocks.DISMAL.get()))
                .build());
        FeatureUtils.register(context, THE_BREACH_IRON_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_IRON_ORE.get()
                .defaultBlockState(), 4));
        FeatureUtils.register(context, THE_BREACH_GOLD_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_GOLD_ORE.get()
                .defaultBlockState(), 9));
        FeatureUtils.register(context, THE_BREACH_COPPER_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_COPPER_ORE.get()
                .defaultBlockState(), 10));
        FeatureUtils.register(context, THE_BREACH_DIAMOND_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_DIAMOND_ORE.get()
                .defaultBlockState(), 4));
        FeatureUtils.register(context, THE_BREACH_EMERALD_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_EMERALD_ORE.get()
                .defaultBlockState(), 3));
        FeatureUtils.register(context, THE_BREACH_REDSTONE_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_REDSTONE_ORE.get()
                .defaultBlockState(), 8));
        FeatureUtils.register(context, THE_BREACH_COAL_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_COAL_ORE.get()
                .defaultBlockState(), 17));
        FeatureUtils.register(context, THE_BREACH_LAPIS_ORE, Feature.ORE, new OreConfiguration(dismalReplaceable, TBBlocks.DISMAL_LAPIS_ORE.get()
                .defaultBlockState(), 7));
        
        FeatureUtils.register(
                context,
                DISMAL_AMBIANCE,
                AMBIANCE_FEATURE.get(),
                new AmbianceConfiguration(
                        List.of(
                                AmbianceConfiguration.target(dismalReplaceable, Blocks.ACACIA_TRAPDOOR.defaultBlockState())
                        ),
                        List.of(2, 4)
                )
        );
        FeatureUtils.register(context, BREACH_PORTAL, TBFeatures.BREACH_PORTAL_FEATURE.get(), new BreachPortalConfiguration(true));
        FeatureUtils.register(context, BREACH_PORTAL_FRAME, TBFeatures.BREACH_PORTAL_FEATURE.get(), new BreachPortalConfiguration(false));
        
    }
    
}
