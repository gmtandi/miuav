package com.fimi.soul.module.update;

import android.os.Message;
import com.fimi.soul.view.photodraweeview.C2020f;

/* renamed from: com.fimi.soul.module.update.d */
class C1912d implements Runnable {
    final /* synthetic */ CheckFirmwareActvity f9821a;

    C1912d(CheckFirmwareActvity checkFirmwareActvity) {
        this.f9821a = checkFirmwareActvity;
    }

    public void run() {
        this.f9821a.f9667p = C2020f.f10933c + this.f9821a.f9667p;
        Message obtainMessage = this.f9821a.f9656e.obtainMessage();
        obtainMessage.obj = Float.valueOf(this.f9821a.f9667p);
        obtainMessage.what = 1;
        this.f9821a.f9656e.sendMessage(obtainMessage);
    }
}
