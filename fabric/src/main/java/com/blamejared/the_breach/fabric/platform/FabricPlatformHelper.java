package com.blamejared.the_breach.fabric.platform;

import com.blamejared.the_breach.platform.IPlatformHelper;
import com.google.auto.service.AutoService;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;

@AutoService(IPlatformHelper.class)
public class FabricPlatformHelper implements IPlatformHelper {
    
    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {
        
        return FabricCreativeModeTab.builder();
    }
    
    @Override
    public boolean isModLoaded(final String modId) {
        
        return FabricLoader.getInstance().isModLoaded(modId);
    }
    
}
