package com.fimi.soul.drone.droneconnection.connection.service;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import com.fimi.soul.drone.droneconnection.connection.C1235n;
import com.fimi.soul.drone.droneconnection.connection.C1514k;
import com.fimi.soul.drone.droneconnection.connection.C1516m;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.a */
public class C1522a implements C1516m {
    private static final String f7145a;
    private boolean f7146b;
    private final Handler f7147c;
    private final AtomicReference<String> f7148d;
    private final C1514k f7149e;
    private final ServiceConnection f7150f;
    private final Context f7151g;
    private final C1235n f7152h;
    private C1531j f7153i;
    private boolean f7154j;
    private boolean f7155k;

    static {
        f7145a = C1522a.class.getSimpleName();
    }

    public C1522a(Context context, C1235n c1235n) {
        this.f7147c = new Handler();
        this.f7148d = new AtomicReference();
        this.f7149e = new C1523b(this);
        this.f7150f = new C1530i(this);
        this.f7151g = context;
        this.f7152h = c1235n;
    }

    private void m10020h() {
        if (this.f7154j) {
            m10021i();
        } else {
            this.f7151g.bindService(new Intent(this.f7151g, MiLinkService.class), this.f7150f, 1);
        }
    }

    private void m10021i() {
        this.f7153i.m10045b();
        this.f7153i.m10043a(f7145a, this.f7149e);
    }

    private void m10022j() {
        this.f7154j = true;
        m10021i();
    }

    private void m10023k() {
        this.f7146b = false;
        this.f7154j = false;
        this.f7152h.m8513f();
    }

    public void m10024a(C1465c c1465c) {
        if (m10026a()) {
            this.f7153i.m10041a(c1465c);
        }
    }

    public void m10025a(boolean z) {
        this.f7153i.m10044a(z);
    }

    public boolean m10026a() {
        return this.f7146b;
    }

    public void m10027b() {
        if (m10026a()) {
            m10033g();
        } else {
            m10020h();
        }
    }

    public void m10028b(boolean z) {
        this.f7153i.m10046b(z);
    }

    public void m10029c() {
        if (m10026a()) {
            this.f7152h.m8512e();
        } else {
            this.f7152h.m8513f();
        }
    }

    public void m10030d() {
        m10033g();
    }

    public String m10031e() {
        return C1497a.m9893a();
    }

    public boolean m10032f() {
        return this.f7153i.m10048d();
    }

    public void m10033g() {
        if (this.f7154j) {
            if (this.f7153i.m10040a() == 2) {
                this.f7153i.m10047c();
            }
            this.f7153i.m10042a(f7145a);
            try {
                this.f7151g.unbindService(this.f7150f);
            } catch (Exception e) {
            }
            m10023k();
        }
    }
}
