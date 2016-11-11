package com.p016a;

import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

/* renamed from: com.a.ex */
public final class ex {
    int f1164a;
    int f1165b;
    int f1166c;
    int f1167d;
    int f1168e;

    ex(CellLocation cellLocation) {
        this.f1164a = Integer.MAX_VALUE;
        this.f1165b = Integer.MAX_VALUE;
        this.f1166c = Integer.MAX_VALUE;
        this.f1167d = Integer.MAX_VALUE;
        this.f1168e = Integer.MAX_VALUE;
        if (cellLocation == null) {
            return;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            this.f1168e = gsmCellLocation.getCid();
            this.f1167d = gsmCellLocation.getLac();
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            this.f1166c = cdmaCellLocation.getBaseStationId();
            this.f1165b = cdmaCellLocation.getNetworkId();
            this.f1164a = cdmaCellLocation.getSystemId();
        }
    }
}
