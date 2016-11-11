package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Hotel implements Parcelable {
    public static final Creator<Hotel> CREATOR;
    private String f3324a;
    private String f3325b;
    private String f3326c;
    private String f3327d;
    private String f3328e;
    private String f3329f;
    private String f3330g;
    private String f3331h;
    private String f3332i;
    private String f3333j;
    private String f3334k;
    private List<Photo> f3335l;

    static {
        CREATOR = new C0520e();
    }

    public Hotel() {
        this.f3335l = new ArrayList();
    }

    public Hotel(Parcel parcel) {
        this.f3335l = new ArrayList();
        this.f3324a = parcel.readString();
        this.f3325b = parcel.readString();
        this.f3326c = parcel.readString();
        this.f3327d = parcel.readString();
        this.f3328e = parcel.readString();
        this.f3329f = parcel.readString();
        this.f3330g = parcel.readString();
        this.f3331h = parcel.readString();
        this.f3332i = parcel.readString();
        this.f3333j = parcel.readString();
        this.f3334k = parcel.readString();
        this.f3335l = parcel.createTypedArrayList(Photo.CREATOR);
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
        Hotel hotel = (Hotel) obj;
        if (this.f3333j == null) {
            if (hotel.f3333j != null) {
                return false;
            }
        } else if (!this.f3333j.equals(hotel.f3333j)) {
            return false;
        }
        if (this.f3334k == null) {
            if (hotel.f3334k != null) {
                return false;
            }
        } else if (!this.f3334k.equals(hotel.f3334k)) {
            return false;
        }
        if (this.f3330g == null) {
            if (hotel.f3330g != null) {
                return false;
            }
        } else if (!this.f3330g.equals(hotel.f3330g)) {
            return false;
        }
        if (this.f3328e == null) {
            if (hotel.f3328e != null) {
                return false;
            }
        } else if (!this.f3328e.equals(hotel.f3328e)) {
            return false;
        }
        if (this.f3329f == null) {
            if (hotel.f3329f != null) {
                return false;
            }
        } else if (!this.f3329f.equals(hotel.f3329f)) {
            return false;
        }
        if (this.f3326c == null) {
            if (hotel.f3326c != null) {
                return false;
            }
        } else if (!this.f3326c.equals(hotel.f3326c)) {
            return false;
        }
        if (this.f3327d == null) {
            if (hotel.f3327d != null) {
                return false;
            }
        } else if (!this.f3327d.equals(hotel.f3327d)) {
            return false;
        }
        if (this.f3335l == null) {
            if (hotel.f3335l != null) {
                return false;
            }
        } else if (!this.f3335l.equals(hotel.f3335l)) {
            return false;
        }
        if (this.f3324a == null) {
            if (hotel.f3324a != null) {
                return false;
            }
        } else if (!this.f3324a.equals(hotel.f3324a)) {
            return false;
        }
        if (this.f3331h == null) {
            if (hotel.f3331h != null) {
                return false;
            }
        } else if (!this.f3331h.equals(hotel.f3331h)) {
            return false;
        }
        if (this.f3325b == null) {
            if (hotel.f3325b != null) {
                return false;
            }
        } else if (!this.f3325b.equals(hotel.f3325b)) {
            return false;
        }
        return this.f3332i == null ? hotel.f3332i == null : this.f3332i.equals(hotel.f3332i);
    }

    public String getAddition() {
        return this.f3333j;
    }

    public String getDeepsrc() {
        return this.f3334k;
    }

    public String getEnvironmentRating() {
        return this.f3330g;
    }

    public String getFaciRating() {
        return this.f3328e;
    }

    public String getHealthRating() {
        return this.f3329f;
    }

    public String getIntro() {
        return this.f3326c;
    }

    public String getLowestPrice() {
        return this.f3327d;
    }

    public List<Photo> getPhotos() {
        return this.f3335l;
    }

    public String getRating() {
        return this.f3324a;
    }

    public String getServiceRating() {
        return this.f3331h;
    }

    public String getStar() {
        return this.f3325b;
    }

    public String getTraffic() {
        return this.f3332i;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3325b == null ? 0 : this.f3325b.hashCode()) + (((this.f3331h == null ? 0 : this.f3331h.hashCode()) + (((this.f3324a == null ? 0 : this.f3324a.hashCode()) + (((this.f3335l == null ? 0 : this.f3335l.hashCode()) + (((this.f3327d == null ? 0 : this.f3327d.hashCode()) + (((this.f3326c == null ? 0 : this.f3326c.hashCode()) + (((this.f3329f == null ? 0 : this.f3329f.hashCode()) + (((this.f3328e == null ? 0 : this.f3328e.hashCode()) + (((this.f3330g == null ? 0 : this.f3330g.hashCode()) + (((this.f3334k == null ? 0 : this.f3334k.hashCode()) + (((this.f3333j == null ? 0 : this.f3333j.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3332i != null) {
            i = this.f3332i.hashCode();
        }
        return hashCode + i;
    }

    public void setAddition(String str) {
        this.f3333j = str;
    }

    public void setDeepsrc(String str) {
        this.f3334k = str;
    }

    public void setEnvironmentRating(String str) {
        this.f3330g = str;
    }

    public void setFaciRating(String str) {
        this.f3328e = str;
    }

    public void setHealthRating(String str) {
        this.f3329f = str;
    }

    public void setIntro(String str) {
        this.f3326c = str;
    }

    public void setLowestPrice(String str) {
        this.f3327d = str;
    }

    public void setPhotos(List<Photo> list) {
        this.f3335l = list;
    }

    public void setRating(String str) {
        this.f3324a = str;
    }

    public void setServiceRating(String str) {
        this.f3331h = str;
    }

    public void setStar(String str) {
        this.f3325b = str;
    }

    public void setTraffic(String str) {
        this.f3332i = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3324a);
        parcel.writeString(this.f3325b);
        parcel.writeString(this.f3326c);
        parcel.writeString(this.f3327d);
        parcel.writeString(this.f3328e);
        parcel.writeString(this.f3329f);
        parcel.writeString(this.f3330g);
        parcel.writeString(this.f3331h);
        parcel.writeString(this.f3332i);
        parcel.writeString(this.f3333j);
        parcel.writeString(this.f3334k);
        parcel.writeTypedList(this.f3335l);
    }
}
