package p147m.framework.ui.widget.slidingmenu;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: m.framework.ui.widget.slidingmenu.b */
public abstract class C2896b {
    private ArrayList<C2907m> f14713a;

    public C2896b(SlidingMenu slidingMenu) {
        this.f14713a = new ArrayList();
    }

    private C2907m m16687c(int i) {
        if (this.f14713a == null) {
            return null;
        }
        Iterator it = this.f14713a.iterator();
        while (it.hasNext()) {
            C2907m c2907m = (C2907m) it.next();
            if (c2907m != null && c2907m.f14735a == i) {
                return c2907m;
            }
        }
        return null;
    }

    public View m16688a() {
        return null;
    }

    public abstract View m16689a(int i, ViewGroup viewGroup);

    public abstract View m16690a(C2908n c2908n, ViewGroup viewGroup);

    C2907m m16691a(int i) {
        return (C2907m) this.f14713a.get(i);
    }

    protected C2908n m16692a(int i, int i2) {
        return ((C2907m) this.f14713a.get(i)).m16708b(i2);
    }

    public void m16693a(int i, String str) {
        C2907m c = m16687c(i);
        if (c == null) {
            c = new C2907m();
            c.f14735a = i;
            this.f14713a.add(c);
        }
        c.f14736b = str;
    }

    public void m16694a(int i, C2908n c2908n) {
        if (c2908n != null) {
            C2907m c = m16687c(i);
            if (c != null) {
                c.m16707a(c2908n);
            }
        }
    }

    void m16695a(C2907m c2907m) {
        if (c2907m != null) {
            C2907m c = m16687c(c2907m.f14735a);
            if (c == null) {
                this.f14713a.add(c2907m);
                return;
            }
            int indexOf = this.f14713a.indexOf(c);
            this.f14713a.remove(indexOf);
            this.f14713a.add(indexOf, c2907m);
        }
    }

    public void m16696a(C2908n c2908n) {
    }

    public void m16697a(boolean z) {
    }

    int m16698b() {
        return this.f14713a == null ? 0 : this.f14713a.size();
    }

    protected String m16699b(int i) {
        return ((C2907m) this.f14713a.get(i)).f14736b;
    }

    public C2908n m16700b(int i, int i2) {
        C2907m c = m16687c(i);
        return c == null ? null : c.m16706a(i2);
    }

    public boolean m16701b(C2908n c2908n) {
        return false;
    }
}
