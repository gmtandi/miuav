package com.fimi.soul.base;

import android.util.Log;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;

/* renamed from: com.fimi.soul.base.b */
class C1237b implements LoggerInterface {
    final /* synthetic */ DroidPlannerApp f5629a;

    C1237b(DroidPlannerApp droidPlannerApp) {
        this.f5629a = droidPlannerApp;
    }

    public void log(String str) {
        Log.d(C1236a.f5606d, str);
    }

    public void log(String str, Throwable th) {
        Log.d(C1236a.f5606d, str, th);
    }

    public void setTag(String str) {
    }
}
