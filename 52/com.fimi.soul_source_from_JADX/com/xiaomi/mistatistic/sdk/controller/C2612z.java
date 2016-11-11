package com.xiaomi.mistatistic.sdk.controller;

import android.app.Activity;
import android.text.TextUtils;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.z */
class C2612z implements C2582e {
    final /* synthetic */ Activity f12986a;
    final /* synthetic */ String f12987b;
    final /* synthetic */ C2610x f12988c;

    C2612z(C2610x c2610x, Activity activity, String str) {
        this.f12988c = c2610x;
        this.f12986a = activity;
        this.f12987b = str;
    }

    public void m14809a() {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        long a = C2605s.m14780a(this.f12986a.getApplicationContext(), "session_begin", 0);
        long a2 = C2605s.m14780a(this.f12986a.getApplicationContext(), "last_deactivate", 0);
        String a3 = C2605s.m14781a(this.f12986a.getApplicationContext(), "pv_path", C2915a.f14760f);
        if (a <= 0) {
            C2605s.m14784b(this.f12986a.getApplicationContext(), "session_begin", currentTimeMillis);
        } else if (a2 <= 0) {
            C2605s.m14784b(this.f12986a.getApplicationContext(), "session_begin", currentTimeMillis);
            if (!TextUtils.isEmpty(a3)) {
                this.f12988c.m14803a(this.f12986a, a3);
                a3 = C2915a.f14760f;
            }
        }
        if (a2 > 0 && currentTimeMillis - a2 > C2610x.f12983c) {
            this.f12988c.m14802a(this.f12986a, a, a2);
            if (TextUtils.isEmpty(a3)) {
                str = a3;
            } else {
                this.f12988c.m14803a(this.f12986a, a3);
                str = C2915a.f14760f;
            }
            C2605s.m14784b(this.f12986a.getApplicationContext(), "session_begin", currentTimeMillis);
            a3 = str;
        }
        str = TextUtils.isEmpty(this.f12987b) ? this.f12986a.getClass().getName() : this.f12987b;
        CharSequence packageName = this.f12986a.getPackageName();
        if (str.startsWith(packageName)) {
            str = str.replace(packageName, C2915a.f14760f);
        }
        if (!a3.endsWith(str)) {
            C2605s.m14785b(this.f12986a.getApplicationContext(), "pv_path", this.f12988c.m14801a(a3, str));
        }
    }
}
