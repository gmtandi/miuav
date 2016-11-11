package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStationItem implements Parcelable {
    public static final Creator<BusStationItem> CREATOR;
    private String f2902a;
    private String f2903b;
    private LatLonPoint f2904c;
    private String f2905d;
    private String f2906e;
    private List<BusLineItem> f2907f;

    static {
        CREATOR = new C0448b();
    }

    public BusStationItem() {
        this.f2907f = new ArrayList();
    }

    private BusStationItem(Parcel parcel) {
        this.f2907f = new ArrayList();
        this.f2903b = parcel.readString();
        this.f2902a = parcel.readString();
        this.f2904c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f2905d = parcel.readString();
        this.f2906e = parcel.readString();
        this.f2907f = parcel.readArrayList(BusLineItem.class.getClassLoader());
    }

    private String m4430a(List<BusLineItem> list) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                stringBuffer.append(((BusLineItem) list.get(i)).getBusLineName());
                if (i < list.size() - 1) {
                    stringBuffer.append("|");
                }
            }
        }
        return stringBuffer.toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BusStationItem busStationItem = (BusStationItem) obj;
        return this.f2902a == null ? busStationItem.f2902a == null : this.f2902a.equals(busStationItem.f2902a);
    }

    public String getAdCode() {
        return this.f2906e;
    }

    public List<BusLineItem> getBusLineItems() {
        return this.f2907f;
    }

    public String getBusStationId() {
        return this.f2902a;
    }

    public String getBusStationName() {
        return this.f2903b;
    }

    public String getCityCode() {
        return this.f2905d;
    }

    public LatLonPoint getLatLonPoint() {
        return this.f2904c;
    }

    public int hashCode() {
        return (this.f2902a == null ? 0 : this.f2902a.hashCode()) + 31;
    }

    public void setAdCode(String str) {
        this.f2906e = str;
    }

    public void setBusLineItems(List<BusLineItem> list) {
        this.f2907f = list;
    }

    public void setBusStationId(String str) {
        this.f2902a = str;
    }

    public void setBusStationName(String str) {
        this.f2903b = str;
    }

    public void setCityCode(String str) {
        this.f2905d = str;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.f2904c = latLonPoint;
    }

    public String toString() {
        return "BusStationName: " + this.f2903b + " LatLonPoint: " + this.f2904c.toString() + " BusLines: " + m4430a(this.f2907f) + " CityCode: " + this.f2905d + " AdCode: " + this.f2906e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2903b);
        parcel.writeString(this.f2902a);
        parcel.writeValue(this.f2904c);
        parcel.writeString(this.f2905d);
        parcel.writeString(this.f2906e);
        parcel.writeList(this.f2907f);
    }
}
