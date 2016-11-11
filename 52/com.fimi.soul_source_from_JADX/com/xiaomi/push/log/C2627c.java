package com.xiaomi.push.log;

import android.util.Log;
import com.xiaomi.channel.commonutils.file.C2461a;
import com.xiaomi.channel.commonutils.misc.C2467b.C2466b;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.push.log.c */
class C2627c extends C2466b {
    final /* synthetic */ C2626b f13056a;

    C2627c(C2626b c2626b) {
        this.f13056a = c2626b;
    }

    public void m14890b() {
        if (!C2626b.f13053f.isEmpty()) {
            try {
                if (C2461a.m14115d()) {
                    this.f13056a.m14888b();
                } else {
                    Log.w(this.f13056a.f13054d, "SDCard is unavailable.");
                }
            } catch (Throwable e) {
                Log.e(this.f13056a.f13054d, C2915a.f14760f, e);
            }
        }
    }
}
