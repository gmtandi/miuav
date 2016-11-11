package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.mapcore.util.bj;

public final class BitmapDescriptor implements Parcelable, Cloneable {
    public static final BitmapDescriptorCreator CREATOR;
    int f2652a;
    int f2653b;
    Bitmap f2654c;

    static {
        CREATOR = new BitmapDescriptorCreator();
    }

    BitmapDescriptor(Bitmap bitmap) {
        this.f2652a = 0;
        this.f2653b = 0;
        if (bitmap != null) {
            this.f2652a = bitmap.getWidth();
            this.f2653b = bitmap.getHeight();
            this.f2654c = m4316a(bitmap, bj.m3604a(this.f2652a), bj.m3604a(this.f2653b));
        }
    }

    private BitmapDescriptor(Bitmap bitmap, int i, int i2) {
        this.f2652a = 0;
        this.f2653b = 0;
        this.f2652a = i;
        this.f2653b = i2;
        this.f2654c = bitmap;
    }

    private Bitmap m4316a(Bitmap bitmap, int i, int i2) {
        return bj.m3611a(bitmap, i, i2);
    }

    public BitmapDescriptor clone() {
        try {
            return new BitmapDescriptor(Bitmap.createBitmap(this.f2654c), this.f2652a, this.f2653b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this.f2654c == null || this.f2654c.isRecycled() || obj == null) {
            return z;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return z;
        }
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) obj;
        if (bitmapDescriptor.f2654c == null || bitmapDescriptor.f2654c.isRecycled() || this.f2652a != bitmapDescriptor.getWidth() || this.f2653b != bitmapDescriptor.getHeight()) {
            return z;
        }
        try {
            return this.f2654c.sameAs(bitmapDescriptor.f2654c);
        } catch (Throwable th) {
            return z;
        }
    }

    public Bitmap getBitmap() {
        return this.f2654c;
    }

    public int getHeight() {
        return this.f2653b;
    }

    public int getWidth() {
        return this.f2652a;
    }

    public void recycle() {
        if (this.f2654c != null && !this.f2654c.isRecycled()) {
            this.f2654c.recycle();
            this.f2654c = null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2654c, i);
        parcel.writeInt(this.f2652a);
        parcel.writeInt(this.f2653b);
    }
}
