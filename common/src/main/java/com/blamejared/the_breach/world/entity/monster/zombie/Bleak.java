package com.blamejared.the_breach.world.entity.monster.zombie;

import com.blamejared.the_breach.world.entity.ai.goal.BleakExcitedAtBrightGoal;
import com.blamejared.the_breach.world.entity.ai.goal.BleakTryExitPortalGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Bleak extends Zombie {
    
    public Bleak(EntityType<? extends Zombie> type, Level level) {
        
        super(type, level);
    }
    
    protected void addBehaviourGoals() {
        
        this.goalSelector.addGoal(1, new BleakExcitedAtBrightGoal(this, 0.8));
        this.goalSelector.addGoal(2, new BleakTryExitPortalGoal(this, 0.8));
        this.goalSelector.addGoal(3, new ZombieAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }
    
    @Override
    public void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {}
    
    public static AttributeSupplier.Builder createAttributes() {
        
        return Zombie.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.13F)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ARMOR, 0.0)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }
    
    public void enlighten() {
        
        if(this.level() instanceof ServerLevel level) {
            this.dead = true;
            level.explode(this, this.getX(), this.getY(), this.getZ(), 0.5f, Level.ExplosionInteraction.MOB);
            this.triggerOnDeathMobEffects(level, Entity.RemovalReason.KILLED);
            this.discard();
        }
    }
    
    @Override
    public boolean canBreakDoors() {
        
        return false;
    }
    
    @Override
    protected boolean convertsInWater() {
        
        return false;
    }
    
}
