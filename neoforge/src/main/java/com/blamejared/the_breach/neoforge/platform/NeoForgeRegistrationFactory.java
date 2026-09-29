package com.blamejared.the_breach.neoforge.platform;

import com.blamejared.the_breach.Util;
import com.blamejared.the_breach.platform.RegistrationProvider;
import com.blamejared.the_breach.platform.RegistryObject;
import com.google.auto.service.AutoService;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.ModList;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

@AutoService(RegistrationProvider.Factory.class)
public class NeoForgeRegistrationFactory implements RegistrationProvider.Factory {
    
    @Override
    public <T> RegistrationProvider<T> create(final ResourceKey<? extends Registry<T>> resourceKey, final String modId) {
        
        final var containerOpt = ModList.get().getModContainerById(modId);
        if(containerOpt.isEmpty()) {
            throw new NullPointerException("Cannot find mod container for id " + modId);
        }
        final var cont = containerOpt.get();
        if(cont instanceof FMLModContainer fmlModContainer) {
            final var register = DeferredRegister.create(resourceKey, modId);
            register.register(Objects.requireNonNull(fmlModContainer.getEventBus()));
            return new Provider<>(modId, register);
        } else {
            throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
        }
    }
    
    private static class Provider<T> implements RegistrationProvider<T> {
        
        private final String modId;
        private final DeferredRegister<T> registry;
        
        private final Set<RegistryObject<T>> entries = new HashSet<>();
        private final Set<RegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);
        
        private Provider(final String modId, final DeferredRegister<T> registry) {
            
            this.modId = modId;
            this.registry = registry;
        }
        
        @Override
        public String getModId() {
            
            return modId;
        }
        
        @Override
        public ResourceKey<? extends Registry<T>> resourceKey() {
            
            return this.registry.getRegistryKey();
        }
        
        @Override
        public <I extends T> RegistryObject<I> register(final String name, final Supplier<? extends I> supplier) {
            
            final DeferredHolder<T, I> obj = registry.register(name, supplier);
            final RegistryObject<T> ro = new RegistryObject<>() {
                
                @Override
                public ResourceKey<T> getResourceKey() {
                    
                    return obj.getKey();
                }
                
                @Override
                public Identifier getId() {
                    
                    return obj.getId();
                }
                
                @Override
                public I get() {
                    
                    return obj.get();
                }
                
                @Override
                public Holder<T> asHolder() {
                    
                    return obj;
                }
            };
            entries.add(ro);
            return Util.uncheck(ro);
        }
        
        @Override
        public Set<RegistryObject<T>> getEntries() {
            
            return entriesView;
        }
        
    }
    
}