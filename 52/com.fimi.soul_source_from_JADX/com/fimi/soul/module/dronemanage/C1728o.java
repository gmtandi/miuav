package com.fimi.soul.module.dronemanage;

import android.view.View;
import android.view.View.OnClickListener;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.entity.FlyActionBean;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.module.dronemanage.o */
class C1728o implements OnClickListener {
    final /* synthetic */ FlyActionBean f8508a;
    final /* synthetic */ FlightMapFragment f8509b;

    C1728o(FlightMapFragment flightMapFragment, FlyActionBean flyActionBean) {
        this.f8509b = flightMapFragment;
        this.f8508a = flyActionBean;
    }

    public void onClick(View view) {
        C1253k a = C1253k.m8598a(this.f8509b.getActivity());
        switch (this.f8508a.getModelType()) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a.m8600a(1);
                this.f8509b.g.m9589a(C1584h.UPWAYPOINT);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a.m8600a(2);
                this.f8509b.g.m9589a(C1584h.ASSIGNWAYPOINT);
            case Type.BYTE /*3*/:
                a.m8600a(3);
                this.f8509b.g.m9589a(C1584h.INTERESTWAYPOINT);
            default:
        }
    }
}
