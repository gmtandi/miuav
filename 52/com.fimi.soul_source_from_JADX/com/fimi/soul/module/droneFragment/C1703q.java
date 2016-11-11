package com.fimi.soul.module.droneFragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.entity.FlyActionBean;

/* renamed from: com.fimi.soul.module.droneFragment.q */
class C1703q implements OnItemClickListener {
    final /* synthetic */ C1697k f8326a;

    C1703q(C1697k c1697k) {
        this.f8326a = c1697k;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        FlyActionBean j2 = C1247f.m8565k().m8582j();
        this.f8326a.f8313s.setText((String) adapterView.getItemAtPosition(i));
        if (i == 0) {
            i++;
        } else if (i == 1) {
            i--;
        }
        if (j2 != null) {
            j2.setYaw_mode(i);
        }
        this.f8326a.f8316v.dismiss();
        this.f8326a.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_down);
    }
}
