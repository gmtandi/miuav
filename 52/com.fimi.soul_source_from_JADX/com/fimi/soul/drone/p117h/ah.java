package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1495k;
import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.fimi.soul.drone.h.ah */
public class ah extends C1495k {
    private byte f7337b;
    private short f7338c;
    private byte f7339d;

    public ah(C1433a c1433a) {
        super(c1433a);
        this.f7337b = (byte) 0;
        this.f7338c = (short) 0;
        this.f7339d = (byte) 0;
    }

    public byte m10266a() {
        return this.f7337b;
    }

    public void m10267a(byte b) {
        this.f7337b = b;
    }

    public void m10268a(short s) {
        this.f7338c = (short) (s & Util.MASK_8BIT);
    }

    public short m10269b() {
        return this.f7338c;
    }

    public void m10270b(byte b) {
        this.f7339d = b;
    }

    public byte m10271c() {
        return this.f7339d;
    }

    public String toString() {
        return "GimbalVariable [pitch_angle=" + this.f7338c;
    }
}
