package com.fimi.soul.module.dronemanage;

import android.os.Handler;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p116g.C1547g;
import it.p074a.p075a.C2799f;

public class af {
    static C1547g f8397a = null;
    static C1433a f8398b = null;
    static C1465c f8399c = null;
    static int f8400d = 0;
    public static final int f8401e = 0;
    public static final int f8402f = 1;
    static ai f8403g = null;
    private static final int f8404i = 1;
    private static final int f8405j = 2;
    Handler f8406h;

    public af() {
        this.f8406h = new ag(this);
    }

    public af(ai aiVar) {
        this.f8406h = new ag(this);
        f8403g = aiVar;
    }

    public static af m11190a() {
        return new af();
    }

    public static af m11191a(ai aiVar) {
        return new af(aiVar);
    }

    public void m11192a(C1433a c1433a, C1465c c1465c) {
        f8398b = c1433a;
        f8399c = c1465c;
    }

    public void m11193b() {
        f8400d = f8401e;
        if (f8397a != null) {
            f8397a.m10132c();
            return;
        }
        f8397a = new C1547g(C2799f.f14282t, new ah(this));
        f8397a.m10133d();
    }

    public void m11194c() {
        this.f8406h.sendEmptyMessage(f8404i);
    }

    public void m11195d() {
        f8403g = null;
    }

    public boolean m11196e() {
        return f8397a.m10131b();
    }
}
