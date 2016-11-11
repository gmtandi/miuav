package com.fimi.soul.drone.p116g;

import android.os.Handler;

/* renamed from: com.fimi.soul.drone.g.g */
public class C1547g {
    public boolean f7256a;
    public boolean f7257b;
    private int f7258c;
    private Handler f7259d;
    private Runnable f7260e;
    private Runnable f7261f;

    public C1547g(int i) {
        this.f7258c = i;
        this.f7259d = new Handler();
    }

    public C1547g(int i, Runnable runnable) {
        this.f7258c = i;
        m10130a(runnable);
        this.f7259d = new Handler();
    }

    public int m10127a() {
        return this.f7258c;
    }

    public void m10128a(int i) {
        this.f7258c = i;
    }

    public void m10129a(int i, Runnable runnable) {
        this.f7257b = true;
        if (!this.f7256a) {
            this.f7258c = i;
            m10130a(runnable);
            this.f7259d.postDelayed(this.f7261f, (long) this.f7258c);
            this.f7256a = true;
        }
    }

    public void m10130a(Runnable runnable) {
        if (runnable != null) {
            this.f7260e = runnable;
            this.f7261f = new C1548h(this);
        }
    }

    public boolean m10131b() {
        return this.f7256a;
    }

    public void m10132c() {
        this.f7259d.removeCallbacks(this.f7261f);
        this.f7256a = false;
        this.f7259d.postDelayed(this.f7261f, (long) this.f7258c);
        this.f7256a = true;
    }

    public void m10133d() {
        if (!this.f7256a) {
            this.f7259d.postDelayed(this.f7261f, (long) this.f7258c);
            this.f7256a = true;
        }
    }

    public void m10134e() {
        this.f7259d.removeCallbacks(this.f7261f);
        this.f7256a = false;
    }

    public void m10135f() {
        this.f7259d.removeCallbacks(this.f7260e);
        this.f7259d.removeCallbacks(this.f7261f);
    }
}
