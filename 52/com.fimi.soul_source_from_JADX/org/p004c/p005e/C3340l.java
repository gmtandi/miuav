package org.p004c.p005e;

import java.util.Comparator;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p187f.p192a.C3526f;
import org.p004c.p198b.p199a.C3385a;
import org.p004c.p198b.p201c.C3399a;
import org.p004c.p198b.p201c.C3400b;
import org.p004c.p198b.p201c.C3401c;
import org.p004c.p198b.p202d.C3421b;

/* renamed from: org.c.e.l */
public abstract class C3340l {
    public static C3340l m18428a(Class<?> cls) {
        return new C3399a(cls);
    }

    public static C3340l m18429a(Class<?> cls, String str) {
        return C3340l.m18428a((Class) cls).m18437a(C3507d.m19085a((Class) cls, str));
    }

    public static C3340l m18430a(Class<?> cls, Throwable th) {
        return C3340l.m18432a(new C3421b(cls, th));
    }

    public static C3340l m18431a(C3336a c3336a, Class<?>... clsArr) {
        try {
            return C3340l.m18432a(c3336a.m18410a(new C3385a(true), (Class[]) clsArr));
        } catch (C3526f e) {
            throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
        }
    }

    public static C3340l m18432a(C3319s c3319s) {
        return new C3514m(c3319s);
    }

    public static C3340l m18433a(Class<?>... clsArr) {
        return C3340l.m18431a(C3513k.m19124b(), (Class[]) clsArr);
    }

    public static C3340l m18434b(Class<?> cls) {
        return new C3399a(cls, false);
    }

    public C3340l m18435a(Comparator<C3507d> comparator) {
        return new C3401c(this, comparator);
    }

    public C3340l m18436a(C3323a c3323a) {
        return new C3400b(this, c3323a);
    }

    public C3340l m18437a(C3507d c3507d) {
        return m18436a(C3323a.m18368b(c3507d));
    }

    public abstract C3319s m18438a();
}
