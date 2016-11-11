package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.geocoder.a */
final class C0507a implements Creator<BusinessArea> {
    C0507a() {
    }

    public BusinessArea m4929a(Parcel parcel) {
        return new BusinessArea(parcel);
    }

    public BusinessArea[] m4930a(int i) {
        return new BusinessArea[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4929a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4930a(i);
    }
}
