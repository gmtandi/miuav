package com.xiaomi.push.service.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.C2671x;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.timers.C2665a;

public class PingReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new C2665a(context).m15098a(false);
        C2463b.m14126b(intent.getPackage() + " is the package name");
        if (!C2671x.f13213l.equals(intent.getAction())) {
            C2463b.m14123a("cancel the old ping timer");
            ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, 0, new Intent(context, PingReceiver.class), 0));
        } else if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
            C2463b.m14126b("Ping XMChannelService on timer");
            try {
                Intent intent2 = new Intent(context, XMPushService.class);
                intent2.setAction("com.xiaomi.push.timer");
                context.startService(intent2);
            } catch (Throwable e) {
                C2463b.m14125a(e);
            }
        }
    }
}
