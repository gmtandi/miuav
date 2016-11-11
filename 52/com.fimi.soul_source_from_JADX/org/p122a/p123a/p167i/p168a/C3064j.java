package org.p122a.p123a.p167i.p168a;

import java.security.Key;
import java.util.Arrays;
import javax.crypto.Cipher;
import org.apache.http.impl.auth.NTLMEngineException;

/* renamed from: org.a.a.i.a.j */
public class C3064j {
    protected final String f15164a;
    protected final String f15165b;
    protected final String f15166c;
    protected final byte[] f15167d;
    protected final String f15168e;
    protected final byte[] f15169f;
    protected byte[] f15170g;
    protected byte[] f15171h;
    protected byte[] f15172i;
    protected byte[] f15173j;
    protected byte[] f15174k;
    protected byte[] f15175l;
    protected byte[] f15176m;
    protected byte[] f15177n;
    protected byte[] f15178o;
    protected byte[] f15179p;
    protected byte[] f15180q;
    protected byte[] f15181r;
    protected byte[] f15182s;
    protected byte[] f15183t;
    protected byte[] f15184u;
    protected byte[] f15185v;
    protected byte[] f15186w;
    protected byte[] f15187x;
    protected byte[] f15188y;
    protected byte[] f15189z;

    public C3064j(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
        this(str, str2, str3, bArr, str4, bArr2, null, null, null, null);
    }

    public C3064j(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
        this.f15174k = null;
        this.f15175l = null;
        this.f15176m = null;
        this.f15177n = null;
        this.f15178o = null;
        this.f15179p = null;
        this.f15180q = null;
        this.f15181r = null;
        this.f15182s = null;
        this.f15183t = null;
        this.f15184u = null;
        this.f15185v = null;
        this.f15186w = null;
        this.f15187x = null;
        this.f15188y = null;
        this.f15189z = null;
        this.f15164a = str;
        this.f15168e = str4;
        this.f15165b = str2;
        this.f15166c = str3;
        this.f15167d = bArr;
        this.f15169f = bArr2;
        this.f15170g = bArr3;
        this.f15171h = bArr4;
        this.f15172i = bArr5;
        this.f15173j = bArr6;
    }

    public byte[] m17243a() {
        if (this.f15170g == null) {
            this.f15170g = C3063i.m17227e();
        }
        return this.f15170g;
    }

    public byte[] m17244b() {
        if (this.f15171h == null) {
            this.f15171h = C3063i.m17227e();
        }
        return this.f15171h;
    }

    public byte[] m17245c() {
        if (this.f15172i == null) {
            this.f15172i = C3063i.m17231f();
        }
        return this.f15172i;
    }

    public byte[] m17246d() {
        if (this.f15174k == null) {
            this.f15174k = C3063i.m17236i(this.f15166c);
        }
        return this.f15174k;
    }

    public byte[] m17247e() {
        if (this.f15175l == null) {
            this.f15175l = C3063i.m17223d(m17246d(), this.f15167d);
        }
        return this.f15175l;
    }

    public byte[] m17248f() {
        if (this.f15176m == null) {
            this.f15176m = C3063i.m17237j(this.f15166c);
        }
        return this.f15176m;
    }

    public byte[] m17249g() {
        if (this.f15177n == null) {
            this.f15177n = C3063i.m17223d(m17248f(), this.f15167d);
        }
        return this.f15177n;
    }

    public byte[] m17250h() {
        if (this.f15179p == null) {
            this.f15179p = C3063i.m17216c(this.f15164a, this.f15165b, m17248f());
        }
        return this.f15179p;
    }

    public byte[] m17251i() {
        if (this.f15178o == null) {
            this.f15178o = C3063i.m17221d(this.f15164a, this.f15165b, m17248f());
        }
        return this.f15178o;
    }

