package com.fimi.soul.drone;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.util.Log;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.fimi.kernel.C1189f;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.biz.p098j.C1330i;
import com.fimi.soul.drone.droneconnection.connection.C1516m;
import com.fimi.soul.drone.p107c.C1469a;
import com.fimi.soul.drone.p117h.C1560c;
import com.fimi.soul.drone.p117h.C1561d;
import com.fimi.soul.drone.p117h.C1562e;
import com.fimi.soul.drone.p117h.C1563f;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1578u;
import com.fimi.soul.drone.p117h.C1579v;
import com.fimi.soul.drone.p117h.C1580w;
import com.fimi.soul.drone.p117h.C1581x;
import com.fimi.soul.drone.p117h.C1583z;
import com.fimi.soul.drone.p117h.aa;
import com.fimi.soul.drone.p117h.ab;
import com.fimi.soul.drone.p117h.ac;
import com.fimi.soul.drone.p117h.ad;
import com.fimi.soul.drone.p117h.ae;
import com.fimi.soul.drone.p117h.af;
import com.fimi.soul.drone.p117h.ag;
import com.fimi.soul.drone.p117h.ah;
import com.fimi.soul.drone.p117h.ai;
import com.fimi.soul.drone.p117h.aj;
import com.fimi.soul.drone.p117h.ak;
import com.fimi.soul.drone.p117h.al;
import com.fimi.soul.drone.p117h.am;
import com.fimi.soul.drone.p117h.an;
import com.fimi.soul.drone.p117h.ao;
import com.fimi.soul.drone.p117h.ap;
import com.fimi.soul.drone.p117h.aq;
import com.fimi.soul.drone.p117h.ar;
import com.fimi.soul.drone.p117h.as;
import com.fimi.soul.drone.p117h.at;
import com.fimi.soul.drone.p117h.au;
import com.fimi.soul.drone.p117h.av;
import com.fimi.soul.drone.p117h.aw;
import com.fimi.soul.drone.p117h.ax;
import com.fimi.soul.drone.p117h.ay;
import com.fimi.soul.drone.p117h.az;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.drone.p117h.bb;
import com.fimi.soul.drone.p117h.bc;
import com.fimi.soul.drone.p117h.bd;
import com.fimi.soul.drone.p117h.be;
import com.fimi.soul.drone.p117h.bf;
import com.fimi.soul.drone.p117h.bg;
import com.fimi.soul.drone.p117h.p118a.C1552a;
import com.fimi.soul.drone.p117h.p118a.C1553b;
import com.fimi.soul.drone.p117h.p118a.C1554c;
import com.fimi.soul.drone.p117h.p118a.C1555e;
import com.fimi.soul.drone.p117h.p118a.C1557g;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.C1982v;
import com.fimi.soul.utils.C1984x;
import com.fimi.soul.utils.NetUtil;
import com.tencent.mm.sdk.platformtools.Util;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.drone.a */
public class C1433a implements C1432f {
    private final ab f6479A;
    private final an f6480B;
    private final ax f6481C;
    private final ac f6482D;
    private final ay f6483E;
    private final av f6484F;
    private final C1560c f6485G;
    private final ba f6486H;
    private final bg f6487I;
    private final bf f6488J;
    private final be f6489K;
    private final bc f6490L;
    private final bd f6491M;
    private final au f6492N;
    private final aw f6493O;
    private final ah f6494P;
    private final ag f6495Q;
    private final ak f6496R;
    private final C1516m f6497S;
    private C1563f f6498T;
    private aa f6499U;
    private boolean f6500V;
    private double f6501W;
    private long f6502X;
    private AMap f6503Y;
    private boolean f6504Z;
    SharedPreferences f6505a;
    private double aa;
    private boolean ab;
    private C1580w ac;
    private final C1555e ad;
    private boolean ae;
    private C1564g af;
    private LatLng ag;
    private float ah;
    private boolean ai;
    private int aj;
    private Location ak;
    private boolean al;
    private C1578u am;
    private C1553b an;
    private al ao;
    private am ap;
    private C1583z aq;
    private C1581x ar;
    private C1552a as;
    public C1469a f6506b;
    public Context f6507c;
    String f6508d;
    String f6509e;
    C1982v f6510f;
    C1330i f6511g;
    private final C1496d f6512h;
    private final ae f6513i;
    private final C1579v f6514j;
    private final as f6515k;
    private final at f6516l;
    private final aq f6517m;
    private final az f6518n;
    private final ar f6519o;
    private final ai f6520p;
    private final af f6521q;
    private final ap f6522r;
    private final ao f6523s;
    private final aj f6524t;
    private final C1561d f6525u;
    private final C1554c f6526v;
    private final ad f6527w;
    private final C1557g f6528x;
    private final C1562e f6529y;
    private final bb f6530z;

