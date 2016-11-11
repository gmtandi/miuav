package com.fimi.soul.utils;

import java.io.Serializable;

/* renamed from: com.fimi.soul.utils.x */
public class C1984x implements Serializable {
    private String f10219a;
    private String f10220b;
    private long f10221c;
    private double f10222d;

    public String m12523a() {
        return this.f10219a;
    }

    public void m12524a(double d) {
        this.f10222d = d;
    }

    public void m12525a(long j) {
        this.f10221c = j;
    }

    public void m12526a(String str) {
        this.f10219a = str;
    }

    public String m12527b() {
        return this.f10220b;
    }

    public void m12528b(String str) {
        this.f10220b = str;
    }

    public long m12529c() {
        return this.f10221c;
    }

    public double m12530d() {
        return this.f10222d;
    }

    public String toString() {
        return "FlyRecordEntity{user_id='" + this.f10219a + '\'' + ", drone_id='" + this.f10220b + '\'' + ", flyTime=" + this.f10221c + ", flyDistance=" + this.f10222d + '}';
    }
}
