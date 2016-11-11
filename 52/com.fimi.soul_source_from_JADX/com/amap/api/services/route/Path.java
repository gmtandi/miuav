package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Path implements Parcelable {
    public static final Creator<Path> CREATOR;
    private float f3408a;
    private long f3409b;

    static {
        CREATOR = new C0540i();
    }

    public Path(Parcel parcel) {
        this.f3408a = parcel.readFloat();
        this.f3409b = parcel.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public float getDistance() {
        return this.f3408a;
    }

    public long getDuration() {
        return this.f3409b;
    }

    public void setDistance(float f) {
        this.f3408a = f;
    }

    public void setDuration(long j) {
        this.f3409b = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f3408a);
        parcel.writeLong(this.f3409b);
    }
}
