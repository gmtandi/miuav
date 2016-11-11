package com.fimi.soul.drone.droneconnection.connection.p112b;

import android.content.Context;
import android.preference.PreferenceManager;
import com.fimi.soul.drone.droneconnection.connection.C1499a;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.b.a */
public class C1500a extends C1499a {
    private final C1501c f7092h;

    public C1500a(Context context) {
        super(context);
        this.f7092h = new C1502b(this, PreferenceManager.getDefaultSharedPreferences(context));
    }

    protected int m9931a(byte[] bArr) {
        return this.f7092h.m9938b(bArr);
    }

    protected void m9932a() {
        this.f7092h.m9942e();
    }

    protected void m9933b() {
        this.f7092h.m9941d();
    }

    protected void m9934c(byte[] bArr) {
        this.f7092h.m9940c(bArr);
    }

    public int m9935j() {
        return this.f7092h.m9943j();
    }
}
