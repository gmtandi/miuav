package com.fimi.soul.module.droneFragment;

import android.util.Log;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.ci;
import com.fimi.soul.view.cj;

class aw implements cj {
    final /* synthetic */ ShowDroneUiFragment f8194a;

    aw(ShowDroneUiFragment showDroneUiFragment) {
        this.f8194a = showDroneUiFragment;
    }

    public void m11030a(ci ciVar) {
        Log.i("ljh", "current mode : " + ciVar);
        if (ciVar == ci.TakePhoto) {
            this.f8194a.m10964a(bi.TakePhoto);
        } else if (ciVar == ci.Live) {
            this.f8194a.m10964a(bi.Live);
        } else if (ciVar == ci.Record) {
            this.f8194a.m10964a(bi.Record);
        }
        this.f8194a.m10988i();
        if (this.f8194a.f8150y != null) {
            this.f8194a.f8150y;
            if (ShowDroneStatusLineFragment.f8062a && this.f8194a.f8137l.m9575V()) {
                bi d = this.f8194a.af;
                this.f8194a.af;
                if (d != bi.Record) {
                    d = this.f8194a.af;
                    this.f8194a.af;
                    if (d == bi.ContinueCapture) {
                        this.f8194a.f8150y.m10953a("0\u5f20");
                        return;
                    }
                    d = this.f8194a.af;
                    this.f8194a.af;
                    if (d != bi.TakePhoto || this.f8194a.f8150y.m10950a() != 0) {
                    }
                } else if (this.f8194a.f8150y.m10950a() == 0) {
                    this.f8194a.f8150y.m10953a(this.f8194a.getString(C1205R.string.tf_normal));
                }
            }
        }
    }
}
