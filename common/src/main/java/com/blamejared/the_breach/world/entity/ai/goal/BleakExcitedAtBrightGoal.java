package com.blamejared.the_breach.world.entity.ai.goal;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import com.blamejared.the_breach.world.level.block.TBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BleakExcitedAtBrightGoal extends MoveToBlockGoal {
    
    private final Bleak bleak;
    private static final Identifier SPRINTING_MODIFIER_ID = TBConstants.id("sprinting");
    private static final AttributeModifier SPEED_MODIFIER_SPRINTING = new AttributeModifier(
            SPRINTING_MODIFIER_ID, 1.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
    );
    
    public BleakExcitedAtBrightGoal(Bleak bleak, double speedModifier) {
        
        super(bleak, speedModifier, 16);
        this.bleak = bleak;
    }
    
    @Override
    public void start() {
        
        super.start();
        bleak.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(SPEED_MODIFIER_SPRINTING);
    }
    
    @Override
    public void stop() {
        
        super.stop();
        bleak.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_SPRINTING.id());
    }
    
    @Override
    public void tick() {
        
        super.tick();
        if(this.isReachedTarget()) {
            this.bleak.level().destroyBlock(this.getMoveToTarget(), false);
            //TODO look for any touching spread in 8 directions and break them, bigger explosion based on
            this.bleak.enlighten();
        }
    }
    
    
    @Override
    protected BlockPos getMoveToTarget() {
        
        return this.blockPos;
    }
    
    @Override
    public double acceptedDistance() {
        
        return 1.5;
    }
    
    @Override
    protected int nextStartTick(PathfinderMob mob) {
        
        return 20;
    }
    
    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        
        BlockState blockState = level.getBlockState(pos);
        return blockState.is(TBBlocks.BRIGHT_SPREAD.asHolder());
    }
    
}
