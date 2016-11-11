package org.p184b.p185a;

import java.util.ArrayList;
import org.p184b.C3275p;
import org.p184b.C3281t;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.c */
public class C3282c<T> extends C3281t<T> {
    private final C3275p<? super T> f15789a;

    public C3282c(C3275p<? super T> c3275p) {
        this.f15789a = c3275p;
    }

    @C3315n
    public static <LHS> C3283d<LHS> m18139c(C3275p<? super LHS> c3275p) {
        return new C3283d(c3275p);
    }

    @C3315n
    public static <LHS> C3284e<LHS> m18140d(C3275p<? super LHS> c3275p) {
        return new C3284e(c3275p);
    }

    private ArrayList<C3275p<? super T>> m18141e(C3275p<? super T> c3275p) {
        ArrayList<C3275p<? super T>> arrayList = new ArrayList();
        arrayList.add(this.f15789a);
        arrayList.add(c3275p);
        return arrayList;
    }

    public C3282c<T> m18142a(C3275p<? super T> c3275p) {
        return new C3282c(new C3278a(m18141e(c3275p)));
    }

    public void m18143a(C3300k c3300k) {
        c3300k.m18225a(this.f15789a);
    }

    public C3282c<T> m18144b(C3275p<? super T> c3275p) {
        return new C3282c(new C3280b(m18141e(c3275p)));
    }

    protected boolean m18145b(T t, C3300k c3300k) {
        if (this.f15789a.m18107a(t)) {
            return true;
        }
        this.f15789a.m18106a(t, c3300k);
        return false;
    }
}
