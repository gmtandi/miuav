package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.a */
final class C0532a implements Creator<BusPath> {
    C0532a() {
    }

    public BusPath m5002a(Parcel parcel) {
        return new BusPath(parcel);
    }

    public BusPath[] m5003a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5002a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5003a(i);
    }
}
