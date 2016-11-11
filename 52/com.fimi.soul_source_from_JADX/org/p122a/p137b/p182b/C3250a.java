package org.p122a.p137b.p182b;

import com.fimi.kernel.p076b.p080d.C1142e;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C3258g;
import org.p122a.p137b.p183c.C3265c;

/* renamed from: org.a.b.b.a */
public class C3250a extends C3249h {
    private static final C3262m f15728f;
    protected boolean f15729a;
    protected boolean f15730b;
    protected int f15731c;
    protected boolean f15732d;
    private byte[] f15733g;
    private byte[] f15734h;
    private byte[] f15735i;
    private byte[] f15736j;
    private byte[] f15737k;
    private byte[] f15738l;
    private byte[] f15739m;
    private byte[] f15740n;

    static {
        f15728f = new C3262m();
    }

    public C3250a(C3265c c3265c, boolean z, boolean z2) {
        super(c3265c);
        this.f15729a = false;
        this.f15730b = true;
        this.f15732d = false;
        this.f15733g = new byte[1];
        this.f15734h = new byte[2];
        this.f15735i = new byte[4];
        this.f15736j = new byte[8];
        this.f15737k = new byte[1];
        this.f15738l = new byte[2];
        this.f15739m = new byte[4];
        this.f15740n = new byte[8];
        this.f15729a = z;
        this.f15730b = z2;
    }

    private int m17962a(byte[] bArr, int i, int i2) {
        m17981d(i2);
        return this.e.m18075d(bArr, i, i2);
    }

    public void m17963a() {
    }

    public void m17964a(byte b) {
        this.f15733g[0] = b;
        this.e.m18073b(this.f15733g, 0, 1);
    }

    public void m17965a(int i) {
        this.f15735i[0] = (byte) ((i >> 24) & Util.MASK_8BIT);
        this.f15735i[1] = (byte) ((i >> 16) & Util.MASK_8BIT);
        this.f15735i[2] = (byte) ((i >> 8) & Util.MASK_8BIT);
        this.f15735i[3] = (byte) (i & Util.MASK_8BIT);
        this.e.m18073b(this.f15735i, 0, 4);
    }

    public void m17966a(long j) {
        this.f15736j[0] = (byte) ((int) ((j >> 56) & 255));
        this.f15736j[1] = (byte) ((int) ((j >> 48) & 255));
        this.f15736j[2] = (byte) ((int) ((j >> 40) & 255));
        this.f15736j[3] = (byte) ((int) ((j >> 32) & 255));
        this.f15736j[4] = (byte) ((int) ((j >> 24) & 255));
        this.f15736j[5] = (byte) ((int) ((j >> 16) & 255));
        this.f15736j[6] = (byte) ((int) ((j >> 8) & 255));
        this.f15736j[7] = (byte) ((int) (255 & j));
        this.e.m18073b(this.f15736j, 0, 8);
    }

