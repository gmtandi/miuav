package com.p016a;

import android.location.GpsStatus.NmeaListener;

/* renamed from: com.a.ej */
final class ej implements NmeaListener {
    private /* synthetic */ eg f1110a;

    private ej(eg egVar) {
        this.f1110a = egVar;
    }

    public final void onNmeaReceived(long j, String str) {
        try {
            this.f1110a.f1099q = j;
            this.f1110a.f1100r = str;
        } catch (Exception e) {
        }
    }
}
