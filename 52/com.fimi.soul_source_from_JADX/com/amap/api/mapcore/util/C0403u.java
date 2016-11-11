package com.amap.api.mapcore.util;

import org.p122a.p123a.C2915a;

@cl(a = "update_item_file")
/* renamed from: com.amap.api.mapcore.util.u */
class C0403u {
    @cm(a = "mAdcode", b = 6)
    private String f2539a;
    @cm(a = "file", b = 6)
    private String f2540b;

    public C0403u() {
        this.f2539a = C2915a.f14760f;
        this.f2540b = C2915a.f14760f;
    }

    public C0403u(String str, String str2) {
        this.f2539a = C2915a.f14760f;
        this.f2540b = C2915a.f14760f;
        this.f2539a = str;
        this.f2540b = str2;
    }

    public static String m4215a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public static String m4216b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mAdcode");
        stringBuilder.append("='");
        stringBuilder.append(str);
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public String m4217a() {
        return this.f2540b;
    }
}
