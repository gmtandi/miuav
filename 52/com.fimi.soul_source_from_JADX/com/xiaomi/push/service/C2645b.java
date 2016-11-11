package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2695b;
import com.xiaomi.smack.packet.C2696c;
import com.xiaomi.smack.packet.C2699f;
import com.xiaomi.smack.util.C2720h;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.b */
public class C2645b {
    private C2655k f13126a;

    public C2645b() {
        this.f13126a = new C2655k();
    }

    public static String m15000a(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    private static void m15001a(Context context, Intent intent, String str) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, C2645b.m15000a(str));
        }
    }

    C2667b m15002a(C2694d c2694d) {
        Collection c = C2669v.m15106a().m15118c(c2694d.m15234l());
        if (c.isEmpty()) {
            return null;
        }
        Iterator it = c.iterator();
        if (c.size() == 1) {
            return (C2667b) it.next();
        }
        CharSequence n = c2694d.m15238n();
        CharSequence m = c2694d.m15236m();
        while (it.hasNext()) {
            C2667b c2667b = (C2667b) it.next();
            if (TextUtils.equals(n, c2667b.f13178b)) {
                return c2667b;
            }
            if (TextUtils.equals(m, c2667b.f13178b)) {
                return c2667b;
            }
        }
        return null;
    }

    public void m15003a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        context.sendBroadcast(intent);
    }

    public void m15004a(Context context, C2667b c2667b, int i) {
        if (!Constants.VIA_SHARE_TYPE_TEXT.equalsIgnoreCase(c2667b.f13184h)) {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.channel_closed");
            intent.setPackage(c2667b.f13177a);
            intent.putExtra(C2671x.f13215n, c2667b.f13184h);
            intent.putExtra("ext_reason", i);
            intent.putExtra(C2671x.f13214m, c2667b.f13178b);
            intent.putExtra(C2671x.f13226y, c2667b.f13186j);
            C2645b.m15001a(context, intent, c2667b.f13177a);
        }
    }

    public void m15005a(Context context, C2667b c2667b, String str, String str2) {
        if (Constants.VIA_SHARE_TYPE_TEXT.equalsIgnoreCase(c2667b.f13184h)) {
            C2463b.m14127c("mipush kicked by server");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.kicked");
        intent.setPackage(c2667b.f13177a);
        intent.putExtra("ext_kick_type", str);
        intent.putExtra("ext_kick_reason", str2);
        intent.putExtra("ext_chid", c2667b.f13184h);
        intent.putExtra(C2671x.f13214m, c2667b.f13178b);
        intent.putExtra(C2671x.f13226y, c2667b.f13186j);
        C2645b.m15001a(context, intent, c2667b.f13177a);
    }

    public void m15006a(Context context, C2667b c2667b, boolean z, int i, String str) {
        if (Constants.VIA_SHARE_TYPE_TEXT.equalsIgnoreCase(c2667b.f13184h)) {
            this.f13126a.m15057a(context, c2667b, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(c2667b.f13177a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", c2667b.f13184h);
        intent.putExtra(C2671x.f13214m, c2667b.f13178b);
        intent.putExtra(C2671x.f13226y, c2667b.f13186j);
        C2645b.m15001a(context, intent, c2667b.f13177a);
    }

    public void m15007a(XMPushService xMPushService, String str, C2694d c2694d) {
        C2667b a = m15002a(c2694d);
        if (a == null) {
            C2463b.m14127c("error while notify channel closed! channel " + str + " not registered");
        } else if (Constants.VIA_SHARE_TYPE_TEXT.equalsIgnoreCase(str)) {
            this.f13126a.m15058a(xMPushService, c2694d, a);
        } else {
            String str2;
            String str3 = a.f13177a;
            if (c2694d instanceof C2696c) {
                str2 = "com.xiaomi.push.new_msg";
            } else if (c2694d instanceof C2695b) {
                str2 = "com.xiaomi.push.new_iq";
            } else if (c2694d instanceof C2699f) {
                str2 = "com.xiaomi.push.new_pres";
            } else {
                C2463b.m14127c("unknown packet type, drop it");
                return;
            }
            Intent intent = new Intent();
            intent.setAction(str2);
            intent.setPackage(str3);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", c2694d.a_());
            intent.putExtra(C2671x.f13226y, a.f13186j);
            intent.putExtra(C2671x.f13219r, a.f13185i);
            C2645b.m15001a((Context) xMPushService, intent, str3);
            C2720h.m15369a(xMPushService, str3, (long) C2720h.m15366a(c2694d.m15228a()), true, System.currentTimeMillis());
        }
    }
}
