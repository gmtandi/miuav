package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.C2463b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.xiaomi.push.service.j */
public class C2654j {
    private static final Map<String, byte[]> f13151a;
    private static final ArrayList<Pair<String, byte[]>> f13152b;

    static {
        f13151a = new HashMap();
        f13152b = new ArrayList();
    }

    public static void m15037a(Context context, int i, String str) {
        synchronized (f13151a) {
            for (String str2 : f13151a.keySet()) {
                C2654j.m15038a(context, str2, (byte[]) f13151a.get(str2), i, str);
            }
            f13151a.clear();
        }
    }

    public static void m15038a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, C2645b.m15000a(str));
    }

    public static void m15039a(XMPushService xMPushService) {
        try {
            synchronized (f13151a) {
                for (String str : f13151a.keySet()) {
                    xMPushService.m14951a(str, (byte[]) f13151a.get(str));
                }
                f13151a.clear();
            }
        } catch (Throwable e) {
            C2463b.m14125a(e);
            xMPushService.m14956b(10, e);
        }
    }

    public static void m15040a(String str, byte[] bArr) {
        synchronized (f13151a) {
            f13151a.put(str, bArr);
        }
    }

    public static void m15041b(XMPushService xMPushService) {
        try {
            synchronized (f13152b) {
                Iterator it = f13152b.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    xMPushService.m14951a((String) pair.first, (byte[]) pair.second);
                }
                f13152b.clear();
            }
        } catch (Throwable e) {
            C2463b.m14125a(e);
            xMPushService.m14956b(10, e);
        }
    }

    public static void m15042b(String str, byte[] bArr) {
        synchronized (f13152b) {
            f13152b.add(new Pair(str, bArr));
        }
    }
}
