package com.fimi.soul.biz.p103k;

import android.util.Log;
import com.fimi.kernel.p084e.ah;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.biz.p098j.C1375e;
import com.fimi.soul.entity.FdsMsg;
import com.fimi.soul.entity.PlaneMsg;
import com.fimi.soul.entity.SuggestBean;

/* renamed from: com.fimi.soul.biz.k.ap */
class ap implements C1375e {
    final /* synthetic */ SuggestBean f6075a;
    final /* synthetic */ int f6076b;
    final /* synthetic */ C1330i f6077c;
    final /* synthetic */ ao f6078d;

    ap(ao aoVar, SuggestBean suggestBean, int i, C1330i c1330i) {
        this.f6078d = aoVar;
        this.f6075a = suggestBean;
        this.f6076b = i;
        this.f6077c = c1330i;
    }

    public void m9217a(long j, long j2, String str) {
    }

    public void m9218a(PlaneMsg planeMsg) {
        if (planeMsg.isSuccess()) {
            this.f6075a.getList().add(((FdsMsg) planeMsg.getData()).getUrl());
            if (this.f6075a.getList().size() == this.f6076b) {
                ah.m8077b(new C1393r(this.f6078d, 1, this.f6075a, this.f6077c));
                return;
            }
            return;
        }
        Log.i("suguest fds :", "false");
    }
}
