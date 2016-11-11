package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DistrictItem implements Parcelable {
    public static final Creator<DistrictItem> CREATOR;
    private String f3186a;
    private String f3187b;
    private String f3188c;
    private LatLonPoint f3189d;
    private String f3190e;
    private List<DistrictItem> f3191f;
    private String[] f3192g;

    static {
        CREATOR = new C0502a();
    }

    public DistrictItem() {
        this.f3191f = new ArrayList();
        this.f3192g = new String[0];
    }

    public DistrictItem(Parcel parcel) {
        this.f3191f = new ArrayList();
        this.f3192g = new String[0];
        this.f3186a = parcel.readString();
        this.f3187b = parcel.readString();
        this.f3188c = parcel.readString();
        this.f3189d = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.f3190e = parcel.readString();
        this.f3191f = parcel.createTypedArrayList(CREATOR);
        this.f3192g = new String[parcel.readInt()];
        parcel.readStringArray(this.f3192g);
    }

    public DistrictItem(String str, String str2, String str3, LatLonPoint latLonPoint, String str4) {
        this.f3191f = new ArrayList();
        this.f3192g = new String[0];
        this.f3188c = str;
        this.f3186a = str2;
        this.f3187b = str3;
        this.f3189d = latLonPoint;
        this.f3190e = str4;
    }

    public int describeContents() {
        return 0;
    }

    public String[] districtBoundary() {
        return this.f3192g;
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
        DistrictItem districtItem = (DistrictItem) obj;
        if (this.f3187b == null) {
            if (districtItem.f3187b != null) {
                return false;
            }
        } else if (!this.f3187b.equals(districtItem.f3187b)) {
            return false;
        }
        if (this.f3189d == null) {
            if (districtItem.f3189d != null) {
                return false;
            }
        } else if (!this.f3189d.equals(districtItem.f3189d)) {
            return false;
        }
        if (this.f3186a == null) {
            if (districtItem.f3186a != null) {
                return false;
            }
        } else if (!this.f3186a.equals(districtItem.f3186a)) {
            return false;
        }
        if (!Arrays.equals(this.f3192g, districtItem.f3192g)) {
            return false;
        }
        if (this.f3191f == null) {
            if (districtItem.f3191f != null) {
                return false;
            }
        } else if (!this.f3191f.equals(districtItem.f3191f)) {
            return false;
        }
        if (this.f3190e == null) {
            if (districtItem.f3190e != null) {
                return false;
            }
        } else if (!this.f3190e.equals(districtItem.f3190e)) {
            return false;
        }
        return this.f3188c == null ? districtItem.f3188c == null : this.f3188c.equals(districtItem.f3188c);
    }

    public String getAdcode() {
        return this.f3187b;
    }

    public LatLonPoint getCenter() {
        return this.f3189d;
    }

    public String getCitycode() {
        return this.f3186a;
    }

    public String getLevel() {
        return this.f3190e;
    }

    public String getName() {
        return this.f3188c;
    }

    public List<DistrictItem> getSubDistrict() {
        return this.f3191f;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3190e == null ? 0 : this.f3190e.hashCode()) + (((this.f3191f == null ? 0 : this.f3191f.hashCode()) + (((((this.f3186a == null ? 0 : this.f3186a.hashCode()) + (((this.f3189d == null ? 0 : this.f3189d.hashCode()) + (((this.f3187b == null ? 0 : this.f3187b.hashCode()) + 31) * 31)) * 31)) * 31) + Arrays.hashCode(this.f3192g)) * 31)) * 31)) * 31;
        if (this.f3188c != null) {
            i = this.f3188c.hashCode();
        }
        return hashCode + i;
    }

    public void setAdcode(String str) {
        this.f3187b = str;
    }

    public void setCenter(LatLonPoint latLonPoint) {
        this.f3189d = latLonPoint;
    }

    public void setCitycode(String str) {
        this.f3186a = str;
    }

    public void setDistrictBoundary(String[] strArr) {
        this.f3192g = strArr;
    }

    public void setLevel(String str) {
        this.f3190e = str;
    }

    public void setName(String str) {
        this.f3188c = str;
    }

    public void setSubDistrict(ArrayList<DistrictItem> arrayList) {
        this.f3191f = arrayList;
    }

    public String toString() {
        return "DistrictItem [mCitycode=" + this.f3186a + ", mAdcode=" + this.f3187b + ", mName=" + this.f3188c + ", mCenter=" + this.f3189d + ", mLevel=" + this.f3190e + ", mDistricts=" + this.f3191f + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3186a);
        parcel.writeString(this.f3187b);
        parcel.writeString(this.f3188c);
        parcel.writeParcelable(this.f3189d, i);
        parcel.writeString(this.f3190e);
        parcel.writeTypedList(this.f3191f);
        parcel.writeInt(this.f3192g.length);
        parcel.writeStringArray(this.f3192g);
    }
}
