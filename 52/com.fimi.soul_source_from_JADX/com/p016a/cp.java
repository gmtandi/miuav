package com.p016a;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.a.cp */
class cp extends PhoneStateListener {
    final /* synthetic */ co f784a;

    cp(co coVar) {
        this.f784a = coVar;
    }

    public void onCellLocationChanged(CellLocation cellLocation) {
        try {
            if (this.f784a.m1352a(cellLocation)) {
                this.f784a.f781l = cellLocation;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "initPhoneStateListener7");
        }
    }

    public void onServiceStateChanged(ServiceState serviceState) {
        try {
            switch (serviceState.getState()) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    this.f784a.m1347p();
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    this.f784a.m1349r();
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "initPhoneStateListener4");
        }
        ev.m1777a(th, "CgiManager", "initPhoneStateListener4");
    }

    public void onSignalStrengthChanged(int i) {
        int i2 = -113;
        try {
            switch (this.f784a.f773d) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    i2 = dn.m1498a(i);
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    i2 = dn.m1498a(i);
                    break;
            }
            this.f784a.m1329a(i2);
        } catch (Throwable th) {
            ev.m1777a(th, "CgiManager", "initPhoneStateListener6");
        }
    }

    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        if (signalStrength != null) {
            int i = -113;
            try {
                switch (this.f784a.f773d) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        i = dn.m1498a(signalStrength.getGsmSignalStrength());
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        i = signalStrength.getCdmaDbm();
                        break;
                }
                this.f784a.m1329a(i);
            } catch (Throwable th) {
                ev.m1777a(th, "CgiManager", "initPhoneStateListener5");
            }
        }
    }
}
