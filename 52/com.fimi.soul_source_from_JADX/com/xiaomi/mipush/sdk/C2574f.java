package com.xiaomi.mipush.sdk;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.push.service.ac;

/* renamed from: com.xiaomi.mipush.sdk.f */
class C2574f extends ContentObserver {
    final /* synthetic */ C2573e f12896a;

    C2574f(C2573e c2573e, Handler handler) {
        this.f12896a = c2573e;
        super(handler);
    }

    public void onChange(boolean z) {
        this.f12896a.f12895g = Integer.valueOf(ac.m14975a(this.f12896a.f12892c).m14978b());
        if (this.f12896a.f12895g.intValue() != 0) {
            this.f12896a.f12892c.getContentResolver().unregisterContentObserver(this);
            if (C2472a.m14152d(this.f12896a.f12892c)) {
                this.f12896a.m14670c();
            }
        }
    }
}
