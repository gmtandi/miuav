package org.p122a.p123a.p162e;

import org.p122a.p123a.p180o.C3234a;

/* renamed from: org.a.a.e.d */
public class C2998d implements Cloneable {
    public static final C2998d f14971a;
    private final int f14972b;
    private final int f14973c;

    static {
        f14971a = new C2999e().m17030a();
    }

    C2998d(int i, int i2) {
        this.f14972b = i;
        this.f14973c = i2;
    }

    public static C2998d m17024a(int i) {
        return new C2998d(C3234a.m17890b(i, "Max line length"), -1);
    }

    public static C2999e m17025a(C2998d c2998d) {
        C3234a.m17886a((Object) c2998d, "Message constraints");
        return new C2999e().m17032b(c2998d.m17028b()).m17031a(c2998d.m17027a());
    }

    public static C2999e m17026d() {
        return new C2999e();
    }

    public int m17027a() {
        return this.f14972b;
    }

    public int m17028b() {
        return this.f14973c;
    }

    protected C2998d m17029c() {
        return (C2998d) super.clone();
    }

    protected /* synthetic */ Object clone() {
        return m17029c();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[maxLineLength=").append(this.f14972b).append(", maxHeaderCount=").append(this.f14973c).append("]");
        return stringBuilder.toString();
    }
}
