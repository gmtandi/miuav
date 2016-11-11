package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

class by extends dj {
    private Map<String, String> f2293a;
    private String f2294b;
    private Map<String, String> f2295c;

    by() {
        this.f2293a = new HashMap();
        this.f2295c = new HashMap();
    }

    public String m3789a() {
        return this.f2294b;
    }

    void m3790a(String str) {
        this.f2294b = str;
    }

    void m3791a(Map<String, String> map) {
        this.f2293a.clear();
        this.f2293a.putAll(map);
    }

    public Map<String, String> m3792b() {
        return this.f2295c;
    }

    void m3793b(Map<String, String> map) {
        this.f2295c.clear();
        this.f2295c.putAll(map);
    }

    public Map<String, String> m3794c() {
        return this.f2293a;
    }
}
