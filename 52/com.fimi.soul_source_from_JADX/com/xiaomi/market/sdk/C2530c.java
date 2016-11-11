package com.xiaomi.market.sdk;

import android.text.TextUtils;
import android.util.Log;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.c */
public class C2530c {
    private static final String TAG = "MarketConnection";
    protected static final String f12804r = "http";
    private static final int f12805s = 10000;
    private static final int f12806t = 10000;
    private static final int f12807u = 30000;
    protected boolean f12808A;
    protected boolean f12809B;
    protected boolean f12810C;
    protected boolean f12811D;
    protected boolean f12812E;
    protected JSONObject f12813v;
    protected URL f12814w;
    protected C2536h f12815x;
    protected String f12816y;
    protected boolean f12817z;

    public C2530c(String str) {
        this(str, false);
    }

    public C2530c(String str, String str2) {
        this(C2530c.m14501c(str, str2), false);
    }

    public C2530c(String str, boolean z) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            Log.e(TAG, "URL error: " + e);
            url = null;
        }
        m14500a(url);
        this.f12812E = z;
    }

    private C2535g m14498a(int i) {
        if (i == C2799f.f14282t) {
            return C2535g.OK;
        }
        Log.e(TAG, "Network Error : " + i);
        return C2535g.SERVER_ERROR;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.market.sdk.C2535g m14499a(java.lang.String r11, java.lang.String r12, boolean r13, boolean r14, com.xiaomi.market.sdk.C2532i r15) {
        /*
        r10 = this;
        r2 = 0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r0.add(r11);
        r4 = r0.iterator();
    L_0x000d:
        r0 = r4.hasNext();
        if (r0 != 0) goto L_0x0016;
    L_0x0013:
        r0 = com.xiaomi.market.sdk.C2535g.NETWORK_ERROR;
    L_0x0015:
        return r0;
    L_0x0016:
        r0 = r4.next();
        r0 = (java.lang.String) r0;
        r1 = com.xiaomi.market.sdk.C2546s.DEBUG;
        if (r1 == 0) goto L_0x0034;
    L_0x0020:
        r1 = "MarketConnection";
        r3 = new java.lang.StringBuilder;
        r5 = "hosted connection url: ";
        r3.<init>(r5);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.d(r1, r3);
    L_0x0034:
        r5 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x00c8 }
        r5.<init>(r0);	 Catch:{ MalformedURLException -> 0x00c8 }
        r0 = r5.openConnection();	 Catch:{ Exception -> 0x017e, all -> 0x0176 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x017e, all -> 0x0176 }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        r1 = com.xiaomi.market.sdk.XiaomiUpdateAgent.getContext();	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        r1 = com.xiaomi.market.sdk.C2546s.m14553n(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        if (r1 == 0) goto L_0x00df;
    L_0x004e:
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
    L_0x0053:
        if (r13 == 0) goto L_0x0113;
    L_0x0055:
        r1 = "GET";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        r1 = 0;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
    L_0x005e:
        r3 = r10.m14505a(r0);	 Catch:{ d -> 0x0127 }
        r3.connect();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        if (r13 != 0) goto L_0x0093;
    L_0x0067:
        r0 = android.text.TextUtils.isEmpty(r12);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        if (r0 != 0) goto L_0x0093;
    L_0x006d:
        r0 = r3.getOutputStream();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r1 = r12.getBytes();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r0.write(r1);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r1 = com.xiaomi.market.sdk.C2546s.DEBUG;	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        if (r1 == 0) goto L_0x0090;
    L_0x007c:
        r1 = "MarketConnection";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r7 = "[post]";
        r6.<init>(r7);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r6 = r6.append(r12);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        android.util.Log.d(r1, r6);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x0090:
        r0.close();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x0093:
        r0 = r3.getResponseCode();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r0 = r10.m14498a(r0);	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        r1 = com.xiaomi.market.sdk.C2535g.OK;	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
        if (r0 != r1) goto L_0x00c1;
    L_0x009f:
        if (r15 == 0) goto L_0x00c1;
    L_0x00a1:
        r1 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x0184, all -> 0x016a }
        r6 = r3.getInputStream();	 Catch:{ Exception -> 0x0184, all -> 0x016a }
        r7 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r1.<init>(r6, r7);	 Catch:{ Exception -> 0x0184, all -> 0x016a }
        r6 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r6 = new byte[r6];	 Catch:{ Exception -> 0x0138 }
    L_0x00b0:
        r7 = 0;
        r8 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r7 = r1.read(r6, r7, r8);	 Catch:{ Exception -> 0x0138 }
        if (r7 > 0) goto L_0x0132;
    L_0x00b9:
        r15.flush();	 Catch:{ Exception -> 0x0138 }
        if (r1 == 0) goto L_0x00c1;
    L_0x00be:
        r1.close();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x00c1:
        if (r3 == 0) goto L_0x0015;
    L_0x00c3:
        r3.disconnect();
        goto L_0x0015;
    L_0x00c8:
        r0 = move-exception;
        r1 = "MarketConnection";
        r3 = new java.lang.StringBuilder;
        r5 = " URL error :";
        r3.<init>(r5);
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.e(r1, r0);
        goto L_0x000d;
    L_0x00df:
        r1 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        goto L_0x0053;
    L_0x00e6:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x00ea:
        r3 = "MarketConnection";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x017b }
        r7 = "Connection Exception for ";
        r6.<init>(r7);	 Catch:{ all -> 0x017b }
        r5 = r5.getHost();	 Catch:{ all -> 0x017b }
        r5 = r6.append(r5);	 Catch:{ all -> 0x017b }
        r6 = " :";
        r5 = r5.append(r6);	 Catch:{ all -> 0x017b }
        r0 = r5.append(r0);	 Catch:{ all -> 0x017b }
        r0 = r0.toString();	 Catch:{ all -> 0x017b }
        android.util.Log.e(r3, r0);	 Catch:{ all -> 0x017b }
        if (r1 == 0) goto L_0x000d;
    L_0x010e:
        r1.disconnect();
        goto L_0x000d;
    L_0x0113:
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        goto L_0x005e;
    L_0x011e:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
    L_0x0121:
        if (r3 == 0) goto L_0x0126;
    L_0x0123:
        r3.disconnect();
    L_0x0126:
        throw r0;
    L_0x0127:
        r1 = move-exception;
        r1 = r1.f12818F;	 Catch:{ Exception -> 0x00e6, all -> 0x011e }
        if (r0 == 0) goto L_0x012f;
    L_0x012c:
        r0.disconnect();
    L_0x012f:
        r0 = r1;
        goto L_0x0015;
    L_0x0132:
        r8 = 0;
        r15.write(r6, r8, r7);	 Catch:{ Exception -> 0x0138 }
        goto L_0x00b0;
    L_0x0138:
        r0 = move-exception;
    L_0x0139:
        r6 = "MarketConnection";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0182 }
        r8 = "Connection Exception for ";
        r7.<init>(r8);	 Catch:{ all -> 0x0182 }
        r8 = r5.getHost();	 Catch:{ all -> 0x0182 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0182 }
        r8 = " : read file stream error ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0182 }
        r0 = r7.append(r0);	 Catch:{ all -> 0x0182 }
        r0 = r0.toString();	 Catch:{ all -> 0x0182 }
        android.util.Log.e(r6, r0);	 Catch:{ all -> 0x0182 }
        r15.reset();	 Catch:{ all -> 0x0182 }
        if (r1 == 0) goto L_0x0163;
    L_0x0160:
        r1.close();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x0163:
        if (r3 == 0) goto L_0x000d;
    L_0x0165:
        r3.disconnect();
        goto L_0x000d;
    L_0x016a:
        r0 = move-exception;
        r1 = r2;
    L_0x016c:
        if (r1 == 0) goto L_0x0171;
    L_0x016e:
        r1.close();	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x0171:
        throw r0;	 Catch:{ Exception -> 0x0172, all -> 0x0179 }
    L_0x0172:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00ea;
    L_0x0176:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0121;
    L_0x0179:
        r0 = move-exception;
        goto L_0x0121;
    L_0x017b:
        r0 = move-exception;
        r3 = r1;
        goto L_0x0121;
    L_0x017e:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ea;
    L_0x0182:
        r0 = move-exception;
        goto L_0x016c;
    L_0x0184:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0139;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.c.a(java.lang.String, java.lang.String, boolean, boolean, com.xiaomi.market.sdk.i):com.xiaomi.market.sdk.g");
    }

    private void m14500a(URL url) {
        this.f12817z = true;
        this.f12808A = false;
        this.f12809B = true;
        this.f12810C = true;
        this.f12811D = true;
        if (m14509b(url)) {
            this.f12814w = url;
        }
    }

    public static String m14501c(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        Object substring;
        if (str.charAt(str.length() - 1) == '/') {
            substring = str.substring(0, str.length() - 1);
        }
        if (str2.charAt(0) == '/') {
            str2 = str2.substring(1);
        }
        return new StringBuilder(String.valueOf(substring)).append("/").append(str2).toString();
    }

    protected C2535g m14502a(C2532i c2532i) {
        if (this.f12814w == null) {
            return C2535g.URL_ERROR;
        }
        if (!C2546s.m14552m(XiaomiUpdateAgent.getContext())) {
            return C2535g.NETWORK_ERROR;
        }
        if (this.f12815x == null) {
            getClass();
            this.f12815x = new C2536h(this);
        }
        C2536h c2536h = this.f12815x;
        try {
            String url;
            C2536h a = m14503a(this.f12815x);
            String url2 = this.f12814w.toString();
            if (this.f12808A && !a.isEmpty()) {
                CharSequence query = this.f12814w.getQuery();
                url = this.f12814w.toString();
                url2 = TextUtils.isEmpty(query) ? new StringBuilder(String.valueOf(url)).append("?").append(a.toString()).toString() : new StringBuilder(String.valueOf(url)).append("&").append(a.toString()).toString();
            }
            try {
                url = m14504a(url2, a);
                if (C2546s.DEBUG) {
                    Log.d(TAG, "connection url: " + url);
                }
                String str = C2915a.f14760f;
                if (!this.f12808A) {
                    str = a.toString();
                }
                long currentTimeMillis = System.currentTimeMillis();
                C2535g a2 = m14499a(url, str, this.f12808A, false, c2532i);
                if (!C2546s.DEBUG) {
                    return a2;
                }
                Log.d(TAG, "Time(ms) spent in request: " + (System.currentTimeMillis() - currentTimeMillis) + ", " + url);
                return a2;
            } catch (C2531d e) {
                return e.f12818F;
            }
        } catch (C2531d e2) {
            return e2.f12818F;
        }
    }

    protected C2536h m14503a(C2536h c2536h) {
        return c2536h;
    }

    protected String m14504a(String str, C2536h c2536h) {
        return str;
    }

    protected HttpURLConnection m14505a(HttpURLConnection httpURLConnection) {
        return httpURLConnection;
    }

    public void m14506a(boolean z) {
        this.f12808A = z;
    }

    public C2535g m14507b(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }
        try {
            C2532i c2533e = new C2533e(this, file);
            C2535g a = m14502a(c2533e);
            try {
                c2533e.close();
                if (a != C2535g.OK) {
                    Log.e(TAG, "Connection failed : " + a);
                    file.delete();
                }
            } catch (IOException e) {
            }
            return a;
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "File not found: " + e2);
            throw e2;
        }
    }

    public void m14508b(boolean z) {
        this.f12817z = z;
    }

    protected boolean m14509b(URL url) {
        return url != null && TextUtils.equals(url.getProtocol(), f12804r);
    }

    public void m14510c(boolean z) {
        this.f12809B = z;
    }

    public void m14511d(boolean z) {
        this.f12810C = z;
    }

    public JSONObject m14512e() {
        return this.f12813v;
    }

    public void m14513e(boolean z) {
        this.f12811D = z;
    }

    public String m14514f() {
        return this.f12816y;
    }

    public C2536h m14515g() {
        return this.f12815x;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.market.sdk.C2535g m14516h() {
        /*
        r5 = this;
        r1 = new java.io.ByteArrayOutputStream;
        r1.<init>();
        r0 = new com.xiaomi.market.sdk.f;
        r0.<init>(r5, r1);
        r0 = r5.m14502a(r0);
        r2 = com.xiaomi.market.sdk.C2535g.OK;	 Catch:{ JSONException -> 0x0036 }
        if (r0 != r2) goto L_0x0021;
    L_0x0012:
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0036 }
        r3 = r1.toString();	 Catch:{ JSONException -> 0x0036 }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x0036 }
        r5.f12813v = r2;	 Catch:{ JSONException -> 0x0036 }
    L_0x001d:
        r1.close();	 Catch:{ IOException -> 0x005a }
    L_0x0020:
        return r0;
    L_0x0021:
        r2 = "MarketConnection";
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0036 }
        r4 = "Connection failed : ";
        r3.<init>(r4);	 Catch:{ JSONException -> 0x0036 }
        r3 = r3.append(r0);	 Catch:{ JSONException -> 0x0036 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0036 }
        android.util.Log.e(r2, r3);	 Catch:{ JSONException -> 0x0036 }
        goto L_0x001d;
    L_0x0036:
        r0 = move-exception;
        r2 = "MarketConnection";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0053 }
        r4 = "JSON error: ";
        r3.<init>(r4);	 Catch:{ all -> 0x0053 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x0053 }
        r0 = r0.toString();	 Catch:{ all -> 0x0053 }
        android.util.Log.e(r2, r0);	 Catch:{ all -> 0x0053 }
        r0 = com.xiaomi.market.sdk.C2535g.RESULT_ERROR;	 Catch:{ all -> 0x0053 }
        r1.close();	 Catch:{ IOException -> 0x0051 }
        goto L_0x0020;
    L_0x0051:
        r1 = move-exception;
        goto L_0x0020;
    L_0x0053:
        r0 = move-exception;
        r1.close();	 Catch:{ IOException -> 0x0058 }
    L_0x0057:
        throw r0;
    L_0x0058:
        r1 = move-exception;
        goto L_0x0057;
    L_0x005a:
        r1 = move-exception;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.sdk.c.h():com.xiaomi.market.sdk.g");
    }

    public C2535g m14517i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C2535g a = m14502a(new C2534f(this, byteArrayOutputStream));
        if (a == C2535g.OK) {
            this.f12816y = byteArrayOutputStream.toString();
        } else {
            Log.e(TAG, "Connection failed : " + a);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
        }
        return a;
    }
}
