package com.fimi.soul.view;

import android.database.DataSetObserver;

class ah extends DataSetObserver {
    final /* synthetic */ HorizontalListView f10667a;

    ah(HorizontalListView horizontalListView) {
        this.f10667a = horizontalListView;
    }

    public void onChanged() {
        this.f10667a.f10371o = true;
        this.f10667a.f10352A = false;
        this.f10667a.m12617f();
        this.f10667a.invalidate();
        this.f10667a.requestLayout();
    }

    public void onInvalidated() {
        this.f10667a.f10352A = false;
        this.f10667a.m12617f();
        this.f10667a.m12608c();
        this.f10667a.invalidate();
        this.f10667a.requestLayout();
    }
}
