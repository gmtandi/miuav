package com.fimi.soul.drone.p114e;

import java.io.Serializable;

/* renamed from: com.fimi.soul.drone.e.a */
public class C1532a implements Serializable {
    private int f7171a;
    private int f7172b;
    private int f7173c;
    private long f7174d;
    private long f7175e;
    private long f7176f;
    private long f7177g;
    private char f7178h;

    public C1532a(int i, int i2, int i3, char c, long j, long j2, long j3, long j4) {
        this.f7173c = i;
        this.f7171a = i2;
        this.f7172b = i3;
        this.f7178h = c;
        this.f7174d = j;
        this.f7175e = j2;
        this.f7176f = j3;
        this.f7177g = j4;
    }

    public int m10049a() {
        return this.f7171a;
    }

    public void m10050a(char c) {
        this.f7178h = c;
    }

    public void m10051a(int i) {
        this.f7171a = i;
    }

    public void m10052a(long j) {
        this.f7174d = j;
    }

    public void m10053a(short s) {
        this.f7172b = s;
    }

    public int m10054b() {
        return this.f7173c;
    }

    public void m10055b(int i) {
        this.f7173c = i;
    }

    public void m10056b(long j) {
        this.f7175e = j;
    }

    public int m10057c() {
        return this.f7172b;
    }

    public void m10058c(long j) {
        this.f7177g = j;
    }

    public long m10059d() {
        return this.f7174d;
    }

    public void m10060d(long j) {
        this.f7176f = j;
    }

    public long m10061e() {
        return this.f7175e;
    }

    public long m10062f() {
        return this.f7177g;
    }

    public long m10063g() {
        return this.f7176f;
    }

    public long m10064h() {
        return Long.valueOf((long) this.f7178h).longValue();
    }

    public String toString() {
        return "DroneUpdateInfo{model=" + this.f7171a + ", version=" + this.f7172b + ", type=" + this.f7173c + ", IDA=" + this.f7174d + ", IDB=" + this.f7175e + ", IDC=" + this.f7176f + ", IDD=" + this.f7177g + ", hwVersion=" + this.f7178h + '}';
    }
}
