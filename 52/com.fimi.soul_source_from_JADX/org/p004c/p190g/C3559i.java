package org.p004c.p190g;

import java.util.Collections;
import java.util.List;
import org.p004c.p187f.p192a.C3528l;

/* renamed from: org.c.g.i */
public class C3559i implements C3552j {
    private static final List<Exception> f16108a;

    static {
        f16108a = Collections.emptyList();
    }

    public List<Exception> m19281a(C3528l c3528l) {
        return c3528l.m19224g() ? f16108a : Collections.singletonList(new Exception("The class " + c3528l.m19222e() + " is not public."));
    }
}
