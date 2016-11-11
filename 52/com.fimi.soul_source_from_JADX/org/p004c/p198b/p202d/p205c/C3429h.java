package org.p004c.p198b.p202d.p205c;

import java.util.ArrayList;
import java.util.List;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3404g;
import org.p004c.p187f.p192a.C3524d;

/* renamed from: org.c.b.d.c.h */
public class C3429h extends C3377k {
    private final C3377k f15940a;
    private final Object f15941b;
    private final List<C3524d> f15942c;

    public C3429h(C3377k c3377k, List<C3524d> list, Object obj) {
        this.f15940a = c3377k;
        this.f15942c = list;
        this.f15941b = obj;
    }

    public void m18731a() {
        List arrayList = new ArrayList();
        try {
            this.f15940a.m18589a();
            for (C3524d a : this.f15942c) {
                try {
                    a.m19183a(this.f15941b, new Object[0]);
                } catch (Throwable th) {
                    arrayList.add(th);
                }
            }
        } catch (Throwable th2) {
            arrayList.add(th2);
        }
        C3404g.m18670a(arrayList);
    }
}
