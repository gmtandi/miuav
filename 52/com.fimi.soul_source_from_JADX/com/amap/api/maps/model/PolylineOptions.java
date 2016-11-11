package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import com.tencent.open.yyb.TitleBar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements Parcelable {
    public static final PolylineOptionsCreator CREATOR;
    String f2761a;
    private final List<LatLng> f2762b;
    private float f2763c;
    private int f2764d;
    private float f2765e;
    private boolean f2766f;
    private BitmapDescriptor f2767g;
    private List<BitmapDescriptor> f2768h;
    private List<Integer> f2769i;
    private List<Integer> f2770j;
    private boolean f2771k;
    private boolean f2772l;
    private boolean f2773m;
    private boolean f2774n;

    static {
        CREATOR = new PolylineOptionsCreator();
    }

    public PolylineOptions() {
        this.f2763c = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2764d = ViewCompat.MEASURED_STATE_MASK;
        this.f2765e = 0.0f;
        this.f2766f = true;
        this.f2771k = true;
        this.f2772l = false;
        this.f2773m = false;
        this.f2774n = false;
        this.f2762b = new ArrayList();
    }

    public PolylineOptions add(LatLng latLng) {
        this.f2762b.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.f2762b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f2762b.add(add);
        }
        return this;
    }

    public PolylineOptions color(int i) {
        this.f2764d = i;
        return this;
    }

    public PolylineOptions colorValues(List<Integer> list) {
        this.f2769i = list;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolylineOptions geodesic(boolean z) {
        this.f2772l = z;
        return this;
    }

    public int getColor() {
        return this.f2764d;
    }

    public List<Integer> getColorValues() {
        return this.f2769i;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.f2767g;
    }

    public List<Integer> getCustomTextureIndex() {
        return this.f2770j;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.f2768h;
    }

    public List<LatLng> getPoints() {
        return this.f2762b;
    }

    public float getWidth() {
        return this.f2763c;
    }

    public float getZIndex() {
        return this.f2765e;
    }

    public boolean isDottedLine() {
        return this.f2773m;
    }

    public boolean isGeodesic() {
        return this.f2772l;
    }

    public boolean isUseGradient() {
        return this.f2774n;
    }

    public boolean isUseTexture() {
        return this.f2771k;
    }

    public boolean isVisible() {
        return this.f2766f;
    }

    public PolylineOptions setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        this.f2767g = bitmapDescriptor;
        return this;
    }

    public PolylineOptions setCustomTextureIndex(List<Integer> list) {
        this.f2770j = list;
        return this;
    }

    public PolylineOptions setCustomTextureList(List<BitmapDescriptor> list) {
        this.f2768h = list;
        return this;
    }

    public PolylineOptions setDottedLine(boolean z) {
        this.f2773m = z;
        return this;
    }

    public PolylineOptions setUseTexture(boolean z) {
        this.f2771k = z;
        return this;
    }

    public PolylineOptions useGradient(boolean z) {
        this.f2774n = z;
        return this;
    }

    public PolylineOptions visible(boolean z) {
        this.f2766f = z;
        return this;
    }

    public PolylineOptions width(float f) {
        this.f2763c = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f2762b);
        parcel.writeFloat(this.f2763c);
        parcel.writeInt(this.f2764d);
        parcel.writeFloat(this.f2765e);
        parcel.writeString(this.f2761a);
        parcel.writeBooleanArray(new boolean[]{this.f2766f, this.f2773m, this.f2772l, this.f2774n});
        if (this.f2767g != null) {
            parcel.writeParcelable(this.f2767g, i);
        }
        if (this.f2768h != null) {
            parcel.writeList(this.f2768h);
        }
        if (this.f2770j != null) {
            parcel.writeList(this.f2770j);
        }
        if (this.f2769i != null) {
            parcel.writeList(this.f2769i);
        }
    }

    public PolylineOptions zIndex(float f) {
        this.f2765e = f;
        return this;
    }
}
