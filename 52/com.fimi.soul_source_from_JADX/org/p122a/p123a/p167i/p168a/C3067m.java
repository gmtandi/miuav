package org.p122a.p123a.p167i.p168a;

import android.util.Base64;
import com.tencent.mm.sdk.platformtools.Util;
import org.apache.http.impl.auth.NTLMEngineException;
import org.apache.http.util.EncodingUtils;

/* renamed from: org.a.a.i.a.m */
class C3067m {
    private byte[] f15199a;
    private int f15200b;

    C3067m() {
        this.f15199a = null;
        this.f15200b = 0;
    }

    C3067m(String str, int i) {
        int i2 = 0;
        this.f15199a = null;
        this.f15200b = 0;
        this.f15199a = Base64.decode(EncodingUtils.getBytes(str, "ASCII"), 2);
        if (this.f15199a.length < C3063i.f15162s.length) {
            throw new NTLMEngineException("NTLM message decoding error - packet too short");
        }
        while (i2 < C3063i.f15162s.length) {
            if (this.f15199a[i2] != C3063i.f15162s[i2]) {
                throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
            }
            i2++;
        }
        i2 = m17280c(C3063i.f15162s.length);
        if (i2 != i) {
            throw new NTLMEngineException("NTLM type " + Integer.toString(i) + " message expected - instead got type " + Integer.toString(i2));
        }
        this.f15200b = this.f15199a.length;
    }

    protected byte m17272a(int i) {
        if (this.f15199a.length >= i + 1) {
            return this.f15199a[i];
        }
        throw new NTLMEngineException("NTLM: Message too short");
    }

    protected int m17273a() {
        return C3063i.f15162s.length + 4;
    }

    protected void m17274a(byte b) {
        this.f15199a[this.f15200b] = b;
        this.f15200b++;
    }

    protected void m17275a(int i, int i2) {
        this.f15199a = new byte[i];
        this.f15200b = 0;
        m17276a(C3063i.f15162s);
        m17284f(i2);
    }

    protected void m17276a(byte[] bArr) {
        if (bArr != null) {
            for (byte b : bArr) {
                this.f15199a[this.f15200b] = b;
                this.f15200b++;
            }
        }
    }

    protected void m17277a(byte[] bArr, int i) {
        if (this.f15199a.length < bArr.length + i) {
            throw new NTLMEngineException("NTLM: Message too short");
        }
        System.arraycopy(this.f15199a, i, bArr, 0, bArr.length);
    }

    protected int m17278b() {
        return this.f15200b;
    }

    protected int m17279b(int i) {
        return C3063i.m17229f(this.f15199a, i);
    }

    protected int m17280c(int i) {
        return C3063i.m17225e(this.f15199a, i);
    }

    String m17281c() {
        byte[] bArr;
        if (this.f15199a.length > this.f15200b) {
            bArr = new byte[this.f15200b];
            System.arraycopy(this.f15199a, 0, bArr, 0, this.f15200b);
        } else {
            bArr = this.f15199a;
        }
        return EncodingUtils.getAsciiString(Base64.encode(bArr, 2));
    }

    protected byte[] m17282d(int i) {
        return C3063i.m17233g(this.f15199a, i);
    }

    protected void m17283e(int i) {
        m17274a((byte) (i & Util.MASK_8BIT));
        m17274a((byte) ((i >> 8) & Util.MASK_8BIT));
    }

    protected void m17284f(int i) {
        m17274a((byte) (i & Util.MASK_8BIT));
        m17274a((byte) ((i >> 8) & Util.MASK_8BIT));
        m17274a((byte) ((i >> 16) & Util.MASK_8BIT));
        m17274a((byte) ((i >> 24) & Util.MASK_8BIT));
    }
}
