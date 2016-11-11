package com.fimi.soul.biz.p090b;

import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.Polyline;
import com.fimi.soul.entity.FlyActionBean;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.fimi.soul.biz.b.f */
public class C1247f {
    private Circle f5664a;
    private volatile List<LatLng> f5665b;
    private volatile List<LatLng> f5666c;
    private Polyline f5667d;
    private volatile FlyActionBean f5668e;
    private volatile int f5669f;
    private Marker f5670g;
    private boolean f5671h;
    private List<Marker> f5672i;
    private volatile List<FlyActionBean> f5673j;

    public C1247f() {
        this.f5665b = new ArrayList();
        this.f5666c = new ArrayList();
        this.f5669f = 30;
        this.f5672i = new ArrayList();
        this.f5673j = new ArrayList();
    }

    public static C1247f m8565k() {
        return C1248g.f5674a;
    }

    public Circle m8566a() {
        return this.f5664a;
    }

    public void m8567a(int i) {
        this.f5669f = i;
    }

    public void m8568a(Circle circle) {
        this.f5664a = circle;
    }

    public void m8569a(Marker marker) {
        this.f5670g = marker;
    }

    public void m8570a(Polyline polyline) {
        this.f5667d = polyline;
    }

    public void m8571a(FlyActionBean flyActionBean) {
        this.f5668e = flyActionBean;
    }

    public void m8572a(List<FlyActionBean> list) {
        this.f5673j = list;
    }

    public void m8573a(boolean z) {
        this.f5671h = z;
    }

    public List<LatLng> m8574b() {
        return this.f5665b;
    }

    public Marker m8575c() {
        return this.f5670g;
    }

    public boolean m8576d() {
        return this.f5671h;
    }

    public List<Marker> m8577e() {
        return this.f5672i;
    }

    public List<FlyActionBean> m8578f() {
        return this.f5673j;
    }

    public int m8579g() {
        return this.f5669f;
    }

    public List<LatLng> m8580h() {
        return this.f5666c;
    }

    public Polyline m8581i() {
        return this.f5667d;
    }

    public FlyActionBean m8582j() {
        return this.f5668e;
    }

    public void m8583l() {
        if (this.f5672i != null) {
            for (Marker remove : this.f5672i) {
                remove.remove();
            }
            this.f5672i.clear();
        }
        if (this.f5667d != null) {
            this.f5667d.remove();
            this.f5667d = null;
        }
        this.f5666c.clear();
        this.f5673j.clear();
        this.f5665b.clear();
    }
}
