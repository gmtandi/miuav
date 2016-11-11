package p147m.framework.ui.widget.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* renamed from: m.framework.ui.widget.pulltorefresh.b */
public abstract class C2877b {
    private BaseAdapter f14659a;

    private void m16592a(BaseAdapter baseAdapter) {
        this.f14659a = baseAdapter;
    }

    public abstract int m16594a();

    public abstract View m16595a(int i, int i2, View view, ViewGroup viewGroup);

    public abstract View m16596a(int i, View view, ViewGroup viewGroup);

    public abstract Object m16597a(int i, int i2);

    public abstract String m16598a(int i);

    public abstract int m16599b(int i);

    public void m16600b() {
        this.f14659a.notifyDataSetChanged();
    }
}
