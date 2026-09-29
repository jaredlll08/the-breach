package com.blamejared.the_breach.neoforge.event;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.world.entity.TBSpawnPlacements;
import com.blamejared.the_breach.world.entity.ai.attributes.TBDefaultAttributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = TBConstants.MODID)
public class TBEvents {
    
    @SubscribeEvent
    public static void createEntityAttributes(EntityAttributeCreationEvent event) {
        
        TBDefaultAttributes.init(event::put);
    }
    
    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        
        TBSpawnPlacements.init((entityType, data) -> event.register(entityType, data.placement(), data.heightMap(), Util.uncheck(data.predicate()), RegisterSpawnPlacementsEvent.Operation.REPLACE));
    }
    
}
