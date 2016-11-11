package p147m.framework.ui.widget.slidingmenu;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: m.framework.ui.widget.slidingmenu.m */
final class C2907m {
    int f14735a;
    String f14736b;
    private ArrayList<C2908n> f14737c;

    C2907m() {
        this.f14737c = new ArrayList();
    }

    int m16705a() {
        return this.f14737c == null ? 0 : this.f14737c.size();
    }

    C2908n m16706a(int i) {
        if (this.f14737c == null) {
            return null;
        }
        Iterator it = this.f14737c.iterator();
        while (it.hasNext()) {
            C2908n c2908n = (C2908n) it.next();
            if (c2908n != null && c2908n.f14739b == i) {
                return c2908n;
            }
        }
        return null;
    }

    void m16707a(C2908n c2908n) {
        if (c2908n != null) {
            C2908n a = m16706a(c2908n.f14739b);
            c2908n.f14738a = this.f14735a;
            if (a == null) {
                this.f14737c.add(c2908n);
                return;
            }
            int indexOf = this.f14737c.indexOf(a);
            this.f14737c.remove(indexOf);
            this.f14737c.add(indexOf, c2908n);
        }
    }

    C2908n m16708b(int i) {
        return (C2908n) this.f14737c.get(i);
    }
}
