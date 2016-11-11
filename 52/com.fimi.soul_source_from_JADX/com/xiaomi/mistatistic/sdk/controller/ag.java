package com.xiaomi.mistatistic.sdk.controller;

import com.xiaomi.mistatistic.sdk.C2579a;
import com.xiaomi.mistatistic.sdk.MiStatInterface;

class ag implements C2582e {
    final /* synthetic */ ab f12953a;

    ag(ab abVar) {
        this.f12953a = abVar;
    }

    public void m14729a() {
        if (MiStatInterface.isExceptionCatcherEnabled() && !MiStatInterface.shouldExceptionUploadImmediately()) {
            for (Throwable a : C2579a.m14692b()) {
                C2579a.m14690a(a);
            }
            C2579a.m14694c();
        }
    }
}
