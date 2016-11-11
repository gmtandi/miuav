package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WalkPath extends Path implements Parcelable {
    public static final Creator<WalkPath> CREATOR;
    private List<WalkStep> f3453a;

    static {
        CREATOR = new C0550s();
    }

    public WalkPath() {
        this.f3453a = new ArrayList();
    }

    public WalkPath(Parcel parcel) {
        super(parcel);
        this.f3453a = new ArrayList();
        this.f3453a = parcel.createTypedArrayList(WalkStep.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkStep> getSteps() {
        return this.f3453a;
    }

    public void setSteps(List<WalkStep> list) {
        this.f3453a = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.f3453a);
    }
}
