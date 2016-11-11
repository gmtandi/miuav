package it.sephiroth.android.library.widget;

import android.widget.ExpandableListView;
import java.util.ArrayList;

class af {
    public static final int f14466a = 1;
    public static final int f14467b = 2;
    private static final int f14468g = 5;
    private static ArrayList<af> f14469h;
    public int f14470c;
    public int f14471d;
    int f14472e;
    public int f14473f;

    static {
        f14469h = new ArrayList(f14468g);
    }

    private af() {
    }

    static af m16289a(int i) {
        return m16291a(f14467b, i, 0, 0);
    }

    static af m16290a(int i, int i2) {
        return m16291a(f14466a, i, i2, 0);
    }

    static af m16291a(int i, int i2, int i3, int i4) {
        af d = m16294d();
        d.f14473f = i;
        d.f14470c = i2;
        d.f14471d = i3;
        d.f14472e = i4;
        return d;
    }

    static af m16292a(long j) {
        if (j == ExpandableHListView.aZ) {
            return null;
        }
        af d = m16294d();
        d.f14470c = ExpandableListView.getPackedPositionGroup(j);
        if (ExpandableListView.getPackedPositionType(j) == f14466a) {
            d.f14473f = f14466a;
            d.f14471d = ExpandableListView.getPackedPositionChild(j);
            return d;
        }
        d.f14473f = f14467b;
        return d;
    }

    private void m16293c() {
        this.f14470c = 0;
        this.f14471d = 0;
        this.f14472e = 0;
        this.f14473f = 0;
    }

    private static af m16294d() {
        af afVar;
        synchronized (f14469h) {
            if (f14469h.size() > 0) {
                afVar = (af) f14469h.remove(0);
                afVar.m16293c();
            } else {
                afVar = new af();
            }
        }
        return afVar;
    }

    long m16295a() {
        return this.f14473f == f14466a ? ExpandableListView.getPackedPositionForChild(this.f14470c, this.f14471d) : ExpandableListView.getPackedPositionForGroup(this.f14470c);
    }

    public void m16296b() {
        synchronized (f14469h) {
            if (f14469h.size() < f14468g) {
                f14469h.add(this);
            }
        }
    }
}
