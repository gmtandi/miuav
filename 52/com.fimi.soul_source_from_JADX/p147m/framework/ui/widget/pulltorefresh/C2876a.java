package p147m.framework.ui.widget.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/* renamed from: m.framework.ui.widget.pulltorefresh.a */
class C2876a extends BaseAdapter {
    final /* synthetic */ GroupListView f14657a;
    private final /* synthetic */ C2877b f14658b;

    C2876a(GroupListView groupListView, C2877b c2877b) {
        this.f14657a = groupListView;
        this.f14658b = c2877b;
    }

    private int[] m16591a(int i) {
        int[] iArr = new int[]{-1, -2};
        int a = this.f14658b.m16594a();
        int i2 = 0;
        for (int i3 = 0; i3 < a; i3++) {
            int b = this.f14658b.m16599b(i3) + 1;
            if (i2 + b > i) {
                iArr[0] = i3;
                iArr[1] = (i - i2) - 1;
                break;
            }
            i2 += b;
        }
        return iArr;
    }

    public int getCount() {
        int i = 0;
        int i2 = 0;
        while (i < this.f14658b.m16594a()) {
            i2 += this.f14658b.m16599b(i) + 1;
            i++;
        }
        return i2;
    }

    public Object getItem(int i) {
        int[] a = m16591a(i);
        int i2 = a[0];
        int i3 = a[1];
        if (i2 > -1) {
            if (i3 == -1) {
                return this.f14658b.m16598a(i3);
            }
            if (i3 > -1) {
                return this.f14658b.m16597a(i2, i3);
            }
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int[] a = m16591a(i);
        int i2 = a[0];
        int i3 = a[1];
        C2878c c2878c;
        if (view == null) {
            c2878c = new C2878c();
            c2878c.f14660a = new LinearLayout(viewGroup.getContext());
            c2878c.f14660a.setOrientation(1);
            c2878c.f14660a.setTag(c2878c);
            if (i2 > -1) {
                if (i3 == -1) {
                    c2878c.f14661b = this.f14658b.m16596a(i2, c2878c.f14661b, c2878c.f14660a);
                    c2878c.f14660a.addView(c2878c.f14661b);
                } else if (i3 > -1) {
                    c2878c.f14662c = this.f14658b.m16595a(i2, i3, c2878c.f14662c, c2878c.f14660a);
                    c2878c.f14660a.addView(c2878c.f14662c);
                }
            }
            return c2878c.f14660a;
        }
        c2878c = (C2878c) view.getTag();
        if (i2 <= -1) {
            return view;
        }
        if (i3 == -1) {
            c2878c.f14661b = this.f14658b.m16596a(i2, c2878c.f14661b, c2878c.f14660a);
            if (c2878c.f14662c == null) {
                return view;
            }
            c2878c.f14660a.removeView(c2878c.f14662c);
            return view;
        } else if (i3 <= -1) {
            return view;
        } else {
            c2878c.f14662c = this.f14658b.m16595a(i2, i3, c2878c.f14662c, c2878c.f14660a);
            if (c2878c.f14661b == null) {
                return view;
            }
            c2878c.f14660a.removeView(c2878c.f14661b);
            return view;
        }
    }
}
