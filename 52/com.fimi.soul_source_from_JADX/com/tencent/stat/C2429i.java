package com.tencent.stat;

import android.content.Context;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.stat.p136a.C2394e;
import com.tencent.stat.p136a.C2398d;
import java.io.File;
import java.util.Iterator;

/* renamed from: com.tencent.stat.i */
class C2429i implements Runnable {
    private Context f12375a;

    public C2429i(Context context) {
        this.f12375a = null;
        this.f12375a = context;
    }

    public void run() {
        Iterator it = StatNativeCrashReport.m13919a(this.f12375a).iterator();
        while (it.hasNext()) {
            File file = (File) it.next();
            C2394e c2398d = new C2398d(this.f12375a, StatService.m13922a(this.f12375a, false), StatNativeCrashReport.m13918a(file), 3, C1142e.f5202b);
            c2398d.m13948a(StatNativeCrashReport.m13920b(file));
            if (StatService.m13930c(this.f12375a) != null) {
                StatService.m13930c(this.f12375a).post(new C2431k(c2398d));
            }
            file.delete();
            StatService.f12248i.m13976d("delete tombstone file:" + file.getAbsolutePath().toString());
        }
    }
}
