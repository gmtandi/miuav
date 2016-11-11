package org.p122a.p123a.p162e;

import org.p122a.p123a.p150a.C2912b;
import org.p122a.p123a.p180o.C3234a;

@C2912b
/* renamed from: org.a.a.e.h */
public class C3002h implements Cloneable {
    public static final C3002h f14978a;
    private final int f14979b;
    private final boolean f14980c;
    private final int f14981d;
    private final boolean f14982e;
    private final boolean f14983f;

    static {
        f14978a = new C3003i().m17045a();
    }

    C3002h(int i, boolean z, int i2, boolean z2, boolean z3) {
        this.f14979b = i;
        this.f14980c = z;
        this.f14981d = i2;
        this.f14982e = z2;
        this.f14983f = z3;
    }

    public static C3003i m17037a(C3002h c3002h) {
        C3234a.m17886a((Object) c3002h, "Socket config");
        return new C3003i().m17046a(c3002h.m17039a()).m17047a(c3002h.m17040b()).m17048b(c3002h.m17041c()).m17049b(c3002h.m17042d()).m17050c(c3002h.m17043e());
    }

    public static C3003i m17038g() {
        return new C3003i();
    }

    public int m17039a() {
        return this.f14979b;
    }

    public boolean m17040b() {
        return this.f14980c;
    }

    public int m17041c() {
        return this.f14981d;
    }

    protected /* synthetic */ Object clone() {
        return m17044f();
    }

    public boolean m17042d() {
        return this.f14982e;
    }

    public boolean m17043e() {
        return this.f14983f;
    }

    protected C3002h m17044f() {
        return (C3002h) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[soTimeout=").append(this.f14979b).append(", soReuseAddress=").append(this.f14980c).append(", soLinger=").append(this.f14981d).append(", soKeepAlive=").append(this.f14982e).append(", tcpNoDelay=").append(this.f14983f).append("]");
        return stringBuilder.toString();
    }
}
