package com.leacox.dagger.hk2.bridge.api;

import dagger.Module;
import dagger.Provides;

/**
 * @author John Leacox
 */
@Module(injects = DaggerService.class, library = true)
public class DaggerBridgeModule {
    @Provides
    DaggerService provideDaggerService() {
        return new DaggerService();
    }
}
