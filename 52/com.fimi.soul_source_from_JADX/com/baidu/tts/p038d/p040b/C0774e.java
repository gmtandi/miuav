package com.baidu.tts.p038d.p040b;

import com.baidu.tts.client.model.DownloadHandler;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.baidu.tts.d.b.e */
public class C0774e {
    private static volatile C0774e f4299a;
    private ConcurrentHashMap<String, C0773d> f4300b;
    private ConcurrentHashMap<String, C0772c> f4301c;
    private ConcurrentHashMap<String, C0771b> f4302d;

    static {
        f4299a = null;
    }

    private C0774e() {
        this.f4300b = new ConcurrentHashMap();
        this.f4301c = new ConcurrentHashMap();
        this.f4302d = new ConcurrentHashMap();
    }

    public static C0774e m6668a() {
        if (f4299a == null) {
            synchronized (C0774e.class) {
                if (f4299a == null) {
                    f4299a = new C0774e();
                }
            }
        }
        return f4299a;
    }

    public C0773d m6669a(String str) {
        try {
            C0773d c0773d = new C0773d(str);
            C0773d c0773d2 = (C0773d) this.f4300b.putIfAbsent(str, c0773d);
            return c0773d2 == null ? c0773d : c0773d2;
        } catch (Exception e) {
            return null;
        }
    }

    public void m6670a(DownloadHandler downloadHandler) {
        m6669a(downloadHandler.getModelId()).m6658b(downloadHandler);
    }

    public void m6671a(String str, String str2) {
        C0771b d = m6675d(str);
        if (d != null) {
            d.m6635b(str2);
        }
    }

    public C0772c m6672b(String str) {
        try {
            C0772c c0772c = new C0772c(str);
            C0772c c0772c2 = (C0772c) this.f4301c.putIfAbsent(str, c0772c);
            return c0772c2 == null ? c0772c : c0772c2;
        } catch (Exception e) {
            return null;
        }
    }

    public void m6673b() {
        for (C0773d a : this.f4300b.values()) {
            a.m6650a();
        }
    }

    public C0771b m6674c(String str) {
        try {
            C0771b c0771b = new C0771b(str);
            C0771b c0771b2 = (C0771b) this.f4302d.putIfAbsent(str, c0771b);
            return c0771b2 == null ? c0771b : c0771b2;
        } catch (Exception e) {
            return null;
        }
    }

    public C0771b m6675d(String str) {
        C0772c b = m6672b(str);
        return b != null ? m6674c(b.m6644a()) : null;
    }

    public long m6676e(String str) {
        return m6675d(str).m6627a();
    }

    public int m6677f(String str) {
        return m6675d(str).m6638d();
    }
}
