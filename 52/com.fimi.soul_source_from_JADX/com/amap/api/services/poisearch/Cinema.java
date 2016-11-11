package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Cinema implements Parcelable {
    public static final Creator<Cinema> CREATOR;
    private boolean f3273a;
    private String f3274b;
    private String f3275c;
    private String f3276d;
    private String f3277e;
    private String f3278f;
    private String f3279g;
    private List<Photo> f3280h;

    static {
        CREATOR = new C0516a();
    }

    public Cinema() {
        this.f3280h = new ArrayList();
    }

    public Cinema(Parcel parcel) {
        this.f3280h = new ArrayList();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f3273a = zArr[0];
        this.f3274b = parcel.readString();
        this.f3275c = parcel.readString();
        this.f3276d = parcel.readString();
        this.f3277e = parcel.readString();
        this.f3278f = parcel.readString();
        this.f3279g = parcel.readString();
        this.f3280h = parcel.createTypedArrayList(Photo.CREATOR);
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
        Cinema cinema = (Cinema) obj;
        if (this.f3276d == null) {
            if (cinema.f3276d != null) {
                return false;
            }
        } else if (!this.f3276d.equals(cinema.f3276d)) {
            return false;
        }
        if (this.f3274b == null) {
            if (cinema.f3274b != null) {
                return false;
            }
        } else if (!this.f3274b.equals(cinema.f3274b)) {
            return false;
        }
        if (this.f3279g == null) {
            if (cinema.f3279g != null) {
                return false;
            }
        } else if (!this.f3279g.equals(cinema.f3279g)) {
            return false;
        }
        if (this.f3278f == null) {
            if (cinema.f3278f != null) {
                return false;
            }
        } else if (!this.f3278f.equals(cinema.f3278f)) {
            return false;
        }
        if (this.f3277e == null) {
            if (cinema.f3277e != null) {
                return false;
            }
        } else if (!this.f3277e.equals(cinema.f3277e)) {
            return false;
        }
        if (this.f3280h == null) {
            if (cinema.f3280h != null) {
                return false;
            }
        } else if (!this.f3280h.equals(cinema.f3280h)) {
            return false;
        }
        if (this.f3275c == null) {
            if (cinema.f3275c != null) {
                return false;
            }
        } else if (!this.f3275c.equals(cinema.f3275c)) {
            return false;
        }
        return this.f3273a == cinema.f3273a;
    }

    public String getDeepsrc() {
        return this.f3276d;
    }

    public String getIntro() {
        return this.f3274b;
    }

    public String getOpentime() {
        return this.f3279g;
    }

    public String getOpentimeGDF() {
        return this.f3278f;
    }

    public String getParking() {
        return this.f3277e;
    }

    public List<Photo> getPhotos() {
        return this.f3280h;
    }

    public String getRating() {
        return this.f3275c;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3280h == null ? 0 : this.f3280h.hashCode()) + (((this.f3277e == null ? 0 : this.f3277e.hashCode()) + (((this.f3278f == null ? 0 : this.f3278f.hashCode()) + (((this.f3279g == null ? 0 : this.f3279g.hashCode()) + (((this.f3274b == null ? 0 : this.f3274b.hashCode()) + (((this.f3276d == null ? 0 : this.f3276d.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3275c != null) {
            i = this.f3275c.hashCode();
        }
        return (this.f3273a ? 1231 : 1237) + ((hashCode + i) * 31);
    }

    public boolean isSeatOrdering() {
        return this.f3273a;
    }

    public void setDeepsrc(String str) {
        this.f3276d = str;
    }

    public void setIntro(String str) {
        this.f3274b = str;
    }

    public void setOpentime(String str) {
        this.f3279g = str;
    }

    public void setOpentimeGDF(String str) {
        this.f3278f = str;
    }

    public void setParking(String str) {
        this.f3277e = str;
    }

    public void setPhotos(List<Photo> list) {
        this.f3280h = list;
    }

    public void setRating(String str) {
        this.f3275c = str;
    }

    public void setSeatOrdering(boolean z) {
        this.f3273a = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBooleanArray(new boolean[]{this.f3273a});
        parcel.writeString(this.f3274b);
        parcel.writeString(this.f3275c);
        parcel.writeString(this.f3276d);
        parcel.writeString(this.f3277e);
        parcel.writeString(this.f3278f);
        parcel.writeString(this.f3279g);
        parcel.writeTypedList(this.f3280h);
    }
}
