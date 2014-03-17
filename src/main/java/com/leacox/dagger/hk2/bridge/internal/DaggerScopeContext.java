package com.leacox.dagger.hk2.bridge.internal;

import com.leacox.dagger.hk2.bridge.api.DaggerScope;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Context;
import org.glassfish.hk2.api.ServiceHandle;
import org.jvnet.hk2.annotations.Service;

import java.lang.annotation.Annotation;

/**
 * @author John Leacox
 */
@Service
public class DaggerScopeContext implements Context<DaggerScope> {
    @Override
    public Class<? extends Annotation> getScope() {
        return DaggerScope.class;
    }

    @Override
    public <U> U findOrCreate(ActiveDescriptor<U> activeDescriptor, ServiceHandle<?> root) {
        return activeDescriptor.create(root);
    }

    @Override
    public boolean containsKey(ActiveDescriptor<?> descriptor) {
        return false;
    }

    @Override
    public void destroyOne(ActiveDescriptor<?> descriptor) {
        // TODO: If the object is request scoped should it be removed from the request attributes?
    }

    @Override
    public boolean supportsNullCreation() {
        return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void shutdown() {
        // DO nothing
    }
}
