package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.xiaomi.mipush.sdk.MiPushClient;
import org.p122a.p123a.C2915a;

public final class LatLonPoint implements Parcelable {
    public static final Creator<LatLonPoint> CREATOR;
    private double f2927a;
    private double f2928b;

    static {
        CREATOR = new C0478k();
    }

    public LatLonPoint(double d, double d2) {
        this.f2927a = d;
        this.f2928b = d2;
    }

    private LatLonPoint(Parcel parcel) {
        this.f2927a = parcel.readDouble();
        this.f2928b = parcel.readDouble();
    }

    public LatLonPoint copy() {
        return new LatLonPoint(this.f2927a, this.f2928b);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LatLonPoint latLonPoint = (LatLonPoint) obj;
        return Double.doubleToLongBits(this.f2927a) != Double.doubleToLongBits(latLonPoint.f2927a) ? false : Double.doubleToLongBits(this.f2928b) == Double.doubleToLongBits(latLonPoint.f2928b);
    }

    public double getLatitude() {
        return this.f2927a;
    }

    public double getLongitude() {
        return this.f2928b;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f2927a);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.f2928b);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public void setLatitude(double d) {
        this.f2927a = d;
    }

    public void setLongitude(double d) {
        this.f2928b = d;
    }

    public String toString() {
        return C2915a.f14760f + this.f2927a + MiPushClient.ACCEPT_TIME_SEPARATOR + this.f2928b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f2927a);
        parcel.writeDouble(this.f2928b);
    }
}
