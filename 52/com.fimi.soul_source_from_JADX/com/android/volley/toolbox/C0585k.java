package com.android.volley.toolbox;

import com.android.volley.C0570r;
import com.hoho.android.usbserial.driver.FtdiSerialDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.android.volley.toolbox.k */
public class C0585k implements C0584n {
    private static final String f3628b = "Content-Type";
    protected final HttpClient f3629a;

    public C0585k(HttpClient httpClient) {
        this.f3629a = httpClient;
    }

    private static List<NameValuePair> m5219a(Map<String, String> map) {
        List<NameValuePair> arrayList = new ArrayList(map.size());
        for (String str : map.keySet()) {
            arrayList.add(new BasicNameValuePair(str, (String) map.get(str)));
        }
        return arrayList;
    }

    private static void m5220a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, C0570r<?> c0570r) {
        byte[] v = c0570r.m5131v();
        if (v != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(v));
        }
    }

    private static void m5221a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    static HttpUriRequest m5222b(C0570r<?> c0570r, Map<String, String> map) {
        HttpEntityEnclosingRequestBase httpPost;
        switch (c0570r.m5096a()) {
            case Opcodes.F_NEW /*-1*/:
                byte[] r = c0570r.m5127r();
                if (r == null) {
                    return new HttpGet(c0570r.m5116g());
                }
                HttpUriRequest httpPost2 = new HttpPost(c0570r.m5116g());
                httpPost2.addHeader(f3628b, c0570r.m5126q());
                httpPost2.setEntity(new ByteArrayEntity(r));
                return httpPost2;
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return new HttpGet(c0570r.m5116g());
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                httpPost = new HttpPost(c0570r.m5116g());
                httpPost.addHeader(f3628b, c0570r.m5130u());
                C0585k.m5220a(httpPost, (C0570r) c0570r);
                return httpPost;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                httpPost = new HttpPut(c0570r.m5116g());
                httpPost.addHeader(f3628b, c0570r.m5130u());
                C0585k.m5220a(httpPost, (C0570r) c0570r);
                return httpPost;
            case Type.BYTE /*3*/:
                return new HttpDelete(c0570r.m5116g());
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return new HttpHead(c0570r.m5116g());
            case Type.INT /*5*/:
                return new HttpOptions(c0570r.m5116g());
            case Type.FLOAT /*6*/:
                return new HttpTrace(c0570r.m5116g());
            case Type.LONG /*7*/:
                httpPost = new C0586l(c0570r.m5116g());
                httpPost.addHeader(f3628b, c0570r.m5130u());
                C0585k.m5220a(httpPost, (C0570r) c0570r);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    public HttpResponse m5223a(C0570r<?> c0570r, Map<String, String> map) {
        HttpUriRequest b = C0585k.m5222b(c0570r, map);
        C0585k.m5221a(b, (Map) map);
        C0585k.m5221a(b, c0570r.m5123n());
        m5224a(b);
        HttpParams params = b.getParams();
        int y = c0570r.m5134y();
        HttpConnectionParams.setConnectionTimeout(params, FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
        HttpConnectionParams.setSoTimeout(params, y);
        return this.f3629a.execute(b);
    }

    protected void m5224a(HttpUriRequest httpUriRequest) {
    }
}
