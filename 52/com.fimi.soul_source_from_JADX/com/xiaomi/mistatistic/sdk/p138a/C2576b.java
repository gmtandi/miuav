package com.xiaomi.mistatistic.sdk.p138a;

import android.os.SystemClock;
import com.xiaomi.mistatistic.sdk.controller.C2596j;
import com.xiaomi.mistatistic.sdk.data.HttpEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.xiaomi.mistatistic.sdk.a.b */
public class C2576b extends HttpsURLConnection {
    private long f12909a;
    private int f12910b;
    private boolean f12911c;
    private HttpsURLConnection f12912d;

    public C2576b(HttpsURLConnection httpsURLConnection) {
        super(httpsURLConnection.getURL());
        this.f12910b = -1;
        this.f12911c = false;
        this.f12912d = httpsURLConnection;
        this.f12909a = SystemClock.elapsedRealtime();
    }

    public void m14681a() {
        m14684b();
    }

    public void m14682a(long j) {
        this.f12909a = j;
    }

    void m14683a(Exception exception) {
        if (!this.f12911c) {
            this.f12911c = true;
            C2596j.m14753a().m14760a(new HttpEvent(getURL().toString(), exception.getClass().getSimpleName()));
        }
    }

    public void addRequestProperty(String str, String str2) {
        this.f12912d.addRequestProperty(str, str2);
    }

    public void m14684b() {
        if (!this.f12911c) {
            this.f12911c = true;
            C2596j.m14753a().m14760a(this.f12910b == -1 ? new HttpEvent(getURL().toString(), SystemClock.elapsedRealtime() - this.f12909a) : new HttpEvent(getURL().toString(), SystemClock.elapsedRealtime() - this.f12909a, 0, this.f12910b));
        }
    }

    public void connect() {
        try {
            this.f12912d.connect();
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public void disconnect() {
        this.f12912d.disconnect();
        m14684b();
    }

    public boolean getAllowUserInteraction() {
        return this.f12912d.getAllowUserInteraction();
    }

    public String getCipherSuite() {
        return this.f12912d.getCipherSuite();
    }

    public int getConnectTimeout() {
        return this.f12912d.getConnectTimeout();
    }

    public Object getContent() {
        return this.f12912d.getContent();
    }

    public Object getContent(Class[] clsArr) {
        try {
            return this.f12912d.getContent(clsArr);
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public String getContentEncoding() {
        return this.f12912d.getContentEncoding();
    }

    public int getContentLength() {
        return this.f12912d.getContentLength();
    }

    public String getContentType() {
        return this.f12912d.getContentType();
    }

    public long getDate() {
        return this.f12912d.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.f12912d.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.f12912d.getDoInput();
    }

    public boolean getDoOutput() {
        return this.f12912d.getDoOutput();
    }

    public InputStream getErrorStream() {
        return this.f12912d.getErrorStream();
    }

    public long getExpiration() {
        return this.f12912d.getExpiration();
    }

    public String getHeaderField(int i) {
        return this.f12912d.getHeaderField(i);
    }

    public String getHeaderField(String str) {
        return this.f12912d.getHeaderField(str);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.f12912d.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.f12912d.getHeaderFieldInt(str, i);
    }

    public String getHeaderFieldKey(int i) {
        return this.f12912d.getHeaderFieldKey(i);
    }

    public Map getHeaderFields() {
        return this.f12912d.getHeaderFields();
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.f12912d.getHostnameVerifier();
    }

    public long getIfModifiedSince() {
        return this.f12912d.getIfModifiedSince();
    }

    public InputStream getInputStream() {
        try {
            return new C2577c(this, this.f12912d.getInputStream());
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public boolean getInstanceFollowRedirects() {
        return this.f12912d.getInstanceFollowRedirects();
    }

    public long getLastModified() {
        return this.f12912d.getLastModified();
    }

    public Certificate[] getLocalCertificates() {
        return this.f12912d.getLocalCertificates();
    }

    public Principal getLocalPrincipal() {
        return this.f12912d.getLocalPrincipal();
    }

    public OutputStream getOutputStream() {
        try {
            return new C2578d(this, this.f12912d.getOutputStream());
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public Principal getPeerPrincipal() {
        try {
            return this.f12912d.getPeerPrincipal();
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public Permission getPermission() {
        try {
            return this.f12912d.getPermission();
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public int getReadTimeout() {
        return this.f12912d.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.f12912d.getRequestMethod();
    }

    public Map getRequestProperties() {
        return this.f12912d.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.f12912d.getRequestProperty(str);
    }

    public int getResponseCode() {
        try {
            this.f12910b = this.f12912d.getResponseCode();
            return this.f12910b;
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public String getResponseMessage() {
        try {
            return this.f12912d.getResponseMessage();
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.f12912d.getSSLSocketFactory();
    }

    public Certificate[] getServerCertificates() {
        try {
            return this.f12912d.getServerCertificates();
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public URL getURL() {
        return this.f12912d.getURL();
    }

    public boolean getUseCaches() {
        return this.f12912d.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.f12912d.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.f12912d.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i) {
        this.f12912d.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.f12912d.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.f12912d.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.f12912d.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.f12912d.setFixedLengthStreamingMode(i);
    }

    public void setFixedLengthStreamingMode(long j) {
        try {
            this.f12912d.getClass().getMethod("setFixedLengthStreamingMode", new Class[]{Long.TYPE}).invoke(this.f12912d, new Object[]{Long.valueOf(j)});
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f12912d.setHostnameVerifier(hostnameVerifier);
    }

    public void setIfModifiedSince(long j) {
        this.f12912d.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.f12912d.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.f12912d.setReadTimeout(i);
    }

    public void setRequestMethod(String str) {
        try {
            this.f12912d.setRequestMethod(str);
        } catch (Exception e) {
            m14683a(e);
            throw e;
        }
    }

    public void setRequestProperty(String str, String str2) {
        this.f12912d.setRequestProperty(str, str2);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f12912d.setSSLSocketFactory(sSLSocketFactory);
    }

    public void setUseCaches(boolean z) {
        this.f12912d.setUseCaches(z);
    }

    public String toString() {
        return this.f12912d.toString();
    }

    public boolean usingProxy() {
        return this.f12912d.usingProxy();
    }
}
