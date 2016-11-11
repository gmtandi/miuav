package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.CityInfo;

/* renamed from: com.fimi.soul.biz.k.be */
class be implements Runnable {
    final /* synthetic */ bd f6175a;
    private int f6176b;
    private CityInfo f6177c;

    public be(bd bdVar, int i, CityInfo cityInfo) {
        this.f6175a = bdVar;
        this.f6176b = i;
        this.f6177c = cityInfo;
    }

    public void run() {
        Message obtainMessage = this.f6175a.f6172f.obtainMessage();
        Object obj = null;
        if (this.f6176b == 0) {
            obj = this.f6175a.f6170d.m9172a(this.f6175a.f6171e, this.f6177c);
        }
        obtainMessage.what = this.f6176b;
        obtainMessage.obj = obj;
        this.f6175a.f6172f.sendMessage(obtainMessage);
    }
}
