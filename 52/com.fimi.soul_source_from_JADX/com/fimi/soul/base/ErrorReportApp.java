package com.fimi.soul.base;

import com.fimi.kernel.BaseApplication;
import java.lang.Thread.UncaughtExceptionHandler;

public class ErrorReportApp extends BaseApplication {
    private UncaughtExceptionHandler f5566a;
    private UncaughtExceptionHandler f5567b;

    public ErrorReportApp() {
        this.f5567b = new C1238c(this);
    }

    public void onCreate() {
        super.onCreate();
        this.f5566a = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this.f5567b);
    }
}
