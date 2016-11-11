package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.geocoder.b */
final class C0508b implements Creator<GeocodeAddress> {
    C0508b() {
    }

    public GeocodeAddress m4931a(Parcel parcel) {
        return new GeocodeAddress(null);
    }

    public GeocodeAddress[] m4932a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4931a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4932a(i);
    }
}
