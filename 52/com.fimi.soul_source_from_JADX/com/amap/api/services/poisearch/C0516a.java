package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.a */
final class C0516a implements Creator<Cinema> {
    C0516a() {
    }

    public Cinema m4961a(Parcel parcel) {
        return new Cinema(parcel);
    }

    public Cinema[] m4962a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4961a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4962a(i);
    }
}
