package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

public abstract class ct {
    @cm(a = "b2", b = 2)
    protected int f2346a;
    @cm(a = "b1", b = 6)
    protected String f2347b;
    @cm(a = "b3", b = 2)
    protected int f2348c;
    @cm(a = "a1", b = 6)
    private String f2349d;

    public ct() {
        this.f2346a = -1;
        this.f2348c = 1;
    }

    public static String m3907c(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("b2").append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m3908c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("b1", str);
        return ck.m3887a(hashMap);
    }

    public int m3909a() {
        return this.f2346a;
    }

    public void m3910a(int i) {
        this.f2346a = i;
    }

    public void m3911a(String str) {
        this.f2347b = str;
    }

    public String m3912b() {
        return this.f2347b;
    }

    public void m3913b(int i) {
        this.f2348c = i;
    }

    public void m3914b(String str) {
        this.f2349d = bx.m3779b(str);
    }

    public int m3915c() {
        return this.f2348c;
    }
}
