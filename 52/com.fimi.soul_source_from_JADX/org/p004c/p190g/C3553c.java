package org.p004c.p190g;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.p004c.p187f.p192a.C3528l;

/* renamed from: org.c.g.c */
public final class C3553c implements C3552j {
    private static final List<C3555e<?>> f16106a;

    static {
        f16106a = Arrays.asList(new C3555e[]{new C3556f(), new C3558h(), new C3557g()});
    }

    public List<Exception> m19267a(C3528l c3528l) {
        List<Exception> arrayList = new ArrayList();
        for (C3555e b : f16106a) {
            arrayList.addAll(b.m19271b(c3528l));
        }
        return arrayList;
    }
}
