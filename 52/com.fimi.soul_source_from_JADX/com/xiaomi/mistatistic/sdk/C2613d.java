package com.xiaomi.mistatistic.sdk;

import android.os.SystemClock;
import com.xiaomi.mistatistic.sdk.controller.C2596j;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import com.xiaomi.mistatistic.sdk.p138a.C2575a;
import com.xiaomi.mistatistic.sdk.p138a.C2576b;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.xiaomi.mistatistic.sdk.d */
class C2613d extends URLStreamHandler {
    private URLStreamHandler f12989a;

    public C2613d(URLStreamHandler uRLStreamHandler) {
        this.f12989a = uRLStreamHandler;
    }

    protected URLConnection openConnection(URL url) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Method declaredMethod = URLStreamHandler.class.getDeclaredMethod("openConnection", new Class[]{URL.class});
            declaredMethod.setAccessible(true);
            URLConnection uRLConnection = (URLConnection) declaredMethod.invoke(this.f12989a, new Object[]{url});
            URLConnection c2576b;
            if (uRLConnection instanceof HttpsURLConnection) {
                c2576b = new C2576b((HttpsURLConnection) uRLConnection);
                c2576b.m14682a(elapsedRealtime);
                return c2576b;
            } else if (!(uRLConnection instanceof HttpURLConnection)) {
                return uRLConnection;
            } else {
                c2576b = new C2575a((HttpURLConnection) uRLConnection);
                c2576b.m14678a(elapsedRealtime);
                return c2576b;
            }
        } catch (Exception e) {
            C2596j.m14753a().m14760a(new HttpEvent(url.toString(), e.getClass().getSimpleName()));
            throw new IOException();
        }
    }
}
