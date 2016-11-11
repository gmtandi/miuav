package org.p004c.p187f.p192a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.p198b.C3456m;
import org.p004c.p198b.p202d.p203a.C3405b;

/* renamed from: org.c.f.a.g */
public class C3404g extends Exception {
    private static final long serialVersionUID = 1;
    private final List<Throwable> f15908a;

    public C3404g(List<Throwable> list) {
        this.f15908a = new ArrayList(list);
    }

    public static void m18670a(List<Throwable> list) {
        if (!list.isEmpty()) {
            if (list.size() == 1) {
                throw C3456m.m18827a((Throwable) list.get(0));
            }
            throw new C3405b(list);
        }
    }

    public List<Throwable> m18671a() {
        return Collections.unmodifiableList(this.f15908a);
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder(String.format("There were %d errors:", new Object[]{Integer.valueOf(this.f15908a.size())}));
        for (Throwable th : this.f15908a) {
            stringBuilder.append(String.format("\n  %s(%s)", new Object[]{th.getClass().getName(), th.getMessage()}));
        }
        return stringBuilder.toString();
    }
}
