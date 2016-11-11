package com.tencent.stat.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.tencent.stat.StatConfig;
import com.xiaomi.market.sdk.C2537j;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.common.c */
class C2410c {
    String f12312a;
    String f12313b;
    DisplayMetrics f12314c;
    int f12315d;
    String f12316e;
    String f12317f;
    String f12318g;
    String f12319h;
    String f12320i;
    String f12321j;
    String f12322k;
    int f12323l;
    String f12324m;
    Context f12325n;
    private String f12326o;
    private String f12327p;
    private String f12328q;
    private String f12329r;

    private C2410c(Context context) {
        this.f12313b = StatConstants.VERSION;
        this.f12315d = VERSION.SDK_INT;
        this.f12316e = Build.MODEL;
        this.f12317f = Build.MANUFACTURER;
        this.f12318g = Locale.getDefault().getLanguage();
        this.f12323l = 0;
        this.f12324m = null;
        this.f12325n = null;
        this.f12326o = null;
        this.f12327p = null;
        this.f12328q = null;
        this.f12329r = null;
        this.f12325n = context;
        this.f12314c = C2418k.m14025d(context);
        this.f12312a = C2418k.m14041n(context);
        this.f12319h = StatConfig.getInstallChannel(context);
        this.f12320i = C2418k.m14040m(context);
        this.f12321j = TimeZone.getDefault().getID();
        this.f12323l = C2418k.m14046s(context);
        this.f12322k = C2418k.m14047t(context);
        this.f12324m = context.getPackageName();
        if (this.f12315d >= 14) {
            this.f12326o = C2418k.m14002A(context);
        }
        this.f12327p = C2418k.m14053z(context).toString();
        this.f12328q = C2418k.m14051x(context);
        this.f12329r = C2418k.m14027e();
    }

    void m13985a(JSONObject jSONObject) {
        jSONObject.put("sr", this.f12314c.widthPixels + "*" + this.f12314c.heightPixels);
        C2418k.m14014a(jSONObject, "av", this.f12312a);
        C2418k.m14014a(jSONObject, "ch", this.f12319h);
        C2418k.m14014a(jSONObject, "mf", this.f12317f);
        C2418k.m14014a(jSONObject, "sv", this.f12313b);
        C2418k.m14014a(jSONObject, "ov", Integer.toString(this.f12315d));
        jSONObject.put(C2537j.ac, 1);
        C2418k.m14014a(jSONObject, "op", this.f12320i);
        C2418k.m14014a(jSONObject, "lg", this.f12318g);
        C2418k.m14014a(jSONObject, "md", this.f12316e);
        C2418k.m14014a(jSONObject, "tz", this.f12321j);
        if (this.f12323l != 0) {
            jSONObject.put("jb", this.f12323l);
        }
        C2418k.m14014a(jSONObject, "sd", this.f12322k);
        C2418k.m14014a(jSONObject, "apn", this.f12324m);
        if (C2418k.m14034h(this.f12325n)) {
            JSONObject jSONObject2 = new JSONObject();
            C2418k.m14014a(jSONObject2, "bs", C2418k.m14004C(this.f12325n));
            C2418k.m14014a(jSONObject2, "ss", C2418k.m14005D(this.f12325n));
            if (jSONObject2.length() > 0) {
                C2418k.m14014a(jSONObject, "wf", jSONObject2.toString());
            }
        }
        C2418k.m14014a(jSONObject, "sen", this.f12326o);
        C2418k.m14014a(jSONObject, "cpu", this.f12327p);
        C2418k.m14014a(jSONObject, "ram", this.f12328q);
        C2418k.m14014a(jSONObject, "rom", this.f12329r);
    }
}
