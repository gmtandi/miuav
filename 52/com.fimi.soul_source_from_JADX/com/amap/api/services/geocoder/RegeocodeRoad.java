package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad implements Parcelable {
    public static final Creator<RegeocodeRoad> CREATOR;
    private String f3254a;
    private String f3255b;
    private float f3256c;
    private String f3257d;
    private LatLonPoint f3258e;

    static {
        CREATOR = new C0510d();
    }

    private RegeocodeRoad(Parcel parcel) {
        this.f3254a = parcel.readString();
        this.f3255b = parcel.readString();
        this.f3256c = parcel.readFloat();
        this.f3257d = parcel.readString();
        this.f3258e = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public String getDirection() {
        return this.f3257d;
    }

    public float getDistance() {
        return this.f3256c;
    }

    public String getId() {
        return this.f3254a;
    }

    public LatLonPoint getLatLngPoint() {
        return this.f3258e;
    }

    public String getName() {
        return this.f3255b;
    }

    public void setDirection(String str) {
        this.f3257d = str;
    }

    public void setDistance(float f) {
        this.f3256c = f;
    }

    public void setId(String str) {
        this.f3254a = str;
    }

    public void setLatLngPoint(LatLonPoint latLonPoint) {
        this.f3258e = latLonPoint;
    }

    public void setName(String str) {
        this.f3255b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3254a);
        parcel.writeString(this.f3255b);
        parcel.writeFloat(this.f3256c);
        parcel.writeString(this.f3257d);
        parcel.writeValue(this.f3258e);
    }
}
