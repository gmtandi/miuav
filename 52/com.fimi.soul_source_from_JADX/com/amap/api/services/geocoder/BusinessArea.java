package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class BusinessArea implements Parcelable {
    public static final Creator<BusinessArea> CREATOR;
    private LatLonPoint f3212a;
    private String f3213b;

    static {
        CREATOR = new C0507a();
    }

    public BusinessArea(Parcel parcel) {
        this.f3212a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3213b = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getCenterPoint() {
        return this.f3212a;
    }

    public String getName() {
        return this.f3213b;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f3212a = latLonPoint;
    }

    public void setName(String str) {
        this.f3213b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3212a, i);
        parcel.writeString(this.f3213b);
    }
}
