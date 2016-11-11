package org.p004c.p005e.p006a;

import java.util.Iterator;
import org.p004c.p005e.C3507d;

/* renamed from: org.c.e.a.c */
class C3488c extends C3323a {
    final /* synthetic */ C3507d f16012b;

    C3488c(C3507d c3507d) {
        this.f16012b = c3507d;
    }

    public String m19038a() {
        return String.format("Method %s", new Object[]{this.f16012b.m19091a()});
    }

    public boolean m19039a(C3507d c3507d) {
        if (c3507d.m19096d()) {
            return this.f16012b.equals(c3507d);
        }
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            if (m19039a((C3507d) it.next())) {
                return true;
            }
        }
        return false;
    }
}
