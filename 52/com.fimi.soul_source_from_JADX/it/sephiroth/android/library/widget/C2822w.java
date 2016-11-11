package it.sephiroth.android.library.widget;

import android.database.DataSetObserver;
import android.os.Parcelable;

/* renamed from: it.sephiroth.android.library.widget.w */
class C2822w extends DataSetObserver {
    private Parcelable f14530a;
    final /* synthetic */ AdapterView f14531b;

    C2822w(AdapterView adapterView) {
        this.f14531b = adapterView;
        this.f14530a = null;
    }

    public void m16370a() {
        this.f14530a = null;
    }

    public void onChanged() {
        this.f14531b.aJ = true;
        this.f14531b.aP = this.f14531b.aO;
        this.f14531b.aO = this.f14531b.getAdapter().getCount();
        if (!this.f14531b.getAdapter().hasStableIds() || this.f14530a == null || this.f14531b.aP != 0 || this.f14531b.aO <= 0) {
            this.f14531b.m16067A();
        } else {
            this.f14531b.onRestoreInstanceState(this.f14530a);
            this.f14530a = null;
        }
        this.f14531b.m16075w();
        this.f14531b.requestLayout();
    }

    public void onInvalidated() {
        this.f14531b.aJ = true;
        if (this.f14531b.getAdapter().hasStableIds()) {
            this.f14530a = this.f14531b.onSaveInstanceState();
        }
        this.f14531b.aP = this.f14531b.aO;
        this.f14531b.aO = 0;
        this.f14531b.aM = -1;
        this.f14531b.aN = Long.MIN_VALUE;
        this.f14531b.aK = -1;
        this.f14531b.aL = Long.MIN_VALUE;
        this.f14531b.aA = false;
        this.f14531b.m16075w();
        this.f14531b.requestLayout();
    }
}
