package com.blamejared.the_breach.client;

import com.blamejared.the_breach.client.renderer.entity.TBEntityRenderers;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;

import java.util.Map;

public class TheBreachClient {
    
    public static Map<EntityType<?>, EntityRendererProvider<?>> registerEntityRenderers() {
        
        Map<EntityType<?>, EntityRendererProvider<?>> map = new Object2ObjectOpenHashMap<>();
        TBEntityRenderers.init(map);
        return map;
    }
    
}
