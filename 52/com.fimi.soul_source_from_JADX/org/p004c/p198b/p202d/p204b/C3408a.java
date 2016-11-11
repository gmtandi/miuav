package org.p004c.p198b.p202d.p204b;

import java.lang.annotation.Annotation;
import java.util.List;
import org.p004c.p187f.p192a.C3522c;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p207d.C3460r;
import org.p004c.p207d.C3470i;

/* renamed from: org.c.b.d.b.a */
public class C3408a {
    public static final C3408a f15913a;
    public static final C3408a f15914b;
    public static final C3408a f15915c;
    public static final C3408a f15916d;
    private final Class<? extends Annotation> f15917e;
    private final boolean f15918f;
    private final List<C3411l> f15919g;

    static {
        f15913a = C3408a.m18679a().m18693a(new C3412d()).m18693a(new C3417i()).m18693a(new C3416h()).m18693a(new C3414f()).m18694b();
        f15914b = C3408a.m18682b().m18693a(new C3415g()).m18693a(new C3416h()).m18693a(new C3413e()).m18694b();
        f15915c = C3408a.m18679a().m18692a().m18693a(new C3412d()).m18693a(new C3417i()).m18693a(new C3416h()).m18693a(new C3419k()).m18694b();
        f15916d = C3408a.m18682b().m18692a().m18693a(new C3415g()).m18693a(new C3416h()).m18693a(new C3418j()).m18694b();
    }

    C3408a(C3410c c3410c) {
        this.f15917e = c3410c.f15920a;
        this.f15918f = c3410c.f15921b;
        this.f15919g = c3410c.f15922c;
    }

    private static C3410c m18679a() {
        return new C3410c(null);
    }

    private void m18680a(C3522c<?> c3522c, List<Throwable> list) {
        for (C3411l a : this.f15919g) {
            a.m18695a(c3522c, this.f15917e, list);
        }
    }

    private static C3410c m18682b() {
        return new C3410c(null);
    }

    private static boolean m18685d(C3522c<?> c3522c) {
        return C3408a.m18687f(c3522c) || C3408a.m18686e(c3522c);
    }

    private static boolean m18686e(C3522c<?> c3522c) {
        return C3460r.class.isAssignableFrom(c3522c.m19167e());
    }

    private static boolean m18687f(C3522c<?> c3522c) {
        return C3470i.class.isAssignableFrom(c3522c.m19167e());
    }

    public void m18688a(C3528l c3528l, List<Throwable> list) {
        for (C3522c a : this.f15918f ? c3528l.m19217b(this.f15917e) : c3528l.m19220c(this.f15917e)) {
            m18680a(a, (List) list);
        }
    }
}
