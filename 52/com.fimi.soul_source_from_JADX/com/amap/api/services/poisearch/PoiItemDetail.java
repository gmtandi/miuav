package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.List;

public class PoiItemDetail extends PoiItem implements Parcelable {
    public static final Creator<PoiItemDetail> CREATOR;
    private List<Groupbuy> f3339a;
    private List<Discount> f3340b;
    private Dining f3341c;
    private Hotel f3342d;
    private Cinema f3343e;
    private Scenic f3344f;
    private DeepType f3345g;

    public enum DeepType {
        UNKNOWN,
        DINING,
        HOTEL,
        CINEMA,
        SCENIC
    }

    static {
        CREATOR = new C0523h();
    }

    private PoiItemDetail(Parcel parcel) {
        super(parcel);
        this.f3339a = new ArrayList();
        this.f3340b = new ArrayList();
        this.f3339a = parcel.readArrayList(Groupbuy.class.getClassLoader());
        this.f3340b = parcel.readArrayList(Discount.class.getClassLoader());
    }

    public PoiItemDetail(String str, LatLonPoint latLonPoint, String str2, String str3) {
        super(str, latLonPoint, str2, str3);
        this.f3339a = new ArrayList();
        this.f3340b = new ArrayList();
    }

    public void addDiscount(Discount discount) {
        this.f3340b.add(discount);
    }

    public void addGroupbuy(Groupbuy groupbuy) {
        this.f3339a.add(groupbuy);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PoiItemDetail poiItemDetail = (PoiItemDetail) obj;
        if (this.f3343e == null) {
            if (poiItemDetail.f3343e != null) {
                return false;
            }
        } else if (!this.f3343e.equals(poiItemDetail.f3343e)) {
            return false;
        }
        if (this.f3345g != poiItemDetail.f3345g) {
            return false;
        }
        if (this.f3341c == null) {
            if (poiItemDetail.f3341c != null) {
                return false;
            }
        } else if (!this.f3341c.equals(poiItemDetail.f3341c)) {
            return false;
        }
        if (this.f3340b == null) {
            if (poiItemDetail.f3340b != null) {
                return false;
            }
        } else if (!this.f3340b.equals(poiItemDetail.f3340b)) {
            return false;
        }
        if (this.f3339a == null) {
            if (poiItemDetail.f3339a != null) {
                return false;
            }
        } else if (!this.f3339a.equals(poiItemDetail.f3339a)) {
            return false;
        }
        if (this.f3342d == null) {
            if (poiItemDetail.f3342d != null) {
                return false;
            }
        } else if (!this.f3342d.equals(poiItemDetail.f3342d)) {
            return false;
        }
        return this.f3344f == null ? poiItemDetail.f3344f == null : this.f3344f.equals(poiItemDetail.f3344f);
    }

    public Cinema getCinema() {
        return this.f3343e;
    }

    public DeepType getDeepType() {
        return this.f3345g;
    }

    public Dining getDining() {
        return this.f3341c;
    }

    public List<Discount> getDiscounts() {
        return this.f3340b;
    }

    public List<Groupbuy> getGroupbuys() {
        return this.f3339a;
    }

    public Hotel getHotel() {
        return this.f3342d;
    }

    public Scenic getScenic() {
        return this.f3344f;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3342d == null ? 0 : this.f3342d.hashCode()) + (((this.f3339a == null ? 0 : this.f3339a.hashCode()) + (((this.f3340b == null ? 0 : this.f3340b.hashCode()) + (((this.f3341c == null ? 0 : this.f3341c.hashCode()) + (((this.f3345g == null ? 0 : this.f3345g.hashCode()) + (((this.f3343e == null ? 0 : this.f3343e.hashCode()) + (super.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3344f != null) {
            i = this.f3344f.hashCode();
        }
        return hashCode + i;
    }

    public void initDiscounts(List<Discount> list) {
        if (list != null && list.size() != 0) {
            this.f3340b.clear();
            for (Discount add : list) {
                this.f3340b.add(add);
            }
        }
    }

    public void initGroupbuys(List<Groupbuy> list) {
        if (list != null && list.size() != 0) {
            for (Groupbuy add : list) {
                this.f3339a.add(add);
            }
        }
    }

    public void setCinema(Cinema cinema) {
        this.f3343e = cinema;
    }

    public void setDeepType(DeepType deepType) {
        this.f3345g = deepType;
    }

    public void setDining(Dining dining) {
        this.f3341c = dining;
    }

    public void setHotel(Hotel hotel) {
        this.f3342d = hotel;
    }

    public void setScenic(Scenic scenic) {
        this.f3344f = scenic;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeList(this.f3339a);
        parcel.writeList(this.f3340b);
    }
}
