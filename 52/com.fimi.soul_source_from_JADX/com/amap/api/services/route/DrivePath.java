package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class DrivePath extends Path implements Parcelable {
    public static final Creator<DrivePath> CREATOR;
    private String f3428a;
    private float f3429b;
    private float f3430c;
    private List<DriveStep> f3431d;

    static {
        CREATOR = new C0537f();
    }

    public DrivePath() {
        this.f3431d = new ArrayList();
    }

    public DrivePath(Parcel parcel) {
        super(parcel);
        this.f3431d = new ArrayList();
        this.f3428a = parcel.readString();
        this.f3429b = parcel.readFloat();
        this.f3430c = parcel.readFloat();
        this.f3431d = parcel.createTypedArrayList(DriveStep.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public List<DriveStep> getSteps() {
        return this.f3431d;
    }

    public String getStrategy() {
        return this.f3428a;
    }

    public float getTollDistance() {
        return this.f3430c;
    }

    public float getTolls() {
        return this.f3429b;
    }

    public void setSteps(List<DriveStep> list) {
        this.f3431d = list;
    }

    public void setStrategy(String str) {
        this.f3428a = str;
    }

    public void setTollDistance(float f) {
        this.f3430c = f;
    }

    public void setTolls(float f) {
        this.f3429b = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f3428a);
        parcel.writeFloat(this.f3429b);
        parcel.writeFloat(this.f3430c);
        parcel.writeTypedList(this.f3431d);
    }
}
