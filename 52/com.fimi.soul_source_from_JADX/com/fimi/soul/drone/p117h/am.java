package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.fimi.soul.drone.C1584h;

/* renamed from: com.fimi.soul.drone.h.am */
public class am extends C1495k {
    private byte f7382b;
    private byte f7383c;
    private byte f7384d;
    private byte f7385e;
    private byte f7386f;

    public am(C1433a c1433a) {
        super(c1433a);
        c1433a.m9589a(C1584h.PAIR_CODE);
    }

    public byte m10343a() {
        return this.f7382b;
    }

    public void m10344a(byte b) {
        this.f7382b = b;
    }

    public byte m10345b() {
        return this.f7383c;
    }

    public void m10346b(byte b) {
        this.f7383c = b;
    }

    public byte m10347c() {
        return this.f7384d;
    }

    public void m10348c(byte b) {
        this.f7384d = b;
    }

    public byte m10349d() {
        return this.f7385e;
    }

    public void m10350d(byte b) {
        this.f7385e = b;
    }

    public byte m10351e() {
        return this.f7386f;
    }

    public void m10352e(byte b) {
        this.f7386f = b;
    }

    public String toString() {
        return "PairCodeStatus{msg_id=" + this.f7382b + ", target_id=" + this.f7383c + ", ask_status=" + this.f7384d + ", ask_type=" + this.f7385e + ", pair_status=" + this.f7386f + '}';
    }
}