    public byte[] m17252j() {
        if (this.f15173j == null) {
            long currentTimeMillis = 10000 * (System.currentTimeMillis() + 11644473600000L);
            this.f15173j = new byte[8];
            for (int i = 0; i < 8; i++) {
                this.f15173j[i] = (byte) ((int) currentTimeMillis);
                currentTimeMillis >>>= 8;
            }
        }
        return this.f15173j;
    }

    public byte[] m17253k() {
        if (this.f15181r == null) {
            this.f15181r = C3063i.m17228e(m17244b(), this.f15169f, m17252j());
        }
        return this.f15181r;
    }

    public byte[] m17254l() {
        if (this.f15182s == null) {
            this.f15182s = C3063i.m17224d(m17251i(), this.f15167d, m17253k());
        }
        return this.f15182s;
    }

    public byte[] m17255m() {
        if (this.f15180q == null) {
            this.f15180q = C3063i.m17224d(m17250h(), this.f15167d, m17243a());
        }
        return this.f15180q;
    }

    public byte[] m17256n() {
        if (this.f15183t == null) {
            this.f15183t = C3063i.m17204a(m17248f(), this.f15167d, m17243a());
        }
        return this.f15183t;
    }

    public byte[] m17257o() {
        if (this.f15184u == null) {
            Object a = m17243a();
            this.f15184u = new byte[24];
            System.arraycopy(a, 0, this.f15184u, 0, a.length);
            Arrays.fill(this.f15184u, a.length, this.f15184u.length, (byte) 0);
        }
        return this.f15184u;
    }

    public byte[] m17258p() {
        if (this.f15185v == null) {
            this.f15185v = new byte[16];
            System.arraycopy(m17246d(), 0, this.f15185v, 0, 8);
            Arrays.fill(this.f15185v, 8, 16, (byte) 0);
        }
        return this.f15185v;
    }

    public byte[] m17259q() {
        if (this.f15186w == null) {
            C3066l c3066l = new C3066l();
            c3066l.m17266a(m17248f());
            this.f15186w = c3066l.m17268a();
        }
        return this.f15186w;
    }

    public byte[] m17260r() {
        if (this.f15187x == null) {
            byte[] i = m17251i();
            byte[] bArr = new byte[16];
            System.arraycopy(m17254l(), 0, bArr, 0, 16);
            this.f15187x = C3063i.m17203a(bArr, i);
        }
        return this.f15187x;
    }

    public byte[] m17261s() {
        if (this.f15188y == null) {
            Object o = m17257o();
            byte[] bArr = new byte[(this.f15167d.length + o.length)];
            System.arraycopy(this.f15167d, 0, bArr, 0, this.f15167d.length);
            System.arraycopy(o, 0, bArr, this.f15167d.length, o.length);
            this.f15188y = C3063i.m17203a(bArr, m17259q());
        }
        return this.f15188y;
    }

    public byte[] m17262t() {
        if (this.f15189z == null) {
            try {
                byte[] bArr = new byte[14];
                System.arraycopy(m17246d(), 0, bArr, 0, 8);
                Arrays.fill(bArr, 8, bArr.length, (byte) -67);
                Key a = C3063i.m17235h(bArr, 0);
                Key a2 = C3063i.m17235h(bArr, 7);
                Object obj = new byte[8];
                System.arraycopy(m17247e(), 0, obj, 0, obj.length);
                Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
                instance.init(1, a);
                Object doFinal = instance.doFinal(obj);
                instance = Cipher.getInstance("DES/ECB/NoPadding");
                instance.init(1, a2);
                Object doFinal2 = instance.doFinal(obj);
                this.f15189z = new byte[16];
                System.arraycopy(doFinal, 0, this.f15189z, 0, doFinal.length);
                System.arraycopy(doFinal2, 0, this.f15189z, doFinal.length, doFinal2.length);
            } catch (Throwable e) {
                throw new NTLMEngineException(e.getMessage(), e);
            }
        }
        return this.f15189z;
    }
}
