package com.blamejared.the_breach.platform;

import com.blamejared.the_breach.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Utility class for multiloader registration.
 * <p>
 * Example usage:
 * <pre>{@code
 * public static final RegistrationProvider<Test> PROVIDER = RegistrationProvider.get(Test.REGISTRY, "modid");
 * public static final RegistryObject<Test> OBJECT = PROVIDER.register("object", () -> new Test());
 *
 * // The purpose of this method is to be called in the mod's constructor, in order to assure that the class is loaded, and that objects can be registered.
 * public static void loadClass(){}
 * }</pre>
 *
 * @param <T> the type of the objects that this class registers
 */
public interface RegistrationProvider<T> {
    
    /**
     * Gets a provider for specified {@code modId} and {@code resourceKey}. <br>
     * It is <i>recommended</i> to store the resulted provider in a {@code static final} field to
     * the {@link Factory#INSTANCE factory} creating multiple providers for the same resource key and mod id.
     *
     * @param resourceKey the {@link ResourceKey} of the registry of the provider
     * @param modId       the mod id that the provider will register objects for
     * @param <T>         the type of the provider
     *
     * @return the provider
     */
    static <T> RegistrationProvider<T> get(final ResourceKey<? extends Registry<T>> resourceKey, final String modId) {
        
        return Factory.INSTANCE.create(resourceKey, modId);
    }
    
    /**
     * Gets a provider for specified {@code modId} and {@code registry}. <br>
     * It is <i>recommended</i> to store the resulted provider in a {@code static final} field to
     * the {@link Factory#INSTANCE factory} creating multiple providers for the same resource key and mod id.
     *
     * @param registry the {@link Registry} of the provider
     * @param modId    the mod id that the provider will register objects for
     * @param <T>      the type of the provider
     *
     * @return the provider
     */
    static <T> RegistrationProvider<T> get(final Registry<T> registry, final String modId) {
        
        return Factory.INSTANCE.create(registry, modId);
    }
    
    /**
     * Registers an object.
     *
     * @param name     the name of the object
     * @param supplier a supplier of the object to register
     * @param <I>      the type of the object
     *
     * @return a wrapper containing the lazy registered object. <strong>Calling {@link RegistryObject#get() get} too early
     * on the wrapper might result in crashes!</strong>
     */
    <I extends T> RegistryObject<I> register(final String name, final Supplier<? extends I> supplier);
    
    /**
     * Registers an object.
     *
     * @param name the name of the object
     * @param func a function of the object to register with the object's resource key
     * @param <I>  the type of the object
     *
     * @return a wrapper containing the lazy registered object. <strong>Calling {@link RegistryObject#get() get} too early
     * on the wrapper might result in crashes!</strong>
     */
    default <I extends T> RegistryObject<I> register(final String name, final Function<ResourceKey<I>, ? extends I> func) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<I> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return func.apply(rk);
        });
    }
    
    default <I extends T> RegistryObject<I> block(final String name, final Function<Block.Properties, ? extends I> supplier) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Block> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return supplier.apply(Block.Properties.of().setId(rk));
        });
    }
    
    default <I extends T> RegistryObject<I> block(final String name, final Function<Block.Properties, ? extends I> supplier, final BlockBehaviour.Properties defaultProperties) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Block> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return supplier.apply(defaultProperties.setId(rk));
        });
    }
    
    default <I extends T> RegistryObject<I> item(final String name, final Function<Item.Properties, ? extends I> supplier) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Item> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return supplier.apply(new Item.Properties().setId(rk));
        });
    }
    
    default <I extends T> RegistryObject<I> item(final String name, final Function<Item.Properties, ? extends I> supplier, final Item.Properties defaultProperties) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Item> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return supplier.apply(defaultProperties.setId(rk));
        });
    }
    
    default <I extends T> RegistryObject<I> item(final String name, final Function<Item.Properties, ? extends I> supplier, final Supplier<Item.Properties> defaultProperties) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Item> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return supplier.apply(defaultProperties.get().setId(rk));
        });
    }
    
    default <I extends T> RegistryObject<I> blockItem(final String name, final RegistryObject<Block> block, final UnaryOperator<Item.Properties> supplier) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Item> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return Util.uncheck(new BlockItem(block.get(), supplier.apply(new Item.Properties().setId(rk))));
        });
    }
    
    default <I extends T> RegistryObject<I> blockItem(final String name, final RegistryObject<Block> block, final UnaryOperator<Item.Properties> supplier, final Item.Properties defaultProperties) {
        
        return register(name, () -> {
            Identifier location = Identifier.fromNamespaceAndPath(getModId(), name);
            ResourceKey<Item> rk = ResourceKey.create(Util.uncheck(resourceKey()), location);
            return Util.uncheck(new BlockItem(block.get(), supplier.apply(defaultProperties.setId(rk))));
        });
    }
    
    /**
     * Gets all the objects currently registered.
     *
     * @return an <strong>immutable</strong> view of all the objects currently registered
     */
    Collection<RegistryObject<T>> getEntries();
    
    /**
     * Gets the mod id that this provider registers objects for.
     *
     * @return the mod id
     */
    String getModId();
    
    ResourceKey<? extends Registry<T>> resourceKey();
    
    /**
     * Factory class for {@link RegistrationProvider registration providers}. <br>
     * This class is loaded using {@link java.util.ServiceLoader Service Loaders}, and only one
     * should exist per mod loader.
     */
    interface Factory {
        
        /**
         * The singleton instance of the {@link Factory}. This is different on each loader.
         */
        Factory INSTANCE = Services.load(Factory.class);
        
        /**
         * Creates a {@link RegistrationProvider}.
         *
         * @param resourceKey the {@link ResourceKey} of the registry to create this provider for
         * @param modId       the mod id for which the provider will register objects
         * @param <T>         the type of the provider
         *
         * @return the provider
         */
        <T> RegistrationProvider<T> create(final ResourceKey<? extends Registry<T>> resourceKey, final String modId);
        
        /**
         * Creates a {@link RegistrationProvider}.
         *
         * @param registry the {@link Registry} to create this provider for
         * @param modId    the mod id for which the provider will register objects
         * @param <T>      the type of the provider
         *
         * @return the provider
         */
        default <T> RegistrationProvider<T> create(final Registry<T> registry, final String modId) {
            
            return create(registry.key(), modId);
        }
        
    }
    
}
