package org.p004c.p198b.p202d;

import java.util.Arrays;
import java.util.List;

@Deprecated
/* renamed from: org.c.b.d.d */
public class C3432d extends Exception {
    private static final long serialVersionUID = 1;
    private final List<Throwable> f15946a;

    public C3432d(String str) {
        this(new Exception(str));
    }

    public C3432d(List<Throwable> list) {
        this.f15946a = list;
    }

    public C3432d(Throwable... thArr) {
        this(Arrays.asList(thArr));
    }

    public List<Throwable> m18733a() {
        return this.f15946a;
    }
}
