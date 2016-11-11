package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.ArrayList;
import java.util.List;

public final class RegeocodeAddress implements Parcelable {
    public static final Creator<RegeocodeAddress> CREATOR;
    private String f3235a;
    private String f3236b;
    private String f3237c;
    private String f3238d;
    private String f3239e;
    private String f3240f;
    private String f3241g;
    private StreetNumber f3242h;
    private String f3243i;
    private String f3244j;
    private List<RegeocodeRoad> f3245k;
    private List<Crossroad> f3246l;
    private List<PoiItem> f3247m;
    private List<BusinessArea> f3248n;

    static {
        CREATOR = new C0509c();
    }

    public RegeocodeAddress() {
        this.f3245k = new ArrayList();
        this.f3246l = new ArrayList();
        this.f3247m = new ArrayList();
        this.f3248n = new ArrayList();
    }

    private RegeocodeAddress(Parcel parcel) {
        this.f3245k = new ArrayList();
        this.f3246l = new ArrayList();
        this.f3247m = new ArrayList();
        this.f3248n = new ArrayList();
        this.f3235a = parcel.readString();
        this.f3236b = parcel.readString();
        this.f3237c = parcel.readString();
        this.f3238d = parcel.readString();
        this.f3239e = parcel.readString();
        this.f3240f = parcel.readString();
        this.f3241g = parcel.readString();
        this.f3242h = (StreetNumber) parcel.readValue(StreetNumber.class.getClassLoader());
        this.f3245k = parcel.readArrayList(Road.class.getClassLoader());
        this.f3246l = parcel.readArrayList(Crossroad.class.getClassLoader());
        this.f3247m = parcel.readArrayList(PoiItem.class.getClassLoader());
        this.f3243i = parcel.readString();
        this.f3244j = parcel.readString();
        this.f3248n = parcel.readArrayList(BusinessArea.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public String getAdCode() {
        return this.f3244j;
    }

    public String getBuilding() {
        return this.f3241g;
    }

    public List<BusinessArea> getBusinessAreas() {
        return this.f3248n;
    }

    public String getCity() {
        return this.f3237c;
    }

    public String getCityCode() {
        return this.f3243i;
    }

    public List<Crossroad> getCrossroads() {
        return this.f3246l;
    }

    public String getDistrict() {
        return this.f3238d;
    }

    public String getFormatAddress() {
        return this.f3235a;
    }

    public String getNeighborhood() {
        return this.f3240f;
    }

    public List<PoiItem> getPois() {
        return this.f3247m;
    }

    public String getProvince() {
        return this.f3236b;
    }

    public List<RegeocodeRoad> getRoads() {
        return this.f3245k;
    }

    public StreetNumber getStreetNumber() {
        return this.f3242h;
    }

    public String getTownship() {
        return this.f3239e;
    }

    public void setAdCode(String str) {
        this.f3244j = str;
    }

    public void setBuilding(String str) {
        this.f3241g = str;
    }

    public void setBusinessAreas(List<BusinessArea> list) {
        this.f3248n = list;
    }

    public void setCity(String str) {
        this.f3237c = str;
    }

    public void setCityCode(String str) {
        this.f3243i = str;
    }

    public void setCrossroads(List<Crossroad> list) {
        this.f3246l = list;
    }

    public void setDistrict(String str) {
        this.f3238d = str;
    }

    public void setFormatAddress(String str) {
        this.f3235a = str;
    }

    public void setNeighborhood(String str) {
        this.f3240f = str;
    }

    public void setPois(List<PoiItem> list) {
        this.f3247m = list;
    }

    public void setProvince(String str) {
        this.f3236b = str;
    }

    public void setRoads(List<RegeocodeRoad> list) {
        this.f3245k = list;
    }

    public void setStreetNumber(StreetNumber streetNumber) {
        this.f3242h = streetNumber;
    }

    public void setTownship(String str) {
        this.f3239e = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3235a);
        parcel.writeString(this.f3236b);
        parcel.writeString(this.f3237c);
        parcel.writeString(this.f3238d);
        parcel.writeString(this.f3239e);
        parcel.writeString(this.f3240f);
        parcel.writeString(this.f3241g);
        parcel.writeValue(this.f3242h);
        parcel.writeList(this.f3245k);
        parcel.writeList(this.f3246l);
        parcel.writeList(this.f3247m);
        parcel.writeString(this.f3243i);
        parcel.writeString(this.f3244j);
        parcel.writeList(this.f3248n);
    }
}
