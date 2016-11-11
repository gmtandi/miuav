package com.amap.api.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.au;
import com.fimi.soul.view.photodraweeview.C2020f;

public final class GroundOverlayOptions implements Parcelable {
    public static final GroundOverlayOptionsCreator CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    private final int f2677a;
    private BitmapDescriptor f2678b;
    private LatLng f2679c;
    private float f2680d;
    private float f2681e;
    private LatLngBounds f2682f;
    private float f2683g;
    private float f2684h;
    private boolean f2685i;
    private float f2686j;
    private float f2687k;
    private float f2688l;

    static {
        CREATOR = new GroundOverlayOptionsCreator();
    }

    public GroundOverlayOptions() {
        this.f2684h = 0.0f;
        this.f2685i = true;
        this.f2686j = 0.0f;
        this.f2687k = 0.5f;
        this.f2688l = 0.5f;
        this.f2677a = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7) {
        this.f2684h = 0.0f;
        this.f2685i = true;
        this.f2686j = 0.0f;
        this.f2687k = 0.5f;
        this.f2688l = 0.5f;
        this.f2677a = i;
        this.f2678b = BitmapDescriptorFactory.fromBitmap(null);
        this.f2679c = latLng;
        this.f2680d = f;
        this.f2681e = f2;
        this.f2682f = latLngBounds;
        this.f2683g = f3;
        this.f2684h = f4;
        this.f2685i = z;
        this.f2686j = f5;
        this.f2687k = f6;
        this.f2688l = f7;
    }

    private GroundOverlayOptions m4322a(LatLng latLng, float f, float f2) {
        this.f2679c = latLng;
        this.f2680d = f;
        this.f2681e = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.f2687k = f;
        this.f2688l = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.f2683g = f;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.f2687k;
    }

    public float getAnchorV() {
        return this.f2688l;
    }

    public float getBearing() {
        return this.f2683g;
    }

    public LatLngBounds getBounds() {
        return this.f2682f;
    }

    public float getHeight() {
        return this.f2681e;
    }

    public BitmapDescriptor getImage() {
        return this.f2678b;
    }

    public LatLng getLocation() {
        return this.f2679c;
    }

    public float getTransparency() {
        return this.f2686j;
    }

    public float getWidth() {
        return this.f2680d;
    }

    public float getZIndex() {
        return this.f2684h;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.f2678b = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.f2685i;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        au.m3485a(this.f2682f == null, (Object) "Position has already been set using positionFromBounds");
        au.m3487b(latLng != null, "Location must be specified");
        if (f < 0.0f) {
            z = false;
        }
        au.m3487b(z, "Width must be non-negative");
        return m4322a(latLng, f, f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        au.m3485a(this.f2682f == null, (Object) "Position has already been set using positionFromBounds");
        au.m3487b(latLng != null, "Location must be specified");
        au.m3487b(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        au.m3487b(z, "Height must be non-negative");
        return m4322a(latLng, f, f2);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        au.m3485a(this.f2679c == null, "Position has already been set using position: " + this.f2679c);
        this.f2682f = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        boolean z = f >= 0.0f && f <= C2020f.f10933c;
        au.m3487b(z, "Transparency must be in the range [0..1]");
        this.f2686j = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f2685i = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2677a);
        parcel.writeParcelable(this.f2678b, i);
        parcel.writeParcelable(this.f2679c, i);
        parcel.writeFloat(this.f2680d);
        parcel.writeFloat(this.f2681e);
        parcel.writeParcelable(this.f2682f, i);
        parcel.writeFloat(this.f2683g);
        parcel.writeFloat(this.f2684h);
        parcel.writeByte((byte) (this.f2685i ? 1 : 0));
        parcel.writeFloat(this.f2686j);
        parcel.writeFloat(this.f2687k);
        parcel.writeFloat(this.f2688l);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.f2684h = f;
        return this;
    }
}
