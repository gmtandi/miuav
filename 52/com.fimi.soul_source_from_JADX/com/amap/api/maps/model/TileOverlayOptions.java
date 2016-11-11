package com.amap.api.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import org.codehaus.jackson.smile.SmileConstants;

public final class TileOverlayOptions implements Parcelable {
    public static final TileOverlayOptionsCreator CREATOR;
    private final int f2797a;
    private TileProvider f2798b;
    private boolean f2799c;
    private float f2800d;
    private int f2801e;
    private int f2802f;
    private String f2803g;
    private boolean f2804h;
    private boolean f2805i;

    static {
        CREATOR = new TileOverlayOptionsCreator();
    }

    public TileOverlayOptions() {
        this.f2799c = true;
        this.f2801e = 5242880;
        this.f2802f = 20971520;
        this.f2803g = null;
        this.f2804h = true;
        this.f2805i = true;
        this.f2797a = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f) {
        this.f2799c = true;
        this.f2801e = 5242880;
        this.f2802f = 20971520;
        this.f2803g = null;
        this.f2804h = true;
        this.f2805i = true;
        this.f2797a = i;
        this.f2799c = z;
        this.f2800d = f;
    }

    public int describeContents() {
        return 0;
    }

    public TileOverlayOptions diskCacheDir(String str) {
        this.f2803g = str;
        return this;
    }

    public TileOverlayOptions diskCacheEnabled(boolean z) {
        this.f2805i = z;
        return this;
    }

    public TileOverlayOptions diskCacheSize(int i) {
        this.f2802f = i * SmileConstants.MAX_SHARED_STRING_VALUES;
        return this;
    }

    public String getDiskCacheDir() {
        return this.f2803g;
    }

    public boolean getDiskCacheEnabled() {
        return this.f2805i;
    }

    public int getDiskCacheSize() {
        return this.f2802f;
    }

    public int getMemCacheSize() {
        return this.f2801e;
    }

    public boolean getMemoryCacheEnabled() {
        return this.f2804h;
    }

    public TileProvider getTileProvider() {
        return this.f2798b;
    }

    public float getZIndex() {
        return this.f2800d;
    }

    public boolean isVisible() {
        return this.f2799c;
    }

    public TileOverlayOptions memCacheSize(int i) {
        this.f2801e = i;
        return this;
    }

    public TileOverlayOptions memoryCacheEnabled(boolean z) {
        this.f2804h = z;
        return this;
    }

    public TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.f2798b = tileProvider;
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.f2799c = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeInt(this.f2797a);
        parcel.writeValue(this.f2798b);
        parcel.writeByte((byte) (this.f2799c ? 1 : 0));
        parcel.writeFloat(this.f2800d);
        parcel.writeInt(this.f2801e);
        parcel.writeInt(this.f2802f);
        parcel.writeString(this.f2803g);
        parcel.writeByte((byte) (this.f2804h ? 1 : 0));
        if (!this.f2805i) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
    }

    public TileOverlayOptions zIndex(float f) {
        this.f2800d = f;
        return this;
    }
}
