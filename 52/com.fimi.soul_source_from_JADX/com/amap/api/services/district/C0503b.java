package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.district.b */
class C0503b implements Creator<DistrictResult> {
    final /* synthetic */ DistrictResult f3211a;

    C0503b(DistrictResult districtResult) {
        this.f3211a = districtResult;
    }

    public DistrictResult m4923a(Parcel parcel) {
        return new DistrictResult(parcel);
    }

    public DistrictResult[] m4924a(int i) {
        return new DistrictResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4923a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4924a(i);
    }
}
