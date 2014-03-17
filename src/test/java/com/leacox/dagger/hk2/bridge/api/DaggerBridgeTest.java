package com.leacox.dagger.hk2.bridge.api;

import dagger.ObjectGraph;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.junit.Assert;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2TestModule;
import org.jvnet.hk2.testing.junit.HK2TestUtilities;

/**
 * @author John Leacox
 */
public class DaggerBridgeTest {
    /* package */ static final String ALICE = "Alice";
    /* package */ static final String HATTER = "Hatter";

    private static final ServiceLocator testLocator = createLocator("DaggerBridgeTest", new DaggerBridgeTestModule());

    public static ServiceLocator createLocator(String name, HK2TestModule module) {
        ServiceLocator locator = HK2TestUtilities.create(name, module);

        ServiceLocatorUtilities.enableLookupExceptions(locator);

        DaggerBridge.getDaggetBridge().initializeDaggerBridge(locator);

        return locator;
    }

    /**
     * Tests a service from Guice being injected into an HK2 service
     */
    @Test
    public void testDaggerServiceInHk2Service() {
        ObjectGraph objectGraph = ObjectGraph.create(new DaggerBridgeModule());
        Assert.assertNotNull(objectGraph);

        DaggerIntoHk2Bridge daggerBridge = testLocator.getService(DaggerIntoHk2Bridge.class);
        Assert.assertNotNull(daggerBridge);

        daggerBridge.bridgeDaggerObjectGraph(objectGraph);

        Hk2Service hk2Service = testLocator.getService(Hk2Service.class);
        Assert.assertNotNull(hk2Service);

        Assert.assertEquals("Value", hk2Service.getValue());

//        hk2Service.verifyGuiceService();
//
//        HK2Service3 hk2Service3 = testLocator.getService(HK2Service3.class);
//
//        hk2Service3.check();
    }

    /**
     * Tests a service from hk2 being injected into a Guice service
     */
//    @Test
//    public void testHk2ServiceInGuiceService() {
//        Injector injector = Guice.createInjector(
//                new HK2IntoGuiceBridge(testLocator),
//                new HK2BridgeModule());
//        Assert.assertNotNull(injector);
//
//        GuiceService2 guiceService2 = injector.getInstance(GuiceService2.class);
//        Assert.assertNotNull(guiceService2);
//
//        guiceService2.verifyHK2Service();
//    }
}
