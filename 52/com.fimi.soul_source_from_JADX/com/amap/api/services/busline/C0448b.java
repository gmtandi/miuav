package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.busline.b */
final class C0448b implements Creator<BusStationItem> {
    C0448b() {
    }

    public BusStationItem m4441a(Parcel parcel) {
        return new BusStationItem(null);
    }

    public BusStationItem[] m4442a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4441a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4442a(i);
    }
}
