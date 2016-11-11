package com.amap.api.maps.model;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;

public final class TextOptions implements Parcelable {
    public static final TextOptionsCreator CREATOR;
    String f2782a;
    private LatLng f2783b;
    private String f2784c;
    private Typeface f2785d;
    private float f2786e;
    private int f2787f;
    private int f2788g;
    private int f2789h;
    private int f2790i;
    private Object f2791j;
    private int f2792k;
    private float f2793l;
    private boolean f2794m;

    static {
        CREATOR = new TextOptionsCreator();
    }

    public TextOptions() {
        this.f2785d = Typeface.DEFAULT;
        this.f2787f = 4;
        this.f2788g = 32;
        this.f2789h = -1;
        this.f2790i = ViewCompat.MEASURED_STATE_MASK;
        this.f2792k = 20;
        this.f2793l = 0.0f;
        this.f2794m = true;
    }

    public TextOptions align(int i, int i2) {
        this.f2787f = i;
        this.f2788g = i2;
        return this;
    }

    public TextOptions backgroundColor(int i) {
        this.f2789h = i;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public TextOptions fontColor(int i) {
        this.f2790i = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f2792k = i;
        return this;
    }

    public int getAlignX() {
        return this.f2787f;
    }

    public int getAlignY() {
        return this.f2788g;
    }

    public int getBackgroundColor() {
        return this.f2789h;
    }

    public int getFontColor() {
        return this.f2790i;
    }

    public int getFontSize() {
        return this.f2792k;
    }

    public Object getObject() {
        return this.f2791j;
    }

    public LatLng getPosition() {
        return this.f2783b;
    }

    public float getRotate() {
        return this.f2786e;
    }

    public String getText() {
        return this.f2784c;
    }

    public Typeface getTypeface() {
        return this.f2785d;
    }

    public float getZIndex() {
        return this.f2793l;
    }

    public boolean isVisible() {
        return this.f2794m;
    }

    public TextOptions position(LatLng latLng) {
        this.f2783b = latLng;
        return this;
    }

    public TextOptions rotate(float f) {
        this.f2786e = f;
        return this;
    }

    public TextOptions setObject(Object obj) {
        this.f2791j = obj;
        return this;
    }

    public TextOptions text(String str) {
        this.f2784c = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        if (typeface != null) {
            this.f2785d = typeface;
        }
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f2794m = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2782a);
        Bundle bundle = new Bundle();
        if (this.f2783b != null) {
            bundle.putDouble("lat", this.f2783b.latitude);
            bundle.putDouble("lng", this.f2783b.longitude);
        }
        parcel.writeBundle(bundle);
        parcel.writeString(this.f2784c);
        parcel.writeInt(this.f2785d.getStyle());
        parcel.writeFloat(this.f2786e);
        parcel.writeInt(this.f2787f);
        parcel.writeInt(this.f2788g);
        parcel.writeInt(this.f2789h);
        parcel.writeInt(this.f2790i);
        parcel.writeInt(this.f2792k);
        parcel.writeFloat(this.f2793l);
        parcel.writeByte((byte) (this.f2794m ? 1 : 0));
        if (this.f2791j instanceof Parcelable) {
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("obj", (Parcelable) this.f2791j);
            parcel.writeBundle(bundle2);
        }
    }

    public TextOptions zIndex(float f) {
        this.f2793l = f;
        return this;
    }
}
