package p147m.framework.ui.widget.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* renamed from: m.framework.ui.widget.pulltorefresh.d */
public class C2879d extends BaseAdapter {
    private C2883h f14663a;

    public C2879d(C2883h c2883h) {
        this.f14663a = c2883h;
    }

    public int getCount() {
        return this.f14663a.m16616i();
    }

    public Object getItem(int i) {
        return this.f14663a.m16615c(i);
    }

    public long getItemId(int i) {
        return this.f14663a.m16614b(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.f14663a.m16612a(i, view, viewGroup);
    }
}
