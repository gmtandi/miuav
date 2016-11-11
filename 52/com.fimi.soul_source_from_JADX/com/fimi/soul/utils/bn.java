package com.fimi.soul.utils;

import android.graphics.Bitmap;
import com.android.volley.ag;
import com.android.volley.toolbox.C0575y;
import com.android.volley.toolbox.C0597x;
import com.fimi.kernel.p084e.C1181t;

class bn implements C0575y {
    final /* synthetic */ bl f10106a;

    bn(bl blVar) {
        this.f10106a = blVar;
    }

    public void m12433a(ag agVar) {
    }

    public void m12434a(C0597x c0597x, boolean z) {
        Bitmap b = c0597x.m5270b();
        if (this.f10106a.f10102k != null) {
            this.f10106a.f10102k.m12436a(b);
        }
        C1181t.m8221a(bl.class, "\u83b7\u53d6\u5230\u56fe\u7247\uff1a" + b);
    }
}
