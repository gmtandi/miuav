package org.p004c.p187f;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.p004c.p187f.p208b.C3535c;
import org.p004c.p187f.p208b.C3536b;

@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: org.c.f.i */
public @interface C3544i {
    Class<? extends C3535c> m19260a() default C3536b.class;
}
