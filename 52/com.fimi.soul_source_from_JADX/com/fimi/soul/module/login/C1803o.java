package com.fimi.soul.module.login;

import android.content.Intent;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.service.InitAppService;
import com.fimi.soul.utils.at;
import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.fimi.soul.module.login.o */
class C1803o implements at {
    final /* synthetic */ LoginFragment f8859a;

    C1803o(LoginFragment loginFragment) {
        this.f8859a = loginFragment;
    }

    public void m11538a() {
        this.f8859a.f8822l.setVisibility(4);
        MiPushClient.registerPush(this.f8859a.f8812b, C1236a.f5628z, C1236a.f5620r);
        this.f8859a.m11519a();
        this.f8859a.f8812b.startService(new Intent(this.f8859a.f8812b, InitAppService.class));
    }

    public void m11539a(String str) {
        this.f8859a.f8822l.setVisibility(4);
        ak.m8085a(this.f8859a.f8812b, str, ak.f5303c);
    }

    public void m11540b() {
        this.f8859a.f8822l.setVisibility(4);
    }
}
