package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.geocoder.c */
final class C0509c implements Creator<RegeocodeAddress> {
    C0509c() {
    }

    public RegeocodeAddress m4933a(Parcel parcel) {
        return new RegeocodeAddress(null);
    }

    public RegeocodeAddress[] m4934a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4933a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4934a(i);
    }
}
