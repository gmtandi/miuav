package it.sephiroth.android.library.widget;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import it.sephiroth.android.library.widget.AbsHListView.LayoutParams;
import java.util.ArrayList;
import java.util.List;

/* renamed from: it.sephiroth.android.library.widget.p */
public class C2837p {
    final /* synthetic */ AbsHListView f14573a;
    private C2838q f14574b;
    private int f14575c;
    private View[] f14576d;
    private ArrayList<View>[] f14577e;
    private int f14578f;
    private ArrayList<View> f14579g;
    private ArrayList<View> f14580h;
    private SparseArrayCompat<View> f14581i;

    public C2837p(AbsHListView absHListView) {
        this.f14573a = absHListView;
        this.f14576d = new View[0];
    }

    @SuppressLint({"NewApi"})
    private void m16392f() {
        int i = 0;
        int length = this.f14576d.length;
        int i2 = this.f14578f;
        ArrayList[] arrayListArr = this.f14577e;
        for (int i3 = 0; i3 < i2; i3++) {
            ArrayList arrayList = arrayListArr[i3];
            int size = arrayList.size();
            int i4 = size - length;
            size--;
            int i5 = 0;
            while (i5 < i4) {
                int i6 = size - 1;
                this.f14573a.removeDetachedView((View) arrayList.remove(size), false);
                i5++;
                size = i6;
            }
        }
        if (this.f14581i != null) {
            while (i < this.f14581i.size()) {
                if (!((View) this.f14581i.valueAt(i)).hasTransientState()) {
                    this.f14581i.removeAt(i);
                    i--;
                }
                i++;
            }
        }
    }

    public void m16393a() {
        int i;
        int i2 = 0;
        int size;
        if (this.f14578f == 1) {
            ArrayList arrayList = this.f14579g;
            size = arrayList.size();
            for (i = 0; i < size; i++) {
                ((View) arrayList.get(i)).forceLayout();
            }
        } else {
            size = this.f14578f;
            for (int i3 = 0; i3 < size; i3++) {
                ArrayList arrayList2 = this.f14577e[i3];
                int size2 = arrayList2.size();
                for (i = 0; i < size2; i++) {
                    ((View) arrayList2.get(i)).forceLayout();
                }
            }
        }
        if (this.f14581i != null) {
            i = this.f14581i.size();
            while (i2 < i) {
                ((View) this.f14581i.valueAt(i2)).forceLayout();
                i2++;
            }
        }
    }

