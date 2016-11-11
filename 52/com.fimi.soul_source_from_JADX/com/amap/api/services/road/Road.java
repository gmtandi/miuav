package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Road implements Parcelable {
    public static final Creator<Road> CREATOR;
    private String f3396a;
    private String f3397b;
    private String f3398c;
    private float f3399d;
    private String f3400e;
    private LatLonPoint f3401f;

    static {
        CREATOR = new C0528b();
    }

    private Road(Parcel parcel) {
        this.f3396a = parcel.readString();
        this.f3397b = parcel.readString();
        this.f3398c = parcel.readString();
        this.f3399d = parcel.readFloat();
        this.f3400e = parcel.readString();
        this.f3401f = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
    }

    public Road(String str, String str2) {
        this.f3396a = str;
        this.f3397b = str2;
    }

    public int describeContents() {
        return 0;
    }

    public LatLonPoint getCenterPoint() {
        return this.f3401f;
    }

    public String getCityCode() {
        return this.f3398c;
    }

    public String getId() {
        return this.f3396a;
    }

    public String getName() {
        return this.f3397b;
    }

    public float getRoadWidth() {
        return this.f3399d;
    }

    public String getType() {
        return this.f3400e;
    }

    public void setCenterPoint(LatLonPoint latLonPoint) {
        this.f3401f = latLonPoint;
    }

    public void setCityCode(String str) {
        this.f3398c = str;
    }

    public void setId(String str) {
        this.f3396a = str;
    }

    public void setName(String str) {
        this.f3397b = str;
    }

    public void setRoadWidth(float f) {
        this.f3399d = f;
    }

    public void setType(String str) {
        this.f3400e = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3396a);
        parcel.writeString(this.f3397b);
        parcel.writeString(this.f3398c);
        parcel.writeFloat(this.f3399d);
        parcel.writeString(this.f3400e);
        parcel.writeValue(this.f3401f);
    }
}
