package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.au;
import com.amap.api.mapcore.util.bj;

public final class LatLngBounds implements Parcelable {
    public static final LatLngBoundsCreator CREATOR;
    private final int f2709a;
    public final LatLng northeast;
    public final LatLng southwest;

    public final class Builder {
        private double f2705a;
        private double f2706b;
        private double f2707c;
        private double f2708d;

        public Builder() {
            this.f2705a = Double.POSITIVE_INFINITY;
            this.f2706b = Double.NEGATIVE_INFINITY;
            this.f2707c = Double.NaN;
            this.f2708d = Double.NaN;
        }

        private boolean m4339a(double d) {
            boolean z = false;
            if (this.f2707c <= this.f2708d) {
                return this.f2707c <= d && d <= this.f2708d;
            } else {
                if (this.f2707c <= d || d <= this.f2708d) {
                    z = true;
                }
                return z;
            }
        }

        public LatLngBounds build() {
            au.m3485a(!Double.isNaN(this.f2707c), (Object) "no included points");
            return new LatLngBounds(new LatLng(this.f2705a, this.f2707c, false), new LatLng(this.f2706b, this.f2708d, false));
        }

        public Builder include(LatLng latLng) {
            this.f2705a = Math.min(this.f2705a, latLng.latitude);
            this.f2706b = Math.max(this.f2706b, latLng.latitude);
            double d = latLng.longitude;
            if (Double.isNaN(this.f2707c)) {
                this.f2707c = d;
                this.f2708d = d;
            } else if (!m4339a(d)) {
                if (LatLngBounds.m4345c(this.f2707c, d) < LatLngBounds.m4346d(this.f2708d, d)) {
                    this.f2707c = d;
                } else {
                    this.f2708d = d;
                }
            }
            return this;
        }
    }

    static {
        CREATOR = new LatLngBoundsCreator();
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        au.m3484a((Object) latLng, (Object) "null southwest");
        au.m3484a((Object) latLng2, (Object) "null northeast");
        au.m3486a(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude)});
        this.f2709a = i;
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    private boolean m4341a(double d) {
        return this.southwest.latitude <= d && d <= this.northeast.latitude;
    }

    private boolean m4342a(LatLngBounds latLngBounds) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null || this.northeast == null || this.southwest == null) {
            return false;
        }
        return Math.abs(((latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) - this.northeast.longitude) - this.southwest.longitude) < ((this.northeast.longitude - this.southwest.longitude) + latLngBounds.northeast.longitude) - this.southwest.longitude && Math.abs(((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) - this.northeast.latitude) - this.southwest.latitude) < ((this.northeast.latitude - this.southwest.latitude) + latLngBounds.northeast.latitude) - latLngBounds.southwest.latitude;
    }

    private boolean m4344b(double d) {
        boolean z = false;
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= d && d <= this.northeast.longitude;
        } else {
            if (this.southwest.longitude <= d || d <= this.northeast.longitude) {
                z = true;
            }
            return z;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static double m4345c(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    private static double m4346d(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    int m4347a() {
        return this.f2709a;
    }

    public boolean contains(LatLng latLng) {
        return m4341a(latLng.latitude) && m4344b(latLng.longitude);
    }

    public boolean contains(LatLngBounds latLngBounds) {
        return latLngBounds != null && contains(latLngBounds.southwest) && contains(latLngBounds.northeast);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public int hashCode() {
        return bj.m3608a(new Object[]{this.southwest, this.northeast});
    }

    public LatLngBounds including(LatLng latLng) {
        double d;
        double min = Math.min(this.southwest.latitude, latLng.latitude);
        double max = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (m4344b(d4)) {
            d4 = d3;
            d = d2;
        } else if (m4345c(d3, d4) < m4346d(d2, d4)) {
            d = d2;
        } else {
            d = d4;
            d4 = d3;
        }
        return new LatLngBounds(new LatLng(min, d4, false), new LatLng(max, d, false));
    }

    public boolean intersects(LatLngBounds latLngBounds) {
        return latLngBounds == null ? false : m4342a(latLngBounds) || latLngBounds.m4342a(this);
    }

    public String toString() {
        return bj.m3619a(bj.m3618a("southwest", this.southwest), bj.m3618a("northeast", this.northeast));
    }

    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.m4348a(this, parcel, i);
    }
}
