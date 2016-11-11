package com.xiaomi.kenai.jbosh;

import android.content.Context;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.module.setting.newhand.C1873o;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/* renamed from: com.xiaomi.kenai.jbosh.e */
final class C2504e implements am {
    private final Lock f12702a;
    private C2523v f12703b;
    private HttpClient f12704c;

    C2504e() {
        this.f12702a = new ReentrantLock();
        HttpClient.class.getName();
    }

    private synchronized HttpClient m14380b(C2523v c2523v) {
        HttpClient defaultHttpClient;
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        if (!(c2523v == null || c2523v.m14450f() == null || c2523v.m14451g() == 0)) {
            basicHttpParams.setParameter("http.route.default-proxy", new HttpHost(c2523v.m14450f(), c2523v.m14451g()));
        }
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
        defaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        defaultHttpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        return defaultHttpClient;
    }

    public al m14381a(ah ahVar, C2501b c2501b, Context context) {
        HttpClient httpClient;
        this.f12702a.lock();
        try {
            if (this.f12704c == null) {
                this.f12704c = m14380b(this.f12703b);
            }
            httpClient = this.f12704c;
            HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, C1873o.ak);
            if (ahVar != null) {
                HttpConnectionParams.setSoTimeout(params, (int) (((long) (((Integer) ahVar.m14324b().m14304a()).intValue() + 30)) * 1000));
            }
            C2523v c2523v = this.f12703b;
            return new C2503d(httpClient, c2523v, ahVar, c2501b, context);
        } finally {
            httpClient = this.f12702a;
            httpClient.unlock();
        }
    }

    public void m14382a() {
        this.f12702a.lock();
        try {
            if (this.f12704c != null) {
                this.f12704c.getConnectionManager().shutdown();
            }
            this.f12703b = null;
            this.f12704c = null;
            this.f12702a.unlock();
        } catch (Throwable th) {
            this.f12703b = null;
            this.f12704c = null;
            this.f12702a.unlock();
        }
    }

    public void m14383a(C2523v c2523v) {
        this.f12702a.lock();
        try {
            this.f12703b = c2523v;
            this.f12704c = m14380b(c2523v);
        } finally {
            this.f12702a.unlock();
        }
    }
}
