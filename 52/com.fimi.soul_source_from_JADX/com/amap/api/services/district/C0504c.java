package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.amap.api.services.district.c */
final class C0504c implements Creator<DistrictSearchQuery> {
    C0504c() {
    }

    public DistrictSearchQuery m4925a(Parcel parcel) {
        boolean z = true;
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery();
        districtSearchQuery.setKeywords(parcel.readString());
        districtSearchQuery.setKeywordsLevel(parcel.readString());
        districtSearchQuery.setPageNum(parcel.readInt());
        districtSearchQuery.setPageSize(parcel.readInt());
        districtSearchQuery.setShowChild(parcel.readByte() == (byte) 1);
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        districtSearchQuery.setShowBoundary(z);
        return districtSearchQuery;
    }

    public DistrictSearchQuery[] m4926a(int i) {
        return new DistrictSearchQuery[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4925a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4926a(i);
    }
}
