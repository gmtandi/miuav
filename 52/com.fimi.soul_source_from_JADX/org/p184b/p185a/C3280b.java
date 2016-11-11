package org.p184b.p185a;

import java.util.ArrayList;
import java.util.Arrays;
import org.p184b.C3275p;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.b */
public class C3280b<T> extends C3279p<T> {
    public C3280b(Iterable<C3275p<? super T>> iterable) {
        super(iterable);
    }

    @C3315n
    public static <T> C3280b<T> m18126a(Iterable<C3275p<? super T>> iterable) {
        return new C3280b(iterable);
    }

    @C3315n
    public static <T> C3280b<T> m18127a(C3275p<T> c3275p, C3275p<? super T> c3275p2) {
        Iterable arrayList = new ArrayList();
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        return C3280b.m18126a(arrayList);
    }

    @C3315n
    public static <T> C3280b<T> m18128a(C3275p<T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3) {
        Iterable arrayList = new ArrayList();
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        return C3280b.m18126a(arrayList);
    }

    @C3315n
    public static <T> C3280b<T> m18129a(C3275p<T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4) {
        Iterable arrayList = new ArrayList();
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        return C3280b.m18126a(arrayList);
    }

    @C3315n
    public static <T> C3280b<T> m18130a(C3275p<T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4, C3275p<? super T> c3275p5) {
        Iterable arrayList = new ArrayList();
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        arrayList.add(c3275p5);
        return C3280b.m18126a(arrayList);
    }

    @C3315n
    public static <T> C3280b<T> m18131a(C3275p<T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4, C3275p<? super T> c3275p5, C3275p<? super T> c3275p6) {
        Iterable arrayList = new ArrayList();
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        arrayList.add(c3275p5);
        arrayList.add(c3275p6);
        return C3280b.m18126a(arrayList);
    }

    @C3315n
    public static <T> C3280b<T> m18132a(C3275p<? super T>... c3275pArr) {
        return C3280b.m18126a(Arrays.asList(c3275pArr));
    }

    public void m18133a(C3300k c3300k) {
        m18134a(c3300k, "or");
    }

    public boolean m18135a(Object obj) {
        return m18125a(obj, true);
    }
}
