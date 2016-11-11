package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.b */
final class C0517b implements Creator<Dining> {
    C0517b() {
    }

    public Dining m4963a(Parcel parcel) {
        return new Dining(parcel);
    }

    public Dining[] m4964a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4963a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4964a(i);
    }
}
