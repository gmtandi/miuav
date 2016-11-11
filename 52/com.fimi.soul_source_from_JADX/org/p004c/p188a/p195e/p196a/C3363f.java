package org.p004c.p188a.p195e.p196a;

import java.util.ArrayList;
import java.util.List;
import org.p004c.p188a.p195e.C3356e;
import org.p004c.p188a.p195e.C3359g;
import org.p004c.p188a.p195e.C3371d;

/* renamed from: org.c.a.e.a.f */
public class C3363f extends C3356e {
    private Class<?> f15864a;

    public C3363f(Class<?> cls) {
        this.f15864a = cls;
    }

    public List<C3359g> m18513a(C3371d c3371d) {
        Object[] enumConstants = this.f15864a.getEnumConstants();
        List<C3359g> arrayList = new ArrayList();
        for (Object obj : enumConstants) {
            arrayList.add(C3359g.m18493a(obj.toString(), obj));
        }
        return arrayList;
    }
}
