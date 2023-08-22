package com.am53.brand.hub.onboarding.taponphone.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefaultIdValue {
    long longValue() default 0L;
    int intValue() default 0;

}