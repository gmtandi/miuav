package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Scenic implements Parcelable {
    public static final Creator<Scenic> CREATOR;
    private String f3377a;
    private String f3378b;
    private String f3379c;
    private String f3380d;
    private String f3381e;
    private String f3382f;
    private String f3383g;
    private String f3384h;
    private String f3385i;
    private String f3386j;
    private String f3387k;
    private String f3388l;
    private List<Photo> f3389m;

    static {
        CREATOR = new C0526k();
    }

    public Scenic() {
        this.f3389m = new ArrayList();
    }

    public Scenic(Parcel parcel) {
        this.f3389m = new ArrayList();
        this.f3377a = parcel.readString();
        this.f3378b = parcel.readString();
        this.f3379c = parcel.readString();
        this.f3380d = parcel.readString();
        this.f3381e = parcel.readString();
        this.f3382f = parcel.readString();
        this.f3383g = parcel.readString();
        this.f3384h = parcel.readString();
        this.f3385i = parcel.readString();
        this.f3386j = parcel.readString();
        this.f3387k = parcel.readString();
        this.f3388l = parcel.readString();
        this.f3389m = parcel.createTypedArrayList(Photo.CREATOR);
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
        Scenic scenic = (Scenic) obj;
        if (this.f3379c == null) {
            if (scenic.f3379c != null) {
                return false;
            }
        } else if (!this.f3379c.equals(scenic.f3379c)) {
            return false;
        }
        if (this.f3377a == null) {
            if (scenic.f3377a != null) {
                return false;
            }
        } else if (!this.f3377a.equals(scenic.f3377a)) {
            return false;
        }
        if (this.f3380d == null) {
            if (scenic.f3380d != null) {
                return false;
            }
        } else if (!this.f3380d.equals(scenic.f3380d)) {
            return false;
        }
        if (this.f3388l == null) {
            if (scenic.f3388l != null) {
                return false;
            }
        } else if (!this.f3388l.equals(scenic.f3388l)) {
            return false;
        }
        if (this.f3387k == null) {
            if (scenic.f3387k != null) {
                return false;
            }
        } else if (!this.f3387k.equals(scenic.f3387k)) {
            return false;
        }
        if (this.f3385i == null) {
            if (scenic.f3385i != null) {
                return false;
            }
        } else if (!this.f3385i.equals(scenic.f3385i)) {
            return false;
        }
        if (this.f3386j == null) {
            if (scenic.f3386j != null) {
                return false;
            }
        } else if (!this.f3386j.equals(scenic.f3386j)) {
            return false;
        }
        if (this.f3389m == null) {
            if (scenic.f3389m != null) {
                return false;
            }
        } else if (!this.f3389m.equals(scenic.f3389m)) {
            return false;
        }
        if (this.f3381e == null) {
            if (scenic.f3381e != null) {
                return false;
            }
        } else if (!this.f3381e.equals(scenic.f3381e)) {
            return false;
        }
        if (this.f3378b == null) {
            if (scenic.f3378b != null) {
                return false;
            }
        } else if (!this.f3378b.equals(scenic.f3378b)) {
            return false;
        }
        if (this.f3383g == null) {
            if (scenic.f3383g != null) {
                return false;
            }
        } else if (!this.f3383g.equals(scenic.f3383g)) {
            return false;
        }
        if (this.f3382f == null) {
            if (scenic.f3382f != null) {
                return false;
            }
        } else if (!this.f3382f.equals(scenic.f3382f)) {
            return false;
        }
        return this.f3384h == null ? scenic.f3384h == null : this.f3384h.equals(scenic.f3384h);
    }

    public String getDeepsrc() {
        return this.f3379c;
    }

    public String getIntro() {
        return this.f3377a;
    }

    public String getLevel() {
        return this.f3380d;
    }

    public String getOpentime() {
        return this.f3388l;
    }

    public String getOpentimeGDF() {
        return this.f3387k;
    }

    public String getOrderWapUrl() {
        return this.f3385i;
    }

    public String getOrderWebUrl() {
        return this.f3386j;
    }

    public List<Photo> getPhotos() {
        return this.f3389m;
    }

    public String getPrice() {
        return this.f3381e;
    }

    public String getRating() {
        return this.f3378b;
    }

    public String getRecommend() {
        return this.f3383g;
    }

    public String getSeason() {
        return this.f3382f;
    }

    public String getTheme() {
        return this.f3384h;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3382f == null ? 0 : this.f3382f.hashCode()) + (((this.f3383g == null ? 0 : this.f3383g.hashCode()) + (((this.f3378b == null ? 0 : this.f3378b.hashCode()) + (((this.f3381e == null ? 0 : this.f3381e.hashCode()) + (((this.f3389m == null ? 0 : this.f3389m.hashCode()) + (((this.f3386j == null ? 0 : this.f3386j.hashCode()) + (((this.f3385i == null ? 0 : this.f3385i.hashCode()) + (((this.f3387k == null ? 0 : this.f3387k.hashCode()) + (((this.f3388l == null ? 0 : this.f3388l.hashCode()) + (((this.f3380d == null ? 0 : this.f3380d.hashCode()) + (((this.f3377a == null ? 0 : this.f3377a.hashCode()) + (((this.f3379c == null ? 0 : this.f3379c.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3384h != null) {
            i = this.f3384h.hashCode();
        }
        return hashCode + i;
    }

    public void setDeepsrc(String str) {
        this.f3379c = str;
    }

    public void setIntro(String str) {
        this.f3377a = str;
    }

    public void setLevel(String str) {
        this.f3380d = str;
    }

    public void setOpentime(String str) {
        this.f3388l = str;
    }

    public void setOpentimeGDF(String str) {
        this.f3387k = str;
    }

    public void setOrderWapUrl(String str) {
        this.f3385i = str;
    }

    public void setOrderWebUrl(String str) {
        this.f3386j = str;
    }

    public void setPhotos(List<Photo> list) {
        this.f3389m = list;
    }

    public void setPrice(String str) {
        this.f3381e = str;
    }

    public void setRating(String str) {
        this.f3378b = str;
    }

    public void setRecommend(String str) {
        this.f3383g = str;
    }

    public void setSeason(String str) {
        this.f3382f = str;
    }

    public void setTheme(String str) {
        this.f3384h = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3377a);
        parcel.writeString(this.f3378b);
        parcel.writeString(this.f3379c);
        parcel.writeString(this.f3380d);
        parcel.writeString(this.f3381e);
        parcel.writeString(this.f3382f);
        parcel.writeString(this.f3383g);
        parcel.writeString(this.f3384h);
        parcel.writeString(this.f3385i);
        parcel.writeString(this.f3386j);
        parcel.writeString(this.f3387k);
        parcel.writeString(this.f3388l);
        parcel.writeTypedList(this.f3389m);
    }
}
