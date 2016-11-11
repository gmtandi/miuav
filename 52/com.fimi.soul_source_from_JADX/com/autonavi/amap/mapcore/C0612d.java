package com.autonavi.amap.mapcore;

import java.util.Hashtable;

/* renamed from: com.autonavi.amap.mapcore.d */
class C0612d {
    int f3721a;
    long f3722b;
    boolean f3723c;
    private Hashtable<String, C0611c> f3724d;

    public C0612d() {
        this.f3724d = new Hashtable();
        this.f3721a = 0;
        this.f3723c = true;
        m5297b();
    }

    public void m5295a() {
        this.f3724d.clear();
    }

    public void m5296a(String str) {
        this.f3724d.remove(str);
    }

    public void m5297b() {
        this.f3722b = System.currentTimeMillis();
    }

    public boolean m5298b(String str) {
        return this.f3724d.get(str) != null;
    }

    public void m5299c(String str) {
        this.f3724d.put(str, new C0611c(str, 0));
    }
}
