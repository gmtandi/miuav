package org.p004c.p198b.p202d;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import p001b.p002b.C0115j;
import p001b.p008c.C0142a;

/* renamed from: org.c.b.d.p */
public class C3444p extends C3433e {
    public C3444p(Class<?> cls) {
        super(C3444p.m18783a(cls));
    }

    public static C0115j m18783a(Class<?> cls) {
        try {
            Method method = cls.getMethod(C0142a.f160a, new Class[0]);
            if (Modifier.isStatic(method.getModifiers())) {
                return (C0115j) method.invoke(null, new Object[0]);
            }
            throw new Exception(cls.getName() + ".suite() must be static");
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
