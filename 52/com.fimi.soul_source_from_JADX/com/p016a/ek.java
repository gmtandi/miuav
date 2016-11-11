package com.p016a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.a.ek */
final class ek extends BroadcastReceiver {
    final /* synthetic */ eg f1111a;

    private ek(eg egVar) {
        this.f1111a = egVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                if (this.f1111a.f1091i != null && this.f1111a.f1083F != null && this.f1111a.f1082E != null && intent.getAction() != null && "android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                    List scanResults = this.f1111a.f1091i.getScanResults();
                    synchronized (this) {
                        this.f1111a.f1082E.clear();
                        this.f1111a.f1106x = System.currentTimeMillis();
                        if (scanResults != null && scanResults.size() > 0) {
                            for (int i = 0; i < scanResults.size(); i++) {
                                this.f1111a.f1082E.add((ScanResult) scanResults.get(i));
                            }
                        }
                    }
                    TimerTask elVar = new el(this);
                    synchronized (this) {
                        if (this.f1111a.f1083F != null) {
                            this.f1111a.f1083F.cancel();
                            this.f1111a.f1083F = null;
                        }
                        this.f1111a.f1083F = new Timer();
                        this.f1111a.f1083F.schedule(elVar, (long) eg.f1072I);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
