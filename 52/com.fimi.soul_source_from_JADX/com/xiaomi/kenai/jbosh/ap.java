package com.xiaomi.kenai.jbosh;

import java.util.Collections;
import java.util.Map;

final class ap extends C2501b {
    private static final ac f12673a;
    private final Map<ag, String> f12674b;
    private final String f12675c;

    static {
        f12673a = new ae();
    }

    private ap(Map<ag, String> map, String str) {
        this.f12674b = map;
        this.f12675c = str;
    }

    public static ap m14363a(String str) {
        return new ap(f12673a.m14308a(str).m14309a(), str);
    }

    public Map<ag, String> m14364a() {
        return Collections.unmodifiableMap(this.f12674b);
    }

    public String m14365b() {
        return this.f12675c;
    }
}
