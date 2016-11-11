package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public final class Dining implements Parcelable {
    public static final Creator<Dining> CREATOR;
    private boolean f3281a;
    private String f3282b;
    private String f3283c;
    private String f3284d;
    private String f3285e;
    private String f3286f;
    private String f3287g;
    private String f3288h;
    private String f3289i;
    private String f3290j;
    private String f3291k;
    private String f3292l;
    private String f3293m;
    private String f3294n;
    private String f3295o;
    private String f3296p;
    private String f3297q;
    private String f3298r;
    private String f3299s;
    private List<Photo> f3300t;

    static {
        CREATOR = new C0517b();
    }

    public Dining() {
        this.f3300t = new ArrayList();
    }

    public Dining(Parcel parcel) {
        this.f3300t = new ArrayList();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f3281a = zArr[0];
        this.f3282b = parcel.readString();
        this.f3283c = parcel.readString();
        this.f3284d = parcel.readString();
        this.f3285e = parcel.readString();
        this.f3286f = parcel.readString();
        this.f3287g = parcel.readString();
        this.f3288h = parcel.readString();
        this.f3289i = parcel.readString();
        this.f3290j = parcel.readString();
        this.f3291k = parcel.readString();
        this.f3292l = parcel.readString();
        this.f3293m = parcel.readString();
        this.f3294n = parcel.readString();
        this.f3295o = parcel.readString();
        this.f3296p = parcel.readString();
        this.f3297q = parcel.readString();
        this.f3298r = parcel.readString();
        this.f3299s = parcel.readString();
        this.f3300t = parcel.createTypedArrayList(Photo.CREATOR);
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
        Dining dining = (Dining) obj;
        if (this.f3299s == null) {
            if (dining.f3299s != null) {
                return false;
            }
        } else if (!this.f3299s.equals(dining.f3299s)) {
            return false;
        }
        if (this.f3293m == null) {
            if (dining.f3293m != null) {
                return false;
            }
        } else if (!this.f3293m.equals(dining.f3293m)) {
            return false;
        }
        if (this.f3291k == null) {
            if (dining.f3291k != null) {
                return false;
            }
        } else if (!this.f3291k.equals(dining.f3291k)) {
            return false;
        }
        if (this.f3286f == null) {
            if (dining.f3286f != null) {
                return false;
            }
        } else if (!this.f3286f.equals(dining.f3286f)) {
            return false;
        }
        if (this.f3282b == null) {
            if (dining.f3282b != null) {
                return false;
            }
        } else if (!this.f3282b.equals(dining.f3282b)) {
            return false;
        }
        if (this.f3287g == null) {
            if (dining.f3287g != null) {
                return false;
            }
        } else if (!this.f3287g.equals(dining.f3287g)) {
            return false;
        }
        if (this.f3289i == null) {
            if (dining.f3289i != null) {
                return false;
            }
        } else if (!this.f3289i.equals(dining.f3289i)) {
            return false;
        }
        if (this.f3284d == null) {
            if (dining.f3284d != null) {
                return false;
            }
        } else if (!this.f3284d.equals(dining.f3284d)) {
            return false;
        }
        if (this.f3281a != dining.f3281a) {
            return false;
        }
        if (this.f3298r == null) {
            if (dining.f3298r != null) {
                return false;
            }
        } else if (!this.f3298r.equals(dining.f3298r)) {
            return false;
        }
        if (this.f3297q == null) {
            if (dining.f3297q != null) {
                return false;
            }
        } else if (!this.f3297q.equals(dining.f3297q)) {
            return false;
        }
        if (this.f3296p == null) {
            if (dining.f3296p != null) {
                return false;
            }
        } else if (!this.f3296p.equals(dining.f3296p)) {
            return false;
        }
        if (this.f3294n == null) {
            if (dining.f3294n != null) {
                return false;
            }
        } else if (!this.f3294n.equals(dining.f3294n)) {
            return false;
        }
        if (this.f3295o == null) {
            if (dining.f3295o != null) {
                return false;
            }
        } else if (!this.f3295o.equals(dining.f3295o)) {
            return false;
        }
        if (this.f3300t == null) {
            if (dining.f3300t != null) {
                return false;
            }
        } else if (!this.f3300t.equals(dining.f3300t)) {
            return false;
        }
        if (this.f3285e == null) {
            if (dining.f3285e != null) {
                return false;
            }
        } else if (!this.f3285e.equals(dining.f3285e)) {
            return false;
        }
        if (this.f3292l == null) {
            if (dining.f3292l != null) {
                return false;
            }
        } else if (!this.f3292l.equals(dining.f3292l)) {
            return false;
        }
        if (this.f3290j == null) {
            if (dining.f3290j != null) {
                return false;
            }
        } else if (!this.f3290j.equals(dining.f3290j)) {
            return false;
        }
        if (this.f3283c == null) {
            if (dining.f3283c != null) {
                return false;
            }
        } else if (!this.f3283c.equals(dining.f3283c)) {
            return false;
        }
        return this.f3288h == null ? dining.f3288h == null : this.f3288h.equals(dining.f3288h);
    }

    public String getAddition() {
        return this.f3299s;
    }

    public String getAtmosphere() {
        return this.f3293m;
    }

    public String getCost() {
        return this.f3291k;
    }

    public String getCpRating() {
        return this.f3286f;
    }

    public String getCuisines() {
        return this.f3282b;
    }

    public String getDeepsrc() {
        return this.f3287g;
    }

    public String getEnvironmentRating() {
        return this.f3289i;
    }

    public String getIntro() {
        return this.f3284d;
    }

    public String getOpentime() {
        return this.f3298r;
    }

    public String getOpentimeGDF() {
        return this.f3297q;
    }

    public String getOrderinAppUrl() {
        return this.f3296p;
    }

    public String getOrderingWapUrl() {
        return this.f3294n;
    }

    public String getOrderingWebUrl() {
        return this.f3295o;
    }

    public List<Photo> getPhotos() {
        return this.f3300t;
    }

    public String getRating() {
        return this.f3285e;
    }

    public String getRecommend() {
        return this.f3292l;
    }

    public String getServiceRating() {
        return this.f3290j;
    }

    public String getTag() {
        return this.f3283c;
    }

    public String getTasteRating() {
        return this.f3288h;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3283c == null ? 0 : this.f3283c.hashCode()) + (((this.f3290j == null ? 0 : this.f3290j.hashCode()) + (((this.f3292l == null ? 0 : this.f3292l.hashCode()) + (((this.f3285e == null ? 0 : this.f3285e.hashCode()) + (((this.f3300t == null ? 0 : this.f3300t.hashCode()) + (((this.f3295o == null ? 0 : this.f3295o.hashCode()) + (((this.f3294n == null ? 0 : this.f3294n.hashCode()) + (((this.f3296p == null ? 0 : this.f3296p.hashCode()) + (((this.f3297q == null ? 0 : this.f3297q.hashCode()) + (((this.f3298r == null ? 0 : this.f3298r.hashCode()) + (((this.f3281a ? 1231 : 1237) + (((this.f3284d == null ? 0 : this.f3284d.hashCode()) + (((this.f3289i == null ? 0 : this.f3289i.hashCode()) + (((this.f3287g == null ? 0 : this.f3287g.hashCode()) + (((this.f3282b == null ? 0 : this.f3282b.hashCode()) + (((this.f3286f == null ? 0 : this.f3286f.hashCode()) + (((this.f3291k == null ? 0 : this.f3291k.hashCode()) + (((this.f3293m == null ? 0 : this.f3293m.hashCode()) + (((this.f3299s == null ? 0 : this.f3299s.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f3288h != null) {
            i = this.f3288h.hashCode();
        }
        return hashCode + i;
    }

    public boolean isMealOrdering() {
        return this.f3281a;
    }

    public void setAddition(String str) {
        this.f3299s = str;
    }

    public void setAtmosphere(String str) {
        this.f3293m = str;
    }

    public void setCost(String str) {
        this.f3291k = str;
    }

    public void setCpRating(String str) {
        this.f3286f = str;
    }

    public void setCuisines(String str) {
        this.f3282b = str;
    }

    public void setDeepsrc(String str) {
        this.f3287g = str;
    }

    public void setEnvironmentRating(String str) {
        this.f3289i = str;
    }

    public void setIntro(String str) {
        this.f3284d = str;
    }

    public void setMealOrdering(boolean z) {
        this.f3281a = z;
    }

    public void setOpentime(String str) {
        this.f3298r = str;
    }

    public void setOpentimeGDF(String str) {
        this.f3297q = str;
    }

    public void setOrderinAppUrl(String str) {
        this.f3296p = str;
    }

    public void setOrderingWapUrl(String str) {
        this.f3294n = str;
    }

    public void setOrderingWebUrl(String str) {
        this.f3295o = str;
    }

    public void setPhotos(List<Photo> list) {
        this.f3300t = list;
    }

    public void setRating(String str) {
        this.f3285e = str;
    }

    public void setRecommend(String str) {
        this.f3292l = str;
    }

    public void setServiceRating(String str) {
        this.f3290j = str;
    }

    public void setTag(String str) {
        this.f3283c = str;
    }

    public void setTasteRating(String str) {
        this.f3288h = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBooleanArray(new boolean[]{this.f3281a});
        parcel.writeString(this.f3282b);
        parcel.writeString(this.f3283c);
        parcel.writeString(this.f3284d);
        parcel.writeString(this.f3285e);
        parcel.writeString(this.f3286f);
        parcel.writeString(this.f3287g);
        parcel.writeString(this.f3288h);
        parcel.writeString(this.f3289i);
        parcel.writeString(this.f3290j);
        parcel.writeString(this.f3291k);
        parcel.writeString(this.f3292l);
        parcel.writeString(this.f3293m);
        parcel.writeString(this.f3294n);
        parcel.writeString(this.f3295o);
        parcel.writeString(this.f3296p);
        parcel.writeString(this.f3297q);
        parcel.writeString(this.f3298r);
        parcel.writeString(this.f3299s);
        parcel.writeTypedList(this.f3300t);
    }
}
