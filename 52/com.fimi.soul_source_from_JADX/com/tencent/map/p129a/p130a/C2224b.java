package com.tencent.map.p129a.p130a;

import com.tencent.map.p131b.C2255h;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.tencent.map.a.a.b */
public class C2224b {
    private int f11524a;
    private int f11525b;
    private int f11526c;
    private int f11527d;

    public C2224b(int i, int i2, int i3, int i4) {
        this.f11524a = 1;
        this.f11525b = 0;
        this.f11526c = 12;
        this.f11527d = 1;
        C2255h.m13481a("argument: " + this.f11524a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f11527d + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.f11525b);
        if (i >= 0 && i <= 1) {
            this.f11524a = i;
        }
        if (i2 >= 0 && i2 <= 1) {
            this.f11527d = i2;
        }
        if (i3 == 0 || i3 == 3 || i3 == 4 || i3 == 7) {
            this.f11525b = i3;
        }
        if (this.f11527d == 0) {
            this.f11525b = 0;
        }
        this.f11526c = i4;
    }

    public int m13352a() {
        return this.f11524a;
    }

    public void m13353a(int i) {
    }

    public void m13354a(C2226d c2226d) {
    }

    public void m13355a(byte[] bArr, int i) {
    }

    public int m13356b() {
        return this.f11525b;
    }

    public int m13357c() {
        return this.f11527d;
    }
}
