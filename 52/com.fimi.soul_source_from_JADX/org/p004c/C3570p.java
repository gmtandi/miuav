package org.p004c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: org.c.p */
public @interface C3570p {
    Class<? extends Throwable> m19300a() default C3571q.class;

    long m19301b() default 0;
}
