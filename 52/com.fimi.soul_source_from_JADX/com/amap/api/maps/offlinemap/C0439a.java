package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.offlinemap.a */
final class C0439a implements Creator<City> {
    C0439a() {
    }

    public City m4369a(Parcel parcel) {
        return new City(parcel);
    }

    public City[] m4370a(int i) {
        return new City[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4369a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4370a(i);
    }
}
