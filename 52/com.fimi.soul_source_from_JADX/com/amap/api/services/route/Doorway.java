package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Doorway implements Parcelable {
    public static final Creator<Doorway> CREATOR;
    private String f3426a;
    private LatLonPoint f3427b;

    static {
        CREATOR = new C0536e();
    }

    public Doorway(Parcel parcel) {
        this.f3426a = parcel.readString();
        this.f3427b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3427b;
    }

    public String getName() {
        return this.f3426a;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3427b = latLonPoint;
    }

    public void setName(String str) {
        this.f3426a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3426a);
        parcel.writeParcelable(this.f3427b, i);
    }
}
