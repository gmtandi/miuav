package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class District implements Parcelable {
    public static final Creator<District> CREATOR;
    private String f3424a;
    private String f3425b;

    static {
        CREATOR = new C0535d();
    }

    public District(Parcel parcel) {
        this.f3424a = parcel.readString();
        this.f3425b = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getDistrictAdcode() {
        return this.f3425b;
    }

    public String getDistrictName() {
        return this.f3424a;
    }

    public void setDistrictAdcode(String str) {
        this.f3425b = str;
    }

    public void setDistrictName(String str) {
        this.f3424a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3424a);
        parcel.writeString(this.f3425b);
    }
}
