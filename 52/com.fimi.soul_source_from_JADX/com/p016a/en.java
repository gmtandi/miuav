package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.a.en */
final class en extends BroadcastReceiver {
    en(dw dwVar) {
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                String action = intent.getAction();
                if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                    eg.f1076c = false;
                }
                if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                    eg.f1076c = true;
                }
                if (action.equals("android.intent.action.MEDIA_EJECT")) {
                    eg.f1076c = true;
                }
            } catch (Exception e) {
            }
        }
    }
}
