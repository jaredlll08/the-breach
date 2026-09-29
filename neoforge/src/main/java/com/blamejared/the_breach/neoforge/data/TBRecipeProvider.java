package com.blamejared.the_breach.neoforge.data;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.item.TBItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class TBRecipeProvider extends RecipeProvider {
    
    
    protected TBRecipeProvider(final HolderLookup.Provider registries, final RecipeOutput output) {
        
        super(registries, output);
    }
    
    @Override
    protected void buildRecipes() {
        
        this.shapeless(RecipeCategory.MISC, Items.COBBLESTONE, 4)
                .requires(TBItems.BRIGHT.get(), 8)
                .unlockedBy(TBConstants.sid(getHasName(TBItems.BRIGHT.get())), this.has(TBItems.BRIGHT.get()))
                .save(this.output, TBConstants.sid("bright_to_cobblestone"));
        
        this.shapeless(RecipeCategory.MISC, Items.OAK_PLANKS)
                .requires(TBItems.BRIGHT.get(), 4)
                .unlockedBy(TBConstants.sid(getHasName(TBItems.BRIGHT.get())), this.has(TBItems.BRIGHT.get()))
                .save(this.output, TBConstants.sid("bright_to_oak_planks"));
    }
    
    
    public static class Runner extends RecipeProvider.Runner {
        
        public Runner(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
            
            super(output, lookupProvider);
        }
        
        @Override
        protected RecipeProvider createRecipeProvider(final HolderLookup.Provider provider, final RecipeOutput output) {
            
            return new TBRecipeProvider(provider, output);
        }
        
        @Override
        public String getName() {
            
            return TBConstants.MODID + " Recipe Provider";
        }
        
    }
    
}
