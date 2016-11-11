package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.C0205d;

/* renamed from: cn.sharesdk.framework.g */
class C0187g extends Thread {
    final /* synthetic */ int f324a;
    final /* synthetic */ Object f325b;
    final /* synthetic */ C0186f f326c;

    C0187g(C0186f c0186f, int i, Object obj) {
        this.f326c = c0186f;
        this.f324a = i;
        this.f325b = obj;
    }

    public void run() {
        try {
            this.f326c.m629j();
            if (this.f326c.f315a.checkAuthorize(this.f324a, this.f325b)) {
                this.f326c.m645b(this.f324a, this.f325b);
            }
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }
}
