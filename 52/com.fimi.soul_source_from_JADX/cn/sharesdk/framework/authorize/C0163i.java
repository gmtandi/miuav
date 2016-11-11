package cn.sharesdk.framework.authorize;

import android.app.Instrumentation;
import cn.sharesdk.framework.utils.C0205d;

/* renamed from: cn.sharesdk.framework.authorize.i */
class C0163i extends Thread {
    final /* synthetic */ C0162h f216a;

    C0163i(C0162h c0162h) {
        this.f216a = c0162h;
    }

    public void run() {
        try {
            new Instrumentation().sendKeyDownUpSync(4);
        } catch (Throwable th) {
            C0205d.m752a().m750w(th);
            AuthorizeListener authorizeListener = this.f216a.f215a.a.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
            this.f216a.f215a.finish();
        }
    }
}
