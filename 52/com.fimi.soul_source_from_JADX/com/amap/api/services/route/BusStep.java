package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusStep implements Parcelable {
    public static final Creator<BusStep> CREATOR;
    private RouteBusWalkItem f3420a;
    private List<RouteBusLineItem> f3421b;
    private Doorway f3422c;
    private Doorway f3423d;

    static {
        CREATOR = new C0534c();
    }

    public BusStep() {
        this.f3421b = new ArrayList();
    }

    public BusStep(Parcel parcel) {
        this.f3421b = new ArrayList();
        this.f3420a = (RouteBusWalkItem) parcel.readParcelable(RouteBusWalkItem.class.getClassLoader());
        this.f3421b = parcel.createTypedArrayList(RouteBusLineItem.CREATOR);
        this.f3422c = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
        this.f3423d = (Doorway) parcel.readParcelable(Doorway.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public RouteBusLineItem getBusLine() {
        return (this.f3421b == null || this.f3421b.size() == 0) ? null : (RouteBusLineItem) this.f3421b.get(0);
    }

    public Doorway getEntrance() {
        return this.f3422c;
    }

    public Doorway getExit() {
        return this.f3423d;
    }

    public RouteBusWalkItem getWalk() {
        return this.f3420a;
    }

    public void setBusLine(RouteBusLineItem routeBusLineItem) {
        if (this.f3421b != null) {
            if (this.f3421b.size() == 0) {
                this.f3421b.add(routeBusLineItem);
            }
            this.f3421b.set(0, routeBusLineItem);
        }
    }

    public void setBusLines(List<RouteBusLineItem> list) {
        this.f3421b = list;
    }

    public void setEntrance(Doorway doorway) {
        this.f3422c = doorway;
    }

    public void setExit(Doorway doorway) {
        this.f3423d = doorway;
    }

    public void setWalk(RouteBusWalkItem routeBusWalkItem) {
        this.f3420a = routeBusWalkItem;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3420a, i);
        parcel.writeTypedList(this.f3421b);
        parcel.writeParcelable(this.f3422c, i);
        parcel.writeParcelable(this.f3423d, i);
    }
}
