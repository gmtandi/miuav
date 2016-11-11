package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.k */
final class C0542k implements Creator<RouteBusWalkItem> {
    C0542k() {
    }

    public RouteBusWalkItem m5022a(Parcel parcel) {
        return new RouteBusWalkItem(parcel);
    }

    public RouteBusWalkItem[] m5023a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5022a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5023a(i);
    }
}
