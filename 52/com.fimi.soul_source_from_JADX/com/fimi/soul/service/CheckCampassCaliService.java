package com.fimi.soul.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import com.amap.api.location.AMapLocation;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p103k.C1394s;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.CheckCampssBeann;
import com.fimi.soul.module.update.p121a.C1904d;
import com.fimi.soul.utils.ac;

public class CheckCampassCaliService extends IntentService {
    DroidPlannerApp f9921a;
    private AMapLocation f9922b;

    public CheckCampassCaliService() {
        super("CheckCampassCaliService");
    }

    public CheckCampassCaliService(String str) {
        super(str);
    }

    public void onCreate() {
        super.onCreate();
        this.f9921a = (DroidPlannerApp) getApplication();
    }

    protected void onHandleIntent(Intent intent) {
        this.f9921a.f5570a.m9589a(C1584h.XIAOMI_INSURENCE);
        CheckCampssBeann checkCampssBeann = (CheckCampssBeann) C1157c.m7938a().m7939a(C1543c.f7225Z, CheckCampssBeann.class);
        if (checkCampssBeann == null) {
            this.f9921a.f5570a.m9589a(C1584h.SHOWCAMPASSCALI);
            return;
        }
        Location d = C1394s.m9340a((Context) this).m9352d();
        if (d == null) {
            this.f9921a.f5570a.m9589a(C1584h.SHOWCAMPASSCALI);
            return;
        }
        LatLng latLng = new LatLng(d.getLatitude(), d.getLongitude());
        double d2 = 0.0d;
        for (LatLng c : checkCampssBeann.getListLatlng()) {
            d2 = ac.m12223c(latLng, c).m12254a();
            if (d2 <= 3000.0d) {
                break;
            }
        }
        if (d2 > 3000.0d) {
            this.f9921a.f5570a.m9589a(C1584h.SHOWCAMPASSCALI);
        }
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        C1904d.m12040a(this.f9921a.f5570a);
    }
}
