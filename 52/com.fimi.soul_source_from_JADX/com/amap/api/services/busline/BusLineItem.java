package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C0471d;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class BusLineItem implements Parcelable {
    public static final Creator<BusLineItem> CREATOR;
    private float f2868a;
    private String f2869b;
    private String f2870c;
    private String f2871d;
    private List<LatLonPoint> f2872e;
    private List<LatLonPoint> f2873f;
    private String f2874g;
    private String f2875h;
    private String f2876i;
    private Date f2877j;
    private Date f2878k;
    private String f2879l;
    private float f2880m;
    private float f2881n;
    private List<BusStationItem> f2882o;

    static {
        CREATOR = new C0447a();
    }

    public BusLineItem() {
        this.f2872e = new ArrayList();
        this.f2873f = new ArrayList();
        this.f2882o = new ArrayList();
    }

    public BusLineItem(Parcel parcel) {
        this.f2872e = new ArrayList();
        this.f2873f = new ArrayList();
        this.f2882o = new ArrayList();
        this.f2868a = parcel.readFloat();
        this.f2869b = parcel.readString();
        this.f2870c = parcel.readString();
        this.f2871d = parcel.readString();
        this.f2872e = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f2873f = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f2874g = parcel.readString();
        this.f2875h = parcel.readString();
        this.f2876i = parcel.readString();
        this.f2877j = C0471d.m4767e(parcel.readString());
        this.f2878k = C0471d.m4767e(parcel.readString());
        this.f2879l = parcel.readString();
        this.f2880m = parcel.readFloat();
        this.f2881n = parcel.readFloat();
        this.f2882o = parcel.readArrayList(BusStationItem.class.getClassLoader());
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
        BusLineItem busLineItem = (BusLineItem) obj;
        return this.f2874g == null ? busLineItem.f2874g == null : this.f2874g.equals(busLineItem.f2874g);
    }

    public float getBasicPrice() {
        return this.f2880m;
    }

    public List<LatLonPoint> getBounds() {
        return this.f2873f;
    }

    public String getBusCompany() {
        return this.f2879l;
    }

    public String getBusLineId() {
        return this.f2874g;
    }

    public String getBusLineName() {
        return this.f2869b;
    }

    public String getBusLineType() {
        return this.f2870c;
    }

    public List<BusStationItem> getBusStations() {
        return this.f2882o;
    }

    public String getCityCode() {
        return this.f2871d;
    }

    public List<LatLonPoint> getDirectionsCoordinates() {
        return this.f2872e;
    }

    public float getDistance() {
        return this.f2868a;
    }

    public Date getFirstBusTime() {
        return this.f2877j == null ? null : (Date) this.f2877j.clone();
    }

    public Date getLastBusTime() {
        return this.f2878k == null ? null : (Date) this.f2878k.clone();
    }

    public String getOriginatingStation() {
        return this.f2875h;
    }

    public String getTerminalStation() {
        return this.f2876i;
    }

    public float getTotalPrice() {
        return this.f2881n;
    }

    public int hashCode() {
        return (this.f2874g == null ? 0 : this.f2874g.hashCode()) + 31;
    }

    public void setBasicPrice(float f) {
        this.f2880m = f;
    }

    public void setBounds(List<LatLonPoint> list) {
        this.f2873f = list;
    }

    public void setBusCompany(String str) {
        this.f2879l = str;
    }

    public void setBusLineId(String str) {
        this.f2874g = str;
    }

    public void setBusLineName(String str) {
        this.f2869b = str;
    }

    public void setBusLineType(String str) {
        this.f2870c = str;
    }

    public void setBusStations(List<BusStationItem> list) {
        this.f2882o = list;
    }

    public void setCityCode(String str) {
        this.f2871d = str;
    }

    public void setDirectionsCoordinates(List<LatLonPoint> list) {
        this.f2872e = list;
    }

    public void setDistance(float f) {
        this.f2868a = f;
    }

    public void setFirstBusTime(Date date) {
        if (date == null) {
            this.f2877j = null;
        } else {
            this.f2877j = (Date) date.clone();
        }
    }

    public void setLastBusTime(Date date) {
        if (date == null) {
            this.f2878k = null;
        } else {
            this.f2878k = (Date) date.clone();
        }
    }

    public void setOriginatingStation(String str) {
        this.f2875h = str;
    }

    public void setTerminalStation(String str) {
        this.f2876i = str;
    }

    public void setTotalPrice(float f) {
        this.f2881n = f;
    }

    public String toString() {
        return this.f2869b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + C0471d.m4760a(this.f2877j) + "-" + C0471d.m4760a(this.f2878k);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f2868a);
        parcel.writeString(this.f2869b);
        parcel.writeString(this.f2870c);
        parcel.writeString(this.f2871d);
        parcel.writeList(this.f2872e);
        parcel.writeList(this.f2873f);
        parcel.writeString(this.f2874g);
        parcel.writeString(this.f2875h);
        parcel.writeString(this.f2876i);
        parcel.writeString(C0471d.m4760a(this.f2877j));
        parcel.writeString(C0471d.m4760a(this.f2878k));
        parcel.writeString(this.f2879l);
        parcel.writeFloat(this.f2880m);
        parcel.writeFloat(this.f2881n);
        parcel.writeList(this.f2882o);
    }
}
