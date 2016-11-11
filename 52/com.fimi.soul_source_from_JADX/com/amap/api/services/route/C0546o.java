package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.FromAndTo;

/* renamed from: com.amap.api.services.route.o */
final class C0546o implements Creator<FromAndTo> {
    C0546o() {
    }

    public FromAndTo m5030a(Parcel parcel) {
        return new FromAndTo(parcel);
    }

    public FromAndTo[] m5031a(int i) {
        return new FromAndTo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m5030a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m5031a(i);
    }
}
