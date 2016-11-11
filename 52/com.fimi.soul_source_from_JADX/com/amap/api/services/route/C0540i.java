package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.i */
final class C0540i implements Creator<Path> {
    C0540i() {
    }

    public Path m5018a(Parcel parcel) {
        return new Path(parcel);
    }

    public Path[] m5019a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5018a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5019a(i);
    }
}
