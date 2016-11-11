package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.network.C2472a;
import com.xiaomi.mipush.sdk.C2566a;
import com.xiaomi.mipush.sdk.C2573e;
import com.xiaomi.push.service.XMPushService;

public class NetworkStatusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (!(C2573e.m14652a(context).m14669b() || !C2566a.m14615a(context).m14632i() || C2566a.m14615a(context).m14637n())) {
            try {
                Intent intent2 = new Intent(context, XMPushService.class);
                intent2.setAction("com.xiaomi.push.network_status_changed");
                context.startService(intent2);
            } catch (Throwable e) {
                C2463b.m14125a(e);
            }
        }
        if (C2472a.m14152d(context) && C2573e.m14652a(context).m14673f()) {
            C2573e.m14652a(context).m14670c();
        }
    }
}
