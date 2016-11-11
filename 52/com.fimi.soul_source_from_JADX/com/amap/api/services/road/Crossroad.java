package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Crossroad extends Road implements Parcelable {
    public static final Creator<Crossroad> CREATOR;
    private float f3402a;
    private String f3403b;
    private String f3404c;
    private String f3405d;
    private String f3406e;
    private String f3407f;

    static {
        CREATOR = new C0527a();
    }

    private Crossroad(Parcel parcel) {
        this.f3402a = parcel.readFloat();
        this.f3403b = parcel.readString();
        this.f3404c = parcel.readString();
        this.f3405d = parcel.readString();
        this.f3406e = parcel.readString();
        this.f3407f = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getDirection() {
        return this.f3403b;
    }

    public float getDistance() {
        return this.f3402a;
    }

    public String getFirstRoadId() {
        return this.f3404c;
    }

    public String getFirstRoadName() {
        return this.f3405d;
    }

    public String getSecondRoadId() {
        return this.f3406e;
    }

    public String getSecondRoadName() {
        return this.f3407f;
    }

    public void setDirection(String str) {
        this.f3403b = str;
    }

    public void setDistance(float f) {
        this.f3402a = f;
    }

    public void setFirstRoadId(String str) {
        this.f3404c = str;
    }

    public void setFirstRoadName(String str) {
        this.f3405d = str;
    }

    public void setSecondRoadId(String str) {
        this.f3406e = str;
    }

    public void setSecondRoadName(String str) {
        this.f3407f = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3402a);
        parcel.writeString(this.f3403b);
        parcel.writeString(this.f3404c);
        parcel.writeString(this.f3405d);
        parcel.writeString(this.f3406e);
        parcel.writeString(this.f3407f);
    }
}
