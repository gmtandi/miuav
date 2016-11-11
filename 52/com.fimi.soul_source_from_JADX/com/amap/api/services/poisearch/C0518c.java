package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.c */
final class C0518c implements Creator<Discount> {
    C0518c() {
    }

    public Discount m4965a(Parcel parcel) {
        return new Discount(parcel);
    }

    public Discount[] m4966a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4965a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4966a(i);
    }
}
