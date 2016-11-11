package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.fence.a */
final class C0265a implements Creator<Fence> {
    C0265a() {
    }

    public Fence m2070a(Parcel parcel) {
        return new Fence(null);
    }

    public Fence[] m2071a(int i) {
        return new Fence[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2070a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2071a(i);
    }
}
