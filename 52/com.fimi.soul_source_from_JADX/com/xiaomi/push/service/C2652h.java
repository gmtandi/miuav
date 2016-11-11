package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.push.service.h */
public class C2652h {
    private static C2652h f13143a;
    private Context f13144b;
    private List<String> f13145c;

    static {
        f13143a = null;
    }

    private C2652h(Context context) {
        int i = 0;
        this.f13145c = new ArrayList();
        this.f13144b = context.getApplicationContext();
        if (this.f13144b == null) {
            this.f13144b = context;
        }
        String[] split = this.f13144b.getSharedPreferences("mipush_app_info", 0).getString("unregistered_pkg_names", C2915a.f14760f).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        int length = split.length;
        while (i < length) {
            CharSequence charSequence = split[i];
            if (TextUtils.isEmpty(charSequence)) {
                this.f13145c.add(charSequence);
            }
            i++;
        }
    }

    public static C2652h m15031a(Context context) {
        if (f13143a == null) {
            f13143a = new C2652h(context);
        }
        return f13143a;
    }

    public boolean m15032a(String str) {
        boolean contains;
        synchronized (this.f13145c) {
            contains = this.f13145c.contains(str);
        }
        return contains;
    }

    public void m15033b(String str) {
        synchronized (this.f13145c) {
            if (!this.f13145c.contains(str)) {
                this.f13145c.add(str);
                this.f13144b.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C2476d.m14167a(this.f13145c, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }

    public void m15034c(String str) {
        synchronized (this.f13145c) {
            if (this.f13145c.contains(str)) {
                this.f13145c.remove(str);
                this.f13144b.getSharedPreferences("mipush_app_info", 0).edit().putString("unregistered_pkg_names", C2476d.m14167a(this.f13145c, MiPushClient.ACCEPT_TIME_SEPARATOR)).commit();
            }
        }
    }
}
