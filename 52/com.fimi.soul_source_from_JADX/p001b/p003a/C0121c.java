package p001b.p003a;

import p001b.p002b.C0115j;
import p001b.p002b.C0139n;

/* renamed from: b.a.c */
public class C0121c extends C0120d {
    private int f128b;

    public C0121c(C0115j c0115j, int i) {
        super(c0115j);
        if (i < 0) {
            throw new IllegalArgumentException("Repetition count must be >= 0");
        }
        this.f128b = i;
    }

    public int m194a() {
        return super.m190a() * this.f128b;
    }

    public void m195a(C0139n c0139n) {
        for (int i = 0; i < this.f128b && !c0139n.m312f(); i++) {
            super.m191a(c0139n);
        }
    }

    public String toString() {
        return super.toString() + "(repeated)";
    }
}
