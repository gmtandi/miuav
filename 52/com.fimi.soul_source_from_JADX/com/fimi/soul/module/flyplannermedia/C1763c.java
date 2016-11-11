package com.fimi.soul.module.flyplannermedia;

import com.fimi.soul.C1205R;
import com.fimi.soul.p087b.C1229r;

/* renamed from: com.fimi.soul.module.flyplannermedia.c */
class C1763c implements C1229r {
    final /* synthetic */ DroneBaseMediaFragment f8695a;

    C1763c(DroneBaseMediaFragment droneBaseMediaFragment) {
        this.f8695a = droneBaseMediaFragment;
    }

    public void m11449a(int i) {
        this.f8695a.m11396g().setText(String.format(this.f8695a.getActivity().getString(C1205R.string.media_you_select_size), new Object[]{Integer.valueOf(i)}));
    }
}
