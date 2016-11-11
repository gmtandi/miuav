package com.xiaomi.kenai.jbosh;

/* renamed from: com.xiaomi.kenai.jbosh.h */
final class C2507h extends C2498a<String> {
    private final String[] f12706a;

    private C2507h(String str) {
        super(str);
        this.f12706a = str.split("\\ +");
    }

    static C2507h m14386a(String str) {
        return str == null ? null : new C2507h(str);
    }
}
