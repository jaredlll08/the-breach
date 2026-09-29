package com.blamejared.the_breach.platform;

import net.minecraft.world.item.CreativeModeTab;

public interface IPlatformHelper {
    
    CreativeModeTab.Builder creativeTabBuilder();
    
    boolean isModLoaded(final String modId);
    
}
