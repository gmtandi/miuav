package com.p017b.p018a;

/* renamed from: com.b.a.ap */
public class ap extends as {
    private aq f3833p;
    private long f3834q;

    public ap() {
        this.f3834q = -1;
    }

    void m5553a(float f) {
    }

    public void m5554a(aq aqVar) {
        this.f3833p = aqVar;
    }

    boolean m5555c(long j) {
        long j2 = 0;
        if (this.i == 0) {
            this.i = 1;
            if (this.h < 0) {
                this.g = j;
            } else {
                this.g = j - this.h;
                this.h = -1;
            }
        }
        if (this.f3833p != null) {
            long j3 = j - this.g;
            if (this.f3834q >= 0) {
                j2 = j - this.f3834q;
            }
            this.f3834q = j;
            this.f3833p.m5557a(this, j3, j2);
        }
        return false;
    }

    void m5556n() {
    }
}
