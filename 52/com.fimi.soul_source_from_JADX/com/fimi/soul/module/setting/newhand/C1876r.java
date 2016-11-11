package com.fimi.soul.module.setting.newhand;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.C1397v;

/* renamed from: com.fimi.soul.module.setting.newhand.r */
class C1876r implements OnClickListener {
    final /* synthetic */ C1397v f9593a;
    final /* synthetic */ C1874p f9594b;

    C1876r(C1874p c1874p, C1397v c1397v) {
        this.f9594b = c1874p;
        this.f9593a = c1397v;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f9594b.m11877a(2, 1);
        this.f9593a.m9358a(C1236a.m8533b(this.f9594b.f9591x), C1397v.f6277b, new C1877s(this));
    }
}
