package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteResult implements Parcelable {
    public static final Creator<RouteResult> CREATOR;
    private LatLonPoint f3415a;
    private LatLonPoint f3416b;

    static {
        CREATOR = new C0543l();
    }

    public RouteResult(Parcel parcel) {
        this.f3415a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3416b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getStartPos() {
        return this.f3415a;
    }

    public LatLonPoint getTargetPos() {
        return this.f3416b;
    }

    public void setStartPos(LatLonPoint latLonPoint) {
        this.f3415a = latLonPoint;
    }

    public void setTargetPos(LatLonPoint latLonPoint) {
        this.f3416b = latLonPoint;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3415a, i);
        parcel.writeParcelable(this.f3416b, i);
    }
}
