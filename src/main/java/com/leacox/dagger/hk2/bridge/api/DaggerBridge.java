package com.leacox.dagger.hk2.bridge.api;

import com.leacox.dagger.hk2.bridge.internal.DaggerBridgeImpl;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceLocator;

/**
 * A class used to initialized a {@link ServiceLocator} for use with the Dagger-HK2 bridge.
 *
 * @author John Leacox
 */
public abstract class DaggerBridge {
    private final static DaggerBridge INSTANCE = new DaggerBridgeImpl();

    public static DaggerBridge getDaggetBridge() {
        return INSTANCE;
    }

    /**
     * Initializes the given service locator for use with the Dagger-HK2 bridge. It adds an implementation of
     * {@code DaggerIntoHk2Bridge} and the custom scope needed for Dagger to the service locator.
     *
     * @param locator The locator to use with the Dagger-HK2 bridge (not null)
     * @throws MultiException if initialization fails
     */
    public abstract void initializeDaggerBridge(ServiceLocator locator) throws MultiException;
}
