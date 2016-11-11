package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.C2669v.C2667b;
import com.xiaomi.smack.packet.C2692a;
import com.xiaomi.smack.packet.C2694d;
import com.xiaomi.smack.packet.C2696c;
import com.xiaomi.smack.util.C2720h;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2742h;
import com.xiaomi.xmpush.thrift.C2748k;
import com.xiaomi.xmpush.thrift.C2773x;
import java.util.List;
import java.util.Map;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C2478b;

/* renamed from: com.xiaomi.push.service.k */
public class C2655k {
    private static void m15044a(XMPushService xMPushService, C2748k c2748k, String str) {
        xMPushService.m14945a(new C2660p(4, xMPushService, c2748k, str));
    }

    private static void m15045a(XMPushService xMPushService, C2748k c2748k, String str, String str2) {
        xMPushService.m14945a(new C2661q(4, xMPushService, c2748k, str, str2));
    }

    private static void m15046a(XMPushService xMPushService, byte[] bArr, long j) {
        C2748k c2748k = new C2748k();
        try {
            C2773x.m15814a(c2748k, bArr);
            if (TextUtils.isEmpty(c2748k.f13745f)) {
                C2463b.m14123a("receive a mipush message without package name");
                return;
            }
            Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.putExtra("mipush_payload", bArr);
            intent.setPackage(c2748k.f13745f);
            String a = C2662r.m15077a(c2748k);
            C2720h.m15369a(xMPushService, a, j, true, System.currentTimeMillis());
            C2734d m = c2748k.m15587m();
            String str;
            if (C2729a.SendMessage == c2748k.m15561a() && C2652h.m15031a((Context) xMPushService).m15032a(c2748k.f13745f)) {
                str = C2915a.f14760f;
                if (m != null) {
                    str = m.m15433b();
                }
                C2463b.m14123a("Drop a message for unregistered, msgid=" + str);
                C2655k.m15044a(xMPushService, c2748k, c2748k.f13745f);
            } else if (C2729a.SendMessage != c2748k.m15561a() || TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") || TextUtils.equals(xMPushService.getPackageName(), c2748k.f13745f)) {
                if (m != null) {
                    if (m.m15433b() != null) {
                        C2463b.m14123a(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[]{c2748k.m15582h(), m.m15433b()}));
                    }
                }
                if (C2655k.m15054c(c2748k) && C2655k.m15048a((Context) xMPushService, a)) {
                    C2655k.m15053c(xMPushService, c2748k);
                } else if (C2655k.m15049a(c2748k) && !C2655k.m15048a((Context) xMPushService, a) && !C2655k.m15052b(c2748k)) {
                    C2655k.m15055d(xMPushService, c2748k);
                } else if (C2655k.m15047a((Context) xMPushService, intent)) {
                    if (C2729a.Registration == c2748k.m15561a()) {
                        str = c2748k.m15584j();
                        Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                        edit.putString(str, c2748k.f13744e);
                        edit.commit();
                    }
                    if (m == null || TextUtils.isEmpty(m.m15446h()) || TextUtils.isEmpty(m.m15448j()) || m.f13548h == 1 || (!C2655k.m15050a(m.m15457s()) && C2662r.m15080a((Context) xMPushService, c2748k.f13745f))) {
                        xMPushService.sendBroadcast(intent, C2645b.m15000a(c2748k.f13745f));
                    } else {
                        boolean a2;
                        str = null;
                        if (m != null) {
                            if (m.f13550j != null) {
                                str = (String) m.f13550j.get("jobkey");
                            }
                            if (TextUtils.isEmpty(str)) {
                                str = m.m15433b();
                            }
                            a2 = C2663s.m15089a(xMPushService, c2748k.f13745f, str);
                        } else {
                            a2 = false;
                        }
                        if (a2) {
                            C2463b.m14123a("drop a duplicate message, key=" + str);
                        } else {
                            C2662r.m15078a((Context) xMPushService, c2748k, bArr);
                        }
                        C2655k.m15051b(xMPushService, c2748k);
                    }
                    if (c2748k.m15561a() == C2729a.UnRegistration && !"com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                        xMPushService.stopSelf();
                    }
                } else {
                    xMPushService.m14945a(new C2656l(4, xMPushService, c2748k));
                }
            } else {
                C2463b.m14123a("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + c2748k.f13745f);
                C2655k.m15045a(xMPushService, c2748k, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + c2748k.f13745f);
            }
        } catch (Throwable e) {
            C2463b.m14125a(e);
        }
    }

