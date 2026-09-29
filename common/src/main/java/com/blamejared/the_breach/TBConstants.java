package com.blamejared.the_breach;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public class TBConstants {
    
    public static final String MODID = "the_breach";
    
    public static <T> ResourceKey<T> rc(final ResourceKey<? extends Registry<T>> registry, final String path) {
        
        return ResourceKey.create(registry, id(path));
    }
    
    public static <T> TagKey<T> tk(final ResourceKey<? extends Registry<T>> registry, final String path) {
        
        return TagKey.create(registry, id(path));
    }
    
    public static Identifier id(String path) {
        
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
    
    public static String sid(String path) {
        
        return MODID + ":" + path;
    }
    
}
