package com.xiaomi.market.sdk;

import android.text.TextUtils;
import java.io.File;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.market.sdk.q */
public class C2544q {
    public String aT;
    public String aU;
    public String aV;
    public boolean aW;
    public String packageName;
    public String sourceDir;
    public int versionCode;
    public String versionName;

    private C2544q() {
        this.packageName = C2915a.f14760f;
        this.aT = C2915a.f14760f;
        this.versionCode = 0;
        this.versionName = C2915a.f14760f;
        this.aU = C2915a.f14760f;
        this.sourceDir = C2915a.f14760f;
        this.aV = C2915a.f14760f;
        this.aW = false;
    }

    public static C2544q m14541h(String str) {
        C2544q c2544q = new C2544q();
        c2544q.packageName = str;
        return c2544q;
    }

    public String m14542n() {
        return TextUtils.isEmpty(this.sourceDir) ? null : TextUtils.isEmpty(this.aV) ? C2529b.m14486a(new File(this.sourceDir)) : this.aV;
    }
}
