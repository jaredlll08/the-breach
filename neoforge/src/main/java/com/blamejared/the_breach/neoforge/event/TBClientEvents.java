package com.blamejared.the_breach.neoforge.event;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.client.TheBreachClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(value = Dist.CLIENT, modid = TBConstants.MODID)
public class TBClientEvents {
    
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        
        TheBreachClient.registerEntityRenderers()
                .forEach((entityType, entityRendererProvider) -> event.registerEntityRenderer(entityType, Util.uncheck(entityRendererProvider)));
    }
    
    
}
