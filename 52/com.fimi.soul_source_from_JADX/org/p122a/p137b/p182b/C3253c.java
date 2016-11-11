package org.p122a.p137b.p182b;

import com.fimi.kernel.p076b.p080d.C1142e;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C3248a;
import org.p122a.p137b.C3258g;
import org.p122a.p137b.p183c.C3265c;

/* renamed from: org.a.b.b.c */
public final class C3253c extends C3249h {
    private static final C3262m f15744d;
    private static final C3255e f15745f;
    private static final byte[] f15746g;
    byte[] f15747a;
    byte[] f15748b;
    byte[] f15749c;
    private C3248a f15750h;
    private short f15751i;
    private C3255e f15752j;
    private Boolean f15753k;
    private byte[] f15754l;

    static {
        f15744d = new C3262m(C2915a.f14760f);
        f15745f = new C3255e(C2915a.f14760f, (byte) 0, (short) 0);
        f15746g = new byte[16];
        f15746g[0] = (byte) 0;
        f15746g[2] = (byte) 1;
        f15746g[3] = (byte) 3;
        f15746g[6] = (byte) 4;
        f15746g[8] = (byte) 5;
        f15746g[10] = (byte) 6;
        f15746g[4] = (byte) 7;
        f15746g[11] = (byte) 8;
        f15746g[15] = (byte) 9;
        f15746g[14] = (byte) 10;
        f15746g[13] = (byte) 11;
        f15746g[12] = (byte) 12;
    }

    public C3253c(C3265c c3265c) {
        super(c3265c);
        this.f15750h = new C3248a(15);
        this.f15751i = (short) 0;
        this.f15752j = null;
        this.f15753k = null;
        this.f15747a = new byte[5];
        this.f15748b = new byte[10];
        this.f15754l = new byte[1];
        this.f15749c = new byte[1];
    }

