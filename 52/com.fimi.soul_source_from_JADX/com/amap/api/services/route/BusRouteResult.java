package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class BusRouteResult extends RouteResult implements Parcelable {
    public static final Creator<BusRouteResult> CREATOR;
    private float f3417a;
    private List<BusPath> f3418b;
    private BusRouteQuery f3419c;

    static {
        CREATOR = new C0533b();
    }

    public BusRouteResult() {
        this.f3418b = new ArrayList();
    }

    public BusRouteResult(Parcel parcel) {
        super(parcel);
        this.f3418b = new ArrayList();
        this.f3417a = parcel.readFloat();
        this.f3418b = parcel.createTypedArrayList(BusPath.CREATOR);
        this.f3419c = (BusRouteQuery) parcel.readParcelable(BusRouteQuery.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public BusRouteQuery getBusQuery() {
        return this.f3419c;
    }

    public List<BusPath> getPaths() {
        return this.f3418b;
    }

    public float getTaxiCost() {
        return this.f3417a;
    }

    public void setBusQuery(BusRouteQuery busRouteQuery) {
        this.f3419c = busRouteQuery;
    }

    public void setPaths(List<BusPath> list) {
        this.f3418b = list;
    }

    public void setTaxiCost(float f) {
        this.f3417a = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f3417a);
        parcel.writeTypedList(this.f3418b);
        parcel.writeParcelable(this.f3419c, i);
    }
}
