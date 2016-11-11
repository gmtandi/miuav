package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class WalkStep implements Parcelable {
    public static final Creator<WalkStep> CREATOR;
    private String f3486a;
    private String f3487b;
    private String f3488c;
    private float f3489d;
    private float f3490e;
    private List<LatLonPoint> f3491f;
    private String f3492g;
    private String f3493h;

    static {
        CREATOR = new C0552u();
    }

    public WalkStep() {
        this.f3491f = new ArrayList();
    }

    public WalkStep(Parcel parcel) {
        this.f3491f = new ArrayList();
        this.f3486a = parcel.readString();
        this.f3487b = parcel.readString();
        this.f3488c = parcel.readString();
        this.f3489d = parcel.readFloat();
        this.f3490e = parcel.readFloat();
        this.f3491f = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f3492g = parcel.readString();
        this.f3493h = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getAction() {
        return this.f3492g;
    }

    public String getAssistantAction() {
        return this.f3493h;
    }

    public float getDistance() {
        return this.f3489d;
    }

    public float getDuration() {
        return this.f3490e;
    }

    public String getInstruction() {
        return this.f3486a;
    }

    public String getOrientation() {
        return this.f3487b;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f3491f;
    }

    public String getRoad() {
        return this.f3488c;
    }

    public void setAction(String str) {
        this.f3492g = str;
    }

    public void setAssistantAction(String str) {
        this.f3493h = str;
    }

    public void setDistance(float f) {
        this.f3489d = f;
    }

    public void setDuration(float f) {
        this.f3490e = f;
    }

    public void setInstruction(String str) {
        this.f3486a = str;
    }

    public void setOrientation(String str) {
        this.f3487b = str;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f3491f = list;
    }

    public void setRoad(String str) {
        this.f3488c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3486a);
        parcel.writeString(this.f3487b);
        parcel.writeString(this.f3488c);
        parcel.writeFloat(this.f3489d);
        parcel.writeFloat(this.f3490e);
        parcel.writeTypedList(this.f3491f);
        parcel.writeString(this.f3492g);
        parcel.writeString(this.f3493h);
    }
}
