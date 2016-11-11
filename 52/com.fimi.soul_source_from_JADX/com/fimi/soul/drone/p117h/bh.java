package com.fimi.soul.drone.p117h;

import com.amap.api.maps.model.LatLng;
import java.io.Serializable;

/* renamed from: com.fimi.soul.drone.h.bh */
public class bh implements Serializable {
    public static final int f7483a = 1;
    public static final int f7484b = 2;
    public static final int f7485c = 3;
    public static final int f7486d = 1;
    public static final int f7487e = 0;
    public static final int f7488f = 0;
    public static final int f7489g = 1;
    private static final long serialVersionUID = -7608904935347290492L;
    private LatLng f7490h;
    private int f7491i;
    private int f7492j;
    private int f7493k;
    private int f7494l;
    private int f7495m;
    private boolean f7496n;

    public bh() {
        this.f7491i = 10;
        this.f7492j = f7488f;
        this.f7493k = f7488f;
        this.f7495m = f7489g;
    }

    public void m10495a(int i) {
        this.f7491i = i;
    }

    public void m10496a(LatLng latLng) {
        this.f7490h = latLng;
    }

    public void m10497a(boolean z) {
        this.f7496n = z;
    }

    public boolean m10498a() {
        return this.f7496n;
    }

    public LatLng m10499b() {
        return this.f7490h;
    }

    public void m10500b(int i) {
        this.f7492j = i;
    }

    public int m10501c() {
        return this.f7491i;
    }

    public void m10502c(int i) {
        this.f7493k = i;
    }

    public int m10503d() {
        return this.f7492j;
    }

    public void m10504d(int i) {
        this.f7494l = i;
    }

    public int m10505e() {
        return this.f7493k;
    }

    public void m10506e(int i) {
        this.f7495m = i;
    }

    public int m10507f() {
        return this.f7494l;
    }

    public int m10508g() {
        return this.f7495m;
    }
}
