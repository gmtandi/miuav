package com.xiaomi.push.service.timers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.push.service.C2671x;
import com.xiaomi.smack.C2706r;

/* renamed from: com.xiaomi.push.service.timers.a */
public class C2665a {
    private static volatile long f13174c;
    private PendingIntent f13175a;
    private Context f13176b;

    static {
        f13174c = 0;
    }

    public C2665a(Context context) {
        this.f13175a = null;
        this.f13176b = null;
        this.f13176b = context;
    }

    private void m15095a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke(alarmManager, new Object[]{Integer.valueOf(0), Long.valueOf(j), pendingIntent});
        } catch (Throwable e) {
            C2463b.m14125a(e);
        }
    }

    public synchronized void m15096a() {
        if (this.f13175a != null) {
            ((AlarmManager) this.f13176b.getSystemService("alarm")).cancel(this.f13175a);
            this.f13175a = null;
            C2463b.m14126b("unregister timer");
            f13174c = 0;
        }
    }

    public synchronized void m15097a(Intent intent, long j) {
        if (this.f13175a == null) {
            AlarmManager alarmManager = (AlarmManager) this.f13176b.getSystemService("alarm");
            this.f13175a = PendingIntent.getBroadcast(this.f13176b, 0, intent, 0);
            if (VERSION.SDK_INT >= 19) {
                m15095a(alarmManager, j, this.f13175a);
            } else {
                alarmManager.set(0, j, this.f13175a);
            }
            C2463b.m14126b("register timer " + f13174c);
        }
    }

    public synchronized void m15098a(boolean z) {
        Intent intent = new Intent(C2671x.f13213l);
        intent.setPackage(this.f13176b.getPackageName());
        long d = (long) C2706r.m15303d();
        if (z || f13174c == 0) {
            f13174c = (SystemClock.elapsedRealtime() % d) + System.currentTimeMillis();
        } else {
            f13174c = d + f13174c;
        }
        m15097a(intent, f13174c);
    }

    public synchronized boolean m15099b() {
        return this.f13175a != null;
    }
}
