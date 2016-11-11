package com.baidu.tts.p038d;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.baidu.tts.d.a */
public class C0769a {
    private boolean f4278a;
    private Map<String, Integer> f4279b;

    public C0769a() {
        this.f4278a = false;
        this.f4279b = new HashMap();
    }

    public void m6610a(String str, int i) {
        this.f4279b.put(str, Integer.valueOf(i));
    }

    public void m6611a(boolean z) {
        this.f4278a = z;
    }

    public boolean m6612a() {
        return this.f4278a;
    }

    public boolean m6613b() {
        for (Integer intValue : this.f4279b.values()) {
            if (intValue.intValue() != 7) {
                return false;
            }
        }
        return true;
    }
}
