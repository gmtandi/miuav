package org.p004c.p198b.p199a;

import java.util.Arrays;
import org.p004c.p005e.C3319s;
import org.p004c.p187f.p192a.C3384i;

/* renamed from: org.c.b.a.a */
public class C3385a extends C3384i {
    private final boolean f15884a;

    public C3385a(boolean z) {
        this.f15884a = z;
    }

    protected C3390f m18616a() {
        return new C3390f();
    }

    public C3319s m18617a(Class<?> cls) {
        for (C3384i c : Arrays.asList(new C3384i[]{m18620d(), m18619c(), m18621e(), m18618b(), m18616a()})) {
            C3319s c2 = c.m18613c(cls);
            if (c2 != null) {
                return c2;
            }
        }
        return null;
    }

    protected C3389e m18618b() {
        return new C3389e();
    }

    protected C3386b m18619c() {
        return new C3386b(this);
    }

    protected C3387c m18620d() {
        return new C3387c();
    }

    protected C3384i m18621e() {
        return this.f15884a ? new C3392h() : new C3391g();
    }
}
