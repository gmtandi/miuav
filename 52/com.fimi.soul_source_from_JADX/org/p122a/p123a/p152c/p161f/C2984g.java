package org.p122a.p123a.p152c.p161f;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.c.f.g */
public class C2984g implements C2983f {
    private final Method f14914a;

    public C2984g() {
        try {
            this.f14914a = Class.forName("java.net.IDN").getMethod("toUnicode", new Class[]{String.class});
        } catch (Throwable e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        }
    }

    public String m16927a(String str) {
        Throwable e;
        try {
            return (String) this.f14914a.invoke(null, new Object[]{str});
        } catch (Throwable e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }
}
