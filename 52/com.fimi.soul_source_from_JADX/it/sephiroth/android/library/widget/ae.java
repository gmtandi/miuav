package it.sephiroth.android.library.widget;

import java.util.ArrayList;

public class ae {
    private static final int f14461d = 5;
    private static ArrayList<ae> f14462e;
    public af f14463a;
    public GroupMetadata f14464b;
    public int f14465c;

    static {
        f14462e = new ArrayList(f14461d);
    }

    private ae() {
    }

    static ae m16284a(int i, int i2, int i3, int i4, GroupMetadata groupMetadata, int i5) {
        ae d = m16286d();
        d.f14463a = af.m16291a(i2, i3, i4, i);
        d.f14464b = groupMetadata;
        d.f14465c = i5;
        return d;
    }

    private void m16285c() {
        if (this.f14463a != null) {
            this.f14463a.m16296b();
            this.f14463a = null;
        }
        this.f14464b = null;
        this.f14465c = 0;
    }

    private static ae m16286d() {
        ae aeVar;
        synchronized (f14462e) {
            if (f14462e.size() > 0) {
                aeVar = (ae) f14462e.remove(0);
                aeVar.m16285c();
            } else {
                aeVar = new ae();
            }
        }
        return aeVar;
    }

    public void m16287a() {
        m16285c();
        synchronized (f14462e) {
            if (f14462e.size() < f14461d) {
                f14462e.add(this);
            }
        }
    }

    public boolean m16288b() {
        return this.f14464b != null;
    }
}
