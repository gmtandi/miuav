package org.p004c.p188a.p195e;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: org.c.a.e.b */
public @interface C3369b {
    String[] m18525a() default {};

    Class<? extends Throwable>[] m18526b() default {};
}