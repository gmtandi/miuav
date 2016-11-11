package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class LatLng implements Parcelable, Cloneable {
    public static final LatLngCreator CREATOR;
    private static DecimalFormat f2704a;
    public final double latitude;
    public final double longitude;

    static {
        CREATOR = new LatLngCreator();
        f2704a = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US));
    }

    public LatLng(double d, double d2) {
        this(d, d2, true);
    }

    public LatLng(double d, double d2, boolean z) {
        if (z) {
            if (-180.0d > d2 || d2 >= 180.0d) {
                this.longitude = m4338a(((((d2 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d);
            } else {
                this.longitude = m4338a(d2);
            }
            if (d < -90.0d || d > 90.0d) {
                try {
                    throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
                } catch (AMapException e) {
                    e.printStackTrace();
                    Log.d(MapTilsCacheAndResManager.AUTONAVI_PATH, e.getErrorMessage());
                }
            }
            this.latitude = m4338a(Math.max(-90.0d, Math.min(90.0d, d)));
            return;
        }
        this.latitude = d;
        this.longitude = d2;
    }

    private static double m4338a(double d) {
        return Double.parseDouble(f2704a.format(d));
    }

    public LatLng clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(latLng.longitude);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        doubleToLongBits = Double.doubleToLongBits(this.longitude);
        return (31 * i) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        return "lat/lng: (" + this.latitude + MiPushClient.ACCEPT_TIME_SEPARATOR + this.longitude + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
    }
}
