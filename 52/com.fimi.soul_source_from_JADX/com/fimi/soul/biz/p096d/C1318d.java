package com.fimi.soul.biz.p096d;

import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.entity.APConfig;
import java.util.Scanner;

/* renamed from: com.fimi.soul.biz.d.d */
class C1318d implements C1153f<String> {
    final /* synthetic */ C1153f f5925a;
    final /* synthetic */ C1316b f5926b;

    C1318d(C1316b c1316b, C1153f c1153f) {
        this.f5926b = c1316b;
        this.f5925a = c1153f;
    }

    public void m8904a(String str) {
        if (str != null && str.length() > 3) {
            Scanner scanner = new Scanner(str.substring(4));
            while (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split("=");
                if (split != null && split.length == 2) {
                    this.f5926b.f5920o.put(split[0], split[1]);
                }
            }
        }
        Object c = this.f5926b.m8885d();
        C1189f.m8333c().m7930a(C1316b.f5916k, c);
        this.f5925a.m7924a(c);
    }

    public void m8906b(String str) {
        this.f5925a.m7925b(new APConfig());
    }
}
