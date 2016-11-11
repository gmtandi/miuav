package com.fimi.kernel.p076b.p080d;

import com.fimi.kernel.p076b.C1109g;

/* renamed from: com.fimi.kernel.b.d.e */
public class C1142e extends C1109g {
    public static final String f5201a = "UTF-8";
    public static final int f5202b = 10240;
    private String f5203c;
    private int f5204d;
    private boolean f5205e;
    private boolean f5206f;

    public C1142e() {
        this.f5203c = f5201a;
        this.f5204d = f5202b;
        this.f5205e = false;
        this.f5206f = false;
    }

    public void m7891a(boolean z) {
        this.f5206f = z;
    }

    public void m7892b(int i) {
        this.f5204d = i;
    }

    public void m7893b(String str) {
        this.f5203c = str;
    }

    public void m7894b(boolean z) {
        this.f5205e = z;
    }

    public boolean m7895c() {
        return this.f5205e;
    }

    public boolean m7896d() {
        return this.f5206f;
    }

    public int m7897e() {
        return this.f5204d;
    }

    public String m7898f() {
        return this.f5203c;
    }
}
