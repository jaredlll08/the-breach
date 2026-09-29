package com.blamejared.the_breach.platform;

import com.blamejared.the_breach.TheBreach;

import java.util.ServiceLoader;
import java.util.stream.Stream;

public class Services {
    
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    
    public static <T> T load(final Class<T> clazz) {
        
        final T loadedService = ServiceLoader.load(clazz, Services.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TheBreach.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
    
    public static <T> Stream<ServiceLoader.Provider<T>> loadAll(final Class<T> clazz) {
        
        return ServiceLoader.load(clazz).stream();
    }
    
}
