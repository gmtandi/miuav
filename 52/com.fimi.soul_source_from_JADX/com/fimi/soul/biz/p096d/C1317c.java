package com.fimi.soul.biz.p096d;

import android.util.Log;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.APStatus;
import java.util.regex.Matcher;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.d.c */
class C1317c implements C1153f<String> {
    final /* synthetic */ C1153f f5923a;
    final /* synthetic */ C1316b f5924b;

    C1317c(C1316b c1316b, C1153f c1153f) {
        this.f5924b = c1316b;
        this.f5923a = c1153f;
    }

    public void m8900a(String str) {
        Log.d("Good", str + "SUCCESS");
        APStatus aPStatus = new APStatus();
        Matcher matcher = this.f5924b.f5921p.matcher(str);
        if (matcher.find()) {
            aPStatus.setSsid(matcher.group().replace("\"", C2915a.f14760f));
        }
        this.f5923a.m7924a(aPStatus);
    }

    public void m8902b(String str) {
        this.f5923a.m7925b(null);
    }
}
