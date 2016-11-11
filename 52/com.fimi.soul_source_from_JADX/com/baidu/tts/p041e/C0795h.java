package com.baidu.tts.p041e;

/* renamed from: com.baidu.tts.e.h */
public enum C0795h {
    ZH("chinese", "ZH"),
    EN("english", "EN");
    
    private final String f4460c;
    private final String f4461d;

    private C0795h(String str, String str2) {
        this.f4460c = str;
        this.f4461d = str2;
    }

    public static C0795h m6743a(String str) {
        for (C0795h c0795h : C0795h.values()) {
            if (c0795h.m6744a().equalsIgnoreCase(str)) {
                return c0795h;
            }
        }
        return null;
    }

    public String m6744a() {
        return this.f4461d;
    }
}
