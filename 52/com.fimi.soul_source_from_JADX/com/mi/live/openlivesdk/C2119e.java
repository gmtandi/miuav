package com.mi.live.openlivesdk;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.mi.live.openlivesdk.p120a.C1687a;

/* renamed from: com.mi.live.openlivesdk.e */
public class C2119e {
    static final String f11132a = "liveopen://startstream?appid=%s&appkey=%s&packagename=%s&playui=%s";
    static final String f11133b = "liveopen://endstream?appid=%s&appkey=%s&packagename=%s";
    private String f11134c;
    private String f11135d;
    private int f11136e;
    private C1687a f11137f;
    private Application f11138g;
    private BroadcastReceiver f11139h;

    public C2119e(String str, String str2, int i, C1687a c1687a, Application application) {
        this.f11136e = 0;
        this.f11139h = new C2120f(this);
        this.f11134c = str;
        this.f11135d = str2;
        this.f11137f = c1687a;
        this.f11138g = application;
        this.f11136e = i;
        m13051b();
    }

    private void m13051b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(C2116b.f11119b);
        intentFilter.addAction(C2116b.f11120c);
        if (this.f11138g != null) {
            this.f11138g.registerReceiver(this.f11139h, intentFilter);
        }
    }

    private void m13052c() {
        if (this.f11138g != null) {
            this.f11138g.unregisterReceiver(this.f11139h);
        }
    }

    public void m13053a() {
        this.f11137f = null;
        m13052c();
        this.f11138g = null;
    }

    public void m13054a(Context context) {
        if (context != null) {
            int a = C2121g.m13056a(context);
            if (a == 1) {
                Intent intent = new Intent();
                intent.setAction(C2116b.f11118a);
                intent.setData(Uri.parse(String.format(f11132a, new Object[]{this.f11134c, this.f11135d, context.getPackageName(), Integer.valueOf(this.f11136e)})));
                context.startActivity(intent);
            } else if (a == -2) {
                this.f11137f.m11032a(-2, "MiLive App not install");
            } else if (a == -3) {
                this.f11137f.m11032a(-3, "MiLive App version is too low");
            }
        }
    }

    public void m13055b(Context context) {
        if (context != null) {
            int a = C2121g.m13056a(context);
            if (a == 1) {
                Intent intent = new Intent();
                intent.setAction(C2116b.f11118a);
                intent.setData(Uri.parse(String.format(f11133b, new Object[]{this.f11134c, this.f11135d, context.getPackageName()})));
                context.startActivity(intent);
            } else if (a == -2) {
                this.f11137f.m11034b(-2, "MiLive App not install");
            } else if (a == -3) {
                this.f11137f.m11034b(-3, "MiLive App version is too low");
            }
        }
    }
}
