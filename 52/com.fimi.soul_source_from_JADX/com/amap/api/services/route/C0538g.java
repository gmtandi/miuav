package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.g */
final class C0538g implements Creator<DriveRouteResult> {
    C0538g() {
    }

    public DriveRouteResult m5014a(Parcel parcel) {
        return new DriveRouteResult(parcel);
    }

    public DriveRouteResult[] m5015a(int i) {
        return new DriveRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5014a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5015a(i);
    }
}
