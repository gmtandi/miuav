package com.xiaomi.mistatistic.sdk.controller;

import java.util.Collection;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.l */
class C2598l implements C2582e {
    final /* synthetic */ C2597k f12972a;

    C2598l(C2597k c2597k) {
        this.f12972a = c2597k;
    }

    public void m14765a() {
        if (this.f12972a.f12971a.m14763c()) {
            try {
                Collection b = this.f12972a.f12971a.m14762b();
                if (this.f12972a.f12971a.m14758f()) {
                    synchronized (this.f12972a.f12971a.f12969d) {
                        this.f12972a.f12971a.f12969d.removeAll(b);
                    }
                }
            } catch (Throwable e) {
                new C2601o().m14770a(C2915a.f14760f, e);
            } catch (Throwable e2) {
                new C2601o().m14770a(C2915a.f14760f, e2);
            }
        }
    }
}
