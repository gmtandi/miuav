package com.p016a;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.gg */
class gg extends bp {
    private Map<String, String> f1274d;
    private String f1275e;
    private Map<String, String> f1276f;

    gg() {
        this.f1274d = new HashMap();
        this.f1276f = new HashMap();
    }

    public Map<String, String> m1969a() {
        return this.f1274d;
    }

    void m1970a(String str) {
        this.f1275e = str;
    }

    void m1971a(Map<String, String> map) {
        this.f1274d.clear();
        this.f1274d.putAll(map);
    }

    public Map<String, String> m1972b() {
        return this.f1276f;
    }

    void m1973b(Map<String, String> map) {
        this.f1276f.clear();
        this.f1276f.putAll(map);
    }

    public String m1974c() {
        return this.f1275e;
    }
}
