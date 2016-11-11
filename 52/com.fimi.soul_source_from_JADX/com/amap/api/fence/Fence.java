package com.amap.api.fence;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.p016a.dn;

public class Fence implements Parcelable {
    public static final Creator<Fence> CREATOR;
    public PendingIntent f1339a;
    public String f1340b;
    public double f1341c;
    public double f1342d;
    public float f1343e;
    public long f1344f;
    public int f1345g;
    public long f1346h;
    private long f1347i;
    private int f1348j;

    static {
        CREATOR = new C0265a();
    }

    public Fence() {
        this.f1339a = null;
        this.f1340b = null;
        this.f1341c = 0.0d;
        this.f1342d = 0.0d;
        this.f1343e = 0.0f;
        this.f1344f = -1;
        this.f1347i = -1;
        this.f1348j = 3;
        this.f1345g = -1;
        this.f1346h = -1;
    }

    private Fence(Parcel parcel) {
        this.f1339a = null;
        this.f1340b = null;
        this.f1341c = 0.0d;
        this.f1342d = 0.0d;
        this.f1343e = 0.0f;
        this.f1344f = -1;
        this.f1347i = -1;
        this.f1348j = 3;
        this.f1345g = -1;
        this.f1346h = -1;
        if (parcel != null) {
            this.f1340b = parcel.readString();
            this.f1341c = parcel.readDouble();
            this.f1342d = parcel.readDouble();
            this.f1343e = parcel.readFloat();
            this.f1344f = parcel.readLong();
            this.f1347i = parcel.readLong();
            this.f1348j = parcel.readInt();
            this.f1345g = parcel.readInt();
            this.f1346h = parcel.readLong();
        }
    }

    public int m2066a() {
        return this.f1348j;
    }

    public void m2067a(long j) {
        if (j < 0) {
            this.f1344f = -1;
        } else {
            this.f1344f = dn.m1519b() + j;
        }
    }

    public long m2068b() {
        return this.f1344f;
    }

    public long m2069c() {
        return this.f1347i;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1340b);
        parcel.writeDouble(this.f1341c);
        parcel.writeDouble(this.f1342d);
        parcel.writeFloat(this.f1343e);
        parcel.writeLong(this.f1344f);
        parcel.writeLong(this.f1347i);
        parcel.writeInt(this.f1348j);
        parcel.writeInt(this.f1345g);
        parcel.writeLong(this.f1346h);
    }
}
