package com.fimi.soul.drone.p107c.p108a;

import com.tencent.mm.sdk.platformtools.Util;
import java.math.BigInteger;
import java.nio.ByteBuffer;

/* renamed from: com.fimi.soul.drone.c.a.d */
public class C1466d {
    public static final int f7003a = 512;
    public ByteBuffer f7004b;
    public int f7005c;

    public C1466d() {
        this.f7004b = ByteBuffer.allocate(f7003a);
    }

    public ByteBuffer m9819a() {
        return this.f7004b;
    }

    public void m9820a(byte b) {
        this.f7004b.put(b);
    }

    public void m9821a(char c) {
        m9820a((byte) (c >> 0));
        m9820a((byte) (c >> 8));
    }

    public void m9822a(double d) {
        m9830b(Double.doubleToLongBits(d));
    }

    public void m9823a(float f) {
        m9829b(Float.floatToIntBits(f));
    }

    public void m9824a(int i) {
        m9820a((byte) (i >> 0));
        m9820a((byte) (i >> 8));
        m9820a((byte) (i >> 16));
    }

    public void m9825a(long j) {
        m9820a((byte) ((int) (j >> null)));
        m9820a((byte) ((int) (j >> 8)));
        m9820a((byte) ((int) (j >> 16)));
        m9820a((byte) ((int) (j >> 24)));
    }

    public void m9826a(short s) {
        m9820a((byte) (s >> 0));
        m9820a((byte) (s >> 8));
    }

    public int m9827b() {
        return this.f7004b.position() + 1;
    }

    public void m9828b(byte b) {
        m9820a(b);
    }

    public void m9829b(int i) {
        m9820a((byte) (i >> 0));
        m9820a((byte) (i >> 8));
        m9820a((byte) (i >> 16));
        m9820a((byte) (i >> 24));
    }

    public void m9830b(long j) {
        m9820a((byte) ((int) (j >> null)));
        m9820a((byte) ((int) (j >> 8)));
        m9820a((byte) ((int) (j >> 16)));
        m9820a((byte) ((int) (j >> 24)));
        m9820a((byte) ((int) (j >> 32)));
        m9820a((byte) ((int) (j >> 40)));
        m9820a((byte) ((int) (j >> 48)));
        m9820a((byte) ((int) (j >> 56)));
    }

    public void m9831c() {
        this.f7005c = 0;
    }

    public void m9832c(int i) {
        this.f7005c = i;
    }

    public byte m9833d() {
        byte b = (byte) (0 | (this.f7004b.get(this.f7005c + 0) & Util.MASK_8BIT));
        this.f7005c++;
        return b;
    }

    public short m9834e() {
        short s = (short) (((short) (0 | ((this.f7004b.get(this.f7005c + 1) & Util.MASK_8BIT) << 8))) | (this.f7004b.get(this.f7005c + 0) & Util.MASK_8BIT));
        this.f7005c += 2;
        return s;
    }

    public int m9835f() {
        int i = (((0 | ((this.f7004b.get(this.f7005c + 3) & Util.MASK_8BIT) << 24)) | ((this.f7004b.get(this.f7005c + 2) & Util.MASK_8BIT) << 16)) | ((this.f7004b.get(this.f7005c + 1) & Util.MASK_8BIT) << 8)) | (this.f7004b.get(this.f7005c + 0) & Util.MASK_8BIT);
        this.f7005c += 4;
        return i;
    }

    public long m9836g() {
        long j = (((((((0 | ((((long) this.f7004b.get(this.f7005c + 7)) & 255) << 56)) | ((((long) this.f7004b.get(this.f7005c + 6)) & 255) << 48)) | ((((long) this.f7004b.get(this.f7005c + 5)) & 255) << 40)) | ((((long) this.f7004b.get(this.f7005c + 4)) & 255) << 32)) | ((((long) this.f7004b.get(this.f7005c + 3)) & 255) << 24)) | ((((long) this.f7004b.get(this.f7005c + 2)) & 255) << 16)) | ((((long) this.f7004b.get(this.f7005c + 1)) & 255) << 8)) | (((long) this.f7004b.get(this.f7005c + 0)) & 255);
        this.f7005c += 8;
        return j;
    }

    public long m9837h() {
        long j = (((((((0 | ((((long) this.f7004b.get(this.f7005c + 0)) & 255) << 56)) | ((((long) this.f7004b.get(this.f7005c + 1)) & 255) << 48)) | ((((long) this.f7004b.get(this.f7005c + 2)) & 255) << 40)) | ((((long) this.f7004b.get(this.f7005c + 3)) & 255) << 32)) | ((((long) this.f7004b.get(this.f7005c + 4)) & 255) << 24)) | ((((long) this.f7004b.get(this.f7005c + 5)) & 255) << 16)) | ((((long) this.f7004b.get(this.f7005c + 6)) & 255) << 8)) | (((long) this.f7004b.get(this.f7005c + 7)) & 255);
        this.f7005c += 8;
        return j;
    }

    public float m9838i() {
        return Float.intBitsToFloat(m9835f());
    }

    public float m9839j() {
        byte b = (byte) ((this.f7004b.get(this.f7005c + 2) & Util.MASK_8BIT) | 0);
        byte b2 = (byte) ((this.f7004b.get(this.f7005c + 1) & Util.MASK_8BIT) | 0);
        byte b3 = (byte) ((this.f7004b.get(this.f7005c + 0) & Util.MASK_8BIT) | 0);
        BigInteger bigInteger = new BigInteger(1, new byte[]{b, b2, b3});
        this.f7005c += 3;
        return Float.parseFloat(bigInteger.toString());
    }

    public Double m9840k() {
        return Double.valueOf(Double.longBitsToDouble(m9836g()));
    }

    public char m9841l() {
        char c = (char) (((char) (0 | ((this.f7004b.get(this.f7005c + 1) & Util.MASK_8BIT) << 8))) | (this.f7004b.get(this.f7005c + 0) & Util.MASK_8BIT));
        this.f7005c += 2;
        return c;
    }

    public long m9842m() {
        long j = (((0 | ((((long) this.f7004b.get(this.f7005c + 3)) & 255) << 24)) | ((((long) this.f7004b.get(this.f7005c + 2)) & 255) << 16)) | ((((long) this.f7004b.get(this.f7005c + 1)) & 255) << 8)) | (((long) this.f7004b.get(this.f7005c + 0)) & 255);
        this.f7005c += 4;
        return j;
    }
}
