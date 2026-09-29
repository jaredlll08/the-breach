package com.blamejared.the_breach.neoforge;

import com.blamejared.the_breach.TBConstants;
import com.blamejared.the_breach.TheBreach;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TBConstants.MODID)
public class TheBreachNeoForge {
    
    public TheBreachNeoForge(final IEventBus modEventBus) {
        
        TheBreach.init();
    }
    
}

