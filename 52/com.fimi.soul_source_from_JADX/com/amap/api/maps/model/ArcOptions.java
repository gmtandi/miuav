package com.amap.api.maps.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;

public final class ArcOptions implements Parcelable {
    public static final ArcOptionsCreator CREATOR;
    String f2644a;
    private LatLng f2645b;
    private LatLng f2646c;
    private LatLng f2647d;
    private float f2648e;
    private int f2649f;
    private float f2650g;
    private boolean f2651h;

    static {
        CREATOR = new ArcOptionsCreator();
    }

    public ArcOptions() {
        this.f2648e = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2649f = ViewCompat.MEASURED_STATE_MASK;
        this.f2650g = 0.0f;
        this.f2651h = true;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getEnd() {
        return this.f2647d;
    }

    public LatLng getPassed() {
        return this.f2646c;
    }

    public LatLng getStart() {
        return this.f2645b;
    }

    public int getStrokeColor() {
        return this.f2649f;
    }

    public float getStrokeWidth() {
        return this.f2648e;
    }

    public float getZIndex() {
        return this.f2650g;
    }

    public boolean isVisible() {
        return this.f2651h;
    }

    public ArcOptions point(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        this.f2645b = latLng;
        this.f2646c = latLng2;
        this.f2647d = latLng3;
        return this;
    }

    public ArcOptions strokeColor(int i) {
        this.f2649f = i;
        return this;
    }

    public ArcOptions strokeWidth(float f) {
        this.f2648e = f;
        return this;
    }

    public ArcOptions visible(boolean z) {
        this.f2651h = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        if (this.f2645b != null) {
            bundle.putDouble("startlat", this.f2645b.latitude);
            bundle.putDouble("startlng", this.f2645b.longitude);
        }
        if (this.f2646c != null) {
            bundle.putDouble("passedlat", this.f2646c.latitude);
            bundle.putDouble("passedlng", this.f2646c.longitude);
        }
        if (this.f2647d != null) {
            bundle.putDouble("endlat", this.f2647d.latitude);
            bundle.putDouble("endlng", this.f2647d.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeFloat(this.f2648e);
        parcel.writeInt(this.f2649f);
        parcel.writeFloat(this.f2650g);
        parcel.writeByte((byte) (this.f2651h ? 1 : 0));
        parcel.writeString(this.f2644a);
    }

    public ArcOptions zIndex(float f) {
        this.f2650g = f;
        return this;
    }
}
