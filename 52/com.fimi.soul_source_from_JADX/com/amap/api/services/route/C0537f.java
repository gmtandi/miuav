package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.f */
final class C0537f implements Creator<DrivePath> {
    C0537f() {
    }

    public DrivePath m5012a(Parcel parcel) {
        return new DrivePath(parcel);
    }

    public DrivePath[] m5013a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5012a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5013a(i);
    }
}
