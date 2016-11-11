package org.p122a.p137b.p183c;

/* renamed from: org.a.b.c.b */
public final class C3267b extends C3265c {
    private byte[] f15775a;
    private int f15776b;
    private int f15777c;

    public int m18078a(byte[] bArr, int i, int i2) {
        int c = m18084c();
        if (i2 > c) {
            i2 = c;
        }
        if (i2 > 0) {
            System.arraycopy(this.f15775a, this.f15776b, bArr, i, i2);
            m18079a(i2);
        }
        return i2;
    }

    public void m18079a(int i) {
        this.f15776b += i;
    }

    public void m18080a(byte[] bArr) {
        m18085c(bArr, 0, bArr.length);
    }

    public byte[] m18081a() {
        return this.f15775a;
    }

    public int m18082b() {
        return this.f15776b;
    }

    public void m18083b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    public int m18084c() {
        return this.f15777c - this.f15776b;
    }

    public void m18085c(byte[] bArr, int i, int i2) {
        this.f15775a = bArr;
        this.f15776b = i;
        this.f15777c = i + i2;
    }
}
