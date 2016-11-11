package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.u */
final class C0552u implements Creator<WalkStep> {
    C0552u() {
    }

    public WalkStep m5042a(Parcel parcel) {
        return new WalkStep(parcel);
    }

    public WalkStep[] m5043a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5042a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5043a(i);
    }
}
