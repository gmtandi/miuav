package com.baidu.tts.p041e;

/* renamed from: com.baidu.tts.e.m */
public enum C0800m {
    ONLINE(0, "just online"),
    OFFLINE(1, "just offline"),
    MIX(2, "if online cannot use switch from online to offline");
    
    private final int f4484d;
    private final String f4485e;

    private C0800m(int i, String str) {
        this.f4484d = i;
        this.f4485e = str;
    }

    public int m6749a() {
        return this.f4484d;
    }

    public String m6750b() {
        return this.f4485e;
    }
}
