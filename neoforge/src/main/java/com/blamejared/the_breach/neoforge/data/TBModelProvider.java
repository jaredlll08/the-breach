package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.item.TBItems;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class TBModelProvider extends ModelProvider {
    
    public TBModelProvider(final PackOutput output) {
        
        super(output, TBConstants.MODID);
    }
    
    @Override
    protected void registerModels(final BlockModelGenerators blockModels, final ItemModelGenerators itemModels) {
        
        itemModels.generateFlatItem(TBItems.BLEAK_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        TexturedModel.Provider templateOre = block -> new TexturedModel(TextureMapping.defaultTexture(block), ModelTemplates.create(TBConstants.sid("template_ore"), TextureSlot.LAYER0, TextureSlot.LAYER1, TextureSlot.LAYER2, TextureSlot.PARTICLE)).updateTextures(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER0, new Material(TBConstants.id("block/dismal_base")));
            textureMapping.put(TextureSlot.PARTICLE, new Material(TBConstants.id("block/dismal_base")));
        });
        
        TexturedModel.Provider bright = block -> new TexturedModel(TextureMapping.defaultTexture(block), ModelTemplates.create(TBConstants.sid("template_bright"), TextureSlot.LAYER0, TextureSlot.PARTICLE)).updateTextures(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER0, new Material(TBConstants.id("block/bright")));
            textureMapping.put(TextureSlot.PARTICLE, new Material(TBConstants.id("block/bright")));
        });
        
        List<Variant> variantList = new ArrayList<>();
        
        blockModels.createTrivialCube(TBBlocks.AMBIANT.get());
        blockModels.createTrivialBlock(TBBlocks.BRIGHT.get(), bright);
        
        blockModels.createTrivialBlock(TBBlocks.DISMAL_IRON_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_IRON_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_IRON_ORE.get(), "_blur"));
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_COPPER_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_COPPER_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_COPPER_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_DIAMOND_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_DIAMOND_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_DIAMOND_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_EMERALD_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_EMERALD_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_EMERALD_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_GOLD_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_GOLD_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_GOLD_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_REDSTONE_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_REDSTONE_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_REDSTONE_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_COAL_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_COAL_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_COAL_ORE.get(), "_blur"));
            
        }));
        blockModels.createTrivialBlock(TBBlocks.DISMAL_LAPIS_ORE.get(), templateOre.updateTexture(textureMapping -> {
            textureMapping.put(TextureSlot.LAYER2, TextureMapping.getBlockTexture(TBBlocks.DISMAL_LAPIS_ORE.get()));
            textureMapping.put(TextureSlot.LAYER1, TextureMapping.getBlockTexture(TBBlocks.DISMAL_LAPIS_ORE.get(), "_blur"));
            
        }));
        
        TexturedModel.CUBE.updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, TextureMapping.getBlockTexture(TBBlocks.DISMAL.get(), "_base")))
                .create(TBBlocks.DISMAL.get(), blockModels.modelOutput);
        
        suffixedCube(TBBlocks.DISMAL, "_base", blockModels, TexturedModel.CUBE);
        IntStream.range(0, 5).forEach(i -> {
            String key = "_" + i;
            suffixedCube(TBBlocks.DISMAL, key, blockModels, TexturedModel.CUBE);
            variantList.add(variant(TBBlocks.DISMAL, key));
            variantList.add(variant(TBBlocks.DISMAL, key).with(BlockModelGenerators.Y_ROT_90));
            variantList.add(variant(TBBlocks.DISMAL, key).with(BlockModelGenerators.Y_ROT_180));
            variantList.add(variant(TBBlocks.DISMAL, key).with(BlockModelGenerators.Y_ROT_270));
        });
        
        
        //        blockModels.createTrivialBlock(TBBlocks.DISMAL.get(), TexturedModel.CUBE);
        MultiVariant variants = BlockModelGenerators.variants(variantList.toArray(new Variant[0]));
        MultiVariantGenerator dispatch = MultiVariantGenerator.dispatch(TBBlocks.DISMAL.get(), variants);
        blockModels.blockStateOutput.accept(dispatch);
        
        blockModels.createMultiface(TBBlocks.BRIGHT_SPREAD.get());
        blockModels.createTrivialCube(TBBlocks.BREACH_PORTAL.get());
        itemModels.generateFlatItem(TBItems.MUMBLES.get(), ModelTemplates.FLAT_ITEM);
    }
    
    private void suffixedCube(Supplier<Block> block, String suffix, BlockModelGenerators blockModels, TexturedModel.Provider model) {
        
        model.updateTexture(textureMapping -> textureMapping.put(TextureSlot.ALL, TextureMapping.getBlockTexture(block.get(), suffix)))
                .createWithSuffix(block.get(), suffix, blockModels.modelOutput);
    }
    
    private static Variant variant(Supplier<Block> block, String suffix) {
        
        return BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(block.get(), suffix));
    }
    
}
