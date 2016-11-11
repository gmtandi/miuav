package com.xiaomi.mistatistic.sdk.p138a;

import android.os.SystemClock;
import com.xiaomi.mistatistic.sdk.controller.C2596j;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.util.Map;

/* renamed from: com.xiaomi.mistatistic.sdk.a.a */
public class C2575a extends HttpURLConnection {
    private long f12903a;
    private int f12904b;
    private boolean f12905c;
    private C2578d f12906d;
    private C2577c f12907e;
    private HttpURLConnection f12908f;

    public C2575a(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.f12904b = -1;
        this.f12905c = false;
        this.f12903a = SystemClock.elapsedRealtime();
        this.f12908f = httpURLConnection;
    }

    private int m14676c() {
        int i = 0;
        int a = this.f12907e != null ? this.f12907e.m14686a() : 0;
        if (this.f12906d != null) {
            i = this.f12906d.m14688a();
        }
        return ((a + 1100) + i) + getURL().toString().getBytes().length;
    }

    public void m14677a() {
        m14680b();
    }

    public void m14678a(long j) {
        this.f12903a = j;
    }

    void m14679a(Exception exception) {
        if (!this.f12905c) {
            this.f12905c = true;
            C2596j.m14753a().m14760a(new HttpEvent(getURL().toString(), exception.getClass().getSimpleName()));
        }
    }

    public void addRequestProperty(String str, String str2) {
        this.f12908f.addRequestProperty(str, str2);
    }

    void m14680b() {
        if (!this.f12905c) {
            this.f12905c = true;
            C2596j.m14753a().m14760a(this.f12904b == -1 ? new HttpEvent(getURL().toString(), SystemClock.elapsedRealtime() - this.f12903a) : new HttpEvent(getURL().toString(), SystemClock.elapsedRealtime() - this.f12903a, (long) m14676c(), this.f12904b));
        }
    }

    public void connect() {
        try {
            this.f12908f.connect();
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public void disconnect() {
        this.f12908f.disconnect();
    }

    public boolean getAllowUserInteraction() {
        return this.f12908f.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.f12908f.getConnectTimeout();
    }

    public Object getContent() {
        try {
            return this.f12908f.getContent();
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public Object getContent(Class[] clsArr) {
        try {
            return this.f12908f.getContent(clsArr);
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public String getContentEncoding() {
        return this.f12908f.getContentEncoding();
    }

    public int getContentLength() {
        return this.f12908f.getContentLength();
    }

    public String getContentType() {
        return this.f12908f.getContentType();
    }

    public long getDate() {
        return this.f12908f.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.f12908f.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.f12908f.getDoInput();
    }

    public boolean getDoOutput() {
        return this.f12908f.getDoOutput();
    }

    public InputStream getErrorStream() {
        return this.f12908f.getErrorStream();
    }

    public long getExpiration() {
        return this.f12908f.getExpiration();
    }

    public String getHeaderField(int i) {
        return this.f12908f.getHeaderField(i);
    }

    public String getHeaderField(String str) {
        return this.f12908f.getHeaderField(str);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.f12908f.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.f12908f.getHeaderFieldInt(str, i);
    }

    public String getHeaderFieldKey(int i) {
        return this.f12908f.getHeaderFieldKey(i);
    }

    public Map getHeaderFields() {
        return this.f12908f.getHeaderFields();
    }

    public long getIfModifiedSince() {
        return this.f12908f.getIfModifiedSince();
    }

    public InputStream getInputStream() {
        try {
            this.f12907e = new C2577c(this, this.f12908f.getInputStream());
            return this.f12907e;
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public boolean getInstanceFollowRedirects() {
        return this.f12908f.getInstanceFollowRedirects();
    }

    public long getLastModified() {
        return this.f12908f.getLastModified();
    }

    public OutputStream getOutputStream() {
        try {
            this.f12906d = new C2578d(this, this.f12908f.getOutputStream());
            return this.f12906d;
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public Permission getPermission() {
        try {
            return this.f12908f.getPermission();
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public int getReadTimeout() {
        return this.f12908f.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.f12908f.getRequestMethod();
    }

    public Map getRequestProperties() {
        return this.f12908f.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.f12908f.getRequestProperty(str);
    }

    public int getResponseCode() {
        try {
            this.f12904b = this.f12908f.getResponseCode();
            return this.f12904b;
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public String getResponseMessage() {
        try {
            return this.f12908f.getResponseMessage();
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public URL getURL() {
        return this.f12908f.getURL();
    }

    public boolean getUseCaches() {
        return this.f12908f.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.f12908f.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.f12908f.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i) {
        this.f12908f.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.f12908f.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.f12908f.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.f12908f.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.f12908f.setFixedLengthStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(long j) {
        try {
            this.f12908f.getClass().getDeclaredMethod("setFixedLengthStreamingMode", new Class[]{Long.TYPE}).invoke(this.f12908f, new Object[]{Long.valueOf(j)});
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void setIfModifiedSince(long j) {
        this.f12908f.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.f12908f.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.f12908f.setReadTimeout(i);
    }

    public void setRequestMethod(String str) {
        try {
            this.f12908f.setRequestMethod(str);
        } catch (Exception e) {
            m14679a(e);
            throw e;
        }
    }

    public void setRequestProperty(String str, String str2) {
        this.f12908f.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.f12908f.setUseCaches(z);
    }

    public String toString() {
        return this.f12908f.toString();
    }

    public boolean usingProxy() {
        return this.f12908f.usingProxy();
    }
}
