package org.p122a.p123a.p167i.p168a;

import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: org.a.a.i.a.l */
class C3066l {
    protected int f15193a;
    protected int f15194b;
    protected int f15195c;
    protected int f15196d;
    protected long f15197e;
    protected byte[] f15198f;

    C3066l() {
        this.f15193a = 1732584193;
        this.f15194b = -271733879;
        this.f15195c = -1732584194;
        this.f15196d = 271733878;
        this.f15197e = 0;
        this.f15198f = new byte[64];
    }

    void m17266a(byte[] bArr) {
        int i = (int) (this.f15197e & 63);
        int i2 = 0;
        while ((bArr.length - i2) + i >= this.f15198f.length) {
            int length = this.f15198f.length - i;
            System.arraycopy(bArr, i2, this.f15198f, i, length);
            this.f15197e += (long) length;
            i2 += length;
            m17269b();
            i = 0;
        }
        if (i2 < bArr.length) {
            int length2 = bArr.length - i2;
            System.arraycopy(bArr, i2, this.f15198f, i, length2);
            this.f15197e += (long) length2;
            i2 = i + length2;
        }
    }

    protected void m17267a(int[] iArr) {
        this.f15193a = C3063i.m17197a((this.f15193a + C3063i.m17198a(this.f15194b, this.f15195c, this.f15196d)) + iArr[0], 3);
        this.f15196d = C3063i.m17197a((this.f15196d + C3063i.m17198a(this.f15193a, this.f15194b, this.f15195c)) + iArr[1], 7);
        this.f15195c = C3063i.m17197a((this.f15195c + C3063i.m17198a(this.f15196d, this.f15193a, this.f15194b)) + iArr[2], 11);
        this.f15194b = C3063i.m17197a((this.f15194b + C3063i.m17198a(this.f15195c, this.f15196d, this.f15193a)) + iArr[3], 19);
        this.f15193a = C3063i.m17197a((this.f15193a + C3063i.m17198a(this.f15194b, this.f15195c, this.f15196d)) + iArr[4], 3);
        this.f15196d = C3063i.m17197a((this.f15196d + C3063i.m17198a(this.f15193a, this.f15194b, this.f15195c)) + iArr[5], 7);
        this.f15195c = C3063i.m17197a((this.f15195c + C3063i.m17198a(this.f15196d, this.f15193a, this.f15194b)) + iArr[6], 11);
        this.f15194b = C3063i.m17197a((this.f15194b + C3063i.m17198a(this.f15195c, this.f15196d, this.f15193a)) + iArr[7], 19);
        this.f15193a = C3063i.m17197a((this.f15193a + C3063i.m17198a(this.f15194b, this.f15195c, this.f15196d)) + iArr[8], 3);
        this.f15196d = C3063i.m17197a((this.f15196d + C3063i.m17198a(this.f15193a, this.f15194b, this.f15195c)) + iArr[9], 7);
        this.f15195c = C3063i.m17197a((this.f15195c + C3063i.m17198a(this.f15196d, this.f15193a, this.f15194b)) + iArr[10], 11);
        this.f15194b = C3063i.m17197a((this.f15194b + C3063i.m17198a(this.f15195c, this.f15196d, this.f15193a)) + iArr[11], 19);
        this.f15193a = C3063i.m17197a((this.f15193a + C3063i.m17198a(this.f15194b, this.f15195c, this.f15196d)) + iArr[12], 3);
        this.f15196d = C3063i.m17197a((this.f15196d + C3063i.m17198a(this.f15193a, this.f15194b, this.f15195c)) + iArr[13], 7);
        this.f15195c = C3063i.m17197a((this.f15195c + C3063i.m17198a(this.f15196d, this.f15193a, this.f15194b)) + iArr[14], 11);
        this.f15194b = C3063i.m17197a((this.f15194b + C3063i.m17198a(this.f15195c, this.f15196d, this.f15193a)) + iArr[15], 19);
    }

