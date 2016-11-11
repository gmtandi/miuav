package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Tip implements Parcelable {
    public static final Creator<Tip> CREATOR;
    private String f3270a;
    private String f3271b;
    private String f3272c;

    static {
        CREATOR = new C0513a();
    }

    private Tip(Parcel parcel) {
        this.f3270a = parcel.readString();
        this.f3272c = parcel.readString();
        this.f3271b = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f3272c;
    }

    public String getDistrict() {
        return this.f3271b;
    }

    public String getName() {
        return this.f3270a;
    }

    public void setAdcode(String str) {
        this.f3272c = str;
    }

    public void setDistrict(String str) {
        this.f3271b = str;
    }

    public void setName(String str) {
        this.f3270a = str;
    }

    public String toString() {
        return "name:" + this.f3270a + " district:" + this.f3271b + " adcode:" + this.f3272c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3270a);
        parcel.writeString(this.f3272c);
        parcel.writeString(this.f3271b);
    }
}
