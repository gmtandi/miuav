package it.sephiroth.android.library.widget;

import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: it.sephiroth.android.library.widget.l */
public class C2833l implements Runnable {
    private static final int f14550b = 200;
    private static final int f14551c = 1;
    private static final int f14552d = 2;
    private static final int f14553e = 3;
    private static final int f14554f = 4;
    private static final int f14555g = 5;
    final /* synthetic */ AbsHListView f14556a;
    private int f14557h;
    private int f14558i;
    private int f14559j;
    private int f14560k;
    private int f14561l;
    private final int f14562m;
    private int f14563n;

    C2833l(AbsHListView absHListView) {
        this.f14556a = absHListView;
        this.f14562m = ViewConfiguration.get(absHListView.getContext()).getScaledFadingEdgeLength();
    }

    public void m16384a() {
        this.f14556a.removeCallbacks(this);
    }

    void m16385a(int i) {
        m16384a();
        if (this.f14556a.aJ) {
            this.f14556a.ak = new C2834m(this, i);
            return;
        }
        int childCount = this.f14556a.getChildCount();
        if (childCount != 0) {
            int i2 = this.f14556a.av;
            childCount = (childCount + i2) - 1;
            int max = Math.max(0, Math.min(this.f14556a.getCount() - 1, i));
            if (max < i2) {
                childCount = (i2 - max) + f14551c;
                this.f14557h = f14552d;
            } else if (max > childCount) {
                childCount = (max - childCount) + f14551c;
                this.f14557h = f14551c;
            } else {
                m16389b(max, -1, f14550b);
                return;
            }
            if (childCount > 0) {
                this.f14561l = f14550b / childCount;
            } else {
                this.f14561l = f14550b;
            }
            this.f14558i = max;
            this.f14559j = -1;
            this.f14560k = -1;
            this.f14556a.f14385s.m16044a((Runnable) this);
        }
    }

    void m16386a(int i, int i2) {
        m16384a();
        if (i2 == -1) {
            m16385a(i);
        } else if (this.f14556a.aJ) {
            this.f14556a.ak = new C2835n(this, i, i2);
        } else {
            int childCount = this.f14556a.getChildCount();
            if (childCount != 0) {
                int i3 = this.f14556a.av;
                childCount = (childCount + i3) - 1;
                int max = Math.max(0, Math.min(this.f14556a.getCount() - 1, i));
                if (max < i3) {
                    childCount -= i2;
                    if (childCount >= f14551c) {
                        i3 = (i3 - max) + f14551c;
                        childCount--;
                        if (childCount < i3) {
                            this.f14557h = f14554f;
                        } else {
                            this.f14557h = f14552d;
                            childCount = i3;
                        }
                    } else {
                        return;
                    }
                } else if (max > childCount) {
                    int i4 = i2 - i3;
                    if (i4 >= f14551c) {
                        i3 = (max - childCount) + f14551c;
                        childCount = i4 - 1;
                        if (childCount < i3) {
                            this.f14557h = f14553e;
                        } else {
                            this.f14557h = f14551c;
                            childCount = i3;
                        }
                    } else {
                        return;
                    }
                } else {
                    m16389b(max, i2, f14550b);
                    return;
                }
                if (childCount > 0) {
                    this.f14561l = f14550b / childCount;
                } else {
                    this.f14561l = f14550b;
                }
                this.f14558i = max;
                this.f14559j = i2;
                this.f14560k = -1;
                this.f14556a.f14385s.m16044a((Runnable) this);
            }
        }
    }

    void m16387a(int i, int i2, int i3) {
        m16384a();
        if (this.f14556a.aJ) {
            this.f14556a.ak = new C2836o(this, i, i2, i3);
            return;
        }
        int childCount = this.f14556a.getChildCount();
        if (childCount != 0) {
            int paddingLeft = this.f14556a.getPaddingLeft() + i2;
            this.f14558i = Math.max(0, Math.min(this.f14556a.getCount() - 1, i));
            this.f14563n = paddingLeft;
            this.f14559j = -1;
            this.f14560k = -1;
            this.f14557h = f14555g;
            int i4 = this.f14556a.av;
            int i5 = (i4 + childCount) - 1;
            if (this.f14558i < i4) {
                paddingLeft = i4 - this.f14558i;
            } else if (this.f14558i > i5) {
                paddingLeft = this.f14558i - i5;
            } else {
                this.f14556a.m16123a(this.f14556a.getChildAt(this.f14558i - i4).getLeft() - paddingLeft, i3, false);
                return;
            }
            float f = ((float) paddingLeft) / ((float) childCount);
            if (f >= C2020f.f10933c) {
                i3 = (int) (((float) i3) / f);
            }
            this.f14561l = i3;
            this.f14560k = -1;
            this.f14556a.f14385s.m16044a((Runnable) this);
        }
    }

    void m16388b(int i, int i2) {
        m16387a(i, i2, f14550b);
    }

