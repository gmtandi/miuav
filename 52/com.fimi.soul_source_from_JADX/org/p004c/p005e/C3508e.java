package org.p004c.p005e;

import org.p004c.p005e.p006a.C3323a;
import org.p004c.p198b.C3402c;
import org.p122a.p123a.C2915a;

/* renamed from: org.c.e.e */
class C3508e {
    C3508e() {
    }

    public static C3323a m19104a(Class<? extends C3328f> cls, C3510h c3510h) {
        return C3508e.m19107a((Class) cls).m18395a(c3510h);
    }

    public static C3323a m19105a(String str, C3510h c3510h) {
        return C3508e.m19108a(str).m18395a(c3510h);
    }

    public static C3323a m19106a(C3340l c3340l, String str) {
        C3507d d = c3340l.m18438a().m18317d();
        String[] split = str.contains("=") ? str.split("=", 2) : new String[]{str, C2915a.f14760f};
        return C3508e.m19105a(split[0], new C3510h(d, split[1]));
    }

    static C3328f m19107a(Class<? extends C3328f> cls) {
        try {
            return (C3328f) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new C3509g(e);
        }
    }

    static C3328f m19108a(String str) {
        try {
            return C3508e.m19107a(C3402c.m18663a(str).asSubclass(C3328f.class));
        } catch (Exception e) {
            throw new C3509g(e);
        }
    }
}
