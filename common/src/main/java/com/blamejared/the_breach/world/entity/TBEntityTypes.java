package com.blamejared.the_breach.world.entity;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.blamejared.the_breach.world.entity.monster.zombie.Bleak;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.Collection;

@AutoService(IRegister.class)
public class TBEntityTypes implements IRegister {
    
    private static final RegistrationProvider<EntityType<? extends Entity>> ENTITY_TYPES = RegistrationProvider.get(Registries.ENTITY_TYPE, TBConstants.MODID);
    
    public static final RegistryObject<EntityType<Bleak>> BLEAK = ENTITY_TYPES.register("bleak", (key) -> EntityType.Builder.of(Bleak::new, MobCategory.MONSTER)
            .sized(0.6F, 1.95F)
            .eyeHeight(1.74F)
            .passengerAttachments(2.0125F)
            .ridingOffset(-0.7F)
            .clientTrackingRange(8)
            .notInPeaceful()
            .build(Util.uncheck(key)));
    
    public static Collection<RegistryObject<EntityType<?>>> getEntries() {
        
        return ENTITY_TYPES.getEntries();
    }
    
}
