package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.offlinemap.c */
final class C0441c implements Creator<OfflineMapProvince> {
    C0441c() {
    }

    public OfflineMapProvince m4373a(Parcel parcel) {
        return new OfflineMapProvince(parcel);
    }

    public OfflineMapProvince[] m4374a(int i) {
        return new OfflineMapProvince[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4373a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4374a(i);
    }
}
