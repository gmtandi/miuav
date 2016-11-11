package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.route.h */
final class C0539h implements Creator<DriveStep> {
    C0539h() {
    }

    public DriveStep m5016a(Parcel parcel) {
        return new DriveStep(parcel);
    }

    public DriveStep[] m5017a(int i) {
        return null;
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5016a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5017a(i);
    }
}
