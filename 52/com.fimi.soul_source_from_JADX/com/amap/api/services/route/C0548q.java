package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.q */
final class C0548q implements Creator<RouteSearchCity> {
    C0548q() {
    }

    public RouteSearchCity m5034a(Parcel parcel) {
        return new RouteSearchCity(parcel);
    }

    public RouteSearchCity[] m5035a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5034a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5035a(i);
    }
}
