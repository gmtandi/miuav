package org.p004c.p198b.p202d;

import org.p004c.p005e.C0130c;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;
import p001b.p002b.C0115j;
import p001b.p002b.C0125b;
import p001b.p002b.C0136k;
import p001b.p002b.C0138m;

/* renamed from: org.c.b.d.g */
final class C3435g implements C0138m {
    private final C3495d f15948a;

    private C3435g(C3495d c3495d) {
        this.f15948a = c3495d;
    }

    private C3507d m18744c(C0115j c0115j) {
        return c0115j instanceof C0130c ? ((C0130c) c0115j).m213d() : C3507d.m19085a(m18745d(c0115j), m18746e(c0115j));
    }

    private Class<? extends C0115j> m18745d(C0115j c0115j) {
        return c0115j.getClass();
    }

    private String m18746e(C0115j c0115j) {
        return c0115j instanceof C0136k ? ((C0136k) c0115j).m288j() : c0115j.toString();
    }

    public void m18747a(C0115j c0115j) {
        this.f15948a.m19064d(m18744c(c0115j));
    }

    public void m18748a(C0115j c0115j, C0125b c0125b) {
        m18749a(c0115j, (Throwable) c0125b);
    }

    public void m18749a(C0115j c0115j, Throwable th) {
        this.f15948a.m19054a(new C3493a(m18744c(c0115j), th));
    }

    public void m18750b(C0115j c0115j) {
        this.f15948a.m19060b(m18744c(c0115j));
    }
}
