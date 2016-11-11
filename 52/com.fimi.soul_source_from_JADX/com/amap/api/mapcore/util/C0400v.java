package com.amap.api.mapcore.util;

import org.p122a.p123a.C2915a;

@cl(a = "update_item")
/* renamed from: com.amap.api.mapcore.util.v */
public class C0400v {
    @cm(a = "title", b = 6)
    protected String f2520a;
    @cm(a = "url", b = 6)
    protected String f2521b;
    @cm(a = "mAdcode", b = 6)
    protected String f2522c;
    @cm(a = "fileName", b = 6)
    protected String f2523d;
    @cm(a = "version", b = 6)
    protected String f2524e;
    @cm(a = "lLocalLength", b = 5)
    protected long f2525f;
    @cm(a = "lRemoteLength", b = 5)
    protected long f2526g;
    @cm(a = "localPath", b = 6)
    protected String f2527h;
    @cm(a = "isProvince", b = 2)
    protected int f2528i;
    @cm(a = "mCompleteCode", b = 2)
    protected int f2529j;
    @cm(a = "mCityCode", b = 6)
    protected String f2530k;
    @cm(a = "mState", b = 2)
    public int f2531l;

    public C0400v() {
        this.f2520a = null;
        this.f2521b = null;
        this.f2522c = null;
        this.f2523d = null;
        this.f2524e = C2915a.f14760f;
        this.f2525f = 0;
        this.f2526g = 0;
        this.f2528i = 0;
        this.f2530k = C2915a.f14760f;
    }

    public static String m4193d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public void m4194a(int i) {
        this.f2529j = i;
    }

    public void m4195a(long j) {
        this.f2525f = j;
    }

    public void m4196c(String str) {
        this.f2530k = str;
    }

    public String m4197e() {
        return this.f2520a;
    }

    public String m4198f() {
        return this.f2524e;
    }

    public String m4199g() {
        return this.f2522c;
    }

    public String m4200h() {
        return this.f2521b;
    }

    public long m4201i() {
        return this.f2526g;
    }

    public int m4202j() {
        return this.f2529j;
    }
}
