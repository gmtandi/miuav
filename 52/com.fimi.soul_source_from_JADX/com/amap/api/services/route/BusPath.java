package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusPath extends Path implements Parcelable {
    public static final Creator<BusPath> CREATOR;
    private float f3410a;
    private boolean f3411b;
    private float f3412c;
    private float f3413d;
    private List<BusStep> f3414e;

    static {
        CREATOR = new C0532a();
    }

    public BusPath() {
        this.f3414e = new ArrayList();
    }

    public BusPath(Parcel parcel) {
        super(parcel);
        this.f3414e = new ArrayList();
        this.f3410a = parcel.readFloat();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f3411b = zArr[0];
        this.f3412c = parcel.readFloat();
        this.f3413d = parcel.readFloat();
        this.f3414e = parcel.createTypedArrayList(BusStep.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public float getBusDistance() {
        return this.f3413d;
    }

    public float getCost() {
        return this.f3410a;
    }

    public float getDistance() {
        return this.f3412c + this.f3413d;
    }

    public List<BusStep> getSteps() {
        return this.f3414e;
    }

    public float getWalkDistance() {
        return this.f3412c;
    }

    public boolean isNightBus() {
        return this.f3411b;
    }

    public void setBusDistance(float f) {
        this.f3413d = f;
    }

    public void setCost(float f) {
        this.f3410a = f;
    }

    public void setNightBus(boolean z) {
        this.f3411b = z;
    }

    public void setSteps(List<BusStep> list) {
        this.f3414e = list;
    }

    public void setWalkDistance(float f) {
        this.f3412c = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f3410a);
        parcel.writeBooleanArray(new boolean[]{this.f3411b});
        parcel.writeFloat(this.f3412c);
        parcel.writeFloat(this.f3413d);
        parcel.writeTypedList(this.f3414e);
    }
}
