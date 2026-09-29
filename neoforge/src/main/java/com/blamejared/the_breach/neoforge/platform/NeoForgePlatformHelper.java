package com.blamejared.the_breach.neoforge.platform;

import com.blamejared.the_breach.platform.IPlatformHelper;
import com.google.auto.service.AutoService;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.fml.ModList;

@AutoService(IPlatformHelper.class)
public class NeoForgePlatformHelper implements IPlatformHelper {
    
    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        
        return new CreativeModeTab.Builder(CreativeModeTab.Row.TOP, 0);
    }
    
    @Override
    public boolean isModLoaded(final String modId) {
        
        return ModList.get().isLoaded(modId);
    }
    
}