    public C1433a(C1516m c1516m, Context context, Handler handler) {
        this.f6501W = 0.0d;
        this.f6502X = 0;
        this.aa = 0.0d;
        this.f6505a = null;
        this.f6508d = C2915a.f14760f;
        this.f6509e = C2915a.f14760f;
        this.f6510f = null;
        this.f6511g = new C1473c(this);
        this.f6507c = context;
        this.f6505a = com.fimi.soul.utils.ay.m12293a(context);
        this.f6497S = c1516m;
        this.f6513i = new ae(this);
        this.f6514j = new C1579v(this);
        this.f6515k = new as(this);
        this.f6516l = new at(this);
        this.f6517m = new aq(this);
        this.f6518n = new az(this);
        this.f6519o = new ar(this);
        this.f6520p = new ai(this);
        this.f6521q = new af(this);
        this.f6522r = new ap(this);
        this.f6523s = new ao(this);
        this.f6524t = new aj(this);
        this.f6525u = new C1561d(this);
        this.f6526v = new C1554c(this);
        this.f6527w = new ad(this);
        this.f6528x = new C1557g(this);
        this.f6529y = new C1562e(this);
        this.f6530z = new bb(this);
        this.f6479A = new ab(this);
        this.f6480B = new an(this);
        this.f6481C = new ax(this);
        this.f6482D = new ac(this);
        this.f6483E = new ay(this);
        this.f6484F = new av(this);
        this.f6485G = new C1560c(this);
        this.f6486H = new ba(this);
        this.f6487I = new bg(this);
        this.f6488J = new bf(this);
        this.f6489K = new be(this);
        this.f6490L = new bc(this);
        this.f6491M = new bd(this);
        this.f6494P = new ah(this);
        this.f6512h = new C1496d(this, handler);
        this.f6498T = new C1563f(this);
        this.f6492N = new au(this);
        this.f6493O = new aw(this);
        this.f6499U = new aa(this);
        this.ac = new C1580w(this);
        this.an = new C1553b(this);
        this.am = new C1578u(this);
        this.ad = new C1555e(this);
        this.f6495Q = new ag(this);
        this.f6496R = new ak(this);
        this.ao = new al(this);
        this.ap = new am(this);
        this.ar = new C1581x(this);
        this.as = new C1552a(this);
        this.aq = new C1583z(this);
    }

    private void m9552a(long j, double d) {
        boolean a = NetUtil.m12202a(this.f6507c);
        C1982v a2 = C1982v.m12518a(this.f6507c);
        C1984x c1984x = new C1984x();
        c1984x.m12524a(d);
        c1984x.m12525a(j);
        c1984x.m12526a(C1236a.m8533b(this.f6507c).getUserID());
        c1984x.m12528b(C1901a.m12002a().m12003a(0).m12059i());
        a2.m12521a(c1984x);
        if (a) {
            ao();
        }
    }

    private void m9553a(long j, long j2) {
        if (j <= 1800000) {
            C1189f.m8333c().m7929a(C1236a.f5597U, C1189f.m8333c().m7936c(C1236a.f5597U) + j);
            C1189f.m8333c().m7929a(C1236a.f5599W, C1189f.m8333c().m7936c(C1236a.f5599W) + j2);
            this.aa = 0.0d;
            m9552a((long) (this.f6501W * 1000.0d), this.aa);
        }
    }

