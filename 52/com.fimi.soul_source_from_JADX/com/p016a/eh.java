package com.p016a;

import android.os.Looper;
import java.util.Timer;

/* renamed from: com.a.eh */
final class eh extends Thread {
    private /* synthetic */ eg f1108a;

    eh(eg egVar, String str) {
        this.f1108a = egVar;
        super(str);
    }

    public final void run() {
        eg egVar;
        try {
            synchronized (this.f1108a.f1086d) {
                Looper.prepare();
                this.f1108a.f1085H = Looper.myLooper();
                this.f1108a.f1083F = new Timer();
                this.f1108a.f1078A = new ei((byte) 0);
                eg.m1683a(this.f1108a, this.f1108a.f1078A);
                this.f1108a.f1079B = new ej((byte) 0);
                try {
                    eg.m1681a(this.f1108a, this.f1108a.f1079B);
                } catch (Exception e) {
                }
            }
            if (this.f1108a.f1087e) {
                Looper.loop();
            }
            if (this.f1108a.f1078A != null) {
                this.f1108a.m1680a(this.f1108a.f1078A);
                this.f1108a.f1078A = null;
            }
            if (this.f1108a.f1079B != null) {
                this.f1108a.m1678a(this.f1108a.f1079B);
                this.f1108a.f1079B = null;
            }
            if (this.f1108a.f1085H != null) {
                this.f1108a.f1085H.quit();
                egVar = this.f1108a;
                egVar.f1085H = null;
            }
        } catch (Exception e2) {
            if (this.f1108a.f1078A != null) {
                this.f1108a.m1680a(this.f1108a.f1078A);
                this.f1108a.f1078A = null;
            }
            if (this.f1108a.f1079B != null) {
                this.f1108a.m1678a(this.f1108a.f1079B);
                this.f1108a.f1079B = null;
            }
            if (this.f1108a.f1085H != null) {
                this.f1108a.f1085H.quit();
                egVar = this.f1108a;
            }
        } catch (Throwable th) {
            if (this.f1108a.f1078A != null) {
                this.f1108a.m1680a(this.f1108a.f1078A);
                this.f1108a.f1078A = null;
            }
            if (this.f1108a.f1079B != null) {
                this.f1108a.m1678a(this.f1108a.f1079B);
                this.f1108a.f1079B = null;
            }
            if (this.f1108a.f1085H != null) {
                this.f1108a.f1085H.quit();
                this.f1108a.f1085H = null;
            }
        }
    }
}
