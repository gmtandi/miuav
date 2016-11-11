package com.p016a;

import android.content.Context;
import android.net.NetworkInfo;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.baidu.tts.loopj.RequestParams;
import com.facebook.common.util.UriUtil;
import com.tencent.connect.common.Constants;
import com.tencent.stat.DeviceInfo;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
import org.p122a.p123a.C3004e;
import org.p122a.p123a.p152c.p161f.C2989l;

/* renamed from: com.a.dd */
public class dd {
    private static dd f831e;
    gd f832a;
    String f833b;
    bk f834c;
    bl f835d;
    private long f836f;
    private int f837g;
    private int f838h;

    static {
        f831e = null;
    }

    private dd(Context context) {
        this.f832a = null;
        this.f833b = null;
        this.f834c = null;
        this.f835d = null;
        this.f836f = 0;
        this.f837g = ev.f1151j;
        this.f838h = ev.f1151j;
        try {
            this.f832a = new ge("loc", "2.4.1", "AMAP_Location_SDK_Android 2.4.1").m1954a(ev.m1781b()).m1951a();
        } catch (Throwable e) {
            ev.m1777a(e, "LocNetManager", "LocNetManager");
        }
        this.f833b = fp.m1853a(context, this.f832a, new HashMap(), true);
        this.f834c = bk.m1169a();
    }

    public static int m1441a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static synchronized dd m1442a(Context context) {
        dd ddVar;
        synchronized (dd.class) {
            if (f831e == null) {
                f831e = new dd(context);
            }
            ddVar = f831e;
        }
        return ddVar;
    }

    public String m1443a(byte[] bArr, Context context, String str, boolean z) {
        if (dd.m1441a(dn.m1528c(context)) == -1) {
            return null;
        }
        byte[] a;
        Map hashMap = new HashMap();
        bp deVar = new de();
        hashMap.clear();
        hashMap.put(C3004e.f15031q, C2989l.f14939a);
        hashMap.put(C3004e.f15024j, "Keep-Alive");
        if (z) {
            hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
            hashMap.put(C3004e.f15013Y, "AMAP_Location_SDK_Android 2.4.1");
            hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{"2.4.1", "loc"}));
            hashMap.put("logversion", "2.1");
        }
        deVar.m1448a(hashMap);
        deVar.m1447a(str);
        deVar.m1449a(bArr);
        deVar.m1035a(ga.m1922a(context));
        deVar.m1034a(ev.f1151j);
        deVar.m1037b(ev.f1151j);
        if (z) {
            try {
                a = this.f834c.m1171a(deVar);
            } catch (Throwable e) {
                ev.m1777a(e, "LocNetManager", "post");
                return null;
            } catch (Throwable e2) {
                ev.m1777a(e2, "LocNetManager", "post");
                return null;
            }
        }
        a = this.f834c.m1173b(deVar);
        return new String(a, "utf-8");
    }

    public HttpURLConnection m1444a(Context context, String str, HashMap<String, String> hashMap, byte[] bArr) {
        HttpURLConnection httpURLConnection = null;
        try {
            if (dd.m1441a(dn.m1528c(context)) != -1) {
                boolean z = false;
                bp deVar = new de();
                deVar.m1448a((Map) hashMap);
                deVar.m1447a(str);
                deVar.m1449a(bArr);
                deVar.m1035a(ga.m1922a(context));
                deVar.m1034a(ev.f1151j);
                deVar.m1037b(ev.f1151j);
                if (str.toLowerCase(Locale.US).startsWith(UriUtil.HTTPS_SCHEME)) {
                    z = true;
                }
                httpURLConnection = this.f834c.m1170a(deVar, z);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "LocNetManager", "doHttpPost");
        }
        return httpURLConnection;
    }

    public byte[] m1445a(Context context, JSONObject jSONObject, di diVar, String str) {
        if (dn.m1514a(jSONObject, "httptimeout")) {
            try {
                this.f837g = jSONObject.getInt("httptimeout");
            } catch (Throwable th) {
                ev.m1777a(th, "LocNetManager", "req");
            }
        }
        if (dd.m1441a(dn.m1528c(context)) == -1) {
            return null;
        }
        Map hashMap = new HashMap();
        bp deVar = new de();
        hashMap.clear();
        hashMap.put(C3004e.f15031q, RequestParams.APPLICATION_OCTET_STREAM);
        hashMap.put(C3004e.f15017c, AsyncHttpClient.ENCODING_GZIP);
        hashMap.put("gzipped", Constants.VIA_TO_TYPE_QQ_GROUP);
        hashMap.put(C3004e.f15024j, "Keep-Alive");
        hashMap.put(C3004e.f15013Y, "AMAP_Location_SDK_Android 2.4.1");
        hashMap.put("X-INFO", this.f833b);
        hashMap.put("KEY", fn.m1842f(context));
        hashMap.put("enginever", "4.2");
        String a = fp.m1850a();
        String a2 = fp.m1854a(context, a, "key=" + fn.m1842f(context));
        hashMap.put(DeviceInfo.TAG_TIMESTAMPS, a);
        hashMap.put("scode", a2);
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", new Object[]{"2.4.1", "loc"}));
        hashMap.put("logversion", "2.1");
        hashMap.put("encr", Constants.VIA_TO_TYPE_QQ_GROUP);
        deVar.m1448a(hashMap);
        deVar.m1447a(str);
        deVar.m1449a(dn.m1517a(diVar.m1459a()));
        deVar.m1035a(ga.m1922a(context));
        deVar.m1034a(this.f837g);
        deVar.m1037b(this.f837g);
        return this.f834c.m1173b(deVar);
    }
}
