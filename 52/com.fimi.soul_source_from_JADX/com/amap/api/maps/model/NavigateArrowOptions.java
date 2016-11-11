package com.amap.api.maps.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.fimi.soul.drone.p107c.p108a.p109a.C1458u;
import com.tencent.open.yyb.TitleBar;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class NavigateArrowOptions implements Parcelable {
    public static final NavigateArrowOptionsCreator CREATOR;
    String f2740a;
    private final List<LatLng> f2741b;
    private float f2742c;
    private int f2743d;
    private int f2744e;
    private float f2745f;
    private boolean f2746g;

    static {
        CREATOR = new NavigateArrowOptionsCreator();
    }

    public NavigateArrowOptions() {
        this.f2742c = TitleBar.SHAREBTN_RIGHT_MARGIN;
        this.f2743d = Color.argb(C2799f.f14252B, 87, 235, C1458u.f6934b);
        this.f2744e = Color.argb(Opcodes.TABLESWITCH, 0, Opcodes.IRETURN, Opcodes.I2C);
        this.f2745f = 0.0f;
        this.f2746g = true;
        this.f2741b = new ArrayList();
    }

    public NavigateArrowOptions add(LatLng latLng) {
        this.f2741b.add(latLng);
        return this;
    }

    public NavigateArrowOptions add(LatLng... latLngArr) {
        this.f2741b.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public NavigateArrowOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.f2741b.add(add);
        }
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public List<LatLng> getPoints() {
        return this.f2741b;
    }

    @Deprecated
    public int getSideColor() {
        return this.f2744e;
    }

    public int getTopColor() {
        return this.f2743d;
    }

    public float getWidth() {
        return this.f2742c;
    }

    public float getZIndex() {
        return this.f2745f;
    }

    public boolean isVisible() {
        return this.f2746g;
    }

    @Deprecated
    public NavigateArrowOptions sideColor(int i) {
        this.f2744e = i;
        return this;
    }

    public NavigateArrowOptions topColor(int i) {
        this.f2743d = i;
        return this;
    }

    public NavigateArrowOptions visible(boolean z) {
        this.f2746g = z;
        return this;
    }

    public NavigateArrowOptions width(float f) {
        this.f2742c = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f2741b);
        parcel.writeFloat(this.f2742c);
        parcel.writeInt(this.f2743d);
        parcel.writeInt(this.f2744e);
        parcel.writeFloat(this.f2745f);
        parcel.writeByte((byte) (this.f2746g ? 1 : 0));
        parcel.writeString(this.f2740a);
    }

    public NavigateArrowOptions zIndex(float f) {
        this.f2745f = f;
        return this;
    }
}
