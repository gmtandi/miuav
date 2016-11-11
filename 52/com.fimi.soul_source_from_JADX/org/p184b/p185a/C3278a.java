package org.p184b.p185a;

import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p184b.C3274r;
import org.p184b.C3275p;
import org.p184b.C3277m;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.a */
public class C3278a<T> extends C3277m<T> {
    private final Iterable<C3275p<? super T>> f15785a;

    public C3278a(Iterable<C3275p<? super T>> iterable) {
        this.f15785a = iterable;
    }

    @C3315n
    public static <T> C3275p<T> m18113a(Iterable<C3275p<? super T>> iterable) {
        return new C3278a(iterable);
    }

    @C3315n
    public static <T> C3275p<T> m18114a(C3275p<? super T> c3275p, C3275p<? super T> c3275p2) {
        Iterable arrayList = new ArrayList(2);
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<T> m18115a(C3275p<? super T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3) {
        Iterable arrayList = new ArrayList(3);
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<T> m18116a(C3275p<? super T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4) {
        Iterable arrayList = new ArrayList(4);
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<T> m18117a(C3275p<? super T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4, C3275p<? super T> c3275p5) {
        Iterable arrayList = new ArrayList(5);
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        arrayList.add(c3275p5);
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<T> m18118a(C3275p<? super T> c3275p, C3275p<? super T> c3275p2, C3275p<? super T> c3275p3, C3275p<? super T> c3275p4, C3275p<? super T> c3275p5, C3275p<? super T> c3275p6) {
        Iterable arrayList = new ArrayList(6);
        arrayList.add(c3275p);
        arrayList.add(c3275p2);
        arrayList.add(c3275p3);
        arrayList.add(c3275p4);
        arrayList.add(c3275p5);
        arrayList.add(c3275p6);
        return C3278a.m18113a(arrayList);
    }

    @C3315n
    public static <T> C3275p<T> m18119a(C3275p<? super T>... c3275pArr) {
        return C3278a.m18113a(Arrays.asList(c3275pArr));
    }

    public void m18120a(C3300k c3300k) {
        c3300k.m18226b("(", " and ", ")", this.f15785a);
    }

    public boolean m18121b(Object obj, C3300k c3300k) {
        for (C3274r c3274r : this.f15785a) {
            if (!c3274r.m18107a(obj)) {
                c3300k.m18225a(c3274r).m18222a(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                c3274r.m18106a(obj, c3300k);
                return false;
            }
        }
        return true;
    }
}
