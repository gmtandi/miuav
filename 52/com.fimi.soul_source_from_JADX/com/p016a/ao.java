package com.p016a;

import android.content.Context;
import java.util.List;

/* renamed from: com.a.ao */
final class ao extends Thread {
    final /* synthetic */ Context f555a;
    final /* synthetic */ String f556b;
    final /* synthetic */ String f557c;

    ao(Context context, String str, String str2) {
        this.f555a = context;
        this.f556b = str;
        this.f557c = str2;
    }

    public void run() {
        try {
            C0261v c0261v = new C0261v(this.f555a, at.m1080c());
            List<av> c = c0261v.m2052c(au.m1085a(this.f556b), new au());
            if (c != null && c.size() > 0) {
                for (av avVar : c) {
                    if (!this.f557c.equalsIgnoreCase(avVar.m1100d())) {
                        an.m1051a(this.f555a, c0261v, avVar.m1096a());
                    }
                }
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "DexFileManager", "clearUnSuitableVersion");
        }
    }
}
