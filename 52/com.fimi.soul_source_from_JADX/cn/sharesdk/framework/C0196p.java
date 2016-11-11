package cn.sharesdk.framework;

import cn.sharesdk.framework.p013b.C0170a;
import cn.sharesdk.framework.utils.C0205d;
import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.p */
class C0196p extends Thread {
    final /* synthetic */ C0170a f356a;
    final /* synthetic */ C0194n f357b;

    C0196p(C0194n c0194n, C0170a c0170a) {
        this.f357b = c0194n;
        this.f356a = c0170a;
    }

    public void run() {
        try {
            HashMap g = this.f356a.m522g();
            HashMap hashMap = new HashMap();
            if (this.f357b.m676a(this.f356a, g, hashMap)) {
                this.f357b.m703b(hashMap);
            }
            this.f356a.m513a(g);
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }
}
