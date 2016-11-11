package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.s */
final class C0550s implements Creator<WalkPath> {
    C0550s() {
    }

    public WalkPath m5038a(Parcel parcel) {
        return new WalkPath(parcel);
    }

    public WalkPath[] m5039a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5038a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5039a(i);
    }
}
