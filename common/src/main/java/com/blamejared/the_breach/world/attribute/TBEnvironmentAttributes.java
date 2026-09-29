package com.blamejared.the_breach.world.attribute;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.attribute.AttributeTypes;
import net.minecraft.world.attribute.EnvironmentAttribute;

import java.util.Collection;

@AutoService(IRegister.class)
public class TBEnvironmentAttributes implements IRegister {
    
    private static final RegistrationProvider<EnvironmentAttribute<?>> ATTRIBUTES = RegistrationProvider.get(Registries.ENVIRONMENT_ATTRIBUTE, TBConstants.MODID);
    
    public static final RegistryObject<EnvironmentAttribute<Float>> EXHAUSTION_MODIFIER = ATTRIBUTES.register("gameplay/exhaustion_modifier", () -> EnvironmentAttribute.builder(AttributeTypes.FLOAT)
            .defaultValue(1f)
            .syncable()
            .build());
    
    public static final RegistryObject<EnvironmentAttribute<Float>> GRAVITY_MODIFIER = ATTRIBUTES.register("gameplay/gravity_modifier", () -> EnvironmentAttribute.builder(AttributeTypes.FLOAT)
            .defaultValue(1f)
            .build());
    
    public static Collection<RegistryObject<EnvironmentAttribute<?>>> getEntries() {
        
        return ATTRIBUTES.getEntries();
    }
    
}
