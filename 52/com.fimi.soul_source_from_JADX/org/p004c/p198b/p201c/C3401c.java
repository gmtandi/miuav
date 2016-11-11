package org.p004c.p198b.p201c;

import java.util.Comparator;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3340l;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C3491h;

/* renamed from: org.c.b.c.c */
public class C3401c extends C3340l {
    private final C3340l f15904a;
    private final Comparator<C3507d> f15905b;

    public C3401c(C3340l c3340l, Comparator<C3507d> comparator) {
        this.f15904a = c3340l;
        this.f15905b = comparator;
    }

    public C3319s m18662a() {
        C3319s a = this.f15904a.m18438a();
        new C3491h(this.f15905b).m19043a(a);
        return a;
    }
}
