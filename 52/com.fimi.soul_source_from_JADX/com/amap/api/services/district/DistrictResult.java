package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

public final class DistrictResult implements Parcelable {
    public Creator<DistrictResult> CREATOR;
    private DistrictSearchQuery f3193a;
    private ArrayList<DistrictItem> f3194b;
    private int f3195c;
    private AMapException f3196d;

    public DistrictResult() {
        this.f3194b = new ArrayList();
        this.CREATOR = new C0503b(this);
    }

    protected DistrictResult(Parcel parcel) {
        this.f3194b = new ArrayList();
        this.CREATOR = new C0503b(this);
        this.f3193a = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.f3194b = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public DistrictResult(DistrictSearchQuery districtSearchQuery, ArrayList<DistrictItem> arrayList) {
        this.f3194b = new ArrayList();
        this.CREATOR = new C0503b(this);
        this.f3193a = districtSearchQuery;
        this.f3194b = arrayList;
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
        DistrictResult districtResult = (DistrictResult) obj;
        if (this.f3193a == null) {
            if (districtResult.f3193a != null) {
                return false;
            }
        } else if (!this.f3193a.equals(districtResult.f3193a)) {
            return false;
        }
        return this.f3194b == null ? districtResult.f3194b == null : this.f3194b.equals(districtResult.f3194b);
    }

    public AMapException getAMapException() {
        return this.f3196d;
    }

    public ArrayList<DistrictItem> getDistrict() {
        return this.f3194b;
    }

    public int getPageCount() {
        return this.f3195c;
    }

    public DistrictSearchQuery getQuery() {
        return this.f3193a;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3193a == null ? 0 : this.f3193a.hashCode()) + 31) * 31;
        if (this.f3194b != null) {
            i = this.f3194b.hashCode();
        }
        return hashCode + i;
    }

    public void setAMapException(AMapException aMapException) {
        this.f3196d = aMapException;
    }

    public void setDistrict(ArrayList<DistrictItem> arrayList) {
        this.f3194b = arrayList;
    }

    public void setPageCount(int i) {
        this.f3195c = i;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f3193a = districtSearchQuery;
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.f3193a + ", mDistricts=" + this.f3194b + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3193a, i);
        parcel.writeTypedList(this.f3194b);
    }
}
