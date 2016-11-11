package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class MyLocationStyle implements Parcelable {
    private BitmapDescriptor f2727a;
    private float f2728b;
    private float f2729c;
    private int f2730d;
    private int f2731e;
    private float f2732f;

    public MyLocationStyle() {
        this.f2728b = 0.5f;
        this.f2729c = 0.5f;
        this.f2730d = Color.argb(100, 0, 0, Opcodes.GETFIELD);
        this.f2731e = Color.argb(Util.MASK_8BIT, 0, 0, C2799f.f14251A);
        this.f2732f = C2020f.f10933c;
    }

    public MyLocationStyle anchor(float f, float f2) {
        this.f2728b = f;
        this.f2729c = f2;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.f2728b;
    }

    public float getAnchorV() {
        return this.f2729c;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.f2727a;
    }

    public int getRadiusFillColor() {
        return this.f2730d;
    }

    public int getStrokeColor() {
        return this.f2731e;
    }

    public float getStrokeWidth() {
        return this.f2732f;
    }

    public MyLocationStyle myLocationIcon(BitmapDescriptor bitmapDescriptor) {
        this.f2727a = bitmapDescriptor;
        return this;
    }

    public MyLocationStyle radiusFillColor(int i) {
        this.f2730d = i;
        return this;
    }

    public MyLocationStyle strokeColor(int i) {
        this.f2731e = i;
        return this;
    }

    public MyLocationStyle strokeWidth(float f) {
        this.f2732f = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2727a, i);
        parcel.writeFloat(this.f2728b);
        parcel.writeFloat(this.f2729c);
        parcel.writeInt(this.f2730d);
        parcel.writeInt(this.f2731e);
        parcel.writeFloat(this.f2732f);
    }
}
