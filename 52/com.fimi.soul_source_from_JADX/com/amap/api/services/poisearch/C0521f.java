package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.f */
final class C0521f implements Creator<Photo> {
    C0521f() {
    }

    public Photo m4971a(Parcel parcel) {
        return new Photo(parcel);
    }

    public Photo[] m4972a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4971a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4972a(i);
    }
}
