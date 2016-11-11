package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class WalkRouteResult extends RouteResult implements Parcelable {
    public static final Creator<WalkRouteResult> CREATOR;
    private List<WalkPath> f3484a;
    private WalkRouteQuery f3485b;

    static {
        CREATOR = new C0551t();
    }

    public WalkRouteResult() {
        this.f3484a = new ArrayList();
    }

    public WalkRouteResult(Parcel parcel) {
        super(parcel);
        this.f3484a = new ArrayList();
        this.f3484a = parcel.createTypedArrayList(WalkPath.CREATOR);
        this.f3485b = (WalkRouteQuery) parcel.readParcelable(WalkRouteQuery.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkPath> getPaths() {
        return this.f3484a;
    }

    public WalkRouteQuery getWalkQuery() {
        return this.f3485b;
    }

    public void setPaths(List<WalkPath> list) {
        this.f3484a = list;
    }

    public void setWalkQuery(WalkRouteQuery walkRouteQuery) {
        this.f3485b = walkRouteQuery;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3484a);
        parcel.writeParcelable(this.f3485b, i);
    }
}
