package cn.sharesdk.framework;

import cn.sharesdk.framework.utils.C0205d;
import java.util.HashMap;

/* renamed from: cn.sharesdk.framework.o */
class C0195o extends Thread {
    final /* synthetic */ C0194n f355a;

    C0195o(C0194n c0194n) {
        this.f355a = c0194n;
    }

    public void run() {
        try {
            HashMap hashMap = new HashMap();
            if (!this.f355a.m712f() && this.f355a.m695a(hashMap)) {
                this.f355a.m703b(hashMap);
            }
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
        }
    }
}
