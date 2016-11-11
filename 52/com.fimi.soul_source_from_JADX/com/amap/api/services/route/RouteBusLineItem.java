package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Creator<RouteBusLineItem> CREATOR;
    private BusStationItem f3447a;
    private BusStationItem f3448b;
    private List<LatLonPoint> f3449c;
    private int f3450d;
    private List<BusStationItem> f3451e;
    private float f3452f;

    static {
        CREATOR = new C0541j();
    }

    public RouteBusLineItem() {
        this.f3449c = new ArrayList();
        this.f3451e = new ArrayList();
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.f3449c = new ArrayList();
        this.f3451e = new ArrayList();
        this.f3447a = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.f3448b = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.f3449c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f3450d = parcel.readInt();
        this.f3451e = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.f3452f = parcel.readFloat();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
        if (this.f3448b == null) {
            if (routeBusLineItem.f3448b != null) {
                return false;
            }
        } else if (!this.f3448b.equals(routeBusLineItem.f3448b)) {
            return false;
        }
        return this.f3447a == null ? routeBusLineItem.f3447a == null : this.f3447a.equals(routeBusLineItem.f3447a);
    }

    public BusStationItem getArrivalBusStation() {
        return this.f3448b;
    }

    public BusStationItem getDepartureBusStation() {
        return this.f3447a;
    }

    public float getDuration() {
        return this.f3452f;
    }

    public int getPassStationNum() {
        return this.f3450d;
    }

    public List<BusStationItem> getPassStations() {
        return this.f3451e;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f3449c;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3448b == null ? 0 : this.f3448b.hashCode()) + (super.hashCode() * 31)) * 31;
        if (this.f3447a != null) {
            i = this.f3447a.hashCode();
        }
        return hashCode + i;
    }

    public void setArrivalBusStation(BusStationItem busStationItem) {
        this.f3448b = busStationItem;
    }

    public void setDepartureBusStation(BusStationItem busStationItem) {
        this.f3447a = busStationItem;
    }

    public void setDuration(float f) {
        this.f3452f = f;
    }

    public void setPassStationNum(int i) {
        this.f3450d = i;
    }

    public void setPassStations(List<BusStationItem> list) {
        this.f3451e = list;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f3449c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f3447a, i);
        parcel.writeParcelable(this.f3448b, i);
        parcel.writeTypedList(this.f3449c);
        parcel.writeInt(this.f3450d);
        parcel.writeTypedList(this.f3451e);
        parcel.writeFloat(this.f3452f);
    }
}
