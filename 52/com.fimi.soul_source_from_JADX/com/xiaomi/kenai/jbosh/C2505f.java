package com.xiaomi.kenai.jbosh;

/* renamed from: com.xiaomi.kenai.jbosh.f */
final class C2505f extends C2498a<String> {
    private final String[] f12705a;

    private C2505f(String str) {
        super(str);
        this.f12705a = str.split("[\\s,]+");
    }

    static C2505f m14384a(String str) {
        return str == null ? null : new C2505f(str);
    }
}
