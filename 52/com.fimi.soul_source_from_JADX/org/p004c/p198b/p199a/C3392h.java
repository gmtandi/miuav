package org.p004c.p198b.p199a;

import org.p004c.p005e.C3319s;
import org.p004c.p187f.p192a.C3384i;
import org.p004c.p198b.p202d.C3444p;
import p001b.p008c.C0142a;

/* renamed from: org.c.b.a.h */
public class C3392h extends C3384i {
    public C3319s m18632a(Class<?> cls) {
        return m18633b(cls) ? new C3444p(cls) : null;
    }

    public boolean m18633b(Class<?> cls) {
        try {
            cls.getMethod(C0142a.f160a, new Class[0]);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
