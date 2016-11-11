package com.amap.api.mapcore.util;

import com.fimi.soul.view.photodraweeview.C2020f;

public class bi {
    public short[] f2201a;
    public int f2202b;
    public boolean f2203c;

    public bi() {
        this(true, 16);
    }

    public bi(boolean z, int i) {
        this.f2203c = z;
        this.f2201a = new short[i];
    }

    public short m3593a(int i) {
        if (i < this.f2202b) {
            return this.f2201a[i];
        }
        throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f2202b);
    }

    public void m3594a() {
        this.f2202b = 0;
    }

    public void m3595a(short s) {
        short[] sArr = this.f2201a;
        if (this.f2202b == sArr.length) {
            sArr = m3598d(Math.max(8, (int) (((float) this.f2202b) * C2020f.f10932b)));
        }
        int i = this.f2202b;
        this.f2202b = i + 1;
        sArr[i] = s;
    }

    public short m3596b(int i) {
        if (i >= this.f2202b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f2202b);
        }
        Object obj = this.f2201a;
        short s = obj[i];
        this.f2202b--;
        if (this.f2203c) {
            System.arraycopy(obj, i + 1, obj, i, this.f2202b - i);
        } else {
            obj[i] = obj[this.f2202b];
        }
        return s;
    }

    public short[] m3597c(int i) {
        int i2 = this.f2202b + i;
        if (i2 > this.f2201a.length) {
            m3598d(Math.max(8, i2));
        }
        return this.f2201a;
    }

    protected short[] m3598d(int i) {
        Object obj = new short[i];
        System.arraycopy(this.f2201a, 0, obj, 0, Math.min(this.f2202b, obj.length));
        this.f2201a = obj;
        return obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof bi)) {
            return false;
        }
        bi biVar = (bi) obj;
        int i = this.f2202b;
        if (i != biVar.f2202b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f2201a[i2] != biVar.f2201a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f2202b == 0) {
            return "[]";
        }
        short[] sArr = this.f2201a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(sArr[0]);
        for (int i = 1; i < this.f2202b; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(sArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
