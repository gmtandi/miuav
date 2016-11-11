package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class OfflineMapProvince extends Province {
    public static final Creator<OfflineMapProvince> CREATOR;
    private String f2834a;
    private int f2835b;
    private long f2836c;
    private String f2837d;
    private int f2838e;
    private ArrayList<OfflineMapCity> f2839f;

    static {
        CREATOR = new C0441c();
    }

    public OfflineMapProvince() {
        this.f2835b = 6;
        this.f2838e = 0;
    }

    public OfflineMapProvince(Parcel parcel) {
        super(parcel);
        this.f2835b = 6;
        this.f2838e = 0;
        this.f2834a = parcel.readString();
        this.f2835b = parcel.readInt();
        this.f2836c = parcel.readLong();
        this.f2837d = parcel.readString();
        this.f2838e = parcel.readInt();
        this.f2839f = parcel.createTypedArrayList(OfflineMapCity.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<OfflineMapCity> getCityList() {
        return this.f2839f == null ? new ArrayList() : this.f2839f;
    }

    public long getSize() {
        return this.f2836c;
    }

    public int getState() {
        return this.f2835b;
    }

    public String getUrl() {
        return this.f2834a;
    }

    public String getVersion() {
        return this.f2837d;
    }

    public int getcompleteCode() {
        return this.f2838e;
    }

    public void setCityList(ArrayList<OfflineMapCity> arrayList) {
        this.f2839f = arrayList;
    }

    public void setCompleteCode(int i) {
        this.f2838e = i;
    }

    public void setSize(long j) {
        this.f2836c = j;
    }

    public void setState(int i) {
        this.f2835b = i;
    }

    public void setUrl(String str) {
        this.f2834a = str;
    }

    public void setVersion(String str) {
        this.f2837d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f2834a);
        parcel.writeInt(this.f2835b);
        parcel.writeLong(this.f2836c);
        parcel.writeString(this.f2837d);
        parcel.writeInt(this.f2838e);
        parcel.writeTypedList(this.f2839f);
    }
}
