package com.android.volley.toolbox;

import com.android.volley.C0570r;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import it.p074a.p075a.C2799f;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.p152c.p156c.C2949f;
import org.p122a.p123a.p152c.p156c.C2951i;
import org.p122a.p123a.p152c.p156c.C2952j;
import org.p122a.p123a.p152c.p156c.C2953k;
import org.p122a.p123a.p152c.p156c.C2954l;
import org.p122a.p123a.p152c.p156c.C2955m;
import org.p122a.p123a.p152c.p156c.C2956n;
import org.p122a.p123a.p152c.p156c.C2960s;

/* renamed from: com.android.volley.toolbox.o */
public class C0588o implements C0584n {
    private static final String f3631a = "Content-Type";
    private final C0589p f3632b;
    private final SSLSocketFactory f3633c;

    public C0588o() {
        this(null);
    }

    public C0588o(C0589p c0589p) {
        this(c0589p, null);
    }

    public C0588o(C0589p c0589p, SSLSocketFactory sSLSocketFactory) {
        this.f3632b = c0589p;
        this.f3633c = sSLSocketFactory;
    }

    private HttpURLConnection m5229a(URL url, C0570r<?> c0570r) {
        HttpURLConnection a = m5234a(url);
        int y = c0570r.m5134y();
        a.setConnectTimeout(y);
        a.setReadTimeout(y);
        a.setUseCaches(false);
        a.setDoInput(true);
        if (UriUtil.HTTPS_SCHEME.equals(url.getProtocol()) && this.f3633c != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.f3633c);
        }
        return a;
    }

    private static HttpEntity m5230a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    static void m5231a(HttpURLConnection httpURLConnection, C0570r<?> c0570r) {
        switch (c0570r.m5096a()) {
            case Opcodes.F_NEW /*-1*/:
                byte[] r = c0570r.m5127r();
                if (r != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(C2955m.f14864a);
                    httpURLConnection.addRequestProperty(f3631a, c0570r.m5126q());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(r);
                    dataOutputStream.close();
                }
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                httpURLConnection.setRequestMethod(C2951i.f14860a);
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                httpURLConnection.setRequestMethod(C2955m.f14864a);
                C0588o.m5233b(httpURLConnection, c0570r);
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                httpURLConnection.setRequestMethod(C2956n.f14865a);
                C0588o.m5233b(httpURLConnection, c0570r);
            case Type.BYTE /*3*/:
                httpURLConnection.setRequestMethod(C2949f.f14858a);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                httpURLConnection.setRequestMethod(C2952j.f14861a);
            case Type.INT /*5*/:
                httpURLConnection.setRequestMethod(C2953k.f14862a);
            case Type.FLOAT /*6*/:
                httpURLConnection.setRequestMethod(C2960s.f14871a);
            case Type.LONG /*7*/:
                httpURLConnection.setRequestMethod(C2954l.f14863a);
                C0588o.m5233b(httpURLConnection, c0570r);
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static boolean m5232a(int i, int i2) {
        return (i == 4 || ((100 <= i2 && i2 < C2799f.f14282t) || i2 == C1458u.f6934b || i2 == 304)) ? false : true;
    }

    private static void m5233b(HttpURLConnection httpURLConnection, C0570r<?> c0570r) {
        byte[] v = c0570r.m5131v();
        if (v != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(f3631a, c0570r.m5130u());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(v);
            dataOutputStream.close();
        }
    }

    protected HttpURLConnection m5234a(URL url) {
        return (HttpURLConnection) url.openConnection();
    }

    public HttpResponse m5235a(C0570r<?> c0570r, Map<String, String> map) {
        String a;
        String g = c0570r.m5116g();
        HashMap hashMap = new HashMap();
        hashMap.putAll(c0570r.m5123n());
        hashMap.putAll(map);
        if (this.f3632b != null) {
            a = this.f3632b.m5236a(g);
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + g);
            }
        }
        a = g;
        HttpURLConnection a2 = m5229a(new URL(a), (C0570r) c0570r);
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        C0588o.m5231a(a2, (C0570r) c0570r);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        StatusLine basicStatusLine = new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage());
        HttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        if (C0588o.m5232a(c0570r.m5096a(), basicStatusLine.getStatusCode())) {
            basicHttpResponse.setEntity(C0588o.m5230a(a2));
        }
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }
}
