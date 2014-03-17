package com.leacox.dagger.hk2.bridge.api;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.jvnet.hk2.testing.junit.HK2TestModule;

/**
 * @author John Leacox
 */
public class DaggerBridgeTestModule implements HK2TestModule {

    @Override
    public void configure(DynamicConfiguration config) {
        config.addActiveDescriptor(Hk2Service.class);
    }
}
