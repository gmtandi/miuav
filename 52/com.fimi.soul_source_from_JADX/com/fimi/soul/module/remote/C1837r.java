package com.fimi.soul.module.remote;

import android.os.Handler;
import android.os.Message;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.remote.r */
class C1837r extends Handler {
    final /* synthetic */ WhellRemoteFragment f9021a;

    C1837r(WhellRemoteFragment whellRemoteFragment) {
        this.f9021a = whellRemoteFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (!this.f9021a.f9002o) {
            C1160b.m7953b(this.f9021a.getActivity()).m7959a(this.f9021a.getString(C1205R.string.caliwhell));
        }
    }
}
