package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Photo implements Parcelable {
    public static final Creator<Photo> CREATOR;
    private String f3336a;
    private String f3337b;

    static {
        CREATOR = new C0521f();
    }

    public Photo(Parcel parcel) {
        this.f3336a = parcel.readString();
        this.f3337b = parcel.readString();
    }

    public Photo(String str, String str2) {
        this.f3336a = str;
        this.f3337b = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return this.f3336a;
    }

    public String getUrl() {
        return this.f3337b;
    }

    public void setTitle(String str) {
        this.f3336a = str;
    }

    public void setUrl(String str) {
        this.f3337b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3336a);
        parcel.writeString(this.f3337b);
    }
}
