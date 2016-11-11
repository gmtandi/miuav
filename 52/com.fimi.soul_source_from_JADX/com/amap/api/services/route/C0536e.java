package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.e */
final class C0536e implements Creator<Doorway> {
    C0536e() {
    }

    public Doorway m5010a(Parcel parcel) {
        return new Doorway(parcel);
    }

    public Doorway[] m5011a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5010a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5011a(i);
    }
}
