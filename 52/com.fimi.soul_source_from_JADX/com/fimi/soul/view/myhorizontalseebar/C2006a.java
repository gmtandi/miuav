package com.fimi.soul.view.myhorizontalseebar;

import android.util.Log;

/* renamed from: com.fimi.soul.view.myhorizontalseebar.a */
class C2006a<T extends C2007c<T>> implements C2005b<T> {
    private static final String f10900a = "FinitePool";
    private final C2008d<T> f10901b;
    private final int f10902c;
    private final boolean f10903d;
    private T f10904e;
    private int f10905f;

    C2006a(C2008d<T> c2008d) {
        this.f10901b = c2008d;
        this.f10902c = 0;
        this.f10903d = true;
    }

    C2006a(C2008d<T> c2008d, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The pool limit must be > 0");
        }
        this.f10901b = c2008d;
        this.f10902c = i;
        this.f10903d = false;
    }

    public T m12885a() {
        T t;
        if (this.f10904e != null) {
            T t2 = this.f10904e;
            this.f10904e = (C2007c) t2.m12887a();
            this.f10905f--;
            t = t2;
        } else {
            t = this.f10901b.m12891a();
        }
        if (t != null) {
            t.m12888a(null);
            t.m12889a(false);
            this.f10901b.m12892a(t);
        }
        return t;
    }

    public void m12886a(T t) {
        if (t.m12890b()) {
            Log.w(f10900a, "Element is already in pool: " + t);
            return;
        }
        if (this.f10903d || this.f10905f < this.f10902c) {
            this.f10905f++;
            t.m12888a(this.f10904e);
            t.m12889a(true);
            this.f10904e = t;
        }
        this.f10901b.m12893b(t);
    }
}
