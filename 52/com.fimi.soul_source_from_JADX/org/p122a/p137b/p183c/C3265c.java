package org.p122a.p137b.p183c;

/* renamed from: org.a.b.c.c */
public abstract class C3265c {
    public abstract int m18068a(byte[] bArr, int i, int i2);

    public void m18069a(int i) {
    }

    public byte[] m18070a() {
        return null;
    }

    public int m18071b() {
        return 0;
    }

    public void m18072b(byte[] bArr) {
        m18073b(bArr, 0, bArr.length);
    }

    public abstract void m18073b(byte[] bArr, int i, int i2);

    public int m18074c() {
        return -1;
    }

    public int m18075d(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int a = m18068a(bArr, i + i3, i2 - i3);
            if (a <= 0) {
                throw new C3268d("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes.");
            }
            i3 += a;
        }
        return i3;
    }
}
