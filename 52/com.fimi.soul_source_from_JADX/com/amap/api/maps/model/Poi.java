package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Poi implements Parcelable {
    public static final PoiCreator CREATOR;
    private final String f2747a;
    private final LatLng f2748b;
    private final String f2749c;

    static {
        CREATOR = new PoiCreator();
    }

    public Poi(String str, LatLng latLng, String str2) {
        this.f2747a = str;
        this.f2748b = latLng;
        this.f2749c = str2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Poi)) {
            return false;
        }
        Poi poi = (Poi) obj;
        return poi.getName().equals(this.f2747a) && poi.getCoordinate().equals(this.f2748b) && poi.getPoiId().equals(this.f2749c);
    }

    public LatLng getCoordinate() {
        return this.f2748b;
    }

    public String getName() {
        return this.f2747a;
    }

    public String getPoiId() {
        return this.f2749c;
    }

    public String toString() {
        return "poiid " + this.f2749c + " name:" + this.f2747a + "  coordinate:" + this.f2748b.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2747a);
        parcel.writeParcelable(this.f2748b, i);
        parcel.writeString(this.f2749c);
    }
}
