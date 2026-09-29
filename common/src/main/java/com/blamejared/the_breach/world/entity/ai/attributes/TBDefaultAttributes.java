package com.blamejared.the_breach.world.entity.ai.attributes;

import com.blamejared.the_breach.world.entity.TBEntityTypes;
import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.BiConsumer;

public class TBDefaultAttributes {
    
    public static void init(final BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier> map) {
        
        map.accept(TBEntityTypes.BLEAK.get(), Bleak.createAttributes().build());
    }
    
}
