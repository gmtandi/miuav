package org.p004c.p187f;

import java.util.Collections;
import java.util.List;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p187f.p192a.C3384i;
import org.p004c.p187f.p192a.C3526f;
import org.p004c.p198b.p199a.C3385a;

/* renamed from: org.c.f.o */
public class C3321o extends C3320j<C3319s> {
    private final List<C3319s> f15831a;

    protected C3321o(Class<?> cls, List<C3319s> list) {
        super(cls);
        this.f15831a = Collections.unmodifiableList(list);
    }

    public C3321o(Class<?> cls, C3384i c3384i) {
        this(c3384i, cls, C3321o.m18351b(cls));
    }

    protected C3321o(Class<?> cls, Class<?>[] clsArr) {
        this(new C3385a(true), cls, clsArr);
    }

    protected C3321o(C3384i c3384i, Class<?> cls, Class<?>[] clsArr) {
        this((Class) cls, c3384i.m18611a((Class) cls, (Class[]) clsArr));
    }

    public C3321o(C3384i c3384i, Class<?>[] clsArr) {
        this(null, c3384i.m18611a(null, (Class[]) clsArr));
    }

    public static C3319s m18350a() {
        try {
            return new C3321o((Class) null, new Class[0]);
        } catch (C3526f e) {
            throw new RuntimeException("This shouldn't be possible");
        }
    }

    private static Class<?>[] m18351b(Class<?> cls) {
        C3549p c3549p = (C3549p) cls.getAnnotation(C3549p.class);
        if (c3549p != null) {
            return c3549p.m19264a();
        }
        throw new C3526f(String.format("class '%s' must have a SuiteClasses annotation", new Object[]{cls.getName()}));
    }

    protected C3507d m18352a(C3319s c3319s) {
        return c3319s.m18317d();
    }

    protected void m18354a(C3319s c3319s, C3495d c3495d) {
        c3319s.m18316a(c3495d);
    }

    protected List<C3319s> m18355c() {
        return this.f15831a;
    }

    protected /* synthetic */ C3507d m18356d(Object obj) {
        return m18352a((C3319s) obj);
    }
}
