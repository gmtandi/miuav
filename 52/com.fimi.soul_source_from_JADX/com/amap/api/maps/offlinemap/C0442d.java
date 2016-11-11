package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.maps.offlinemap.d */
final class C0442d implements Creator<Province> {
    C0442d() {
    }

    public Province m4375a(Parcel parcel) {
        return new Province(parcel);
    }

    public Province[] m4376a(int i) {
        return new Province[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4375a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4376a(i);
    }
}
