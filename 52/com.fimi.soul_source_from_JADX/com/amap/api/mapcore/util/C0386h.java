package com.amap.api.mapcore.util;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.mapcore.util.h */
final class C0386h implements Creator<C0385g> {
    C0386h() {
    }

    public C0385g m4100a(Parcel parcel) {
        return new C0385g(parcel);
    }

    public C0385g[] m4101a(int i) {
        return new C0385g[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4100a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4101a(i);
    }
}
