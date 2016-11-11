package org.p004c.p198b.p201c;

import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3340l;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p005e.p006a.C3490f;
import org.p004c.p198b.p202d.C3421b;

/* renamed from: org.c.b.c.b */
public final class C3400b extends C3340l {
    private final C3340l f15902a;
    private final C3323a f15903b;

    public C3400b(C3340l c3340l, C3323a c3323a) {
        this.f15902a = c3340l;
        this.f15903b = c3323a;
    }

    public C3319s m18661a() {
        try {
            Object a = this.f15902a.m18438a();
            this.f15903b.m18371a(a);
            return a;
        } catch (C3490f e) {
            return new C3421b(C3323a.class, new Exception(String.format("No tests found matching %s from %s", new Object[]{this.f15903b.m18369a(), this.f15902a.toString()})));
        }
    }
}
