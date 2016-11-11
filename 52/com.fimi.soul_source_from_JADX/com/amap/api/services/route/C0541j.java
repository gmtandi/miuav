package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.j */
final class C0541j implements Creator<RouteBusLineItem> {
    C0541j() {
    }

    public RouteBusLineItem m5020a(Parcel parcel) {
        return new RouteBusLineItem(parcel);
    }

    public RouteBusLineItem[] m5021a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5020a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5021a(i);
    }
}
