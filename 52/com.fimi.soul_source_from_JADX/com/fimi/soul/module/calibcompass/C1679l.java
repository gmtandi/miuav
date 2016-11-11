package com.fimi.soul.module.calibcompass;

import android.location.Location;
import android.os.AsyncTask;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.CheckCampssBeann;
import java.util.List;

/* renamed from: com.fimi.soul.module.calibcompass.l */
class C1679l extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ CaliCompassErrorFragment f7937a;

    C1679l(CaliCompassErrorFragment caliCompassErrorFragment) {
        this.f7937a = caliCompassErrorFragment;
    }

    protected Void m10885a(Void... voidArr) {
        Object obj;
        List listLatlng;
        CheckCampssBeann checkCampssBeann = (CheckCampssBeann) C1157c.m7938a().m7939a(C1543c.f7225Z, CheckCampssBeann.class);
        if (checkCampssBeann != null) {
            obj = checkCampssBeann;
            listLatlng = checkCampssBeann.getListLatlng();
        } else {
            obj = new CheckCampssBeann();
            listLatlng = obj.getListLatlng();
        }
        Location d = C1394s.m9340a(this.f7937a.getActivity()).m9352d();
        if (d != null) {
            listLatlng.add(new LatLng(d.getLatitude(), d.getLongitude()));
            obj.setListLatlng(listLatlng);
            C1157c.m7938a().m7943a(C1543c.f7225Z, obj);
        }
        return null;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10885a((Void[]) objArr);
    }
}
