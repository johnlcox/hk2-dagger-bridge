package com.leacox.dagger.hk2.bridge.api;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

/**
 * @author John Leacox
 */
@Service
public class Hk2Service {
    @Inject
    DaggerService daggerService;

    public String getValue() {
        return daggerService.getValue();
    }
}
