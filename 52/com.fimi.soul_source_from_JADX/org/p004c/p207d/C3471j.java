package org.p004c.p207d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;

/* renamed from: org.c.d.j */
public class C3471j implements C3460r {
    private static final C3471j f15988a;
    private List<C3460r> f15989b;

    static {
        f15988a = new C3471j(Collections.emptyList());
    }

    private C3471j(List<C3460r> list) {
        this.f15989b = list;
    }

    public static C3471j m18947a() {
        return f15988a;
    }

    public static C3471j m18948a(C3460r c3460r) {
        return C3471j.m18947a().m18950b(c3460r);
    }

    public C3377k m18949a(C3377k c3377k, C3507d c3507d) {
        for (C3460r a : this.f15989b) {
            c3377k = a.m18905a(c3377k, c3507d);
        }
        return c3377k;
    }

    public C3471j m18950b(C3460r c3460r) {
        List arrayList = new ArrayList();
        arrayList.add(c3460r);
        arrayList.addAll(this.f15989b);
        return new C3471j(arrayList);
    }
}
