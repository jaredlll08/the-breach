package com.blamejared.the_breach.world.level.storage.loot;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.platform.IRegister;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@AutoService(IRegister.class)
public class TBLoot implements IRegister {
    
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
    
    private static ResourceKey<LootTable> register(final String path) {
        
        ResourceKey<LootTable> location = TBConstants.rc(Registries.LOOT_TABLE, path);
        if(LOCATIONS.add(location)) {
            return location;
        } else {
            throw new IllegalArgumentException(location.identifier() + " is already a registered loot table");
        }
    }
    
    public static Set<ResourceKey<LootTable>> getEntries() {
        
        return IMMUTABLE_LOCATIONS;
    }
    
}
