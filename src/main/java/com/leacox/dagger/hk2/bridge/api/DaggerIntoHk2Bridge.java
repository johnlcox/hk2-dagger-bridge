package com.leacox.dagger.hk2.bridge.api;

import dagger.ObjectGraph;
import org.jvnet.hk2.annotations.Contract;

/**
 * Bridge for Dagger into HK2
 *
 * @author John Leacox
 */
@Contract
public interface DaggerIntoHk2Bridge {
    /**
     * Creates a link between HK2 services and a Dagger object graph.
     *
     * @param objectGraph the Dagger object graph to create a bridge to (not null)
     */
    public void bridgeDaggerObjectGraph(ObjectGraph objectGraph);
}
