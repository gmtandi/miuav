package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.core.k */
final class C0478k implements Creator<LatLonPoint> {
    C0478k() {
    }

    public LatLonPoint m4837a(Parcel parcel) {
        return new LatLonPoint(null);
    }

    public LatLonPoint[] m4838a(int i) {
        return new LatLonPoint[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4837a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4838a(i);
    }
}
