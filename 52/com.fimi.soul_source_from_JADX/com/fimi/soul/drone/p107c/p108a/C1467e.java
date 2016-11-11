package com.fimi.soul.drone.p107c.p108a;

import android.content.Context;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.fimi.soul.drone.c.a.e */
public class C1467e {
    public int f7006a;
    public int f7007b;
    public int f7008c;
    String f7009d;
    private int f7010e;
    private Context f7011f;

    private void m9843c() {
        this.f7010e = (this.f7010e + 1) & Util.MASK_8BIT;
    }

    public void m9844a() {
        this.f7007b++;
    }

    public void m9845a(Context context) {
        Log.e("fdfdfdfd", this.f7006a + "//////" + this.f7007b);
    }

    public void m9846a(C1465c c1465c) {
        this.f7006a++;
    }

    public void m9847b() {
        this.f7010e = -1;
        this.f7008c = 0;
        this.f7007b = 0;
        this.f7006a = 0;
    }
}
