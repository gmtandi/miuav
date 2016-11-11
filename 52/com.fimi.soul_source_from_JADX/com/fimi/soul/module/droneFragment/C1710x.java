package com.fimi.soul.module.droneFragment;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.fimi.soul.module.droneFragment.x */
public class C1710x {
    public static int f8346a;
    public static int f8347b;
    private static AtomicInteger f8348c;
    private static AtomicInteger f8349d;
    private static AtomicInteger f8350e;
    private static AtomicBoolean f8351f;

    static {
        f8346a = 0;
        f8347b = 1;
        f8348c = new AtomicInteger(f8346a);
        f8349d = new AtomicInteger(f8346a);
        f8350e = new AtomicInteger(f8346a);
        f8351f = new AtomicBoolean(true);
    }

    public static AtomicInteger m11158a() {
        return f8348c;
    }

    public static void m11159a(int i) {
        f8350e.set(i);
    }

    public static void m11160a(boolean z) {
        f8351f.set(z);
    }

    public static AtomicInteger m11161b() {
        return f8349d;
    }

    public static void m11162b(int i) {
        f8349d.set(i);
    }

    public static AtomicInteger m11163c() {
        return f8350e;
    }

    public static void m11164c(int i) {
        f8348c.set(i);
    }

    public static AtomicBoolean m11165d() {
        return f8351f;
    }
}
