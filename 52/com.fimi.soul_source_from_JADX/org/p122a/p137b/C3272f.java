package org.p122a.p137b;

import org.p122a.p137b.p182b.C3249h;
import org.p122a.p137b.p182b.C3251j;
import org.p122a.p137b.p182b.C3252b;
import org.p122a.p137b.p183c.C3267b;

/* renamed from: org.a.b.f */
public class C3272f {
    private final C3249h f15780a;
    private final C3267b f15781b;

    public C3272f() {
        this(new C3252b());
    }

    public C3272f(C3251j c3251j) {
        this.f15781b = new C3267b();
        this.f15780a = c3251j.m18002a(this.f15781b);
    }

    public void m18102a(C2478b c2478b, byte[] bArr) {
        try {
            this.f15781b.m18080a(bArr);
            c2478b.m14171a(this.f15780a);
        } finally {
            this.f15780a.m17961y();
        }
    }
}
