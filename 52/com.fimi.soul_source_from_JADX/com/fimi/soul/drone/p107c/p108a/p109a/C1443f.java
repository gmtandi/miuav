package com.fimi.soul.drone.p107c.p108a.p109a;

import com.fimi.kernel.p084e.C1173l;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.C1437b;
import com.fimi.soul.drone.p107c.p108a.C1465c;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1569l;
import com.fimi.soul.drone.p117h.C1571n;
import com.fimi.soul.drone.p117h.C1572o;
import com.fimi.soul.drone.p117h.C1573p;
import com.fimi.soul.drone.p117h.C1574q;
import com.fimi.soul.module.dronemanage.C1713a;
import com.fimi.soul.module.dronemanage.ak;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.drone.c.a.a.f */
public class C1443f {
    public static final int f6828a = 49;
    public static final int f6829b = 51;
    public static final int f6830c = 50;
    public static final int f6831d = 51;
    private static C1443f f6832e;
    private C1433a f6833f;
    private SimpleDateFormat f6834g;

    private C1443f() {
        this.f6834g = new SimpleDateFormat(C1173l.f5333e);
    }

    public static synchronized C1443f m9729a(C1433a c1433a) {
        C1443f c1443f;
        synchronized (C1443f.class) {
            if (f6832e == null) {
                f6832e = new C1443f();
                f6832e.f6833f = c1433a;
            }
            c1443f = f6832e;
        }
        return c1443f;
    }

    private void m9730a(int i, byte[] bArr) {
        m9731a(C1446i.m9764a(i, bArr));
    }

    private void m9731a(C1465c c1465c) {
        m9734n().m9569P().m9993a(c1465c);
    }

    public static boolean m9732a(C1437b c1437b, C1433a c1433a) {
        if (c1437b.f6533a != f6828a && c1437b.f6533a != f6830c && c1437b.f6533a != f6831d) {
            return false;
        }
        c1433a.f6506b = ((C1445h) c1437b).m9761b();
        if (c1437b.f6533a == f6830c) {
            c1433a.m9587a((C1564g) c1433a.f6506b);
        }
        c1433a.m9589a(C1584h.OnRecivedCloudCameraCommandInfo);
        return true;
    }

    private byte[] m9733a(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) 0;
        }
        return bArr;
    }

    private C1433a m9734n() {
        return this.f6833f;
    }

    private byte[] m9735o() {
        return new byte[10];
    }

    public void m9736a() {
        C1465c a = C1446i.m9764a((int) f6830c, new byte[]{(byte) 0});
        C1713a c1713a = new C1713a();
        c1713a.m11187a(this.f6833f, a);
        c1713a.m11186a();
    }

    public void m9737a(C1569l c1569l) {
        byte[] a = m9733a(13);
        switch (C1444g.f6838d[c1569l.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a[3] = (byte) -63;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a[3] = (byte) -62;
                break;
            case Type.BYTE /*3*/:
                a[3] = (byte) -61;
                break;
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9738a(C1571n c1571n) {
        byte[] a = m9733a(13);
        switch (C1444g.f6839e[c1571n.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a[4] = (byte) -63;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a[4] = (byte) -62;
                break;
            case Type.BYTE /*3*/:
                a[4] = (byte) -61;
                break;
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9739a(C1572o c1572o) {
        byte[] a = m9733a(13);
        switch (C1444g.f6835a[c1572o.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a[0] = (byte) -63;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a[0] = (byte) -62;
                break;
            case Type.BYTE /*3*/:
                a[0] = (byte) -61;
                break;
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9740a(C1573p c1573p) {
        byte[] a = m9733a(13);
        switch (C1444g.f6837c[c1573p.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a[2] = (byte) -63;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a[2] = (byte) -62;
                break;
            case Type.BYTE /*3*/:
                a[2] = (byte) -61;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                a[2] = (byte) -60;
                break;
            case Type.INT /*5*/:
                a[2] = (byte) -59;
                break;
            case Type.FLOAT /*6*/:
                a[2] = (byte) -58;
                break;
            case Type.LONG /*7*/:
                a[2] = (byte) -57;
                break;
            case Type.DOUBLE /*8*/:
                a[2] = (byte) -56;
                break;
            case Type.ARRAY /*9*/:
                a[2] = (byte) -55;
                break;
            case Type.OBJECT /*10*/:
                a[2] = (byte) -54;
                break;
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9741a(C1574q c1574q) {
        byte[] a = m9733a(13);
        switch (C1444g.f6836b[c1574q.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                a[1] = (byte) -63;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                a[1] = (byte) -62;
                break;
            case Type.BYTE /*3*/:
                a[1] = (byte) -61;
                break;
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9742a(Date date) {
        byte[] a = m9733a(13);
        byte[] array = ByteBuffer.allocate(4).putInt(Integer.parseInt(this.f6834g.format(date))).array();
        for (int i = 0; i < array.length; i++) {
            a[(a.length - array.length) + i] = array[i];
        }
        m9731a(C1446i.m9764a((int) f6830c, a));
    }

    public void m9743a(boolean z) {
        byte[] a = m9733a(13);
        if (z) {
            a[6] = (byte) -63;
        } else {
            a[6] = (byte) -62;
        }
        m9731a(C1446i.m9764a((int) f6830c, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0}));
    }

    public void m9744b() {
        C1465c a = C1446i.m9764a((int) f6828a, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0});
        C1713a c1713a = new C1713a();
        c1713a.m11187a(this.f6833f, a);
        c1713a.m11186a();
    }

    public void m9745b(boolean z) {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0}));
    }

    public void m9746c() {
        this.f6833f.m9569P().m9993a(C1446i.m9764a((int) f6831d, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0}));
    }

    public void m9747c(boolean z) {
        m9731a(C1446i.m9764a((int) f6830c, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0}));
    }

    public void m9748d() {
        C1465c a = C1446i.m9764a((int) f6828a, new byte[]{(byte) 2, (byte) 0, (byte) 0, (byte) 0});
        C1713a c1713a = new C1713a();
        c1713a.m11187a(this.f6833f, a);
        c1713a.m11186a();
    }

    public void m9749e() {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 3, (byte) 0, (byte) 0, (byte) 0}));
    }

    public void m9750f() {
        C1465c a = C1446i.m9764a((int) f6828a, new byte[]{(byte) 4, (byte) 0, (byte) 0, (byte) 0});
        C1713a c1713a = new C1713a();
        c1713a.m11187a(this.f6833f, a);
        c1713a.m11186a();
    }

    public void m9751g() {
        ak.m11201a(this.f6833f, C1446i.m9764a((int) f6828a, new byte[]{(byte) 8, (byte) 0, (byte) 0, (byte) 0}));
        ak.m11200a();
    }

    public void m9752h() {
        ak.m11201a(this.f6833f, C1446i.m9764a((int) f6828a, new byte[]{(byte) 9, (byte) 0, (byte) 0, (byte) 0}));
        ak.m11200a();
    }

    public void m9753i() {
        ak.m11201a(this.f6833f, C1446i.m9764a((int) f6828a, new byte[]{(byte) 6, (byte) 0, (byte) 0, (byte) 0}));
        ak.m11200a();
    }

    public void m9754j() {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 0, (byte) 1, (byte) 0, (byte) 0}));
    }

    public void m9755k() {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 0, (byte) 2, (byte) 0, (byte) 0}));
    }

    public void m9756l() {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 1, (byte) 0, (byte) 1, (byte) 0}));
    }

    public void m9757m() {
        m9731a(C1446i.m9764a((int) f6828a, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 1}));
    }
}