    private static boolean m15047a(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    private static boolean m15048a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            return (packageManager.queryBroadcastReceivers(intent2, 32).isEmpty() && packageManager.queryIntentServices(intent, 32).isEmpty()) ? false : true;
        } catch (Throwable e) {
            C2463b.m14125a(e);
            return false;
        }
    }

    private static boolean m15049a(C2748k c2748k) {
        return "com.xiaomi.xmsf".equals(c2748k.f13745f) && c2748k.m15587m() != null && c2748k.m15587m().m15457s() != null && c2748k.m15587m().m15457s().containsKey("miui_package_name");
    }

    public static boolean m15050a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return Constants.VIA_TO_TYPE_QQ_GROUP.equals((String) map.get("notify_foreground"));
    }

    private static void m15051b(XMPushService xMPushService, C2748k c2748k) {
        xMPushService.m14945a(new C2657m(4, xMPushService, c2748k));
    }

    private static boolean m15052b(C2748k c2748k) {
        return c2748k.m15587m().m15457s().containsKey("notify_effect");
    }

    private static void m15053c(XMPushService xMPushService, C2748k c2748k) {
        xMPushService.m14945a(new C2658n(4, xMPushService, c2748k));
    }

    private static boolean m15054c(C2748k c2748k) {
        return (c2748k.m15587m() == null || c2748k.m15587m().m15457s() == null) ? false : Constants.VIA_TO_TYPE_QQ_GROUP.equals(c2748k.m15587m().m15457s().get("obslete_ads_message"));
    }

    private static void m15055d(XMPushService xMPushService, C2748k c2748k) {
        xMPushService.m14945a(new C2659o(4, xMPushService, c2748k));
    }

    private static C2748k m15056e(XMPushService xMPushService, C2748k c2748k) {
        C2478b c2742h = new C2742h();
        c2742h.m15505b(c2748k.m15582h());
        C2734d m = c2748k.m15587m();
        if (m != null) {
            c2742h.m15499a(m.m15433b());
            c2742h.m15498a(m.m15440d());
            if (!TextUtils.isEmpty(m.m15444f())) {
                c2742h.m15508c(m.m15444f());
            }
        }
        C2748k a = xMPushService.m14941a(c2748k.m15584j(), c2748k.m15582h(), c2742h, C2729a.AckMessage);
        a.m15563a(c2748k.m15587m().m15422a());
        return a;
    }

    public void m15057a(Context context, C2667b c2667b, boolean z, int i, String str) {
        if (!z) {
            C2650f a = C2651g.m15021a(context);
            if (a != null && "token-expired".equals(str)) {
                try {
                    C2651g.m15022a(context, a.f13135d, a.f13136e, a.f13137f);
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                } catch (Throwable e2) {
                    C2463b.m14125a(e2);
                }
            }
        }
    }

    public void m15058a(XMPushService xMPushService, C2694d c2694d, C2667b c2667b) {
        if (c2694d instanceof C2696c) {
            C2696c c2696c = (C2696c) c2694d;
            C2692a p = c2696c.m15242p("s");
            if (p != null) {
                try {
                    C2655k.m15046a(xMPushService, C2673z.m15131b(C2673z.m15128a(c2667b.f13185i, c2696c.m15232k()), p.m15221c()), (long) C2720h.m15366a(c2694d.m15228a()));
                    return;
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    return;
                }
            }
            return;
        }
        C2463b.m14123a("not a mipush message");
    }
}
