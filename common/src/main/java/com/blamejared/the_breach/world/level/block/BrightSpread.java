package com.blamejared.the_breach.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

import java.util.function.ToIntFunction;

public class BrightSpread extends MultifaceBlock {
    
    public BrightSpread(Properties properties) {
        
        super(properties);
    }
    
    public static ToIntFunction<BlockState> emission(int lightEmission) {
        
        return state -> MultifaceBlock.hasAnyFace(state) ? lightEmission : 0;
    }
    
    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0, true, false));
    }
    
    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }
    
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity by, ItemStack itemStack) {
        
        if(level instanceof ServerLevel) {
            BlockPattern.BlockPatternMatch match = BreachPortalBlock.getOrCreatePortalShape().find(level, pos);
            if(match != null) {
                BlockPos offset = match.getFrontTopLeft().offset(-2, 0, -2);
                for(int x = 0; x < 2; x++) {
                    for(int z = 0; z < 2; z++) {
                        BlockPos portalBlockPos = offset.offset(x, 0, z);
                        level.destroyBlock(portalBlockPos, true, null);
                        level.setBlock(portalBlockPos, TBBlocks.BREACH_PORTAL.get()
                                .defaultBlockState(), Block.UPDATE_CLIENTS);
                        
                    }
                }
            }
        }
    }
    
    
}
