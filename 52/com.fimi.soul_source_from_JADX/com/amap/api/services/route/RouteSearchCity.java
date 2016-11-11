package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class RouteSearchCity extends SearchCity implements Parcelable {
    public static final Creator<RouteSearchCity> CREATOR;
    List<District> f3483a;

    static {
        CREATOR = new C0548q();
    }

    public RouteSearchCity() {
        this.f3483a = new ArrayList();
    }

    public RouteSearchCity(Parcel parcel) {
        super(parcel);
        this.f3483a = new ArrayList();
        this.f3483a = parcel.createTypedArrayList(District.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public List<District> getDistricts() {
        return this.f3483a;
    }

    public void setDistricts(List<District> list) {
        this.f3483a = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3483a);
    }
}
