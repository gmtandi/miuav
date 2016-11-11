package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class DriveStep implements Parcelable {
    public static final Creator<DriveStep> CREATOR;
    private String f3435a;
    private String f3436b;
    private String f3437c;
    private float f3438d;
    private float f3439e;
    private float f3440f;
    private String f3441g;
    private float f3442h;
    private List<LatLonPoint> f3443i;
    private String f3444j;
    private String f3445k;
    private List<RouteSearchCity> f3446l;

    static {
        CREATOR = new C0539h();
    }

    public DriveStep() {
        this.f3443i = new ArrayList();
        this.f3446l = new ArrayList();
    }

    public DriveStep(Parcel parcel) {
        this.f3443i = new ArrayList();
        this.f3446l = new ArrayList();
        this.f3435a = parcel.readString();
        this.f3436b = parcel.readString();
        this.f3437c = parcel.readString();
        this.f3438d = parcel.readFloat();
        this.f3439e = parcel.readFloat();
        this.f3440f = parcel.readFloat();
        this.f3441g = parcel.readString();
        this.f3442h = parcel.readFloat();
        this.f3443i = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.f3444j = parcel.readString();
        this.f3445k = parcel.readString();
        this.f3446l = parcel.createTypedArrayList(RouteSearchCity.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public String getAction() {
        return this.f3444j;
    }

    public String getAssistantAction() {
        return this.f3445k;
    }

    public float getDistance() {
        return this.f3438d;
    }

    public float getDuration() {
        return this.f3442h;
    }

    public String getInstruction() {
        return this.f3435a;
    }

    public String getOrientation() {
        return this.f3436b;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f3443i;
    }

    public String getRoad() {
        return this.f3437c;
    }

    public List<RouteSearchCity> getRouteSearchCityList() {
        return this.f3446l;
    }

    public float getTollDistance() {
        return this.f3440f;
    }

    public String getTollRoad() {
        return this.f3441g;
    }

    public float getTolls() {
        return this.f3439e;
    }

    public void setAction(String str) {
        this.f3444j = str;
    }

    public void setAssistantAction(String str) {
        this.f3445k = str;
    }

    public void setDistance(float f) {
        this.f3438d = f;
    }

    public void setDuration(float f) {
        this.f3442h = f;
    }

    public void setInstruction(String str) {
        this.f3435a = str;
    }

    public void setOrientation(String str) {
        this.f3436b = str;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f3443i = list;
    }

    public void setRoad(String str) {
        this.f3437c = str;
    }

    public void setRouteSearchCityList(List<RouteSearchCity> list) {
        this.f3446l = list;
    }

    public void setTollDistance(float f) {
        this.f3440f = f;
    }

    public void setTollRoad(String str) {
        this.f3441g = str;
    }

    public void setTolls(float f) {
        this.f3439e = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3435a);
        parcel.writeString(this.f3436b);
        parcel.writeString(this.f3437c);
        parcel.writeFloat(this.f3438d);
        parcel.writeFloat(this.f3439e);
        parcel.writeFloat(this.f3440f);
        parcel.writeString(this.f3441g);
        parcel.writeFloat(this.f3442h);
        parcel.writeTypedList(this.f3443i);
        parcel.writeString(this.f3444j);
        parcel.writeString(this.f3445k);
        parcel.writeTypedList(this.f3446l);
    }
}
