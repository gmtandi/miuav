package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.poisearch.h */
final class C0523h implements Creator<PoiItemDetail> {
    C0523h() {
    }

    public PoiItemDetail m4974a(Parcel parcel) {
        return new PoiItemDetail(null);
    }

    public PoiItemDetail[] m4975a(int i) {
        return new PoiItemDetail[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4974a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4975a(i);
    }
}
