package com.xiaomi.channel.commonutils.logger;

import android.util.Log;

/* renamed from: com.xiaomi.channel.commonutils.logger.a */
public class C2462a implements LoggerInterface {
    private String f12413a;

    public C2462a() {
        this.f12413a = "xiaomi";
    }

    public void log(String str) {
        Log.v(this.f12413a, str);
    }

    public void log(String str, Throwable th) {
        Log.v(this.f12413a, str, th);
    }

    public void setTag(String str) {
        this.f12413a = str;
    }
}
