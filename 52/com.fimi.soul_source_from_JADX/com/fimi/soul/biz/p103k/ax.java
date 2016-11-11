package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.UpdateDroneItem;

/* renamed from: com.fimi.soul.biz.k.ax */
class ax implements Runnable {
    final /* synthetic */ aw f6108a;
    private int f6109b;
    private UpdateDroneItem f6110c;
    private String f6111d;
    private int f6112e;
    private String f6113f;

    public ax(aw awVar, int i, UpdateDroneItem updateDroneItem) {
        this.f6108a = awVar;
        this.f6109b = i;
        this.f6110c = updateDroneItem;
    }

    public ax(aw awVar, int i, String str) {
        this.f6108a = awVar;
        this.f6111d = str;
        this.f6109b = i;
    }

    public ax(aw awVar, int i, String str, int i2) {
        this.f6108a = awVar;
        this.f6109b = i;
        this.f6111d = str;
        this.f6112e = i2;
    }

    public ax(aw awVar, int i, String str, int i2, String str2) {
        this.f6108a = awVar;
        this.f6109b = i;
        this.f6111d = str;
        this.f6112e = i2;
        this.f6113f = str2;
    }

    public void run() {
        Message obtainMessage = this.f6108a.f6105i.obtainMessage();
        Object obj = null;
        if (this.f6109b == 0) {
            obj = this.f6108a.f6104h.m9112a(this.f6110c, this.f6108a.f6107k);
        } else if (this.f6109b == 1) {
            obj = this.f6108a.f6104h.m9114b(this.f6110c, this.f6108a.f6107k);
        } else if (this.f6109b == 2) {
            obj = this.f6108a.f6104h.m9115c(this.f6111d, this.f6108a.f6107k);
        } else if (this.f6109b == 3) {
            obj = this.f6108a.f6104h.m9116d(this.f6111d, this.f6108a.f6107k);
        } else if (this.f6109b == 4) {
            obj = this.f6108a.f6104h.m9117e(this.f6111d, this.f6108a.f6107k);
        } else if (this.f6109b == 5) {
            obj = this.f6108a.f6104h.m9113a(this.f6111d, this.f6113f, this.f6112e, this.f6108a.f6107k);
        }
        obtainMessage.what = this.f6109b;
        obtainMessage.obj = obj;
        this.f6108a.f6105i.sendMessage(obtainMessage);
    }
}
