package org.p004c.p198b;

import org.p004c.C3459c;

/* renamed from: org.c.b.f */
public class C3449f extends C3447d {
    public Object f15968a;

    public C3449f(double d) {
        this.f15968a = Double.valueOf(d);
    }

    public C3449f(float f) {
        this.f15968a = Float.valueOf(f);
    }

    protected void m18808a(Object obj, Object obj2) {
        if (obj instanceof Double) {
            C3459c.m18876b(((Double) obj).doubleValue(), ((Double) obj2).doubleValue(), ((Double) this.f15968a).doubleValue());
        } else {
            C3459c.m18877b(((Float) obj).floatValue(), ((Float) obj2).floatValue(), ((Float) this.f15968a).floatValue());
        }
    }
}
