package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;

public final class MarkerOptions implements Parcelable {
    public static final MarkerOptionsCreator CREATOR;
    String f2711a;
    private LatLng f2712b;
    private String f2713c;
    private String f2714d;
    private float f2715e;
    private float f2716f;
    private float f2717g;
    private boolean f2718h;
    private boolean f2719i;
    private boolean f2720j;
    private int f2721k;
    private int f2722l;
    private ArrayList<BitmapDescriptor> f2723m;
    private int f2724n;
    private boolean f2725o;
    private boolean f2726p;

    static {
        CREATOR = new MarkerOptionsCreator();
    }

    public MarkerOptions() {
        this.f2715e = 0.5f;
        this.f2716f = C2020f.f10933c;
        this.f2717g = 0.0f;
        this.f2718h = false;
        this.f2719i = true;
        this.f2720j = false;
        this.f2721k = 0;
        this.f2722l = 0;
        this.f2723m = new ArrayList();
        this.f2724n = 20;
        this.f2725o = false;
        this.f2726p = false;
    }

    private void m4349a() {
        if (this.f2723m == null) {
            this.f2723m = new ArrayList();
        }
    }

    public MarkerOptions anchor(float f, float f2) {
        this.f2715e = f;
        this.f2716f = f2;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public MarkerOptions draggable(boolean z) {
        this.f2718h = z;
        return this;
    }

    public float getAnchorU() {
        return this.f2715e;
    }

    public float getAnchorV() {
        return this.f2716f;
    }

    public BitmapDescriptor getIcon() {
        return (this.f2723m == null || this.f2723m.size() == 0) ? null : (BitmapDescriptor) this.f2723m.get(0);
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f2723m;
    }

    public int getInfoWindowOffsetX() {
        return this.f2721k;
    }

    public int getInfoWindowOffsetY() {
        return this.f2722l;
    }

    public int getPeriod() {
        return this.f2724n;
    }

    public LatLng getPosition() {
        return this.f2712b;
    }

    public String getSnippet() {
        return this.f2714d;
    }

    public String getTitle() {
        return this.f2713c;
    }

    public float getZIndex() {
        return this.f2717g;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        m4349a();
        this.f2723m.clear();
        this.f2723m.add(bitmapDescriptor);
        return this;
    }

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        this.f2723m = arrayList;
        return this;
    }

    public boolean isDraggable() {
        return this.f2718h;
    }

    public boolean isFlat() {
        return this.f2726p;
    }

    public boolean isGps() {
        return this.f2725o;
    }

    public boolean isPerspective() {
        return this.f2720j;
    }

    public boolean isVisible() {
        return this.f2719i;
    }

    public MarkerOptions period(int i) {
        if (i <= 1) {
            this.f2724n = 1;
        } else {
            this.f2724n = i;
        }
        return this;
    }

    public MarkerOptions perspective(boolean z) {
        this.f2720j = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        this.f2712b = latLng;
        return this;
    }

    public MarkerOptions setFlat(boolean z) {
        this.f2726p = z;
        return this;
    }

    public MarkerOptions setGps(boolean z) {
        this.f2725o = z;
        return this;
    }

    public MarkerOptions setInfoWindowOffset(int i, int i2) {
        this.f2721k = i;
        this.f2722l = i2;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.f2714d = str;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f2713c = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f2719i = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2712b, i);
        if (!(this.f2723m == null || this.f2723m.size() == 0)) {
            parcel.writeParcelable((Parcelable) this.f2723m.get(0), i);
        }
        parcel.writeString(this.f2713c);
        parcel.writeString(this.f2714d);
        parcel.writeFloat(this.f2715e);
        parcel.writeFloat(this.f2716f);
        parcel.writeInt(this.f2721k);
        parcel.writeInt(this.f2722l);
        parcel.writeBooleanArray(new boolean[]{this.f2719i, this.f2718h, this.f2725o, this.f2726p});
        parcel.writeString(this.f2711a);
        parcel.writeInt(this.f2724n);
        parcel.writeList(this.f2723m);
        parcel.writeFloat(this.f2717g);
    }

    public MarkerOptions zIndex(float f) {
        this.f2717g = f;
        return this;
    }
}
