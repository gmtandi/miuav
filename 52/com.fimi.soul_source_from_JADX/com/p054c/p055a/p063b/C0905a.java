package com.p054c.p055a.p063b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.p054c.p055a.p056a.p057a.C0864a;
import com.p054c.p055a.p056a.p057a.p058a.C0871c;
import com.p054c.p055a.p056a.p057a.p058a.p059a.C0865h;
import com.p054c.p055a.p056a.p057a.p060b.C0872a;
import com.p054c.p055a.p056a.p057a.p060b.C0873b;
import com.p054c.p055a.p056a.p061b.C0875c;
import com.p054c.p055a.p056a.p061b.p062a.C0883f;
import com.p054c.p055a.p063b.p064a.C0902h;
import com.p054c.p055a.p063b.p064a.p065a.C0889c;
import com.p054c.p055a.p063b.p066b.C0906d;
import com.p054c.p055a.p063b.p066b.C0907a;
import com.p054c.p055a.p063b.p067c.C0912a;
import com.p054c.p055a.p063b.p067c.C0918g;
import com.p054c.p055a.p063b.p068d.C0920c;
import com.p054c.p055a.p063b.p068d.C0921a;
import com.p054c.p055a.p072c.C0958f;
import com.p054c.p055a.p072c.C0961i;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.c.a.b.a */
public class C0905a {
    @TargetApi(11)
    private static int m7211a(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static C0864a m7212a(Context context, C0872a c0872a, long j, int i) {
        File b = C0905a.m7220b(context);
        if (j > 0 || i > 0) {
            try {
                return new C0865h(C0961i.m7573b(context), b, c0872a, j, i);
            } catch (Throwable e) {
                C0958f.m7555a(e);
            }
        }
        return new C0871c(C0961i.m7569a(context), b, c0872a);
    }

    public static C0875c m7213a(Context context, int i) {
        if (i == 0) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int a = (C0905a.m7223d() && C0905a.m7222c(context)) ? C0905a.m7211a(activityManager) : activityManager.getMemoryClass();
            i = (a * Util.BYTE_OF_MB) / 8;
        }
        return new C0883f(i);
    }

    public static C0906d m7214a(boolean z) {
        return new C0907a(z);
    }

    public static C0920c m7215a(Context context) {
        return new C0921a(context);
    }

    public static Executor m7216a() {
        return Executors.newCachedThreadPool(C0905a.m7218a(5, "uil-pool-d-"));
    }

    public static Executor m7217a(int i, int i2, C0902h c0902h) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (c0902h == C0902h.LIFO ? 1 : null) != null ? new C0889c() : new LinkedBlockingQueue(), C0905a.m7218a(i2, "uil-pool-"));
    }

    private static ThreadFactory m7218a(int i, String str) {
        return new C0911b(i, str);
    }

    public static C0872a m7219b() {
        return new C0873b();
    }

    private static File m7220b(Context context) {
        File a = C0961i.m7572a(context, false);
        File file = new File(a, "uil-images");
        return (file.exists() || file.mkdir()) ? file : a;
    }

    public static C0912a m7221c() {
        return new C0918g();
    }

    @TargetApi(11)
    private static boolean m7222c(Context context) {
        return (context.getApplicationInfo().flags & Util.BYTE_OF_MB) != 0;
    }

    private static boolean m7223d() {
        return VERSION.SDK_INT >= 11;
    }
}
