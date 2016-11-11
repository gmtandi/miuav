package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;

public final class CircleOptions implements Parcelable {
    public static final CircleOptionsCreator CREATOR;
    String f2660a;
    private LatLng f2661b;
    private double f2662c;
    private float f2663d;
    private int f2664e;
    private int f2665f;
    private float f2666g;
    private boolean f2667h;

    static {
        CREATOR = new CircleOptionsCreator();
    }

    public CircleOptions() {
        this.f2661b = null;
        this.f2662c = 0.0d;
        this.f2663d = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2664e = ViewCompat.MEASURED_STATE_MASK;
        this.f2665f = 0;
        this.f2666g = 0.0f;
        this.f2667h = true;
    }

    public CircleOptions center(LatLng latLng) {
        this.f2661b = latLng;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions fillColor(int i) {
        this.f2665f = i;
        return this;
    }

    public LatLng getCenter() {
        return this.f2661b;
    }

    public int getFillColor() {
        return this.f2665f;
    }

    public double getRadius() {
        return this.f2662c;
    }

    public int getStrokeColor() {
        return this.f2664e;
    }

    public float getStrokeWidth() {
        return this.f2663d;
    }

    public float getZIndex() {
        return this.f2666g;
    }

    public boolean isVisible() {
        return this.f2667h;
    }

    public CircleOptions radius(double d) {
        this.f2662c = d;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.f2664e = i;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.f2663d = f;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f2667h = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f2661b != null) {
            bundle.putDouble("lat", this.f2661b.latitude);
            bundle.putDouble("lng", this.f2661b.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeDouble(this.f2662c);
        parcel.writeFloat(this.f2663d);
        parcel.writeInt(this.f2664e);
        parcel.writeInt(this.f2665f);
        parcel.writeFloat(this.f2666g);
        parcel.writeByte((byte) (this.f2667h ? 1 : 0));
        parcel.writeString(this.f2660a);
    }

    public CircleOptions zIndex(float f) {
        this.f2666g = f;
        return this;
    }
}
