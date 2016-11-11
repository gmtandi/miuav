package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.r */
final class C0549r implements Creator<SearchCity> {
    C0549r() {
    }

    public SearchCity m5036a(Parcel parcel) {
        return new SearchCity(parcel);
    }

    public SearchCity[] m5037a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5036a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5037a(i);
    }
}
