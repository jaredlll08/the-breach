package com.blamejared.the_breach.fabric;

import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.client.TheBreachClient;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class TheBreachFabricClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        
        TheBreachClient.registerEntityRenderers()
                .forEach((entityType, entityRendererProvider) -> EntityRenderers.register(entityType, Util.uncheck(entityRendererProvider)));
    }
    
}