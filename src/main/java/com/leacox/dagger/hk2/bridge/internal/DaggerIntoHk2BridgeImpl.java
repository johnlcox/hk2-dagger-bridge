package com.leacox.dagger.hk2.bridge.internal;

import com.leacox.dagger.hk2.bridge.api.DaggerIntoHk2Bridge;
import dagger.ObjectGraph;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

/**
 * @author John Leacox
 */
@Service
public class DaggerIntoHk2BridgeImpl implements DaggerIntoHk2Bridge {
    @Inject
    protected ServiceLocator locator;

    @Override
    public void bridgeDaggerObjectGraph(ObjectGraph objectGraph) {
        DaggerToHk2JitResolver resolver = new DaggerToHk2JitResolver(locator, objectGraph);
        ServiceLocatorUtilities.addOneConstant(locator, resolver);
    }
}
