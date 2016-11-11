package com.baidu.tts.p038d.p040b;

import com.baidu.tts.client.model.DownloadHandler;
import com.baidu.tts.database.C0782a;

/* renamed from: com.baidu.tts.d.b.a */
public class C0770a {
    private static volatile C0770a f4280a;
    private C0774e f4281b;
    private C0782a f4282c;

    static {
        f4280a = null;
    }

    private C0770a() {
        this.f4281b = C0774e.m6668a();
    }

    public static C0770a m6614a() {
        if (f4280a == null) {
            synchronized (C0770a.class) {
                if (f4280a == null) {
                    f4280a = new C0770a();
                }
            }
        }
        return f4280a;
    }

    public C0771b m6615a(String str) {
        return this.f4281b.m6674c(str);
    }

    public void m6616a(DownloadHandler downloadHandler) {
        this.f4281b.m6670a(downloadHandler);
    }

    public void m6617a(C0782a c0782a) {
        this.f4282c = c0782a;
    }

    public void m6618a(String str, String str2) {
        this.f4281b.m6671a(str, str2);
    }

    public C0773d m6619b(String str) {
        return this.f4281b.m6669a(str);
    }

    public C0782a m6620b() {
        return this.f4282c;
    }

    public C0772c m6621c(String str) {
        return this.f4281b.m6672b(str);
    }

    public void m6622c() {
        this.f4281b.m6673b();
    }

    public long m6623d(String str) {
        return this.f4281b.m6676e(str);
    }

    public int m6624e(String str) {
        return this.f4281b.m6677f(str);
    }
}
