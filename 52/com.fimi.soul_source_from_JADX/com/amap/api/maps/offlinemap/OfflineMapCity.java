package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.p122a.p123a.C2915a;

public class OfflineMapCity extends City {
    public static final Creator<OfflineMapCity> CREATOR;
    private String f2459a;
    private long f2460b;
    private int f2461c;
    private String f2462d;
    private int f2463e;

    static {
        CREATOR = new C0440b();
    }

    public OfflineMapCity() {
        this.f2459a = C2915a.f14760f;
        this.f2460b = 0;
        this.f2461c = 6;
        this.f2462d = C2915a.f14760f;
        this.f2463e = 0;
    }

    public OfflineMapCity(Parcel parcel) {
        super(parcel);
        this.f2459a = C2915a.f14760f;
        this.f2460b = 0;
        this.f2461c = 6;
        this.f2462d = C2915a.f14760f;
        this.f2463e = 0;
        this.f2459a = parcel.readString();
        this.f2460b = parcel.readLong();
        this.f2461c = parcel.readInt();
        this.f2462d = parcel.readString();
        this.f2463e = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public long getSize() {
        return this.f2460b;
    }

    public int getState() {
        return this.f2461c;
    }

    public String getUrl() {
        return this.f2459a;
    }

    public String getVersion() {
        return this.f2462d;
    }

    public int getcompleteCode() {
        return this.f2463e;
    }

    public void setCompleteCode(int i) {
        this.f2463e = i;
    }

    public void setSize(long j) {
        this.f2460b = j;
    }

    public void setState(int i) {
        this.f2461c = i;
    }

    public void setUrl(String str) {
        this.f2459a = str;
    }

    public void setVersion(String str) {
        this.f2462d = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f2459a);
        parcel.writeLong(this.f2460b);
        parcel.writeInt(this.f2461c);
        parcel.writeString(this.f2462d);
        parcel.writeInt(this.f2463e);
    }
}
