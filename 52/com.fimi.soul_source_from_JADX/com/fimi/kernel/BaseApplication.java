package com.fimi.kernel;

import android.app.Application;
import com.fimi.kernel.p084e.ah;

public class BaseApplication extends Application {
    public void onCreate() {
        super.onCreate();
        C1189f.m8330a(getApplicationContext());
    }

    public void onTerminate() {
        ah.m8076b();
        super.onTerminate();
    }
}
