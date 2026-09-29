package com.blamejared.the_breach.mixin.transform.entity;

import com.blamejared.the_breach.world.attribute.TBEnvironmentAttributes;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class MixinPlayer extends Avatar {
    
    protected MixinPlayer(EntityType<? extends LivingEntity> type, Level level) {
        
        super(type, level);
    }
    
    @ModifyArg(method = "causeFoodExhaustion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V"))
    private float the_breach$exhaustionModifierAttribute(float amount) {
        
        return amount * this.level()
                .environmentAttributes()
                .getValue(TBEnvironmentAttributes.EXHAUSTION_MODIFIER.get(), this.position());
    }
    
}
