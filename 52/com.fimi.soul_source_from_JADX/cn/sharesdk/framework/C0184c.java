package cn.sharesdk.framework;

import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.c */
class C0184c implements PlatformActionListener {
    final /* synthetic */ PlatformActionListener f308a;
    final /* synthetic */ int f309b;
    final /* synthetic */ Object f310c;
    final /* synthetic */ C0152a f311d;

    C0184c(C0152a c0152a, PlatformActionListener platformActionListener, int i, Object obj) {
        this.f311d = c0152a;
        this.f308a = platformActionListener;
        this.f309b = i;
        this.f310c = obj;
    }

    public void onCancel(Platform platform, int i) {
        this.f311d.f191a = this.f308a;
        if (this.f311d.f191a != null) {
            this.f311d.f191a.onCancel(platform, this.f309b);
        }
    }

    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        this.f311d.f191a = this.f308a;
        platform.afterRegister(this.f309b, this.f310c);
    }

    public void onError(Platform platform, int i, Throwable th) {
        this.f311d.f191a = this.f308a;
        if (this.f311d.f191a != null) {
            this.f311d.f191a.onError(platform, i, th);
        }
    }
}
