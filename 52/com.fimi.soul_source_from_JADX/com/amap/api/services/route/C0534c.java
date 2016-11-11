package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.c */
final class C0534c implements Creator<BusStep> {
    C0534c() {
    }

    public BusStep m5006a(Parcel parcel) {
        return new BusStep(parcel);
    }

    public BusStep[] m5007a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5006a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5007a(i);
    }
}
