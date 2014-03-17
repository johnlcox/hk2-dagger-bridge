package com.leacox.dagger.hk2.bridge.api;

import org.glassfish.hk2.api.Unproxiable;

import javax.inject.Scope;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author John Leacox
 */
@Retention(RUNTIME)
@Scope
@Unproxiable
@Target({TYPE})
public @interface DaggerScope {
}
