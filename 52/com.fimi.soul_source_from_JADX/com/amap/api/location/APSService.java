package com.amap.api.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.p016a.ak;
import com.p016a.dr;
import com.p016a.ev;
import com.p016a.fn;
import com.p016a.fo;
import com.tencent.mm.sdk.plugin.MMPluginProviderConstants.OAuth;
import com.xiaomi.market.sdk.C2537j;

public class APSService extends Service {
    Messenger f1387a;
    APSServiceBase f1388b;

    public IBinder onBind(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(OAuth.API_KEY);
            if (!TextUtils.isEmpty(stringExtra)) {
                fo.m1846a(stringExtra);
            }
            stringExtra = intent.getStringExtra(C2537j.f12839W);
            String stringExtra2 = intent.getStringExtra("sha1AndPackage");
            fn.m1835a(stringExtra);
            fn.m1837b(stringExtra2);
            this.f1387a = new Messenger(this.f1388b.getHandler());
            return this.f1387a.getBinder();
        } catch (Throwable th) {
            ev.m1777a(th, "APSService", "onBind");
            return null;
        }
    }

    public void onCreate() {
        onCreate(this);
    }

    public void onCreate(Context context) {
        try {
            Context context2 = context;
            this.f1388b = (APSServiceBase) ak.m1032a(context2, ev.m1772a("2.4.1"), "com.amap.api.location.APSServiceWrapper", dr.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            this.f1388b = new dr(this);
            ev.m1777a(th, "APSService", "onCreate");
        }
        this.f1388b.onCreate();
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.f1388b.onDestroy();
        } catch (Throwable th) {
            ev.m1777a(th, "APSService", "onDestroy");
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.f1388b.onStartCommand(intent, i, i2);
        } catch (Throwable th) {
            ev.m1777a(th, "APSService", "onStartCommand");
            return super.onStartCommand(intent, i, i2);
        }
    }
}
