package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C0471d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Discount implements Parcelable {
    public static final Creator<Discount> CREATOR;
    private String f3301a;
    private String f3302b;
    private Date f3303c;
    private Date f3304d;
    private int f3305e;
    private List<Photo> f3306f;
    private String f3307g;
    private String f3308h;

    static {
        CREATOR = new C0518c();
    }

    public Discount() {
        this.f3306f = new ArrayList();
    }

    public Discount(Parcel parcel) {
        this.f3306f = new ArrayList();
        this.f3301a = parcel.readString();
        this.f3302b = parcel.readString();
        this.f3303c = C0471d.m4767e(parcel.readString());
        this.f3304d = C0471d.m4767e(parcel.readString());
        this.f3305e = parcel.readInt();
        this.f3306f = parcel.createTypedArrayList(Photo.CREATOR);
        this.f3307g = parcel.readString();
        this.f3308h = parcel.readString();
    }

    public void addPhotos(Photo photo) {
        this.f3306f.add(photo);
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
        Discount discount = (Discount) obj;
        if (this.f3302b == null) {
            if (discount.f3302b != null) {
                return false;
            }
        } else if (!this.f3302b.equals(discount.f3302b)) {
            return false;
        }
        if (this.f3304d == null) {
            if (discount.f3304d != null) {
                return false;
            }
        } else if (!this.f3304d.equals(discount.f3304d)) {
            return false;
        }
        if (this.f3306f == null) {
            if (discount.f3306f != null) {
                return false;
            }
        } else if (!this.f3306f.equals(discount.f3306f)) {
            return false;
        }
        if (this.f3308h == null) {
            if (discount.f3308h != null) {
                return false;
            }
        } else if (!this.f3308h.equals(discount.f3308h)) {
            return false;
        }
        if (this.f3305e != discount.f3305e) {
            return false;
        }
        if (this.f3303c == null) {
            if (discount.f3303c != null) {
                return false;
            }
        } else if (!this.f3303c.equals(discount.f3303c)) {
            return false;
        }
        if (this.f3301a == null) {
            if (discount.f3301a != null) {
                return false;
            }
        } else if (!this.f3301a.equals(discount.f3301a)) {
            return false;
        }
        return this.f3307g == null ? discount.f3307g == null : this.f3307g.equals(discount.f3307g);
    }

    public String getDetail() {
        return this.f3302b;
    }

    public Date getEndTime() {
        return this.f3304d == null ? null : (Date) this.f3304d.clone();
    }

    public List<Photo> getPhotos() {
        return this.f3306f;
    }

    public String getProvider() {
        return this.f3308h;
    }

    public int getSoldCount() {
        return this.f3305e;
    }

    public Date getStartTime() {
        return this.f3303c == null ? null : (Date) this.f3303c.clone();
    }

    public String getTitle() {
        return this.f3301a;
    }

    public String getUrl() {
        return this.f3307g;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3301a == null ? 0 : this.f3301a.hashCode()) + (((this.f3303c == null ? 0 : this.f3303c.hashCode()) + (((((this.f3308h == null ? 0 : this.f3308h.hashCode()) + (((this.f3306f == null ? 0 : this.f3306f.hashCode()) + (((this.f3304d == null ? 0 : this.f3304d.hashCode()) + (((this.f3302b == null ? 0 : this.f3302b.hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + this.f3305e) * 31)) * 31)) * 31;
        if (this.f3307g != null) {
            i = this.f3307g.hashCode();
        }
        return hashCode + i;
    }

    public void initPhotos(List<Photo> list) {
        if (list != null && list.size() != 0) {
            this.f3306f.clear();
            for (Photo add : list) {
                this.f3306f.add(add);
            }
        }
    }

    public void setDetail(String str) {
        this.f3302b = str;
    }

    public void setEndTime(Date date) {
        if (date == null) {
            this.f3304d = null;
        } else {
            this.f3304d = (Date) date.clone();
        }
    }

    public void setProvider(String str) {
        this.f3308h = str;
    }

    public void setSoldCount(int i) {
        this.f3305e = i;
    }

    public void setStartTime(Date date) {
        if (date == null) {
            this.f3303c = null;
        } else {
            this.f3303c = (Date) date.clone();
        }
    }

    public void setTitle(String str) {
        this.f3301a = str;
    }

    public void setUrl(String str) {
        this.f3307g = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3301a);
        parcel.writeString(this.f3302b);
        parcel.writeString(C0471d.m4760a(this.f3303c));
        parcel.writeString(C0471d.m4760a(this.f3304d));
        parcel.writeInt(this.f3305e);
        parcel.writeTypedList(this.f3306f);
        parcel.writeString(this.f3307g);
        parcel.writeString(this.f3308h);
    }
}
