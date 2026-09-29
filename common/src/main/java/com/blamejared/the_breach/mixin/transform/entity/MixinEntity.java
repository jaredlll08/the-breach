package com.blamejared.the_breach.mixin.transform.entity;

import com.blamejared.the_breach.world.attribute.TBEnvironmentAttributes;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class MixinEntity {
    
    @Shadow
    public abstract Level level();
    
    @Shadow
    public abstract Vec3 position();
    
    @ModifyReturnValue(method = "getGravity", at = @At(value = "RETURN"))
    private double the_breach$exhaustionModifierAttribute(double original) {
        
        return original * this.level()
                .environmentAttributes()
                .getValue(TBEnvironmentAttributes.GRAVITY_MODIFIER.get(), this.position());
    }
    
}
