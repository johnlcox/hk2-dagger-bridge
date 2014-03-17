package com.leacox.dagger.hk2.bridge.internal;

import dagger.ObjectGraph;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * @author John Leacox
 */
@Singleton
public class DaggerToHk2JitResolver implements JustInTimeInjectionResolver {
    private final ServiceLocator locator;
    private final ObjectGraph objectGraph;

    DaggerToHk2JitResolver(ServiceLocator locator, ObjectGraph objectGraph) {
        this.locator = locator;
        this.objectGraph = objectGraph;
    }

    @Override
    public boolean justInTimeResolution(Injectee failedInjectionPoint) {
        Class<?> clazz = getClassFromType(failedInjectionPoint.getRequiredType());

        if (clazz == null) {
            return false;
        }

        try {
            objectGraph.get(clazz);

            HashSet<Type> contracts = new HashSet<Type>();
            contracts.add(failedInjectionPoint.getRequiredType());

            Set<Annotation> qualifiers = new HashSet<Annotation>(failedInjectionPoint.getRequiredQualifiers());

            DaggerServiceHk2Bean daggerBean = new DaggerServiceHk2Bean<Object>(contracts, qualifiers, clazz, objectGraph);

            ServiceLocatorUtilities.addOneDescriptor(locator, daggerBean);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private Class<?> getClassFromType(Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            return (Class<?>) parameterizedType.getRawType();
        }

        return null;
    }
}
