package com.fimi.soul.base;

import java.lang.Thread.UncaughtExceptionHandler;

/* renamed from: com.fimi.soul.base.c */
class C1238c implements UncaughtExceptionHandler {
    final /* synthetic */ ErrorReportApp f5630a;

    C1238c(ErrorReportApp errorReportApp) {
        this.f5630a = errorReportApp;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        new C1239d(this.f5630a, th).m8536a();
        this.f5630a.f5566a.uncaughtException(thread, th);
    }
}