    void m16389b(int i, int i2, int i3) {
        int i4 = this.f14556a.av;
        int childCount = (this.f14556a.getChildCount() + i4) - 1;
        int i5 = this.f14556a.f14373M.left;
        int width = this.f14556a.getWidth() - this.f14556a.f14373M.right;
        if (i < i4 || i > childCount) {
            Log.w("AbsListView", "scrollToVisible called with targetPos " + i + " not visible [" + i4 + ", " + childCount + "]");
        }
        if (i2 < i4 || i2 > childCount) {
            i2 = -1;
        }
        View childAt = this.f14556a.getChildAt(i - i4);
        int left = childAt.getLeft();
        childCount = childAt.getRight();
        childCount = childCount > width ? childCount - width : 0;
        if (left < i5) {
            childCount = left - i5;
        }
        if (childCount != 0) {
            if (i2 >= 0) {
                View childAt2 = this.f14556a.getChildAt(i2 - i4);
                left = childAt2.getLeft();
                i4 = childAt2.getRight();
                int abs = Math.abs(childCount);
                if (childCount < 0 && i4 + abs > width) {
                    childCount = Math.max(0, i4 - width);
                } else if (childCount > 0 && left - abs < i5) {
                    childCount = Math.min(0, left - i5);
                }
            }
            this.f14556a.m16149f(childCount, i3);
        }
    }

    public void run() {
        int i = 0;
        int width = this.f14556a.getWidth();
        int i2 = this.f14556a.av;
        View childAt;
        int width2;
        int max;
        switch (this.f14557h) {
            case f14551c /*1*/:
                i = this.f14556a.getChildCount() - 1;
                i2 += i;
                if (i < 0) {
                    return;
                }
                if (i2 == this.f14560k) {
                    this.f14556a.f14385s.m16044a((Runnable) this);
                    return;
                }
                childAt = this.f14556a.getChildAt(i);
                this.f14556a.m16123a((i2 < this.f14556a.aO + -1 ? Math.max(this.f14556a.f14373M.right, this.f14562m) : this.f14556a.f14373M.right) + (childAt.getWidth() - (width - childAt.getLeft())), this.f14561l, true);
                this.f14560k = i2;
                if (i2 < this.f14558i) {
                    this.f14556a.f14385s.m16044a((Runnable) this);
                }
            case f14552d /*2*/:
                if (i2 == this.f14560k) {
                    this.f14556a.f14385s.m16044a((Runnable) this);
                    return;
                }
                childAt = this.f14556a.getChildAt(0);
                if (childAt != null) {
                    this.f14556a.m16123a(childAt.getLeft() - (i2 > 0 ? Math.max(this.f14562m, this.f14556a.f14373M.left) : this.f14556a.f14373M.left), this.f14561l, true);
                    this.f14560k = i2;
                    if (i2 > this.f14558i) {
                        this.f14556a.f14385s.m16044a((Runnable) this);
                    }
                }
            case f14553e /*3*/:
                width = this.f14556a.getChildCount();
                if (i2 != this.f14559j && width > f14551c && width + i2 < this.f14556a.aO) {
                    width = i2 + f14551c;
                    if (width == this.f14560k) {
                        this.f14556a.f14385s.m16044a((Runnable) this);
                        return;
                    }
                    View childAt2 = this.f14556a.getChildAt(f14551c);
                    width2 = childAt2.getWidth();
                    i2 = childAt2.getLeft();
                    max = Math.max(this.f14556a.f14373M.right, this.f14562m);
                    if (width < this.f14559j) {
                        this.f14556a.m16123a(Math.max(0, (i2 + width2) - max), this.f14561l, true);
                        this.f14560k = width;
                        this.f14556a.f14385s.m16044a((Runnable) this);
                    } else if (i2 > max) {
                        this.f14556a.m16123a(i2 - max, this.f14561l, true);
                    }
                }
            case f14554f /*4*/:
                i = this.f14556a.getChildCount() - 2;
                if (i >= 0) {
                    i2 += i;
                    if (i2 == this.f14560k) {
                        this.f14556a.f14385s.m16044a((Runnable) this);
                        return;
                    }
                    childAt = this.f14556a.getChildAt(i);
                    width2 = childAt.getWidth();
                    i = childAt.getLeft();
                    max = width - i;
                    int max2 = Math.max(this.f14556a.f14373M.left, this.f14562m);
                    this.f14560k = i2;
                    if (i2 > this.f14559j) {
                        this.f14556a.m16123a(-(max - max2), this.f14561l, true);
                        this.f14556a.f14385s.m16044a((Runnable) this);
                        return;
                    }
                    width -= max2;
                    i += width2;
                    if (width > i) {
                        this.f14556a.m16123a(-(width - i), this.f14561l, true);
                    }
                }
            case f14555g /*5*/:
                if (this.f14560k == i2) {
                    this.f14556a.f14385s.m16044a((Runnable) this);
                    return;
                }
                this.f14560k = i2;
                width = this.f14556a.getChildCount();
                width2 = this.f14558i;
                max = (i2 + width) - 1;
                if (width2 < i2) {
                    i = (i2 - width2) + f14551c;
                } else if (width2 > max) {
                    i = width2 - max;
                }
                float min = Math.min(Math.abs(((float) i) / ((float) width)), C2020f.f10933c);
                if (width2 < i2) {
                    this.f14556a.m16123a((int) (((float) (-this.f14556a.getWidth())) * min), (int) (min * ((float) this.f14561l)), true);
                    this.f14556a.f14385s.m16044a((Runnable) this);
                } else if (width2 > max) {
                    this.f14556a.m16123a((int) (((float) this.f14556a.getWidth()) * min), (int) (min * ((float) this.f14561l)), true);
                    this.f14556a.f14385s.m16044a((Runnable) this);
                } else {
                    i = this.f14556a.getChildAt(width2 - i2).getLeft() - this.f14563n;
                    this.f14556a.m16123a(i, (int) (((float) this.f14561l) * (((float) Math.abs(i)) / ((float) this.f14556a.getWidth()))), true);
                }
            default:
        }
    }
}
