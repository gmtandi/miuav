package org.p004c.p207d;

import java.lang.management.ManagementFactory;
import java.util.List;
import org.p004c.p005e.C3507d;
import org.p004c.p187f.p192a.C3377k;

/* renamed from: org.c.d.a */
public class C3461a implements C3460r {
    private final C3460r f15972a;
    private final boolean f15973b;

    public C3461a(C3460r c3460r) {
        this(c3460r, ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    C3461a(C3460r c3460r, List<String> list) {
        this.f15972a = c3460r;
        this.f15973b = C3461a.m18906a(list);
    }

    private static boolean m18906a(List<String> list) {
        for (String str : list) {
            if ("-Xdebug".equals(str)) {
                return true;
            }
            if (str.startsWith("-agentlib:jdwp")) {
                return true;
            }
        }
        return false;
    }

    public C3377k m18907a(C3377k c3377k, C3507d c3507d) {
        return this.f15973b ? c3377k : this.f15972a.m18905a(c3377k, c3507d);
    }

    public boolean m18908a() {
        return this.f15973b;
    }
}
