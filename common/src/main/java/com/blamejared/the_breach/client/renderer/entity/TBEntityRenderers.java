package com.blamejared.the_breach.client.renderer.entity;

import com.blamejared.the_breach.world.entity.TBEntityTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.Map;

public class TBEntityRenderers {
    
    public static void init(final Map<EntityType<?>, EntityRendererProvider<?>> map) {
        
        register(map, TBEntityTypes.BLEAK.get(), BleakRenderer::new);
    }
    
    private static <T extends Entity> void register(final Map<EntityType<?>, EntityRendererProvider<?>> map, final EntityType<? extends T> type, final EntityRendererProvider<T> renderer) {
        
        map.put(type, renderer);
    }
    
}
