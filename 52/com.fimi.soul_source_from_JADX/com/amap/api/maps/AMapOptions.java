package com.amap.api.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.model.CameraPosition;

public class AMapOptions implements Parcelable {
    public static final AMapOptionsCreator CREATOR;
    public static final int LOGO_POSITION_BOTTOM_CENTER = 1;
    public static final int LOGO_POSITION_BOTTOM_LEFT = 0;
    public static final int LOGO_POSITION_BOTTOM_RIGHT = 2;
    public static final int ZOOM_POSITION_RIGHT_BUTTOM = 2;
    public static final int ZOOM_POSITION_RIGHT_CENTER = 1;
    private int f2607a;
    private boolean f2608b;
    private boolean f2609c;
    private boolean f2610d;
    private boolean f2611e;
    private boolean f2612f;
    private boolean f2613g;
    private CameraPosition f2614h;
    private boolean f2615i;
    private boolean f2616j;
    private int f2617k;

    static {
        CREATOR = new AMapOptionsCreator();
    }

    public AMapOptions() {
        this.f2607a = ZOOM_POSITION_RIGHT_CENTER;
        this.f2608b = true;
        this.f2609c = true;
        this.f2610d = true;
        this.f2611e = true;
        this.f2612f = true;
        this.f2613g = false;
        this.f2615i = false;
        this.f2616j = false;
        this.f2617k = LOGO_POSITION_BOTTOM_LEFT;
    }

    public AMapOptions camera(CameraPosition cameraPosition) {
        this.f2614h = cameraPosition;
        return this;
    }

    public AMapOptions compassEnabled(boolean z) {
        this.f2615i = z;
        return this;
    }

    public int describeContents() {
        return LOGO_POSITION_BOTTOM_LEFT;
    }

    public CameraPosition getCamera() {
        return this.f2614h;
    }

    public Boolean getCompassEnabled() {
        return Boolean.valueOf(this.f2615i);
    }

    public int getLogoPosition() {
        return this.f2617k;
    }

    public int getMapType() {
        return this.f2607a;
    }

    public Boolean getRotateGesturesEnabled() {
        return Boolean.valueOf(this.f2608b);
    }

    public Boolean getScaleControlsEnabled() {
        return Boolean.valueOf(this.f2616j);
    }

    public Boolean getScrollGesturesEnabled() {
        return Boolean.valueOf(this.f2609c);
    }

    public Boolean getTiltGesturesEnabled() {
        return Boolean.valueOf(this.f2610d);
    }

    public Boolean getZOrderOnTop() {
        return Boolean.valueOf(this.f2613g);
    }

    public Boolean getZoomControlsEnabled() {
        return Boolean.valueOf(this.f2612f);
    }

    public Boolean getZoomGesturesEnabled() {
        return Boolean.valueOf(this.f2611e);
    }

    public AMapOptions logoPosition(int i) {
        this.f2617k = i;
        return this;
    }

    public AMapOptions mapType(int i) {
        this.f2607a = i;
        return this;
    }

    public AMapOptions rotateGesturesEnabled(boolean z) {
        this.f2608b = z;
        return this;
    }

    public AMapOptions scaleControlsEnabled(boolean z) {
        this.f2616j = z;
        return this;
    }

    public AMapOptions scrollGesturesEnabled(boolean z) {
        this.f2609c = z;
        return this;
    }

    public AMapOptions tiltGesturesEnabled(boolean z) {
        this.f2610d = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2614h, i);
        parcel.writeInt(this.f2607a);
        parcel.writeBooleanArray(new boolean[]{this.f2608b, this.f2609c, this.f2610d, this.f2611e, this.f2612f, this.f2613g, this.f2615i, this.f2616j});
    }

    public AMapOptions zOrderOnTop(boolean z) {
        this.f2613g = z;
        return this;
    }

    public AMapOptions zoomControlsEnabled(boolean z) {
        this.f2612f = z;
        return this;
    }

    public AMapOptions zoomGesturesEnabled(boolean z) {
        this.f2611e = z;
        return this;
    }
}
