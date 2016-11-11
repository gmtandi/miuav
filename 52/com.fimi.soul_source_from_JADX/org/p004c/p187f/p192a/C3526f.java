package org.p004c.p187f.p192a;

import java.util.Arrays;
import java.util.List;

/* renamed from: org.c.f.a.f */
public class C3526f extends Exception {
    private static final long serialVersionUID = 1;
    private final List<Throwable> f16074a;

    public C3526f(String str) {
        this(new Exception(str));
    }

    public C3526f(Throwable th) {
        this(Arrays.asList(new Throwable[]{th}));
    }

    public C3526f(List<Throwable> list) {
        this.f16074a = list;
    }

    public List<Throwable> m19199a() {
        return this.f16074a;
    }
}
