package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.p122a.p123a.C2915a;

public class City implements Parcelable {
    public static final Creator<City> CREATOR;
    private String f2454a;
    private String f2455b;
    private String f2456c;
    private String f2457d;
    private String f2458e;

    static {
        CREATOR = new C0439a();
    }

    public City() {
        this.f2454a = C2915a.f14760f;
        this.f2455b = C2915a.f14760f;
        this.f2458e = C2915a.f14760f;
    }

    public City(Parcel parcel) {
        this.f2454a = C2915a.f14760f;
        this.f2455b = C2915a.f14760f;
        this.f2458e = C2915a.f14760f;
        this.f2454a = parcel.readString();
        this.f2455b = parcel.readString();
        this.f2456c = parcel.readString();
        this.f2457d = parcel.readString();
        this.f2458e = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f2458e;
    }

    public String getCity() {
        return this.f2454a;
    }

    public String getCode() {
        return this.f2455b;
    }

    public String getJianpin() {
        return this.f2456c;
    }

    public String getPinyin() {
        return this.f2457d;
    }

    public void setAdcode(String str) {
        this.f2458e = str;
    }

    public void setCity(String str) {
        this.f2454a = str;
    }

    public void setCode(String str) {
        if (str != null && !str.equals("[]")) {
            this.f2455b = str;
        }
    }

    public void setJianpin(String str) {
        this.f2456c = str;
    }

    public void setPinyin(String str) {
        this.f2457d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2454a);
        parcel.writeString(this.f2455b);
        parcel.writeString(this.f2456c);
        parcel.writeString(this.f2457d);
        parcel.writeString(this.f2458e);
    }
}
