package com.amap.api.services.core;

import com.amap.api.services.core.ad.C0452a;

/* renamed from: com.amap.api.services.core.c */
public class C0470c {
    public static final String[] f3139a;

    static {
        f3139a = new String[]{"com.amap.api.services"};
    }

    public static ad m4754a(boolean z) {
        String str = "getSDKInfo";
        ad adVar = null;
        try {
            adVar = new C0452a("sea", "2.5.0", "AMAP SDK Android Search 2.5.0").m4492a(f3139a).m4491a(z).m4493a();
        } catch (Throwable e) {
            C0471d.m4762a(e, "ConfigableConst", str);
        }
        return adVar;
    }

    public static String m4755a() {
        return ServiceSettings.getInstance().getProtocol() == 1 ? "http://restapi.amap.com/v3" : "https://restapi.amap.com/v3";
    }

    public static String m4756b() {
        return ServiceSettings.getInstance().getLanguage();
    }
}
