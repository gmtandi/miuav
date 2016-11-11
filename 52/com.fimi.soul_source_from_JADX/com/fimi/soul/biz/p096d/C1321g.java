package com.fimi.soul.biz.p096d;

import android.util.Log;
import com.fimi.kernel.p076b.C1153f;

/* renamed from: com.fimi.soul.biz.d.g */
class C1321g implements C1153f<String> {
    final /* synthetic */ C1153f f5930a;
    final /* synthetic */ C1316b f5931b;

    C1321g(C1316b c1316b, C1153f c1153f) {
        this.f5931b = c1316b;
        this.f5930a = c1153f;
    }

    public void m8916a(String str) {
        Log.d("Good", str);
        this.f5930a.m7924a(Boolean.valueOf(true));
    }

    public void m8918b(String str) {
        this.f5930a.m7924a(Boolean.valueOf(false));
    }
}
