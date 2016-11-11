package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.p122a.p123a.C2915a;

public class PoiItem implements Parcelable {
    public static final Creator<PoiItem> CREATOR;
    private String f2929a;
    private String f2930b;
    private String f2931c;
    private String f2932d;
    private String f2933e;
    private int f2934f;
    private LatLonPoint f2935g;
    private LatLonPoint f2936h;
    private String f2937i;
    private String f2938j;
    private String f2939k;
    private boolean f2940l;
    private boolean f2941m;
    protected final LatLonPoint mPoint;
    protected final String mSnippet;
    protected final String mTitle;
    private String f2942n;
    private String f2943o;
    private String f2944p;
    private String f2945q;
    private boolean f2946r;
    private String f2947s;

    static {
        CREATOR = new C0491q();
    }

    protected PoiItem(Parcel parcel) {
        this.f2933e = C2915a.f14760f;
        this.f2934f = -1;
        this.f2929a = parcel.readString();
        this.f2931c = parcel.readString();
        this.f2930b = parcel.readString();
        this.f2933e = parcel.readString();
        this.f2934f = parcel.readInt();
        this.mPoint = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mSnippet = parcel.readString();
        this.f2932d = parcel.readString();
        this.f2935g = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f2936h = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f2937i = parcel.readString();
        this.f2938j = parcel.readString();
        this.f2939k = parcel.readString();
        boolean[] zArr = new boolean[3];
        parcel.readBooleanArray(zArr);
        this.f2940l = zArr[0];
        this.f2941m = zArr[1];
        this.f2946r = zArr[2];
        this.f2942n = parcel.readString();
        this.f2943o = parcel.readString();
        this.f2944p = parcel.readString();
        this.f2945q = parcel.readString();
        this.f2947s = parcel.readString();
    }

    public PoiItem(String str, LatLonPoint latLonPoint, String str2, String str3) {
        this.f2933e = C2915a.f14760f;
        this.f2934f = -1;
        this.f2929a = str;
        this.mPoint = latLonPoint;
        this.mTitle = str2;
        this.mSnippet = str3;
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
        PoiItem poiItem = (PoiItem) obj;
        return this.f2929a == null ? poiItem.f2929a == null : this.f2929a.equals(poiItem.f2929a);
    }

    public String getAdCode() {
        return this.f2931c;
    }

    public String getAdName() {
        return this.f2945q;
    }

    public String getCityCode() {
        return this.f2932d;
    }

    public String getCityName() {
        return this.f2944p;
    }

    public String getDirection() {
        return this.f2942n;
    }

    public int getDistance() {
        return this.f2934f;
    }

    public String getEmail() {
        return this.f2939k;
    }

    public LatLonPoint getEnter() {
        return this.f2935g;
    }

    public LatLonPoint getExit() {
        return this.f2936h;
    }

    public LatLonPoint getLatLonPoint() {
        return this.mPoint;
    }

    public String getPoiId() {
        return this.f2929a;
    }

    public String getPostcode() {
        return this.f2938j;
    }

    public String getProvinceCode() {
        return this.f2947s;
    }

    public String getProvinceName() {
        return this.f2943o;
    }

    public String getSnippet() {
        return this.mSnippet;
    }

    public String getTel() {
        return this.f2930b;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTypeDes() {
        return this.f2933e;
    }

    public String getWebsite() {
        return this.f2937i;
    }

    public int hashCode() {
        return (this.f2929a == null ? 0 : this.f2929a.hashCode()) + 31;
    }

    public boolean isDiscountInfo() {
        return this.f2941m;
    }

    public boolean isGroupbuyInfo() {
        return this.f2940l;
    }

    public boolean isIndoorMap() {
        return this.f2946r;
    }

    public void setAdCode(String str) {
        this.f2931c = str;
    }

    public void setAdName(String str) {
        this.f2945q = str;
    }

    public void setCityCode(String str) {
        this.f2932d = str;
    }

    public void setCityName(String str) {
        this.f2944p = str;
    }

    public void setDirection(String str) {
        this.f2942n = str;
    }

    public void setDiscountInfo(boolean z) {
        this.f2941m = z;
    }

    public void setDistance(int i) {
        this.f2934f = i;
    }

    public void setEmail(String str) {
        this.f2939k = str;
    }

    public void setEnter(LatLonPoint latLonPoint) {
        this.f2935g = latLonPoint;
    }

    public void setExit(LatLonPoint latLonPoint) {
        this.f2936h = latLonPoint;
    }

    public void setGroupbuyInfo(boolean z) {
        this.f2940l = z;
    }

    public void setIndoorMap(boolean z) {
        this.f2946r = z;
    }

    public void setPostcode(String str) {
        this.f2938j = str;
    }

    public void setProvinceCode(String str) {
        this.f2947s = str;
    }

    public void setProvinceName(String str) {
        this.f2943o = str;
    }

    public void setTel(String str) {
        this.f2930b = str;
    }

    public void setTypeDes(String str) {
        this.f2933e = str;
    }

    public void setWebsite(String str) {
        this.f2937i = str;
    }

    public String toString() {
        return this.mTitle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2929a);
        parcel.writeString(this.f2931c);
        parcel.writeString(this.f2930b);
        parcel.writeString(this.f2933e);
        parcel.writeInt(this.f2934f);
        parcel.writeValue(this.mPoint);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSnippet);
        parcel.writeString(this.f2932d);
        parcel.writeValue(this.f2935g);
        parcel.writeValue(this.f2936h);
        parcel.writeString(this.f2937i);
        parcel.writeString(this.f2938j);
        parcel.writeString(this.f2939k);
        parcel.writeBooleanArray(new boolean[]{this.f2940l, this.f2941m, this.f2946r});
        parcel.writeString(this.f2942n);
        parcel.writeString(this.f2943o);
        parcel.writeString(this.f2944p);
        parcel.writeString(this.f2945q);
        parcel.writeString(this.f2947s);
    }
}
