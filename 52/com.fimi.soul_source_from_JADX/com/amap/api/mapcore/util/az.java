package com.amap.api.mapcore.util;

public class az {
    private final bi f2148a;
    private short[] f2149b;
    private float[] f2150c;
    private int f2151d;
    private final be f2152e;
    private final bi f2153f;

    public az() {
        this.f2148a = new bi();
        this.f2152e = new be();
        this.f2153f = new bi();
    }

    private static int m3494a(float f, float f2, float f3, float f4, float f5, float f6) {
        return (int) Math.signum((((f6 - f4) * f) + ((f2 - f6) * f3)) + ((f4 - f2) * f5));
    }

    private int m3495a(int i) {
        short[] sArr = this.f2149b;
        int i2 = sArr[m3501d(i)] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[m3502e(i)] * 2;
        float[] fArr = this.f2150c;
        return m3494a(fArr[i2], fArr[i2 + 1], fArr[i3], fArr[i3 + 1], fArr[i4], fArr[i4 + 1]);
    }

    private void m3496a() {
        int[] iArr = this.f2152e.f2197a;
        while (this.f2151d > 3) {
            int b = m3497b();
            m3500c(b);
            int d = m3501d(b);
            if (b == this.f2151d) {
                b = 0;
            }
            iArr[d] = m3495a(d);
            iArr[b] = m3495a(b);
        }
        if (this.f2151d == 3) {
            bi biVar = this.f2153f;
            short[] sArr = this.f2149b;
            biVar.m3595a(sArr[0]);
            biVar.m3595a(sArr[1]);
            biVar.m3595a(sArr[2]);
        }
    }

    private int m3497b() {
        int i;
        int i2 = this.f2151d;
        for (i = 0; i < i2; i++) {
            if (m3498b(i)) {
                return i;
            }
        }
        int[] iArr = this.f2152e.f2197a;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != -1) {
                return i;
            }
        }
        return 0;
    }

    private boolean m3498b(int i) {
        int[] iArr = this.f2152e.f2197a;
        if (iArr[i] == -1) {
            return false;
        }
        int d = m3501d(i);
        int e = m3502e(i);
        short[] sArr = this.f2149b;
        int i2 = sArr[d] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e] * 2;
        float[] fArr = this.f2150c;
        float f = fArr[i2];
        float f2 = fArr[i2 + 1];
        float f3 = fArr[i3];
        float f4 = fArr[i3 + 1];
        float f5 = fArr[i4];
        float f6 = fArr[i4 + 1];
        int e2 = m3502e(e);
        while (e2 != d) {
            if (iArr[e2] != 1) {
                i4 = sArr[e2] * 2;
                float f7 = fArr[i4];
                float f8 = fArr[i4 + 1];
                if (m3494a(f5, f6, f, f2, f7, f8) >= 0 && m3494a(f, f2, f3, f4, f7, f8) >= 0 && m3494a(f3, f4, f5, f6, f7, f8) >= 0) {
                    return false;
                }
            }
            e2 = m3502e(e2);
        }
        return true;
    }

    private static boolean m3499b(float[] fArr, int i, int i2) {
        if (i2 <= 2) {
            return false;
        }
        float f = 0.0f;
        for (int i3 = i; i3 < (i + i2) - 3; i3 += 2) {
            f += (fArr[i3] * fArr[i3 + 3]) - (fArr[i3 + 1] * fArr[i3 + 2]);
        }
        return ((fArr[(i + i2) + -2] * fArr[i + 1]) + f) - (fArr[i] * fArr[(i + i2) + -1]) < 0.0f;
    }

    private void m3500c(int i) {
        short[] sArr = this.f2149b;
        bi biVar = this.f2153f;
        biVar.m3595a(sArr[m3501d(i)]);
        biVar.m3595a(sArr[i]);
        biVar.m3595a(sArr[m3502e(i)]);
        this.f2148a.m3596b(i);
        this.f2152e.m3587b(i);
        this.f2151d--;
    }

    private int m3501d(int i) {
        if (i == 0) {
            i = this.f2151d;
        }
        return i - 1;
    }

    private int m3502e(int i) {
        return (i + 1) % this.f2151d;
    }

    public bi m3503a(float[] fArr) {
        return m3504a(fArr, 0, fArr.length);
    }

    public bi m3504a(float[] fArr, int i, int i2) {
        int i3;
        this.f2150c = fArr;
        int i4 = i2 / 2;
        this.f2151d = i4;
        int i5 = i / 2;
        bi biVar = this.f2148a;
        biVar.m3594a();
        biVar.m3597c(i4);
        biVar.f2202b = i4;
        short[] sArr = biVar.f2201a;
        this.f2149b = sArr;
        if (m3499b(fArr, i, i2)) {
            for (i3 = 0; i3 < i4; i3 = (short) (i3 + 1)) {
                sArr[i3] = (short) (i5 + i3);
            }
        } else {
            int i6 = i4 - 1;
            for (i3 = 0; i3 < i4; i3++) {
                sArr[i3] = (short) ((i5 + i6) - i3);
            }
        }
        be beVar = this.f2152e;
        beVar.m3585a();
        beVar.m3588c(i4);
        for (i3 = 0; i3 < i4; i3++) {
            beVar.m3586a(m3495a(i3));
        }
        biVar = this.f2153f;
        biVar.m3594a();
        biVar.m3597c(Math.max(0, i4 - 2) * 3);
        m3496a();
        return biVar;
    }
}
