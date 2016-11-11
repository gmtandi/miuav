package com.fimi.soul.module.update.p121a;

import java.io.Serializable;

/* renamed from: com.fimi.soul.module.update.a.e */
public class C1905e implements Serializable {
    private int f9797a;
    private int f9798b;
    private String f9799c;
    private int f9800d;
    private long f9801e;
    private long f9802f;
    private long f9803g;
    private long f9804h;
    private String f9805i;

    public C1905e(int i, int i2, int i3, String str, long j, long j2, long j3, long j4) {
        this.f9797a = i2;
        this.f9798b = i3;
        this.f9799c = str;
        this.f9800d = i;
        this.f9801e = j;
        this.f9802f = j2;
        this.f9803g = j3;
        this.f9804h = j4;
        this.f9805i = String.valueOf(j) + String.valueOf(j2) + String.valueOf(j3) + String.valueOf(j4);
    }

    public C1905e(String str, int i, int i2) {
        this.f9799c = str;
        this.f9800d = i;
        this.f9798b = i2;
    }

    public int m12042a() {
        return this.f9797a;
    }

    public void m12043a(int i) {
        this.f9797a = i;
    }

    public void m12044a(long j) {
        this.f9801e = j;
    }

    public void m12045a(String str) {
        this.f9799c = str;
    }

    public int m12046b() {
        return this.f9798b;
    }

    public void m12047b(int i) {
        this.f9798b = i;
    }

    public void m12048b(long j) {
        this.f9802f = j;
    }

    public void m12049b(String str) {
        this.f9805i = str;
    }

    public String m12050c() {
        return this.f9799c;
    }

    public void m12051c(int i) {
        this.f9800d = i;
    }

    public void m12052c(long j) {
        this.f9803g = j;
    }

    public int m12053d() {
        return this.f9800d;
    }

    public void m12054d(long j) {
        this.f9804h = j;
    }

    public long m12055e() {
        return this.f9801e;
    }

    public long m12056f() {
        return this.f9802f;
    }

    public long m12057g() {
        return this.f9803g;
    }

    public long m12058h() {
        return this.f9804h;
    }

    public String m12059i() {
        return this.f9805i;
    }
}