    private void ao() {
        try {
            new Thread(new C1436b(this)).start();
        } catch (Exception e) {
            Log.d("moweiru", "e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    public bb m9554A() {
        return this.f6530z;
    }

    public ab m9555B() {
        return this.f6479A;
    }

    public an m9556C() {
        return this.f6480B;
    }

    public ax m9557D() {
        return this.f6481C;
    }

    public ac m9558E() {
        return this.f6482D;
    }

    public ay m9559F() {
        return this.f6483E;
    }

    public av m9560G() {
        return this.f6484F;
    }

    public C1560c m9561H() {
        return this.f6485G;
    }

    public ba m9562I() {
        return this.f6486H;
    }

    public bg m9563J() {
        return this.f6487I;
    }

    public bf m9564K() {
        return this.f6488J;
    }

    public be m9565L() {
        return this.f6489K;
    }

    public bc m9566M() {
        return this.f6490L;
    }

    public bd m9567N() {
        return this.f6491M;
    }

    public ah m9568O() {
        return this.f6494P;
    }

    public C1516m m9569P() {
        return this.f6497S;
    }

    public boolean m9570Q() {
        return this.ai;
    }

    public int m9571R() {
        return C1189f.m8333c().m7934b(C1236a.f5598V);
    }

    public long m9572S() {
        long c = C1189f.m8333c().m7936c(C1236a.f5600X) + C1189f.m8333c().m7936c(C1236a.f5599W);
        return c > 0 ? c : 0;
    }

    public long m9573T() {
        long c = (C1189f.m8333c().m7936c(C1236a.f5595S) * 1000) + (C1189f.m8333c().m7936c(C1236a.f5597U) + 30000);
        return c > 0 ? c / Util.MILLSECONDS_OF_MINUTE : 0;
    }

    public long m9574U() {
        long c = (C1189f.m8333c().m7936c(C1236a.f5596T) * 1000) + (C1189f.m8333c().m7936c(C1236a.f5597U) + 30000);
        return c > 0 ? c / Util.MILLSECONDS_OF_MINUTE : 0;
    }

    public boolean m9575V() {
        return C1325k.m8930a().m8943h();
    }

    public C1563f m9576W() {
        return this.f6498T;
    }

    public au m9577X() {
        return this.f6492N;
    }

    public aw m9578Y() {
        return this.f6493O;
    }

    public AMap m9579Z() {
        return this.f6503Y;
    }

    public C1564g m9580a() {
        return this.af;
    }

    public void m9581a(float f) {
        this.ah = f;
    }

    public void m9582a(int i) {
        this.ac.m10614a(this.f6507c.getResources().getString(i));
    }

    public void m9583a(Location location) {
        this.ak = location;
    }

    public void m9584a(AMap aMap) {
        this.f6503Y = aMap;
    }

    public void m9585a(LatLng latLng) {
        this.ag = latLng;
    }

    public void m9586a(C1553b c1553b) {
        this.an = c1553b;
    }

    public void m9587a(C1564g c1564g) {
        this.af = c1564g;
    }

    public void m9588a(C1578u c1578u) {
        this.am = c1578u;
    }

    public void m9589a(C1584h c1584h) {
        this.f6512h.m9890a(c1584h);
    }

    public void m9590a(C1234i c1234i) {
        this.f6512h.m9891a(c1234i);
    }

    public void m9591a(boolean z) {
        this.al = z;
    }

    public boolean aa() {
        return this.f6504Z;
    }

    public aa ab() {
        return this.f6499U;
    }

    public float ac() {
        return this.ah;
    }

    public boolean ad() {
        return this.ab;
    }

    public String ae() {
        return this.ac.m10613a();
    }

    public C1496d af() {
        return this.f6512h;
    }

    public LatLng ag() {
        return this.ag;
    }

    public C1555e ah() {
        return this.ad;
    }

    public ag ai() {
        return this.f6495Q;
    }

    public ak aj() {
        return this.f6496R;
    }

    public boolean ak() {
        return this.f6500V;
    }

    public int al() {
        return this.aj;
    }

    public boolean am() {
        return this.ae;
    }

    public C1552a an() {
        return this.as;
    }

    public Location m9592b() {
        return this.ak;
    }

    public void m9593b(int i) {
        this.aj = i;
    }

    public void m9594b(C1234i c1234i) {
        this.f6512h.m9892b(c1234i);
    }

    public void m9595b(boolean z) {
        this.ai = z;
    }

    public void m9596c(boolean z) {
        if (z) {
            this.f6501W = m9617t().m10358b();
        }
        if (!z && this.f6504Z) {
            m9553a((long) (this.f6501W * 1000.0d), (long) this.aa);
            this.f6501W = 0.0d;
        }
        this.f6504Z = z;
        if (this.f6504Z && System.currentTimeMillis() - this.f6502X >= 1000) {
            this.f6502X = System.currentTimeMillis();
            this.aa = Math.abs(com.fimi.kernel.p084e.ab.m8015a(m9614q().m10272a() / 100.0d, 1)) + this.aa;
        }
    }

    public boolean m9597c() {
        return this.al;
    }

    public am m9598d() {
        return this.ap;
    }

    public void m9599d(boolean z) {
        this.ab = z;
    }

    public C1583z m9600e() {
        return this.aq;
    }

    public void m9601e(boolean z) {
        this.f6500V = z;
    }

    public al m9602f() {
        return this.ao;
    }

    public void m9603f(boolean z) {
        this.ae = z;
    }

    public C1553b m9604g() {
        return this.an;
    }

    public C1578u m9605h() {
        return this.am;
    }

    public ae m9606i() {
        return this.f6513i;
    }

    public C1579v m9607j() {
        return this.f6514j;
    }

    public as m9608k() {
        return this.f6515k;
    }

    public at m9609l() {
        return this.f6516l;
    }

    public aq m9610m() {
        return this.f6517m;
    }

    public az m9611n() {
        return this.f6518n;
    }

    public C1581x m9612o() {
        return this.ar;
    }

    public ar m9613p() {
        return this.f6519o;
    }

    public ai m9614q() {
        return this.f6520p;
    }

    public af m9615r() {
        return this.f6521q;
    }

    public ap m9616s() {
        return this.f6522r;
    }

    public ao m9617t() {
        return this.f6523s;
    }

    public aj m9618u() {
        return this.f6524t;
    }

    public C1561d m9619v() {
        return this.f6525u;
    }

    public C1554c m9620w() {
        return this.f6526v;
    }

    public ad m9621x() {
        return this.f6527w;
    }

    public C1557g m9622y() {
        return this.f6528x;
    }

    public C1562e m9623z() {
        return this.f6529y;
    }
}