    private long m18004A() {
        long j = null;
        long j2 = 0;
        if (this.e.m18074c() >= 10) {
            int i;
            byte[] a = this.e.m18070a();
            int b = this.e.m18071b();
            long j3 = 0;
            while (true) {
                byte b2 = a[b + i];
                j2 |= ((long) (b2 & Opcodes.LAND)) << j3;
                if ((b2 & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.e.m18069a(i + 1);
        } else {
            while (true) {
                byte r = m18051r();
                j2 |= ((long) (r & Opcodes.LAND)) << j;
                if ((r & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    break;
                }
                j += 7;
            }
        }
        return j2;
    }

    private long m18005a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    private void m18006a(C3255e c3255e, byte b) {
        if (b == -1) {
            b = m18017e(c3255e.f15756b);
        }
        if (c3255e.f15757c <= this.f15751i || c3255e.f15757c - this.f15751i > 15) {
            m18008b(b);
            m18033a(c3255e.f15757c);
        } else {
            m18016d(((c3255e.f15757c - this.f15751i) << 4) | b);
        }
        this.f15751i = c3255e.f15757c;
    }

    private void m18007a(byte[] bArr, int i, int i2) {
        m18009b(i2);
        this.e.m18073b(bArr, i, i2);
    }

    private void m18008b(byte b) {
        this.f15754l[0] = b;
        this.e.m18072b(this.f15754l);
    }

    private void m18009b(int i) {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.f15747a[i2] = (byte) ((i & Opcodes.LAND) | SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.f15747a[i2] = (byte) i;
        this.e.m18073b(this.f15747a, 0, i4);
    }

    private void m18010b(long j) {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.f15748b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.f15748b[i] = (byte) ((int) j);
        this.e.m18073b(this.f15748b, 0, i3);
    }

    private int m18011c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private long m18012c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private boolean m18013c(byte b) {
        int i = b & 15;
        return i == 1 || i == 2;
    }

    private byte m18014d(byte b) {
        switch ((byte) (b & 15)) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return (byte) 0;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return (byte) 2;
            case Type.BYTE /*3*/:
                return (byte) 3;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return (byte) 6;
            case Type.INT /*5*/:
                return (byte) 8;
            case Type.FLOAT /*6*/:
                return (byte) 10;
            case Type.LONG /*7*/:
                return (byte) 4;
            case Type.DOUBLE /*8*/:
                return (byte) 11;
            case Type.ARRAY /*9*/:
                return (byte) 15;
            case Type.OBJECT /*10*/:
                return (byte) 14;
            case Opcodes.T_LONG /*11*/:
                return (byte) 13;
            case Opcodes.FCONST_1 /*12*/:
                return (byte) 12;
            default:
                throw new C3259i("don't know what type: " + ((byte) (b & 15)));
        }
    }

    private long m18015d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void m18016d(int i) {
        m18008b((byte) i);
    }

    private byte m18017e(byte b) {
        return f15746g[b];
    }

    private byte[] m18018e(int i) {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.m18075d(bArr, 0, i);
        return bArr;
    }

    private int m18019f(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    private int m18020z() {
        int i = 0;
        int i2;
        if (this.e.m18074c() >= 5) {
            byte[] a = this.e.m18070a();
            int b = this.e.m18071b();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = a[b + i];
                i3 |= (b2 & Opcodes.LAND) << i2;
                if ((b2 & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    this.e.m18069a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte r = m18051r();
                i2 |= (r & Opcodes.LAND) << i;
                if ((r & SmileConstants.TOKEN_PREFIX_TINY_UNICODE) != SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    public void m18021a() {
        this.f15751i = this.f15750h.m17923a();
    }

    public void m18022a(byte b) {
        m18008b(b);
    }

    protected void m18023a(byte b, int i) {
        if (i <= 14) {
            m18016d((i << 4) | m18017e(b));
            return;
        }
        m18016d(m18017e(b) | 240);
        m18009b(i);
    }

    public void m18024a(int i) {
        m18009b(m18011c(i));
    }

    public void m18025a(long j) {
        m18010b(m18012c(j));
    }

    public void m18026a(String str) {
        try {
            byte[] bytes = str.getBytes(C1142e.f5201a);
            m18007a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new C3258g("UTF-8 not supported!");
        }
    }

    public void m18027a(ByteBuffer byteBuffer) {
        m18007a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset());
    }

    public void m18028a(C3255e c3255e) {
        if (c3255e.f15756b == 2) {
            this.f15752j = c3255e;
        } else {
            m18006a(c3255e, (byte) -1);
        }
    }

    public void m18029a(C3256f c3256f) {
        m18023a(c3256f.f15758a, c3256f.f15759b);
    }

    public void m18030a(C3257g c3257g) {
        if (c3257g.f15762c == 0) {
            m18016d(0);
            return;
        }
        m18009b(c3257g.f15762c);
        m18016d((m18017e(c3257g.f15760a) << 4) | m18017e(c3257g.f15761b));
    }

    public void m18031a(C3261l c3261l) {
        m18023a(c3261l.f15765a, c3261l.f15766b);
    }

    public void m18032a(C3262m c3262m) {
        this.f15750h.m17924a(this.f15751i);
        this.f15751i = (short) 0;
    }

    public void m18033a(short s) {
        m18009b(m18011c((int) s));
    }

    public void m18034a(boolean z) {
        byte b = (byte) 1;
        if (this.f15752j != null) {
            C3255e c3255e = this.f15752j;
            if (!z) {
                b = (byte) 2;
            }
            m18006a(c3255e, b);
            this.f15752j = null;
            return;
        }
        if (!z) {
            b = (byte) 2;
        }
        m18008b(b);
    }

    public void m18035b() {
    }

    public void m18036c() {
        m18008b((byte) 0);
    }

    public void m18037d() {
    }

    public void m18038e() {
    }

    public void m18039f() {
    }

    public C3262m m18040g() {
        this.f15750h.m17924a(this.f15751i);
        this.f15751i = (short) 0;
        return f15744d;
    }

    public void m18041h() {
        this.f15751i = this.f15750h.m17923a();
    }

    public C3255e m18042i() {
        byte r = m18051r();
        if (r == null) {
            return f15745f;
        }
        short s = (short) ((r & 240) >> 4);
        C3255e c3255e = new C3255e(C2915a.f14760f, m18014d((byte) (r & 15)), s == (short) 0 ? m18052s() : (short) (s + this.f15751i));
        if (m18013c(r)) {
            this.f15753k = ((byte) (r & 15)) == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f15751i = c3255e.f15757c;
        return c3255e;
    }

    public void m18043j() {
    }

    public C3257g m18044k() {
        int z = m18020z();
        int r = z == 0 ? 0 : m18051r();
        return new C3257g(m18014d((byte) (r >> 4)), m18014d((byte) (r & 15)), z);
    }

    public void m18045l() {
    }

    public C3256f m18046m() {
        byte r = m18051r();
        int i = (r >> 4) & 15;
        if (i == 15) {
            i = m18020z();
        }
        return new C3256f(m18014d(r), i);
    }

    public void m18047n() {
    }

    public C3261l m18048o() {
        return new C3261l(m18046m());
    }

    public void m18049p() {
    }

    public boolean m18050q() {
        if (this.f15753k == null) {
            return m18051r() == (byte) 1;
        } else {
            boolean booleanValue = this.f15753k.booleanValue();
            this.f15753k = null;
            return booleanValue;
        }
    }

    public byte m18051r() {
        if (this.e.m18074c() > 0) {
            byte b = this.e.m18070a()[this.e.m18071b()];
            this.e.m18069a(1);
            return b;
        }
        this.e.m18075d(this.f15749c, 0, 1);
        return this.f15749c[0];
    }

    public short m18052s() {
        return (short) m18019f(m18020z());
    }

    public int m18053t() {
        return m18019f(m18020z());
    }

    public long m18054u() {
        return m18015d(m18004A());
    }

    public double m18055v() {
        byte[] bArr = new byte[8];
        this.e.m18075d(bArr, 0, 8);
        return Double.longBitsToDouble(m18005a(bArr));
    }

    public String m18056w() {
        int z = m18020z();
        if (z == 0) {
            return C2915a.f14760f;
        }
        try {
            if (this.e.m18074c() < z) {
                return new String(m18018e(z), C1142e.f5201a);
            }
            String str = new String(this.e.m18070a(), this.e.m18071b(), z, C1142e.f5201a);
            this.e.m18069a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new C3258g("UTF-8 not supported!");
        }
    }

    public ByteBuffer m18057x() {
        int z = m18020z();
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.e.m18075d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    public void m18058y() {
        this.f15750h.m17925b();
        this.f15751i = (short) 0;
    }
}
