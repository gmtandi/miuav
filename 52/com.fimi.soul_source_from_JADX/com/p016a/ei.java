package com.p016a;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;

/* renamed from: com.a.ei */
final class ei extends PhoneStateListener {
    private /* synthetic */ eg f1109a;

    private ei(eg egVar) {
        this.f1109a = egVar;
    }

    public final void onCellLocationChanged(CellLocation cellLocation) {
        try {
            this.f1109a.f1107y = System.currentTimeMillis();
            this.f1109a.f1080C = cellLocation;
            super.onCellLocationChanged(cellLocation);
        } catch (Exception e) {
        }
    }

    public final void onServiceStateChanged(ServiceState serviceState) {
        try {
            if (serviceState.getState() == 0) {
                this.f1109a.f1098p = true;
                String[] a = eg.m1698b(this.f1109a.f1089g);
                this.f1109a.f1102t = Integer.parseInt(a[0]);
                this.f1109a.f1103u = Integer.parseInt(a[1]);
            } else {
                this.f1109a.f1098p = false;
            }
            super.onServiceStateChanged(serviceState);
        } catch (Exception e) {
        }
    }

    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        try {
            if (this.f1109a.f1096n) {
                this.f1109a.f1097o = signalStrength.getCdmaDbm();
            } else {
                this.f1109a.f1097o = signalStrength.getGsmSignalStrength();
                if (this.f1109a.f1097o == 99) {
                    this.f1109a.f1097o = -1;
                } else {
                    this.f1109a.f1097o = (this.f1109a.f1097o * 2) - 113;
                }
            }
            super.onSignalStrengthsChanged(signalStrength);
        } catch (Exception e) {
        }
    }
}
