package org.p122a.p123a.p167i.p168a;

import java.security.MessageDigest;
import org.apache.http.impl.auth.NTLMEngineException;

/* renamed from: org.a.a.i.a.k */
class C3065k {
    protected byte[] f15190a;
    protected byte[] f15191b;
    protected MessageDigest f15192c;

    C3065k(byte[] bArr) {
        try {
            this.f15192c = MessageDigest.getInstance("MD5");
            this.f15190a = new byte[64];
            this.f15191b = new byte[64];
            int length = bArr.length;
            if (length > 64) {
                this.f15192c.update(bArr);
                bArr = this.f15192c.digest();
                length = bArr.length;
            }
            int i = 0;
            while (i < length) {
                this.f15190a[i] = (byte) (bArr[i] ^ 54);
                this.f15191b[i] = (byte) (bArr[i] ^ 92);
                i++;
            }
            for (length = i; length < 64; length++) {
                this.f15190a[length] = (byte) 54;
                this.f15191b[length] = (byte) 92;
            }
            this.f15192c.reset();
            this.f15192c.update(this.f15190a);
        } catch (Throwable e) {
            throw new NTLMEngineException("Error getting md5 message digest implementation: " + e.getMessage(), e);
        }
    }

    void m17263a(byte[] bArr) {
        this.f15192c.update(bArr);
    }

    void m17264a(byte[] bArr, int i, int i2) {
        this.f15192c.update(bArr, i, i2);
    }

    byte[] m17265a() {
        byte[] digest = this.f15192c.digest();
        this.f15192c.update(this.f15191b);
        return this.f15192c.digest(digest);
    }
}
