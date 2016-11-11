package com.amap.api.mapcore.util;

import com.fimi.soul.view.photodraweeview.C2020f;

public class be {
    public int[] f2197a;
    public int f2198b;
    public boolean f2199c;

    public be() {
        this(true, 16);
    }

    public be(boolean z, int i) {
        this.f2199c = z;
        this.f2197a = new int[i];
    }

    public void m3585a() {
        this.f2198b = 0;
    }

    public void m3586a(int i) {
        int[] iArr = this.f2197a;
        if (this.f2198b == iArr.length) {
            iArr = m3589d(Math.max(8, (int) (((float) this.f2198b) * C2020f.f10932b)));
        }
        int i2 = this.f2198b;
        this.f2198b = i2 + 1;
        iArr[i2] = i;
    }

    public int m3587b(int i) {
        if (i >= this.f2198b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f2198b);
        }
        Object obj = this.f2197a;
        int i2 = obj[i];
        this.f2198b--;
        if (this.f2199c) {
            System.arraycopy(obj, i + 1, obj, i, this.f2198b - i);
        } else {
            obj[i] = obj[this.f2198b];
        }
        return i2;
    }

    public int[] m3588c(int i) {
        int i2 = this.f2198b + i;
        if (i2 > this.f2197a.length) {
            m3589d(Math.max(8, i2));
        }
        return this.f2197a;
    }

    protected int[] m3589d(int i) {
        Object obj = new int[i];
        System.arraycopy(this.f2197a, 0, obj, 0, Math.min(this.f2198b, obj.length));
        this.f2197a = obj;
        return obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof be)) {
            return false;
        }
        be beVar = (be) obj;
        int i = this.f2198b;
        if (i != beVar.f2198b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f2197a[i2] != beVar.f2197a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f2198b == 0) {
            return "[]";
        }
        int[] iArr = this.f2197a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < this.f2198b; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(iArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
