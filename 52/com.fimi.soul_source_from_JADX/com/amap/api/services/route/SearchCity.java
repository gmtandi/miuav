package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SearchCity implements Parcelable {
    public static final Creator<SearchCity> CREATOR;
    private String f3480a;
    private String f3481b;
    private String f3482c;

    static {
        CREATOR = new C0549r();
    }

    public SearchCity(Parcel parcel) {
        this.f3480a = parcel.readString();
        this.f3481b = parcel.readString();
        this.f3482c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getSearchCityAdCode() {
        return this.f3482c;
    }

    public String getSearchCityName() {
        return this.f3480a;
    }

    public String getSearchCitycode() {
        return this.f3481b;
    }

    public void setSearchCityName(String str) {
        this.f3480a = str;
    }

    public void setSearchCitycode(String str) {
        this.f3481b = str;
    }

    public void setSearchCityhAdCode(String str) {
        this.f3482c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3480a);
        parcel.writeString(this.f3481b);
        parcel.writeString(this.f3482c);
    }
}
