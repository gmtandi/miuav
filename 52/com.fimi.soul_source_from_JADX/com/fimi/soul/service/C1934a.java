package com.fimi.soul.service;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/* renamed from: com.fimi.soul.service.a */
public class C1934a {
    private static CameraSocketService f9963a;
    private static C1934a f9964b;
    private static C1945l f9965e;
    private boolean f9966c;
    private Context f9967d;
    private ServiceConnection f9968f;

    static {
        f9963a = null;
    }

    public C1934a(Context context) {
        this.f9966c = false;
        this.f9968f = new C1935b(this);
        this.f9967d = context;
    }

    public static C1934a m12165a(Context context) {
        if (f9964b == null) {
            f9964b = new C1934a(context);
        }
        if (f9963a == null) {
            f9964b.m12172c();
        } else if (!f9963a.m12135g()) {
            f9963a.m12133e();
        }
        return f9964b;
    }

    public void m12168a() {
        if (f9963a != null) {
            f9963a.m12130b();
        }
    }

    public void m12169a(C1945l c1945l) {
        f9965e = c1945l;
        if (f9963a != null) {
            f9963a.m12128a(f9965e);
        }
    }

    public void m12170a(String str, String str2) {
        f9963a.m12129a(str, str2);
    }

    public C1939f m12171b() {
        return f9963a.m12131c();
    }

    public synchronized void m12172c() {
        if (!this.f9966c) {
            this.f9966c = true;
            Intent intent = new Intent();
            intent.setClass(this.f9967d, CameraSocketService.class);
            this.f9967d.getApplicationContext().bindService(intent, this.f9968f, 1);
        }
    }

    public synchronized void m12173d() {
        if (this.f9966c) {
            this.f9966c = false;
            new Intent().setClass(this.f9967d, CameraSocketService.class);
            this.f9967d.getApplicationContext().unbindService(this.f9968f);
            f9963a.m12132d();
            f9963a.onDestroy();
            f9963a = null;
            f9964b = null;
        }
    }

    public void m12174e() {
        f9963a.m12126a();
    }

    public String m12175f() {
        return f9963a == null ? null : f9963a.m12134f();
    }

    public String m12176g() {
        StringBuffer stringBuffer = new StringBuffer();
        if (f9963a == null || !m12177h()) {
            return null;
        }
        stringBuffer.append("rtsp://");
        stringBuffer.append(f9963a.m12134f());
        stringBuffer.append("/media/stream1");
        return stringBuffer.toString();
    }

    public boolean m12177h() {
        return f9963a == null ? false : f9963a.m12135g();
    }

    public void m12178i() {
        if (f9963a != null) {
            f9963a.m12133e();
        }
    }
}
