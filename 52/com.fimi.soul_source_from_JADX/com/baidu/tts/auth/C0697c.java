package com.baidu.tts.auth;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.common.net.ConnectManager;
import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p030j.C0690a;
import com.baidu.tts.p030j.C0692b;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p044g.p046b.C0809b;
import com.baidu.tts.tools.StringTool;
import com.facebook.common.util.UriUtil;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.connect.common.Constants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.p122a.p123a.p124f.p125c.C3026h;

/* renamed from: com.baidu.tts.auth.c */
public class C0697c implements C0692b<C0697c, C0694a> {
    private String f4085a;
    private String f4086b;
    private String f4087c;

    /* renamed from: com.baidu.tts.auth.c.a */
    public class C0694a implements C0690a {
        private String f4077a;
        private String f4078b;
        private long f4079c;
        private TtsError f4080d;

        public String m6123a() {
            return this.f4078b;
        }

        public void m6124a(long j) {
            this.f4079c = j;
        }

        public void m6125a(TtsError ttsError) {
            if (ttsError != null) {
                LoggerProxy.m6515d("OnlineAuth", "this=" + this + "--error=" + ttsError.getDetailMessage());
            }
            this.f4080d = ttsError;
        }

        public void m6126a(String str) {
            this.f4077a = str;
        }

        public TtsError m6127b() {
            return this.f4080d;
        }

        public void m6128b(String str) {
            this.f4078b = str;
        }

        public boolean m6129g() {
            return StringTool.isEmpty(this.f4077a) ? this.f4078b != null && System.currentTimeMillis() < this.f4079c : true;
        }
    }

    /* renamed from: com.baidu.tts.auth.c.b */
    class C0696b extends SSLSocketFactory {
        SSLContext f4083a;
        final /* synthetic */ C0697c f4084b;

        /* renamed from: com.baidu.tts.auth.c.b.1 */
        class C06951 implements X509TrustManager {
            final /* synthetic */ C0697c f4081a;
            final /* synthetic */ C0696b f4082b;

            C06951(C0696b c0696b, C0697c c0697c) {
                this.f4082b = c0696b;
                this.f4081a = c0697c;
            }

            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        public C0696b(C0697c c0697c, KeyStore keyStore) {
            this.f4084b = c0697c;
            super(keyStore);
            this.f4083a = SSLContext.getInstance(C3026h.f15062a);
            C06951 c06951 = new C06951(this, c0697c);
            this.f4083a.init(null, new TrustManager[]{c06951}, null);
        }

        public Socket createSocket() {
            return this.f4083a.getSocketFactory().createSocket();
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) {
            return this.f4083a.getSocketFactory().createSocket(socket, str, i, z);
        }
    }

    private HttpClient m6130a(Context context) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory c0696b = new C0696b(this, instance);
            c0696b.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            ConnectManager connectManager = new ConnectManager(context);
            Object proxy = connectManager.getProxy();
            String proxyPort = connectManager.getProxyPort();
            if (!TextUtils.isEmpty(proxy)) {
                basicHttpParams.setParameter("http.route.default-proxy", new HttpHost(proxy, Integer.valueOf(proxyPort).intValue()));
            }
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, C1142e.f5201a);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme(UriUtil.HTTP_SCHEME, PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(UriUtil.HTTPS_SCHEME, c0696b, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    private boolean m6131a(String str, String str2) {
        return (StringTool.isEmpty(str) || StringTool.isEmpty(str2)) ? false : true;
    }

    private String m6132b(String str, String str2) {
        List linkedList = new LinkedList();
        linkedList.add(new BasicNameValuePair("grant_type", "client_credentials"));
        linkedList.add(new BasicNameValuePair(Constants.PARAM_CLIENT_ID, str));
        linkedList.add(new BasicNameValuePair("client_secret", str2));
        return "https://openapi.baidu.com/oauth/2.0/token?" + URLEncodedUtils.format(linkedList, "utf-8");
    }

    public int m6133a(C0697c c0697c) {
        String a = c0697c.m6134a();
        if (StringTool.isEmpty(this.f4085a)) {
            a = c0697c.m6136b();
            String c = c0697c.m6138c();
            LoggerProxy.m6515d("OnlineAuth", "mAK=" + this.f4086b + "--mSK=" + this.f4087c + "--ak2=" + a + "--sk2=" + c);
            return (StringTool.isEqual(this.f4086b, a) && StringTool.isEqual(this.f4087c, c)) ? 0 : 1;
        } else {
            LoggerProxy.m6515d("OnlineAuth", "mProductId=" + this.f4085a + "--productId2=" + a);
            return a != null ? this.f4085a.compareTo(a) : 1;
        }
    }

    public String m6134a() {
        return this.f4085a;
    }

    public void m6135a(String str) {
        this.f4085a = str;
    }

    public String m6136b() {
        return this.f4086b;
    }

    public void m6137b(String str) {
        this.f4086b = str;
    }

    public String m6138c() {
        return this.f4087c;
    }

    public void m6139c(String str) {
        this.f4087c = str;
    }

    public /* synthetic */ Object call() {
        return m6140d();
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m6133a((C0697c) obj);
    }

    public C0694a m6140d() {
        LoggerProxy.m6515d("OnlineAuth", "enter online auth");
        C0694a c0694a = new C0694a();
        if (StringTool.isEmpty(this.f4085a)) {
            try {
                HttpClient a = m6130a(C0809b.m6769a().m6779h());
                if (m6131a(this.f4086b, this.f4087c)) {
                    String b = m6132b(this.f4086b, this.f4087c);
                    LoggerProxy.m6515d("OnlineAuth", "url=" + b);
                    HttpResponse execute = a.execute(new HttpPost(b));
                    b = EntityUtils.toString(execute.getEntity());
                    int statusCode = execute.getStatusLine().getStatusCode();
                    LoggerProxy.m6515d("OnlineAuth", "body=" + b + "--code=" + statusCode);
                    if (statusCode == C2799f.f14282t) {
                        JSONObject jSONObject = new JSONObject(b);
                        if (jSONObject.has(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2)) {
                            c0694a.m6128b(jSONObject.getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2));
                        } else {
                            c0694a.m6125a(C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_AUTH_FAILURE));
                        }
                        if (jSONObject.has(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2)) {
                            c0694a.m6124a(System.nanoTime() + (Math.min((long) jSONObject.getInt(XiaomiOAuthConstants.EXTRA_EXPIRES_IN_2), 86400) * 1000000000));
                        }
                    } else {
                        c0694a.m6125a(C0807c.m6758a().m6765b(C0802n.ONLINE_ENGINE_AUTH_FAILURE));
                    }
                } else {
                    c0694a.m6125a(C0807c.m6758a().m6765b(C0802n.TTS_PARAMETER_INVALID));
                }
            } catch (Throwable e) {
                c0694a.m6125a(C0807c.m6758a().m6763a(C0802n.ONLINE_ENGINE_AUTH_FAILURE, e));
            }
        } else {
            c0694a.m6126a(this.f4085a);
        }
        LoggerProxy.m6515d("OnlineAuth", "end online auth");
        return c0694a;
    }
}
