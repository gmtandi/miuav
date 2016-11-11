package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.e */
final class C0520e implements Creator<Hotel> {
    C0520e() {
    }

    public Hotel m4969a(Parcel parcel) {
        return new Hotel(parcel);
    }

    public Hotel[] m4970a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4969a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4970a(i);
    }
}
