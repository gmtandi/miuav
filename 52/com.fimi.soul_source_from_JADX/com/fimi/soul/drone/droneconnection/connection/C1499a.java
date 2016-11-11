package com.fimi.soul.drone.droneconnection.connection;

import android.content.Context;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.a */
public abstract class C1499a extends C1498f {
    private static final String f7089h;
    protected final Context f7090a;
    private boolean f7091i;

    static {
        f7089h = C1499a.class.getSimpleName();
    }

    public C1499a(Context context) {
        this.f7091i = true;
        this.f7090a = context;
    }

    protected abstract int m9922a(byte[] bArr);

    protected abstract void m9923a();

    public void m9924a(boolean z) {
        this.f7091i = z;
    }

    protected int m9925b(byte[] bArr) {
        return this.f7091i ? m9922a(bArr) : 0;
    }

    protected abstract void m9926b();

    public void m9927b(boolean z) {
        if (z) {
            this.e = true;
        } else {
            this.e = false;
        }
    }

    public boolean m9928c() {
        return this.f7091i;
    }

    protected final void m9929d() {
        m9926b();
    }

    public final void m9930e() {
        m9923a();
    }
}
