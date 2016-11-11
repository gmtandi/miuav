package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.au;
import com.amap.api.mapcore.util.bg;
import com.amap.api.mapcore.util.bj;

public final class CameraPosition implements Parcelable {
    public static final CameraPositionCreator CREATOR;
    public final float bearing;
    public final boolean isAbroad;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    public final class Builder {
        private LatLng f2655a;
        private float f2656b;
        private float f2657c;
        private float f2658d;

        public Builder(CameraPosition cameraPosition) {
            target(cameraPosition.target).bearing(cameraPosition.bearing).tilt(cameraPosition.tilt).zoom(cameraPosition.zoom);
        }

        public Builder bearing(float f) {
            this.f2658d = f;
            return this;
        }

        public CameraPosition build() {
            au.m3483a(this.f2655a);
            return new CameraPosition(this.f2655a, this.f2656b, this.f2657c, this.f2658d);
        }

        public Builder target(LatLng latLng) {
            this.f2655a = latLng;
            return this;
        }

        public Builder tilt(float f) {
            this.f2657c = f;
            return this;
        }

        public Builder zoom(float f) {
            this.f2656b = f;
            return this;
        }
    }

    static {
        CREATOR = new CameraPositionCreator();
    }

    public CameraPosition(LatLng latLng, float f, float f2, float f3) {
        au.m3484a((Object) latLng, (Object) "CameraPosition \u4f4d\u7f6e\u4e0d\u80fd\u4e3anull ");
        this.target = latLng;
        this.zoom = bj.m3602a(f);
        this.tilt = bj.m3603a(f2, this.zoom);
        if (((double) f3) <= 0.0d) {
            f3 = (f3 % 360.0f) + 360.0f;
        }
        this.bearing = f3 % 360.0f;
        this.isAbroad = !bg.m3590a(latLng.latitude, latLng.longitude);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CameraPosition cameraPosition) {
        return new Builder(cameraPosition);
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        return new CameraPosition(latLng, f, 0.0f, 0.0f);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        return this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return bj.m3619a(bj.m3618a("target", this.target), bj.m3618a("zoom", Float.valueOf(this.zoom)), bj.m3618a("tilt", Float.valueOf(this.tilt)), bj.m3618a("bearing", Float.valueOf(this.bearing)));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.bearing);
        parcel.writeFloat((float) this.target.latitude);
        parcel.writeFloat((float) this.target.longitude);
        parcel.writeFloat(this.tilt);
        parcel.writeFloat(this.zoom);
    }
}
