package com.xiaomi.push.service;

import android.content.Context;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.xiaomi.push.service.C2669v.C2667b;
import java.util.Locale;

/* renamed from: com.xiaomi.push.service.f */
public class C2650f {
    public final String f13132a;
    protected final String f13133b;
    protected final String f13134c;
    protected final String f13135d;
    protected final String f13136e;
    protected final String f13137f;
    protected final int f13138g;

    public C2650f(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.f13132a = str;
        this.f13133b = str2;
        this.f13134c = str3;
        this.f13135d = str4;
        this.f13136e = str5;
        this.f13137f = str6;
        this.f13138g = i;
    }

    private static boolean m15019a(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }

    public C2667b m15020a(XMPushService xMPushService) {
        C2667b c2667b = new C2667b(xMPushService);
        c2667b.f13177a = xMPushService.getPackageName();
        c2667b.f13178b = this.f13132a;
        c2667b.f13185i = this.f13134c;
        c2667b.f13179c = this.f13133b;
        c2667b.f13184h = Constants.VIA_SHARE_TYPE_TEXT;
        c2667b.f13180d = "XMPUSH-PASS";
        c2667b.f13181e = false;
        c2667b.f13182f = "sdk_ver:2";
        String str = C2650f.m15019a((Context) xMPushService) ? "1000271" : this.f13135d;
        c2667b.f13183g = String.format("%1$s:%2$s,%3$s:%4$s", new Object[]{SocialConstants.PARAM_APP_ID, str, "locale", Locale.getDefault().toString()});
        c2667b.f13187k = xMPushService.m14963e();
        return c2667b;
    }
}
