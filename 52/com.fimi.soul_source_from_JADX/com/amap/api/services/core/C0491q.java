package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.core.q */
final class C0491q implements Creator<PoiItem> {
    C0491q() {
    }

    public PoiItem m4858a(Parcel parcel) {
        return new PoiItem(parcel);
    }

    public PoiItem[] m4859a(int i) {
        return new PoiItem[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4858a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4859a(i);
    }
}
