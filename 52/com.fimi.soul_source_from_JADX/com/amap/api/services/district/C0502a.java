package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.district.a */
final class C0502a implements Creator<DistrictItem> {
    C0502a() {
    }

    public DistrictItem m4921a(Parcel parcel) {
        return new DistrictItem(parcel);
    }

    public DistrictItem[] m4922a(int i) {
        return new DistrictItem[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4921a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4922a(i);
    }
}
