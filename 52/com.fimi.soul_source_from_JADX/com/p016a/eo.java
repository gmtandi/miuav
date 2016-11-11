package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.a.eo */
final class eo extends BroadcastReceiver {
    eo(dw dwVar) {
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                if (intent.getAction().equals("android.location.GPS_FIX_CHANGE")) {
                    dw.f993b = false;
                }
            } catch (Exception e) {
            }
        }
    }
}