    public void m17967a(String str) {
        try {
            byte[] bytes = str.getBytes(C1142e.f5201a);
            m17965a(bytes.length);
            this.e.m18073b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new C3258g("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void m17968a(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        m17965a(limit);
        this.e.m18073b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public void m17969a(C3255e c3255e) {
        m17964a(c3255e.f15756b);
        m17974a(c3255e.f15757c);
    }

    public void m17970a(C3256f c3256f) {
        m17964a(c3256f.f15758a);
        m17965a(c3256f.f15759b);
    }

    public void m17971a(C3257g c3257g) {
        m17964a(c3257g.f15760a);
        m17964a(c3257g.f15761b);
        m17965a(c3257g.f15762c);
    }

    public void m17972a(C3261l c3261l) {
        m17964a(c3261l.f15765a);
        m17965a(c3261l.f15766b);
    }

    public void m17973a(C3262m c3262m) {
    }

    public void m17974a(short s) {
        this.f15734h[0] = (byte) ((s >> 8) & Util.MASK_8BIT);
        this.f15734h[1] = (byte) (s & Util.MASK_8BIT);
        this.e.m18073b(this.f15734h, 0, 2);
    }

    public void m17975a(boolean z) {
        m17964a(z ? (byte) 1 : (byte) 0);
    }

    public String m17976b(int i) {
        try {
            m17981d(i);
            byte[] bArr = new byte[i];
            this.e.m18075d(bArr, 0, i);
            return new String(bArr, C1142e.f5201a);
        } catch (UnsupportedEncodingException e) {
            throw new C3258g("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void m17977b() {
    }

    public void m17978c() {
        m17964a((byte) 0);
    }

    public void m17979c(int i) {
        this.f15731c = i;
        this.f15732d = true;
    }

    public void m17980d() {
    }

    protected void m17981d(int i) {
        if (i < 0) {
            throw new C3258g("Negative length: " + i);
        } else if (this.f15732d) {
            this.f15731c -= i;
            if (this.f15731c < 0) {
                throw new C3258g("Message length exceeded: " + i);
            }
        }
    }

    public void m17982e() {
    }

    public void m17983f() {
    }

    public C3262m m17984g() {
        return f15728f;
    }

    public void m17985h() {
    }

    public C3255e m17986i() {
        byte r = m17995r();
        return new C3255e(C2915a.f14760f, r, r == null ? (short) 0 : m17996s());
    }

    public void m17987j() {
    }

    public C3257g m17988k() {
        return new C3257g(m17995r(), m17995r(), m17997t());
    }

    public void m17989l() {
    }

    public C3256f m17990m() {
        return new C3256f(m17995r(), m17997t());
    }

    public void m17991n() {
    }

    public C3261l m17992o() {
        return new C3261l(m17995r(), m17997t());
    }

    public void m17993p() {
    }

    public boolean m17994q() {
        return m17995r() == (byte) 1;
    }

    public byte m17995r() {
        if (this.e.m18074c() >= 1) {
            byte b = this.e.m18070a()[this.e.m18071b()];
            this.e.m18069a(1);
            return b;
        }
        m17962a(this.f15737k, 0, 1);
        return this.f15737k[0];
    }

    public short m17996s() {
        int i = 0;
        byte[] bArr = this.f15738l;
        if (this.e.m18074c() >= 2) {
            bArr = this.e.m18070a();
            i = this.e.m18071b();
            this.e.m18069a(2);
        } else {
            m17962a(this.f15738l, 0, 2);
        }
        return (short) ((bArr[i + 1] & Util.MASK_8BIT) | ((bArr[i] & Util.MASK_8BIT) << 8));
    }

    public int m17997t() {
        int i = 0;
        byte[] bArr = this.f15739m;
        if (this.e.m18074c() >= 4) {
            bArr = this.e.m18070a();
            i = this.e.m18071b();
            this.e.m18069a(4);
        } else {
            m17962a(this.f15739m, 0, 4);
        }
        return (bArr[i + 3] & Util.MASK_8BIT) | ((((bArr[i] & Util.MASK_8BIT) << 24) | ((bArr[i + 1] & Util.MASK_8BIT) << 16)) | ((bArr[i + 2] & Util.MASK_8BIT) << 8));
    }

    public long m17998u() {
        int i = 0;
        byte[] bArr = this.f15740n;
        if (this.e.m18074c() >= 8) {
            bArr = this.e.m18070a();
            i = this.e.m18071b();
            this.e.m18069a(8);
        } else {
            m17962a(this.f15740n, 0, 8);
        }
        return ((long) (bArr[i + 7] & Util.MASK_8BIT)) | (((((((((long) (bArr[i] & Util.MASK_8BIT)) << 56) | (((long) (bArr[i + 1] & Util.MASK_8BIT)) << 48)) | (((long) (bArr[i + 2] & Util.MASK_8BIT)) << 40)) | (((long) (bArr[i + 3] & Util.MASK_8BIT)) << 32)) | (((long) (bArr[i + 4] & Util.MASK_8BIT)) << 24)) | (((long) (bArr[i + 5] & Util.MASK_8BIT)) << 16)) | (((long) (bArr[i + 6] & Util.MASK_8BIT)) << 8));
    }

    public double m17999v() {
        return Double.longBitsToDouble(m17998u());
    }

    public String m18000w() {
        int t = m17997t();
        if (this.e.m18074c() < t) {
            return m17976b(t);
        }
        try {
            String str = new String(this.e.m18070a(), this.e.m18071b(), t, C1142e.f5201a);
            this.e.m18069a(t);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new C3258g("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer m18001x() {
        int t = m17997t();
        m17981d(t);
        if (this.e.m18074c() >= t) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.m18070a(), this.e.m18071b(), t);
            this.e.m18069a(t);
            return wrap;
        }
        byte[] bArr = new byte[t];
        this.e.m18075d(bArr, 0, t);
        return ByteBuffer.wrap(bArr);
    }
}
