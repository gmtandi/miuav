package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.busline.a */
final class C0447a implements Creator<BusLineItem> {
    C0447a() {
    }

    public BusLineItem m4439a(Parcel parcel) {
        return new BusLineItem(parcel);
    }

    public BusLineItem[] m4440a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4439a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4440a(i);
    }
}
