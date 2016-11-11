package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C0471d;
import org.p122a.p123a.C2915a;

public class DistrictSearchQuery implements Parcelable, Cloneable {
    public static final Creator<DistrictSearchQuery> CREATOR;
    public static final String KEYWORDS_BUSINESS = "biz_area";
    public static final String KEYWORDS_CITY = "city";
    public static final String KEYWORDS_COUNTRY = "country";
    public static final String KEYWORDS_DISTRICT = "district";
    public static final String KEYWORDS_PROVINCE = "province";
    private int f3205a;
    private int f3206b;
    private String f3207c;
    private String f3208d;
    private boolean f3209e;
    private boolean f3210f;

    static {
        CREATOR = new C0504c();
    }

    public DistrictSearchQuery() {
        this.f3205a = 0;
        this.f3206b = 20;
        this.f3209e = true;
        this.f3210f = false;
    }

    public DistrictSearchQuery(String str, String str2, int i) {
        this.f3205a = 0;
        this.f3206b = 20;
        this.f3209e = true;
        this.f3210f = false;
        this.f3207c = str;
        this.f3208d = str2;
        this.f3205a = i;
    }

    public DistrictSearchQuery(String str, String str2, int i, boolean z, int i2) {
        this(str, str2, i);
        this.f3209e = z;
        this.f3206b = i2;
    }

    public boolean checkKeyWords() {
        return (this.f3207c == null || this.f3207c.trim().equalsIgnoreCase(C2915a.f14760f)) ? false : true;
    }

    public boolean checkLevels() {
        return this.f3208d == null ? false : this.f3208d.trim().equals(KEYWORDS_COUNTRY) || this.f3208d.trim().equals(KEYWORDS_PROVINCE) || this.f3208d.trim().equals(KEYWORDS_CITY) || this.f3208d.trim().equals(KEYWORDS_DISTRICT) || this.f3208d.trim().equals(KEYWORDS_BUSINESS);
    }

    public DistrictSearchQuery clone() {
        try {
            super.clone();
        } catch (Throwable e) {
            C0471d.m4762a(e, "DistrictSearchQuery", "clone");
        }
        DistrictSearchQuery districtSearchQuery = new DistrictSearchQuery(this.f3207c, this.f3208d, this.f3205a, this.f3209e, this.f3206b);
        districtSearchQuery.setShowBoundary(this.f3210f);
        return districtSearchQuery;
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
        DistrictSearchQuery districtSearchQuery = (DistrictSearchQuery) obj;
        if (this.f3210f != districtSearchQuery.f3210f) {
            return false;
        }
        if (this.f3207c == null) {
            if (districtSearchQuery.f3207c != null) {
                return false;
            }
        } else if (!this.f3207c.equals(districtSearchQuery.f3207c)) {
            return false;
        }
        if (this.f3208d == null) {
            if (districtSearchQuery.f3208d != null) {
                return false;
            }
        } else if (!this.f3208d.equals(districtSearchQuery.f3208d)) {
            return false;
        }
        return this.f3205a != districtSearchQuery.f3205a ? false : this.f3206b != districtSearchQuery.f3206b ? false : this.f3209e == districtSearchQuery.f3209e;
    }

    public String getKeywords() {
        return this.f3207c;
    }

    public String getKeywordsLevel() {
        return this.f3208d;
    }

    public int getPageNum() {
        return this.f3205a;
    }

    public int getPageSize() {
        return this.f3206b;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = 0;
        int hashCode = ((this.f3207c == null ? 0 : this.f3207c.hashCode()) + (((this.f3210f ? 1231 : 1237) + 31) * 31)) * 31;
        if (this.f3208d != null) {
            i2 = this.f3208d.hashCode();
        }
        hashCode = (((((hashCode + i2) * 31) + this.f3205a) * 31) + this.f3206b) * 31;
        if (!this.f3209e) {
            i = 1237;
        }
        return hashCode + i;
    }

    public boolean isShowBoundary() {
        return this.f3210f;
    }

    public boolean isShowChild() {
        return this.f3209e;
    }

    public void setKeywords(String str) {
        this.f3207c = str;
    }

    public void setKeywordsLevel(String str) {
        this.f3208d = str;
    }

    public void setPageNum(int i) {
        this.f3205a = i;
    }

    public void setPageSize(int i) {
        this.f3206b = i;
    }

    public void setShowBoundary(boolean z) {
        this.f3210f = z;
    }

    public void setShowChild(boolean z) {
        this.f3209e = z;
    }

    protected boolean weakEquals(DistrictSearchQuery districtSearchQuery) {
        if (this == districtSearchQuery) {
            return true;
        }
        if (districtSearchQuery == null) {
            return false;
        }
        if (this.f3207c == null) {
            if (districtSearchQuery.f3207c != null) {
                return false;
            }
        } else if (!this.f3207c.equals(districtSearchQuery.f3207c)) {
            return false;
        }
        if (this.f3208d == null) {
            if (districtSearchQuery.f3208d != null) {
                return false;
            }
        } else if (!this.f3208d.equals(districtSearchQuery.f3208d)) {
            return false;
        }
        return this.f3206b != districtSearchQuery.f3206b ? false : this.f3209e != districtSearchQuery.f3209e ? false : this.f3210f == districtSearchQuery.f3210f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.f3207c);
        parcel.writeString(this.f3208d);
        parcel.writeInt(this.f3205a);
        parcel.writeInt(this.f3206b);
        parcel.writeByte((byte) (this.f3209e ? 1 : 0));
        if (!this.f3210f) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
    }
}
