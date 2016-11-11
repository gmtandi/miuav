package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.p136a.C2398d;
import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.tencent.stat.g */
final class C2427g implements UncaughtExceptionHandler {
    final /* synthetic */ Context f12373a;

    C2427g(Context context) {
        this.f12373a = context;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (StatConfig.isEnableStatService()) {
            C2434n.m14082a(this.f12373a).m14102a(new C2398d(this.f12373a, StatService.m13922a(this.f12373a, false), 2, th), null);
            StatService.f12248i.debug("MTA has caught the following uncaught exception:");
            StatService.f12248i.error((Object) th);
            if (StatService.f12249j != null) {
                StatService.f12248i.debug("Call the original uncaught exception handler.");
                StatService.f12249j.uncaughtException(thread, th);
                return;
            }
            StatService.f12248i.debug("Original uncaught exception handler not set.");
        }
    }
}
