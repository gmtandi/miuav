package it.sephiroth.android.library.widget;

import android.database.DataSetObserver;

public class ad extends DataSetObserver {
    final /* synthetic */ ExpandableHListConnector f14460a;

    protected ad(ExpandableHListConnector expandableHListConnector) {
        this.f14460a = expandableHListConnector;
    }

    public void onChanged() {
        this.f14460a.m16171a(true, true);
        this.f14460a.notifyDataSetChanged();
    }

    public void onInvalidated() {
        this.f14460a.m16171a(true, true);
        this.f14460a.notifyDataSetInvalidated();
    }
}
