package com.fimi.soul.drone.p117h;

import com.fimi.soul.drone.p107c.p108a.C1466d;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.drone.h.k */
public class C1568k extends C1558a {
    private boolean f7550a;
    private C1572o f7551b;
    private C1574q f7552c;
    private C1573p f7553d;
    private C1569l f7554e;
    private C1571n f7555f;
    private C1570m f7556g;
    private boolean f7557h;
    private boolean f7558i;
    private boolean f7559j;
    private int f7560k;
    private short f7561l;
    private short f7562m;

    public C1568k() {
        this.f7550a = true;
        this.f7551b = C1572o.Mid;
        this.f7552c = C1574q.DateTime;
        this.f7553d = C1573p._1280x720_120p_16vs9;
        this.f7554e = C1569l._13M_4128X3096_4vs3;
        this.f7555f = C1571n.Normal;
        this.f7556g = C1570m.DateTime;
        this.f7557h = true;
        this.f7558i = true;
        this.f7559j = true;
        this.f7560k = 0;
        this.f7561l = (short) 0;
        this.f7562m = (short) 0;
    }

    private C1572o m10551a(byte b) {
        return b == 1 ? C1572o.High : b == 2 ? C1572o.Mid : b == 3 ? C1572o.Low : C1572o.Mid;
    }

    private C1574q m10552b(byte b) {
        if (b == 1) {
            return C1574q.Date;
        }
        if (b == 2) {
            return C1574q.Time;
        }
        if (b == 3) {
            return C1574q.DateTime;
        }
        C1574q c1574q = this.f7552c;
        return C1574q.Date;
    }

    private C1573p m10553c(byte b) {
        return b == 1 ? C1573p._1920x1080_60p_16vs9 : b == 2 ? C1573p._1920x1080_30p_16vs9 : b == 3 ? C1573p._1920x1080_48p_16vs9 : b == 4 ? C1573p._1920x1080_24p_16vs9 : b == 5 ? C1573p._1280x960_60p_4vs3 : b == 6 ? C1573p._1280x960_48p_4vs3 : b == 7 ? C1573p._1280x720_60p_16vs9 : b == 8 ? C1573p._1280x720_48p_16vs9 : b == 9 ? C1573p._1280x720_120p_16vs9 : b == 10 ? C1573p._848x480_240p_16vs9 : C1573p._1280x720_120p_16vs9;
    }

    private C1569l m10554d(byte b) {
        return b == 1 ? C1569l._16M_4068x3456_4vs3 : b == 2 ? C1569l._13M_4128X3096_4vs3 : b == 3 ? C1569l._8M_3264X2448_4vs3 : b == 4 ? C1569l._5M_2560X1920_4vs3 : C1569l._16M_4068x3456_4vs3;
    }

    private C1571n m10555e(byte b) {
        return b == 1 ? C1571n.Normal : b == 2 ? C1571n.TimeLapse : b == 3 ? C1571n.BurstShot : C1571n.Normal;
    }

    private C1570m m10556f(byte b) {
        return b == 1 ? C1570m.Date : b == 2 ? C1570m.Time : b == 3 ? C1570m.DateTime : C1570m.DateTime;
    }

    private byte m10557g(byte b) {
        return (byte) (b & 63);
    }

    private byte m10558h(byte b) {
        return (byte) ((b & SmileConstants.TOKEN_PREFIX_SMALL_INT) >>> 6);
    }

    public void m10559a(C1466d c1466d) {
        c1466d.m9831c();
        m10563a(m10551a(c1466d.m9833d()));
        m10565a(m10552b(c1466d.m9833d()));
        m10564a(m10553c(c1466d.m9833d()));
        m10560a(m10554d(c1466d.m9833d()));
        m10562a(m10555e(c1466d.m9833d()));
        m10561a(m10556f(c1466d.m9833d()));
        if (c1466d.m9833d() == (byte) 2) {
            m10573c(true);
        } else {
            m10573c(false);
        }
        if (c1466d.m9833d() == (byte) 2) {
            m10571b(true);
        } else {
            m10571b(false);
        }
        if (c1466d.m9833d() == (byte) 2) {
            m10573c(true);
        } else {
            m10573c(false);
        }
        if (m10173a() == 51) {
            this.f7560k = c1466d.m9835f();
            this.f7561l = c1466d.m9834e();
            this.f7562m = c1466d.m9834e();
        }
    }

    public void m10560a(C1569l c1569l) {
        this.f7554e = c1569l;
    }

    public void m10561a(C1570m c1570m) {
        this.f7556g = c1570m;
    }

    public void m10562a(C1571n c1571n) {
        this.f7555f = c1571n;
    }

    public void m10563a(C1572o c1572o) {
        this.f7551b = c1572o;
    }

    public void m10564a(C1573p c1573p) {
        this.f7553d = c1573p;
    }

    public void m10565a(C1574q c1574q) {
        this.f7552c = c1574q;
    }

    public void m10566a(short s) {
        this.f7561l = s;
    }

    public void m10567a(boolean z) {
        this.f7557h = z;
    }

    public C1569l m10568b() {
        return this.f7554e;
    }

    public void m10569b(int i) {
        this.f7560k = i;
    }

    public void m10570b(short s) {
        this.f7562m = s;
    }

    public void m10571b(boolean z) {
        this.f7558i = z;
    }

    public C1570m m10572c() {
        return this.f7556g;
    }

    public void m10573c(boolean z) {
        this.f7559j = z;
    }

    public void m10574d(boolean z) {
        this.f7550a = z;
    }

    public boolean m10575d() {
        return this.f7557h;
    }

    public boolean m10576e() {
        return this.f7558i;
    }

    public boolean m10577f() {
        return this.f7559j;
    }

    public C1573p m10578g() {
        return this.f7553d;
    }

    public boolean m10579h() {
        return this.f7550a;
    }

    public C1574q m10580i() {
        return this.f7552c;
    }

    public C1572o m10581j() {
        return this.f7551b;
    }

    public C1571n m10582k() {
        return this.f7555f;
    }

    public int m10583l() {
        return this.f7560k;
    }

    public short m10584m() {
        return this.f7561l;
    }

    public short m10585n() {
        return this.f7562m;
    }

    public String toString() {
        return "CloudCameraVariable{isLenAC=" + this.f7550a + ", videoQualityType=" + this.f7551b + ", videoTimeStampType=" + this.f7552c + ", videoResolutionType=" + this.f7553d + ", imageResolutionType=" + this.f7554e + ", shotModeType=" + this.f7555f + ", photoTimeStampType=" + this.f7556g + ", enableAutoLowLight=" + this.f7557h + ", enableRecycleRecord=" + this.f7558i + ", enableAutoAC=" + this.f7559j + ", cameraTime=" + this.f7560k + ", cameraTotalStorage=" + this.f7561l + ", cameraRemainStorage=" + this.f7562m + '}';
    }
}
