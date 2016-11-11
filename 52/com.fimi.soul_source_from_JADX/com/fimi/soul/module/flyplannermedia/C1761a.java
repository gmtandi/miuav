package com.fimi.soul.module.flyplannermedia;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.fimi.soul.p087b.C1228q;

/* renamed from: com.fimi.soul.module.flyplannermedia.a */
class C1761a implements OnScrollListener {
    final /* synthetic */ DroneBaseMediaFragment f8691a;
    private int f8692b;
    private int f8693c;

    C1761a(DroneBaseMediaFragment droneBaseMediaFragment) {
        this.f8691a = droneBaseMediaFragment;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f8692b = i;
        this.f8693c = i2;
        this.f8691a.f8647a.m8482a(absListView, i, i2, i3);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.f8691a.f8647a.m8481a(absListView, i);
        if (i == 1 && this.f8692b == 0 && this.f8691a.f8656j && this.f8691a.f8647a.m8496e() != C1228q.Choose) {
            if (!this.f8691a.f8657k) {
                if (this.f8691a.f8654h) {
                    this.f8691a.f8651e.setText(this.f8691a.m11400i());
                } else {
                    this.f8691a.f8651e.setText(this.f8691a.m11398h());
                }
                this.f8691a.f8657k = true;
            }
            this.f8691a.f8651e.setVisibility(0);
        } else {
            this.f8691a.f8651e.setVisibility(8);
        }
        if (this.f8691a.m11404m().m8855k().getCurDirFileList().size() > this.f8691a.f8650d.getCount() && this.f8692b + this.f8693c >= this.f8691a.f8650d.getCount() && i == 0) {
            this.f8691a.m11381a(this.f8691a.f8650d);
        }
    }
}
