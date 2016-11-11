package org.p122a.p123a.p152c.p161f;

import java.lang.reflect.InvocationTargetException;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.c.f.a */
public class C2978a {
    private C2978a() {
    }

    public static <T> T m16912a(T t) {
        T t2 = null;
        if (t != null) {
            if (t instanceof Cloneable) {
                try {
                    try {
                        t2 = t.getClass().getMethod("clone", (Class[]) null).invoke(t, (Object[]) null);
                    } catch (InvocationTargetException e) {
                        Throwable cause = e.getCause();
                        if (cause instanceof CloneNotSupportedException) {
                            throw ((CloneNotSupportedException) cause);
                        }
                        throw new Error("Unexpected exception", cause);
                    } catch (IllegalAccessException e2) {
                        throw new IllegalAccessError(e2.getMessage());
                    }
                } catch (NoSuchMethodException e3) {
                    throw new NoSuchMethodError(e3.getMessage());
                }
            }
            throw new CloneNotSupportedException();
        }
        return t2;
    }

    public static Object m16913b(Object obj) {
        return C2978a.m16912a(obj);
    }
}
