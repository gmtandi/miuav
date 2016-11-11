package com.amap.api.mapcore;

import android.os.Handler;

class bp implements aq {
    final Handler f1882a;
    private ab f1883b;
    private boolean f1884c;
    private boolean f1885d;
    private boolean f1886e;
    private boolean f1887f;
    private boolean f1888g;
    private boolean f1889h;
    private boolean f1890i;
    private boolean f1891j;
    private int f1892k;
    private int f1893l;
    private boolean f1894m;

    bp(ab abVar) {
        this.f1884c = true;
        this.f1885d = true;
        this.f1886e = true;
        this.f1887f = false;
        this.f1888g = true;
        this.f1889h = true;
        this.f1890i = true;
        this.f1891j = false;
        this.f1892k = 0;
        this.f1893l = 1;
        this.f1894m = true;
        this.f1882a = new bq(this);
        this.f1883b = abVar;
    }

    public void m3191a(int i) {
        this.f1892k = i;
        this.f1883b.m2292d(i);
    }

    public void m3192a(boolean z) {
        this.f1894m = z;
        this.f1882a.obtainMessage(4).sendToTarget();
    }

    public boolean m3193a() {
        return this.f1894m;
    }

    public void m3194b(int i) {
        this.f1893l = i;
        this.f1883b.m2295e(i);
    }

    public void m3195b(boolean z) {
        this.f1891j = z;
        this.f1882a.obtainMessage(1).sendToTarget();
    }

    public boolean m3196b() {
        return this.f1891j;
    }

    public void m3197c(boolean z) {
        this.f1889h = z;
        this.f1882a.obtainMessage(0).sendToTarget();
    }

    public boolean m3198c() {
        return this.f1889h;
    }

    public void m3199d(boolean z) {
        this.f1890i = z;
        this.f1882a.obtainMessage(2).sendToTarget();
    }

    public boolean m3200d() {
        return this.f1890i;
    }

    public void m3201e(boolean z) {
        this.f1887f = z;
        this.f1882a.obtainMessage(3).sendToTarget();
    }

    public boolean m3202e() {
        return this.f1887f;
    }

    public void m3203f(boolean z) {
        this.f1885d = z;
    }

    public boolean m3204f() {
        return this.f1885d;
    }

    public void m3205g(boolean z) {
        this.f1888g = z;
    }

    public boolean m3206g() {
        return this.f1888g;
    }

    public void m3207h(boolean z) {
        this.f1886e = z;
    }

    public boolean m3208h() {
        return this.f1886e;
    }

    public void m3209i(boolean z) {
        this.f1884c = z;
    }

    public boolean m3210i() {
        return this.f1884c;
    }

    public int m3211j() {
        return this.f1892k;
    }

    public void m3212j(boolean z) {
        m3209i(z);
        m3207h(z);
        m3205g(z);
        m3203f(z);
    }

    public int m3213k() {
        return this.f1893l;
    }
}
