package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements Parcelable {
    public static final PolygonOptionsCreator CREATOR;
    String f2753a;
    private final List<LatLng> f2754b;
    private float f2755c;
    private int f2756d;
    private int f2757e;
    private float f2758f;
    private boolean f2759g;

    static {
        CREATOR = new PolygonOptionsCreator();
    }

    public PolygonOptions() {
        this.f2755c = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2756d = ViewCompat.MEASURED_STATE_MASK;
        this.f2757e = ViewCompat.MEASURED_STATE_MASK;
        this.f2758f = 0.0f;
        this.f2759g = true;
        this.f2754b = new ArrayList();
    }

    public PolygonOptions add(LatLng latLng) {
        this.f2754b.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.f2754b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f2754b.add(add);
        }
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolygonOptions fillColor(int i) {
        this.f2757e = i;
        return this;
    }

    public int getFillColor() {
        return this.f2757e;
    }

    public List<LatLng> getPoints() {
        return this.f2754b;
    }

    public int getStrokeColor() {
        return this.f2756d;
    }

    public float getStrokeWidth() {
        return this.f2755c;
    }

    public float getZIndex() {
        return this.f2758f;
    }

    public boolean isVisible() {
        return this.f2759g;
    }

    public PolygonOptions strokeColor(int i) {
        this.f2756d = i;
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.f2755c = f;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f2759g = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f2754b);
        parcel.writeFloat(this.f2755c);
        parcel.writeInt(this.f2756d);
        parcel.writeInt(this.f2757e);
        parcel.writeFloat(this.f2758f);
        parcel.writeByte((byte) (this.f2759g ? 1 : 0));
        parcel.writeString(this.f2753a);
    }

    public PolygonOptions zIndex(float f) {
        this.f2758f = f;
        return this;
    }
}
