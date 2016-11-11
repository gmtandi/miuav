package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber implements Parcelable {
    public static final Creator<StreetNumber> CREATOR;
    private String f3259a;
    private String f3260b;
    private LatLonPoint f3261c;
    private String f3262d;
    private float f3263e;

    static {
        CREATOR = new C0511e();
    }

    private StreetNumber(Parcel parcel) {
        this.f3259a = parcel.readString();
        this.f3260b = parcel.readString();
        this.f3261c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3262d = parcel.readString();
        this.f3263e = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public String getDirection() {
        return this.f3262d;
    }

    public float getDistance() {
        return this.f3263e;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3261c;
    }

    public String getNumber() {
        return this.f3260b;
    }

    public String getStreet() {
        return this.f3259a;
    }

    public void setDirection(String str) {
        this.f3262d = str;
    }

    public void setDistance(float f) {
        this.f3263e = f;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3261c = latLonPoint;
    }

    public void setNumber(String str) {
        this.f3260b = str;
    }

    public void setStreet(String str) {
        this.f3259a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3259a);
        parcel.writeString(this.f3260b);
        parcel.writeValue(this.f3261c);
        parcel.writeString(this.f3262d);
        parcel.writeFloat(this.f3263e);
    }
}
