package com.xiaomi.mistatistic.sdk.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mistatistic.sdk.data.C2619f;
import com.xiaomi.mistatistic.sdk.data.C2620g;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.x */
public class C2610x {
    private static C2610x f12982a;
    private static long f12983c;
    private Handler f12984b;

    static {
        f12983c = 30000;
    }

    private C2610x() {
        this.f12984b = new C2611y(this, Looper.getMainLooper());
    }

    public static C2610x m14799a() {
        if (f12982a == null) {
            f12982a = new C2610x();
        }
        return f12982a;
    }

    private String m14801a(String str, String str2) {
        return TextUtils.isEmpty(str) ? str2 : MiPushClient.ACCEPT_TIME_SEPARATOR + str2;
    }

    private void m14802a(Context context, long j, long j2) {
        String b = C2603q.m14776b(context.getApplicationContext());
        if (TextUtils.isEmpty(b)) {
            b = "NULL";
        }
        C2599m.m14766a(new C2620g(j, j2, b));
        C2605s.m14784b(context.getApplicationContext(), "session_begin", 0);
        C2605s.m14784b(C2588a.m14708a(), "last_deactivate", 0);
    }

    private void m14803a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            C2599m.m14766a(new C2619f(str));
            C2605s.m14785b(context, "pv_path", C2915a.f14760f);
        }
    }

    public void m14807a(Activity activity, String str) {
        this.f12984b.removeMessages(31415927);
        C2589b.m14731a().m14734a(new C2612z(this, activity, str));
    }

    public void m14808b() {
        C2589b.m14731a().m14734a(new aa(this));
        this.f12984b.sendEmptyMessageDelayed(31415927, f12983c);
    }
}
