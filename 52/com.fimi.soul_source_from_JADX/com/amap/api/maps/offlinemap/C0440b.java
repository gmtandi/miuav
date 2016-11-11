package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.offlinemap.b */
final class C0440b implements Creator<OfflineMapCity> {
    C0440b() {
    }

    public OfflineMapCity m4371a(Parcel parcel) {
        return new OfflineMapCity(parcel);
    }

    public OfflineMapCity[] m4372a(int i) {
        return new OfflineMapCity[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4371a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4372a(i);
    }
}
