package com.autonavi.aps.amapapi.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.autonavi.aps.amapapi.model.a */
final class C0615a implements Creator<AmapLoc> {
    C0615a() {
    }

    public AmapLoc m5374a(Parcel parcel) {
        return new AmapLoc(parcel);
    }

    public AmapLoc[] m5375a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5374a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5375a(i);
    }
}
