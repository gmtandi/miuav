package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import java.util.ArrayList;
import java.util.List;

public class DriveRouteResult extends RouteResult implements Parcelable {
    public static final Creator<DriveRouteResult> CREATOR;
    private float f3432a;
    private List<DrivePath> f3433b;
    private DriveRouteQuery f3434c;

    static {
        CREATOR = new C0538g();
    }

    public DriveRouteResult() {
        this.f3433b = new ArrayList();
    }

    public DriveRouteResult(Parcel parcel) {
        super(parcel);
        this.f3433b = new ArrayList();
        this.f3432a = parcel.readFloat();
        this.f3433b = parcel.createTypedArrayList(DrivePath.CREATOR);
        this.f3434c = (DriveRouteQuery) parcel.readParcelable(DriveRouteQuery.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public DriveRouteQuery getDriveQuery() {
        return this.f3434c;
    }

    public List<DrivePath> getPaths() {
        return this.f3433b;
    }

    public float getTaxiCost() {
        return this.f3432a;
    }

    public void setDriveQuery(DriveRouteQuery driveRouteQuery) {
        this.f3434c = driveRouteQuery;
    }

    public void setPaths(List<DrivePath> list) {
        this.f3433b = list;
    }

    public void setTaxiCost(float f) {
        this.f3432a = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f3432a);
        parcel.writeTypedList(this.f3433b);
        parcel.writeParcelable(this.f3434c, i);
    }
}
