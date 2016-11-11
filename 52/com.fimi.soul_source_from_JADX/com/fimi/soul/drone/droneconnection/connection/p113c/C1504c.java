package com.fimi.soul.drone.droneconnection.connection.p113c;

import android.content.Context;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.c.c */
abstract class C1504c {
    protected final int f7105e;
    protected final Context f7106f;

    protected C1504c(Context context, int i) {
        this.f7106f = context;
        this.f7105e = i;
    }

    protected abstract int m9952a(byte[] bArr);

    protected abstract void m9953a();

    protected abstract void m9954b();

    protected abstract void m9955b(byte[] bArr);
}
