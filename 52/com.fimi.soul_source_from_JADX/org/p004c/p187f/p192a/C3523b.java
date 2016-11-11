package org.p004c.p187f.p192a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/* renamed from: org.c.f.a.b */
public class C3523b extends C3522c<C3523b> {
    private final Field f16069a;

    C3523b(Field field) {
        if (field == null) {
            throw new NullPointerException("FrameworkField cannot be created without an underlying field.");
        }
        this.f16069a = field;
    }

    public Object m19171a(Object obj) {
        return this.f16069a.get(obj);
    }

    public <T extends Annotation> T m19172a(Class<T> cls) {
        return this.f16069a.getAnnotation(cls);
    }

    public boolean m19173a(C3523b c3523b) {
        return c3523b.m19176b().equals(m19176b());
    }

    public Annotation[] m19175a() {
        return this.f16069a.getAnnotations();
    }

    public String m19176b() {
        return m19178d().getName();
    }

    protected int m19177c() {
        return this.f16069a.getModifiers();
    }

    public Field m19178d() {
        return this.f16069a;
    }

    public Class<?> m19179e() {
        return this.f16069a.getType();
    }

    public Class<?> m19180f() {
        return this.f16069a.getDeclaringClass();
    }

    public String toString() {
        return this.f16069a.toString();
    }
}
