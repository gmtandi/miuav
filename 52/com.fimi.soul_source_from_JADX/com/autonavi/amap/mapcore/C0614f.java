package com.autonavi.amap.mapcore;

/* renamed from: com.autonavi.amap.mapcore.f */
class C0614f {
    byte[] f3729a;
    String f3730b;
    int f3731c;
    String f3732d;
    int f3733e;

    public C0614f(byte[] bArr) {
        try {
            this.f3731c = (int) (System.currentTimeMillis() / 1000);
            byte b = bArr[4];
            this.f3730b = new String(bArr, 5, b);
            int i = b + 5;
            int i2 = i + 1;
            b = bArr[i];
            this.f3732d = new String(bArr, i2, b);
            i = b + i2;
            this.f3733e = Convert.getInt(bArr, i);
            i += 4;
            this.f3729a = bArr;
        } catch (Exception e) {
            this.f3729a = null;
        }
    }

    public void m5300a(int i) {
        if (this.f3729a != null) {
            this.f3731c = (int) (System.currentTimeMillis() / 1000);
            int i2 = this.f3729a[4] + 5;
            Convert.writeInt(this.f3729a, this.f3729a[i2] + (i2 + 1), i);
            this.f3733e = i;
        }
    }
}
