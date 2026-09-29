package com.blamejared.the_breach;

import com.blamejared.the_breach.platform.IRegister;
import com.blamejared.the_breach.platform.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ServiceLoader;

public class TheBreach {
    
    public static final Logger LOG = LogManager.getLogger(TBConstants.MODID);
    
    public static void init() {
        
        Services.loadAll(IRegister.class).forEach(ServiceLoader.Provider::get);
    }
    
}
