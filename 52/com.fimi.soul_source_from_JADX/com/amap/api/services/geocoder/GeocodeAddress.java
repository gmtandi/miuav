package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class GeocodeAddress implements Parcelable {
    public static final Creator<GeocodeAddress> CREATOR;
    private String f3214a;
    private String f3215b;
    private String f3216c;
    private String f3217d;
    private String f3218e;
    private String f3219f;
    private String f3220g;
    private String f3221h;
    private LatLonPoint f3222i;
    private String f3223j;

    static {
        CREATOR = new C0508b();
    }

    private GeocodeAddress(Parcel parcel) {
        this.f3214a = parcel.readString();
        this.f3215b = parcel.readString();
        this.f3216c = parcel.readString();
        this.f3217d = parcel.readString();
        this.f3218e = parcel.readString();
        this.f3219f = parcel.readString();
        this.f3220g = parcel.readString();
        this.f3221h = parcel.readString();
        this.f3222i = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f3223j = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f3221h;
    }

    public String getBuilding() {
        return this.f3220g;
    }

    public String getCity() {
        return this.f3216c;
    }

    public String getDistrict() {
        return this.f3217d;
    }

    public String getFormatAddress() {
        return this.f3214a;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f3222i;
    }

    public String getLevel() {
        return this.f3223j;
    }

    public String getNeighborhood() {
        return this.f3219f;
    }

    public String getProvince() {
        return this.f3215b;
    }

    public String getTownship() {
        return this.f3218e;
    }

    public void setAdcode(String str) {
        this.f3221h = str;
    }

    public void setBuilding(String str) {
        this.f3220g = str;
    }

    public void setCity(String str) {
        this.f3216c = str;
    }

    public void setDistrict(String str) {
        this.f3217d = str;
    }

    public void setFormatAddress(String str) {
        this.f3214a = str;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f3222i = latLonPoint;
    }

    public void setLevel(String str) {
        this.f3223j = str;
    }

    public void setNeighborhood(String str) {
        this.f3219f = str;
    }

    public void setProvince(String str) {
        this.f3215b = str;
    }

    public void setTownship(String str) {
        this.f3218e = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3214a);
        parcel.writeString(this.f3215b);
        parcel.writeString(this.f3216c);
        parcel.writeString(this.f3217d);
        parcel.writeString(this.f3218e);
        parcel.writeString(this.f3219f);
        parcel.writeString(this.f3220g);
        parcel.writeString(this.f3221h);
        parcel.writeValue(this.f3222i);
        parcel.writeString(this.f3223j);
    }
}
