package com.leacox.dagger.hk2.bridge.internal;

import com.leacox.dagger.hk2.bridge.api.DaggerBridge;
import com.leacox.dagger.hk2.bridge.api.DaggerIntoHk2Bridge;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.BuilderHelper;

/**
 * @author John Leacox
 */
public class DaggerBridgeImpl extends DaggerBridge {
    @Override
    public void initializeDaggerBridge(ServiceLocator locator) throws MultiException {
        boolean addService = true;
        if (locator.getBestDescriptor(BuilderHelper.createContractFilter(DaggerIntoHk2Bridge.class.getName())) != null) {
            addService = false;
        }

        boolean addContext = true;
        if (locator.getBestDescriptor(BuilderHelper.createContractFilter(DaggerScopeContext.class.getName())) != null) {
            addContext = false;
        }

        if (!addService && !addContext) return;

        DynamicConfigurationService configurationService = locator.getService(DynamicConfigurationService.class);
        if (configurationService == null) {
            throw new IllegalStateException("This service locator does not have a DynamicConfigurationService: " + locator);
        }

        DynamicConfiguration config = configurationService.createDynamicConfiguration();

        if (addContext) {
            config.addActiveDescriptor(DaggerScopeContext.class);
        }

        if (addService) {
            config.addActiveDescriptor(DaggerIntoHk2BridgeImpl.class);
        }

        config.commit();
    }
}
