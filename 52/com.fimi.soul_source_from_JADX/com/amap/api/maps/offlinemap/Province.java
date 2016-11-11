package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.p122a.p123a.C2915a;

public class Province implements Parcelable {
    public static final Creator<Province> CREATOR;
    private String f2830a;
    private String f2831b;
    private String f2832c;
    private String f2833d;

    static {
        CREATOR = new C0442d();
    }

    public Province() {
        this.f2830a = C2915a.f14760f;
        this.f2833d = C2915a.f14760f;
    }

    public Province(Parcel parcel) {
        this.f2830a = C2915a.f14760f;
        this.f2833d = C2915a.f14760f;
        this.f2830a = parcel.readString();
        this.f2831b = parcel.readString();
        this.f2832c = parcel.readString();
        this.f2833d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getJianpin() {
        return this.f2831b;
    }

    public String getPinyin() {
        return this.f2832c;
    }

    public String getProvinceCode() {
        return this.f2833d;
    }

    public String getProvinceName() {
        return this.f2830a;
    }

    public void setJianpin(String str) {
        this.f2831b = str;
    }

    public void setPinyin(String str) {
        this.f2832c = str;
    }

    public void setProvinceCode(String str) {
        this.f2833d = str;
    }

    public void setProvinceName(String str) {
        this.f2830a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2830a);
        parcel.writeString(this.f2831b);
        parcel.writeString(this.f2832c);
        parcel.writeString(this.f2833d);
    }
}
