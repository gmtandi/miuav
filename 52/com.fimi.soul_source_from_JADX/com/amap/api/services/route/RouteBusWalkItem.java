package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteBusWalkItem extends WalkPath implements Parcelable {
    public static final Creator<RouteBusWalkItem> CREATOR;
    private LatLonPoint f3454a;
    private LatLonPoint f3455b;

    static {
        CREATOR = new C0542k();
    }

    public RouteBusWalkItem(Parcel parcel) {
        super(parcel);
        this.f3454a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3455b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getDestination() {
        return this.f3455b;
    }

    public LatLonPoint getOrigin() {
        return this.f3454a;
    }

    public void setDestination(LatLonPoint latLonPoint) {
        this.f3455b = latLonPoint;
    }

    public void setOrigin(LatLonPoint latLonPoint) {
        this.f3454a = latLonPoint;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f3454a, i);
        parcel.writeParcelable(this.f3455b, i);
    }
}
