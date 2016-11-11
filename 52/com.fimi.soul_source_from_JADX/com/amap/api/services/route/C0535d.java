package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.d */
final class C0535d implements Creator<District> {
    C0535d() {
    }

    public District m5008a(Parcel parcel) {
        return new District(parcel);
    }

    public District[] m5009a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5008a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5009a(i);
    }
}
