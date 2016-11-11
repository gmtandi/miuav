package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.C0471d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Groupbuy implements Parcelable {
    public static final Creator<Groupbuy> CREATOR;
    private String f3309a;
    private String f3310b;
    private String f3311c;
    private Date f3312d;
    private Date f3313e;
    private int f3314f;
    private int f3315g;
    private float f3316h;
    private float f3317i;
    private float f3318j;
    private String f3319k;
    private String f3320l;
    private List<Photo> f3321m;
    private String f3322n;
    private String f3323o;

    static {
        CREATOR = new C0519d();
    }

    public Groupbuy() {
        this.f3321m = new ArrayList();
    }

    public Groupbuy(Parcel parcel) {
        this.f3321m = new ArrayList();
        this.f3309a = parcel.readString();
        this.f3310b = parcel.readString();
        this.f3311c = parcel.readString();
        this.f3312d = C0471d.m4767e(parcel.readString());
        this.f3313e = C0471d.m4767e(parcel.readString());
        this.f3314f = parcel.readInt();
        this.f3315g = parcel.readInt();
        this.f3316h = parcel.readFloat();
        this.f3317i = parcel.readFloat();
        this.f3318j = parcel.readFloat();
        this.f3319k = parcel.readString();
        this.f3320l = parcel.readString();
        this.f3321m = parcel.createTypedArrayList(Photo.CREATOR);
        this.f3322n = parcel.readString();
        this.f3323o = parcel.readString();
    }

    public void addPhotos(Photo photo) {
        this.f3321m.add(photo);
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
        Groupbuy groupbuy = (Groupbuy) obj;
        if (this.f3314f != groupbuy.f3314f) {
            return false;
        }
        if (this.f3311c == null) {
            if (groupbuy.f3311c != null) {
                return false;
            }
        } else if (!this.f3311c.equals(groupbuy.f3311c)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3318j) != Float.floatToIntBits(groupbuy.f3318j)) {
            return false;
        }
        if (this.f3313e == null) {
            if (groupbuy.f3313e != null) {
                return false;
            }
        } else if (!this.f3313e.equals(groupbuy.f3313e)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3317i) != Float.floatToIntBits(groupbuy.f3317i)) {
            return false;
        }
        if (Float.floatToIntBits(this.f3316h) != Float.floatToIntBits(groupbuy.f3316h)) {
            return false;
        }
        if (this.f3321m == null) {
            if (groupbuy.f3321m != null) {
                return false;
            }
        } else if (!this.f3321m.equals(groupbuy.f3321m)) {
            return false;
        }
        if (this.f3323o == null) {
            if (groupbuy.f3323o != null) {
                return false;
            }
        } else if (!this.f3323o.equals(groupbuy.f3323o)) {
            return false;
        }
        if (this.f3315g != groupbuy.f3315g) {
            return false;
        }
        if (this.f3312d == null) {
            if (groupbuy.f3312d != null) {
                return false;
            }
        } else if (!this.f3312d.equals(groupbuy.f3312d)) {
            return false;
        }
        if (this.f3319k == null) {
            if (groupbuy.f3319k != null) {
                return false;
            }
        } else if (!this.f3319k.equals(groupbuy.f3319k)) {
            return false;
        }
        if (this.f3320l == null) {
            if (groupbuy.f3320l != null) {
                return false;
            }
        } else if (!this.f3320l.equals(groupbuy.f3320l)) {
            return false;
        }
        if (this.f3309a == null) {
            if (groupbuy.f3309a != null) {
                return false;
            }
        } else if (!this.f3309a.equals(groupbuy.f3309a)) {
            return false;
        }
        if (this.f3310b == null) {
            if (groupbuy.f3310b != null) {
                return false;
            }
        } else if (!this.f3310b.equals(groupbuy.f3310b)) {
            return false;
        }
        return this.f3322n == null ? groupbuy.f3322n == null : this.f3322n.equals(groupbuy.f3322n);
    }

    public int getCount() {
        return this.f3314f;
    }

    public String getDetail() {
        return this.f3311c;
    }

    public float getDiscount() {
        return this.f3318j;
    }

    public Date getEndTime() {
        return this.f3313e == null ? null : (Date) this.f3313e.clone();
    }

    public float getGroupbuyPrice() {
        return this.f3317i;
    }

    public float getOriginalPrice() {
        return this.f3316h;
    }

    public List<Photo> getPhotos() {
        return this.f3321m;
    }

    public String getProvider() {
        return this.f3323o;
    }

    public int getSoldCount() {
        return this.f3315g;
    }

    public Date getStartTime() {
        return this.f3312d == null ? null : (Date) this.f3312d.clone();
    }

    public String getTicketAddress() {
        return this.f3319k;
    }

    public String getTicketTel() {
        return this.f3320l;
    }

    public String getTypeCode() {
        return this.f3309a;
    }

    public String getTypeDes() {
        return this.f3310b;
    }

    public String getUrl() {
        return this.f3322n;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3310b == null ? 0 : this.f3310b.hashCode()) + (((this.f3309a == null ? 0 : this.f3309a.hashCode()) + (((this.f3320l == null ? 0 : this.f3320l.hashCode()) + (((this.f3319k == null ? 0 : this.f3319k.hashCode()) + (((this.f3312d == null ? 0 : this.f3312d.hashCode()) + (((((this.f3323o == null ? 0 : this.f3323o.hashCode()) + (((this.f3321m == null ? 0 : this.f3321m.hashCode()) + (((((((this.f3313e == null ? 0 : this.f3313e.hashCode()) + (((((this.f3311c == null ? 0 : this.f3311c.hashCode()) + ((this.f3314f + 31) * 31)) * 31) + Float.floatToIntBits(this.f3318j)) * 31)) * 31) + Float.floatToIntBits(this.f3317i)) * 31) + Float.floatToIntBits(this.f3316h)) * 31)) * 31)) * 31) + this.f3315g) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3322n != null) {
            i = this.f3322n.hashCode();
        }
        return hashCode + i;
    }

    public void initPhotos(List<Photo> list) {
        if (list != null && list.size() != 0) {
            this.f3321m.clear();
            for (Photo add : list) {
                this.f3321m.add(add);
            }
        }
    }

    public void setCount(int i) {
        this.f3314f = i;
    }

    public void setDetail(String str) {
        this.f3311c = str;
    }

    public void setDiscount(float f) {
        this.f3318j = f;
    }

    public void setEndTime(Date date) {
        if (date == null) {
            this.f3313e = null;
        } else {
            this.f3313e = (Date) date.clone();
        }
    }

    public void setGroupbuyPrice(float f) {
        this.f3317i = f;
    }

    public void setOriginalPrice(float f) {
        this.f3316h = f;
    }

    public void setProvider(String str) {
        this.f3323o = str;
    }

    public void setSoldCount(int i) {
        this.f3315g = i;
    }

    public void setStartTime(Date date) {
        if (date == null) {
            this.f3312d = null;
        } else {
            this.f3312d = (Date) date.clone();
        }
    }

    public void setTicketAddress(String str) {
        this.f3319k = str;
    }

    public void setTicketTel(String str) {
        this.f3320l = str;
    }

    public void setTypeCode(String str) {
        this.f3309a = str;
    }

    public void setTypeDes(String str) {
        this.f3310b = str;
    }

    public void setUrl(String str) {
        this.f3322n = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3309a);
        parcel.writeString(this.f3310b);
        parcel.writeString(this.f3311c);
        parcel.writeString(C0471d.m4760a(this.f3312d));
        parcel.writeString(C0471d.m4760a(this.f3313e));
        parcel.writeInt(this.f3314f);
        parcel.writeInt(this.f3315g);
        parcel.writeFloat(this.f3316h);
        parcel.writeFloat(this.f3317i);
        parcel.writeFloat(this.f3318j);
        parcel.writeString(this.f3319k);
        parcel.writeString(this.f3320l);
        parcel.writeTypedList(this.f3321m);
        parcel.writeString(this.f3322n);
        parcel.writeString(this.f3323o);
    }
}
