package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.C0205d;

/* renamed from: cn.sharesdk.framework.h */
class C0188h extends Thread {
    final /* synthetic */ String[] f327a;
    final /* synthetic */ C0186f f328b;

    C0188h(C0186f c0186f, String[] strArr) {
        this.f328b = c0186f;
        this.f327a = strArr;
    }

    public void run() {
        try {
            this.f328b.m629j();
            this.f328b.f315a.doAuthorize(this.f327a);
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }
}
