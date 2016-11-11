package org.p122a.p137b.p182b;

import com.baidu.android.common.logging.Log;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.fimi.soul.module.setting.newhand.C1873o;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.p122a.p137b.C3258g;
import org.p122a.p137b.p183c.C3265c;

/* renamed from: org.a.b.b.n */
public class C3263n extends C3250a {
    private static int f15768f;
    private static int f15769g;
    private static int f15770h;
    private static int f15771i;
    private static int f15772j;

    static {
        f15768f = C1873o.ak;
        f15769g = C1873o.ak;
        f15770h = C1873o.ak;
        f15771i = Log.FILE_LIMETE;
        f15772j = 104857600;
    }

    public C3263n(C3265c c3265c, boolean z, boolean z2) {
        super(c3265c, z, z2);
    }

    public C3257g m18062k() {
        byte r = m17995r();
        byte r2 = m17995r();
        int t = m17997t();
        if (t <= f15768f) {
            return new C3257g(r, r2, t);
        }
        throw new C3259i(3, "Thrift map size " + t + " out of range!");
    }

    public C3256f m18063m() {
        byte r = m17995r();
        int t = m17997t();
        if (t <= f15769g) {
            return new C3256f(r, t);
        }
        throw new C3259i(3, "Thrift list size " + t + " out of range!");
    }

    public C3261l m18064o() {
        byte r = m17995r();
        int t = m17997t();
        if (t <= f15770h) {
            return new C3261l(r, t);
        }
        throw new C3259i(3, "Thrift set size " + t + " out of range!");
    }

    public String m18065w() {
        int t = m17997t();
        if (t > f15771i) {
            throw new C3259i(3, "Thrift string size " + t + " out of range!");
        } else if (this.e.m18074c() < t) {
            return m17976b(t);
        } else {
            try {
                String str = new String(this.e.m18070a(), this.e.m18071b(), t, C1142e.f5201a);
                this.e.m18069a(t);
                return str;
            } catch (UnsupportedEncodingException e) {
                throw new C3258g("JVM DOES NOT SUPPORT UTF-8");
            }
        }
    }

    public ByteBuffer m18066x() {
        int t = m17997t();
        if (t > f15772j) {
            throw new C3259i(3, "Thrift binary size " + t + " out of range!");
        }
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
