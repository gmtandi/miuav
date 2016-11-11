package com.fimi.soul.media.gallery;

import android.database.DataSetObserver;

class aa extends DataSetObserver {
    final /* synthetic */ HorizontalListView f7784a;

    aa(HorizontalListView horizontalListView) {
        this.f7784a = horizontalListView;
    }

    public void onChanged() {
        this.f7784a.f7756o = true;
        this.f7784a.f7737A = false;
        this.f7784a.m10732f();
        this.f7784a.invalidate();
        this.f7784a.requestLayout();
    }

    public void onInvalidated() {
        this.f7784a.f7737A = false;
        this.f7784a.m10732f();
        this.f7784a.m10723c();
        this.f7784a.invalidate();
        this.f7784a.requestLayout();
    }
}