    public void m16394a(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
        }
        ArrayList[] arrayListArr = new ArrayList[i];
        for (int i2 = 0; i2 < i; i2++) {
            arrayListArr[i2] = new ArrayList();
        }
        this.f14578f = i;
        this.f14579g = arrayListArr[0];
        this.f14577e = arrayListArr;
    }

    public void m16395a(int i, int i2) {
        if (this.f14576d.length < i) {
            this.f14576d = new View[i];
        }
        this.f14575c = i2;
        View[] viewArr = this.f14576d;
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = this.f14573a.getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (!(layoutParams == null || layoutParams.f14323a == -2)) {
                viewArr[i3] = childAt;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void m16396a(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.f14326d = i;
            int i2 = layoutParams.f14323a;
            boolean hasTransientState = VERSION.SDK_INT >= 16 ? view.hasTransientState() : false;
            if (!m16399b(i2) || hasTransientState) {
                if (i2 != -2 || hasTransientState) {
                    if (this.f14580h == null) {
                        this.f14580h = new ArrayList();
                    }
                    this.f14580h.add(view);
                }
                if (hasTransientState) {
                    if (this.f14581i == null) {
                        this.f14581i = new SparseArrayCompat();
                    }
                    view.onStartTemporaryDetach();
                    this.f14581i.put(i, view);
                    return;
                }
                return;
            }
            view.onStartTemporaryDetach();
            if (this.f14578f == 1) {
                this.f14579g.add(view);
            } else {
                this.f14577e[i2].add(view);
            }
            if (VERSION.SDK_INT >= 14) {
                view.setAccessibilityDelegate(null);
            }
            if (this.f14574b != null) {
                this.f14574b.m16407a(view);
            }
        }
    }

    void m16397a(List<View> list) {
        if (this.f14578f == 1) {
            list.addAll(this.f14579g);
            return;
        }
        int i = this.f14578f;
        ArrayList[] arrayListArr = this.f14577e;
        for (int i2 = 0; i2 < i; i2++) {
            list.addAll(arrayListArr[i2]);
        }
    }

    public void m16398b() {
        int size;
        int i;
        if (this.f14578f == 1) {
            ArrayList arrayList = this.f14579g;
            size = arrayList.size();
            for (i = 0; i < size; i++) {
                this.f14573a.removeDetachedView((View) arrayList.remove((size - 1) - i), false);
            }
        } else {
            size = this.f14578f;
            for (int i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = this.f14577e[i2];
                int size2 = arrayList2.size();
                for (i = 0; i < size2; i++) {
                    this.f14573a.removeDetachedView((View) arrayList2.remove((size2 - 1) - i), false);
                }
            }
        }
        if (this.f14581i != null) {
            this.f14581i.clear();
        }
    }

    public boolean m16399b(int i) {
        return i >= 0;
    }

    public View m16400c(int i) {
        int i2 = i - this.f14575c;
        View[] viewArr = this.f14576d;
        if (i2 < 0 || i2 >= viewArr.length) {
            return null;
        }
        View view = viewArr[i2];
        viewArr[i2] = null;
        return view;
    }

    void m16401c() {
        if (this.f14581i != null) {
            this.f14581i.clear();
        }
    }

    View m16402d(int i) {
        if (this.f14581i == null) {
            return null;
        }
        int indexOfKey = this.f14581i.indexOfKey(i);
        if (indexOfKey < 0) {
            return null;
        }
        View view = (View) this.f14581i.valueAt(indexOfKey);
        this.f14581i.removeAt(indexOfKey);
        return view;
    }

    public void m16403d() {
        if (this.f14580h != null) {
            int size = this.f14580h.size();
            for (int i = 0; i < size; i++) {
                this.f14573a.removeDetachedView((View) this.f14580h.get(i), false);
            }
            this.f14580h.clear();
        }
    }

    View m16404e(int i) {
        if (this.f14578f == 1) {
            return AbsHListView.m16091a(this.f14579g, i);
        }
        int itemViewType = this.f14573a.f14362B.getItemViewType(i);
        return (itemViewType < 0 || itemViewType >= this.f14577e.length) ? null : AbsHListView.m16091a(this.f14577e[itemViewType], i);
    }

    @SuppressLint({"NewApi"})
    public void m16405e() {
        View[] viewArr = this.f14576d;
        boolean z = this.f14574b != null;
        boolean z2 = this.f14578f > 1;
        ArrayList arrayList = this.f14579g;
        for (int length = viewArr.length - 1; length >= 0; length--) {
            View view = viewArr[length];
            if (view != null) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                int i = layoutParams.f14323a;
                viewArr[length] = null;
                boolean hasTransientState = VERSION.SDK_INT >= 16 ? view.hasTransientState() : false;
                if (!m16399b(i) || hasTransientState) {
                    if (i != -2 || hasTransientState) {
                        this.f14573a.removeDetachedView(view, false);
                    }
                    if (hasTransientState) {
                        if (this.f14581i == null) {
                            this.f14581i = new SparseArrayCompat();
                        }
                        this.f14581i.put(this.f14575c + length, view);
                    }
                } else {
                    ArrayList arrayList2 = z2 ? this.f14577e[i] : arrayList;
                    view.onStartTemporaryDetach();
                    layoutParams.f14326d = this.f14575c + length;
                    arrayList2.add(view);
                    if (VERSION.SDK_INT >= 14) {
                        view.setAccessibilityDelegate(null);
                    }
                    if (z) {
                        this.f14574b.m16407a(view);
                    }
                    arrayList = arrayList2;
                }
            }
        }
        m16392f();
    }

    void m16406f(int i) {
        int i2;
        int size;
        int i3;
        if (this.f14578f == 1) {
            ArrayList arrayList = this.f14579g;
            size = arrayList.size();
            for (i3 = 0; i3 < size; i3++) {
                ((View) arrayList.get(i3)).setDrawingCacheBackgroundColor(i);
            }
        } else {
            size = this.f14578f;
            for (i2 = 0; i2 < size; i2++) {
                ArrayList arrayList2 = this.f14577e[i2];
                int size2 = arrayList2.size();
                for (i3 = 0; i3 < size2; i3++) {
                    ((View) arrayList2.get(i3)).setDrawingCacheBackgroundColor(i);
                }
            }
        }
        for (View view : this.f14576d) {
            if (view != null) {
                view.setDrawingCacheBackgroundColor(i);
            }
        }
    }
}