    byte[] m17268a() {
        int i = (int) (this.f15197e & 63);
        i = i < 56 ? 56 - i : 120 - i;
        byte[] bArr = new byte[(i + 8)];
        bArr[0] = Byte.MIN_VALUE;
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i + i2] = (byte) ((int) ((this.f15197e * 8) >>> (i2 * 8)));
        }
        m17266a(bArr);
        byte[] bArr2 = new byte[16];
        C3063i.m17201a(bArr2, this.f15193a, 0);
        C3063i.m17201a(bArr2, this.f15194b, 4);
        C3063i.m17201a(bArr2, this.f15195c, 8);
        C3063i.m17201a(bArr2, this.f15196d, 12);
        return bArr2;
    }

    protected void m17269b() {
        int i;
        int[] iArr = new int[16];
        for (i = 0; i < 16; i++) {
            iArr[i] = (((this.f15198f[i * 4] & Util.MASK_8BIT) + ((this.f15198f[(i * 4) + 1] & Util.MASK_8BIT) << 8)) + ((this.f15198f[(i * 4) + 2] & Util.MASK_8BIT) << 16)) + ((this.f15198f[(i * 4) + 3] & Util.MASK_8BIT) << 24);
        }
        i = this.f15193a;
        int i2 = this.f15194b;
        int i3 = this.f15195c;
        int i4 = this.f15196d;
        m17267a(iArr);
        m17270b(iArr);
        m17271c(iArr);
        this.f15193a = i + this.f15193a;
        this.f15194b += i2;
        this.f15195c += i3;
        this.f15196d += i4;
    }

    protected void m17270b(int[] iArr) {
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17205b(this.f15194b, this.f15195c, this.f15196d)) + iArr[0]) + 1518500249, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17205b(this.f15193a, this.f15194b, this.f15195c)) + iArr[4]) + 1518500249, 5);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17205b(this.f15196d, this.f15193a, this.f15194b)) + iArr[8]) + 1518500249, 9);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17205b(this.f15195c, this.f15196d, this.f15193a)) + iArr[12]) + 1518500249, 13);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17205b(this.f15194b, this.f15195c, this.f15196d)) + iArr[1]) + 1518500249, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17205b(this.f15193a, this.f15194b, this.f15195c)) + iArr[5]) + 1518500249, 5);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17205b(this.f15196d, this.f15193a, this.f15194b)) + iArr[9]) + 1518500249, 9);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17205b(this.f15195c, this.f15196d, this.f15193a)) + iArr[13]) + 1518500249, 13);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17205b(this.f15194b, this.f15195c, this.f15196d)) + iArr[2]) + 1518500249, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17205b(this.f15193a, this.f15194b, this.f15195c)) + iArr[6]) + 1518500249, 5);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17205b(this.f15196d, this.f15193a, this.f15194b)) + iArr[10]) + 1518500249, 9);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17205b(this.f15195c, this.f15196d, this.f15193a)) + iArr[14]) + 1518500249, 13);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17205b(this.f15194b, this.f15195c, this.f15196d)) + iArr[3]) + 1518500249, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17205b(this.f15193a, this.f15194b, this.f15195c)) + iArr[7]) + 1518500249, 5);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17205b(this.f15196d, this.f15193a, this.f15194b)) + iArr[11]) + 1518500249, 9);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17205b(this.f15195c, this.f15196d, this.f15193a)) + iArr[15]) + 1518500249, 13);
    }

    protected void m17271c(int[] iArr) {
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17212c(this.f15194b, this.f15195c, this.f15196d)) + iArr[0]) + 1859775393, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17212c(this.f15193a, this.f15194b, this.f15195c)) + iArr[8]) + 1859775393, 9);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17212c(this.f15196d, this.f15193a, this.f15194b)) + iArr[4]) + 1859775393, 11);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17212c(this.f15195c, this.f15196d, this.f15193a)) + iArr[12]) + 1859775393, 15);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17212c(this.f15194b, this.f15195c, this.f15196d)) + iArr[2]) + 1859775393, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17212c(this.f15193a, this.f15194b, this.f15195c)) + iArr[10]) + 1859775393, 9);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17212c(this.f15196d, this.f15193a, this.f15194b)) + iArr[6]) + 1859775393, 11);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17212c(this.f15195c, this.f15196d, this.f15193a)) + iArr[14]) + 1859775393, 15);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17212c(this.f15194b, this.f15195c, this.f15196d)) + iArr[1]) + 1859775393, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17212c(this.f15193a, this.f15194b, this.f15195c)) + iArr[9]) + 1859775393, 9);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17212c(this.f15196d, this.f15193a, this.f15194b)) + iArr[5]) + 1859775393, 11);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17212c(this.f15195c, this.f15196d, this.f15193a)) + iArr[13]) + 1859775393, 15);
        this.f15193a = C3063i.m17197a(((this.f15193a + C3063i.m17212c(this.f15194b, this.f15195c, this.f15196d)) + iArr[3]) + 1859775393, 3);
        this.f15196d = C3063i.m17197a(((this.f15196d + C3063i.m17212c(this.f15193a, this.f15194b, this.f15195c)) + iArr[11]) + 1859775393, 9);
        this.f15195c = C3063i.m17197a(((this.f15195c + C3063i.m17212c(this.f15196d, this.f15193a, this.f15194b)) + iArr[7]) + 1859775393, 11);
        this.f15194b = C3063i.m17197a(((this.f15194b + C3063i.m17212c(this.f15195c, this.f15196d, this.f15193a)) + iArr[15]) + 1859775393, 15);
    }
}
