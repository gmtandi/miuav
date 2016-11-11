package com.fimi.kernel.p073a;

import com.fimi.kernel.p084e.C1178q;

/* renamed from: com.fimi.kernel.a.b */
public class C1089b {
    public static final int f5060a = 0;
    public static final int f5061b = -1;
    private int f5062c;
    private String f5063d;

    public C1089b(int i, String str) {
        this.f5062c = i;
        this.f5063d = str;
    }

    public C1089b(String str) {
        C1089b c1089b = (C1089b) C1178q.m8207a(str, getClass());
        this.f5062c = c1089b.m7666a();
        this.f5063d = c1089b.m7669b();
    }

    public int m7666a() {
        return this.f5062c;
    }

    public void m7667a(int i) {
        this.f5062c = i;
    }

    public void m7668a(String str) {
        this.f5063d = str;
    }

    public String m7669b() {
        return this.f5063d;
    }

    public String m7670c() {
        return C1178q.m8208a((Object) this);
    }
}
