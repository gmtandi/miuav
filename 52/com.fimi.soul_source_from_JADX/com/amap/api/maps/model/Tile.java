package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class Tile implements Parcelable {
    public static final C0432d CREATOR;
    private final int f2795a;
    public final byte[] data;
    public final int height;
    public final int width;

    static {
        CREATOR = new C0432d();
    }

    Tile(int i, int i2, int i3, byte[] bArr) {
        this.f2795a = i;
        this.width = i2;
        this.height = i3;
        this.data = bArr;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2795a);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeByteArray(this.data);
    }
}
