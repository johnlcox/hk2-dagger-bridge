package com.leacox.dagger.hk2.bridge.internal;

import com.leacox.dagger.hk2.bridge.api.DaggerScope;
import dagger.ObjectGraph;
import org.glassfish.hk2.api.DescriptorType;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.utilities.AbstractActiveDescriptor;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author John Leacox
 */
public class DaggerServiceHk2Bean<T> extends AbstractActiveDescriptor<T> {

    private final Class<?> clazz;
    private final ObjectGraph objectGraph;

    public DaggerServiceHk2Bean(Set<Type> contracts, Set<Annotation> qualifiers, Class<?> clazz, ObjectGraph objectGraph) {
        super(contracts, DaggerScope.class, ReflectionHelper.getNameFromAllQualifiers(qualifiers, clazz), qualifiers,
                DescriptorType.CLASS, DescriptorVisibility.NORMAL, 0, false, null, null,
                new HashMap<String, List<String>>());
        super.setImplementation(clazz.getName());

        this.clazz = clazz;
        this.objectGraph = objectGraph;
    }

    @Override
    public Class<?> getImplementationClass() {
        return clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T create(ServiceHandle<?> root) {
        return (T) objectGraph.get(clazz);
    }

    @Override
    public String toString() {
        return "DaggerServiceHk2Bean( " + super.toString() + ")";
    }
}
