package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.push.service.s */
public class C2663s {
    private static Object f13170a;
    private static Map<String, Queue<String>> f13171b;

    static {
        f13170a = new Object();
        f13171b = new HashMap();
    }

    public static boolean m15089a(XMPushService xMPushService, String str, String str2) {
        synchronized (f13170a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Collection collection = (Queue) f13171b.get(str);
            if (collection == null) {
                String[] split = sharedPreferences.getString(str, C2915a.f14760f).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                collection = new LinkedList();
                for (Object add : split) {
                    collection.add(add);
                }
                f13171b.put(str, collection);
            }
            if (collection.contains(str2)) {
                return true;
            }
            collection.add(str2);
            if (collection.size() > 10) {
                collection.poll();
            }
            String a = C2476d.m14167a(collection, MiPushClient.ACCEPT_TIME_SEPARATOR);
            Editor edit = sharedPreferences.edit();
            edit.putString(str, a);
            edit.commit();
            return false;
        }
    }
}
