package com.fimi.soul.biz.update;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.p103k.ay;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p107c.p108a.p109a.bi;
import com.fimi.soul.drone.p110d.C1480g;
import com.fimi.soul.drone.p110d.C1488o;
import com.fimi.soul.drone.p110d.C1491r;
import com.fimi.soul.drone.p110d.C1492s;
import com.fimi.soul.drone.p110d.C1493t;
import com.fimi.soul.drone.p116g.C1547g;
import com.fimi.soul.drone.p117h.C1564g;
import com.fimi.soul.drone.p117h.C1575r;
import com.fimi.soul.drone.p117h.au;
import com.fimi.soul.drone.p117h.bd;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.entity.UpgradeResultInfo;
import com.fimi.soul.module.dronemanage.ak;
import com.fimi.soul.module.update.p121a.C1901a;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

/* renamed from: com.fimi.soul.biz.update.u */
public class C1423u extends C1403p implements C1234i {
    public static C1547g f6378A = null;
    private static int f6379H = 0;
    private static int f6380I = 0;
    private static bi f6381O = null;
    static ah f6382f = null;
    public static final int f6383h = 300;
    public static final int f6384i = -1;
    public static final String f6385j = "firmware_info";
    public static int f6386k = 0;
    public static final String f6387l = "sp_upgrading";
    public static final int f6388m = 2;
    public static final int f6389n = 3;
    public static final int f6390o = 10;
    public static final int f6391p = 5;
    public static final int f6392q = 9;
    public static final int f6393r = 100;
    public static final int f6394s = 10;
    public static boolean f6395u;
    public static boolean f6396v;
    public static boolean f6397w;
    public static boolean f6398x;
    static Queue<bi> f6399z;
    List<C1547g> f6400B;
    C1901a f6401C;
    int f6402D;
    private Timer f6403E;
    private Context f6404F;
    private C1433a f6405G;
    private FragmentManager f6406J;
    private boolean f6407K;
    private Timer f6408L;
    private ad f6409M;
    private int f6410N;
    private int f6411P;
    public ac f6412g;
    public int f6413t;
    public C1313t f6414y;

    static {
        f6379H = 0;
        f6386k = 0;
        f6395u = false;
        f6396v = false;
        f6397w = false;
        f6398x = false;
        f6399z = new LinkedList();
    }

    public C1423u() {
        this.f6403E = new Timer();
        this.f6413t = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        this.f6400B = new ArrayList();
        this.f6408L = new Timer();
    }

    public C1423u(Context context, C1433a c1433a) {
        this.f6403E = new Timer();
        this.f6413t = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        this.f6400B = new ArrayList();
        this.f6408L = new Timer();
        this.f6404F = context;
        this.f6405G = c1433a;
        c1433a.m9590a((C1234i) this);
        this.f6414y = (C1313t) C1276b.m8680a().m8699d();
        this.f6401C = C1901a.m12002a();
    }

    private void m9480a(byte b) {
        try {
            if (this.f6405G.m9563J().m10491a() == f6381O.f6776d) {
                if (f6378A != null) {
                    f6378A.m10134e();
                }
                Message message = new Message();
                message.what = f6391p;
                message.arg1 = f6386k;
                message.arg2 = f6383h;
                if (this.f6405G.m9563J().m10491a() == f6380I) {
                    this.f6402D = this.f6405G.m9563J().m10491a();
                    m7685a().sendMessage(message);
                    f6396v = false;
                    this.f6407K = true;
                    if (this.f6403E != null) {
                        this.f6403E.cancel();
                    }
                    f6379H = 1;
                    C1492s.f7060a.f6786d = b;
                    this.f6403E = new Timer();
                    this.f6403E.schedule(new ab(this, C1492s.f7060a.m9715a(), this.f6405G), 100, 1000);
                    return;
                }
                this.f6402D = this.f6405G.m9563J().m10491a();
                m7685a().sendMessage(message);
                if (f6399z.size() > 0) {
                    m9498b();
                }
            }
        } catch (Exception e) {
            if (f6382f != null) {
                f6382f.m9419a(false, 0, 0, f6384i);
            }
        }
    }

    public static void m9485n() {
        try {
            UpgradeResultInfo upgradeResultInfo = (UpgradeResultInfo) C1189f.m8333c().m7926a("upgradeResultInfo", UpgradeResultInfo.class);
            if (C1189f.m8327a() != null && upgradeResultInfo != null) {
                new Thread(new C1427y(upgradeResultInfo)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ int m9487p() {
        int i = f6379H;
        f6379H = i + 1;
        return i;
    }

    private long m9489r() {
        f6380I = this.f6401C.m12011f().m12030a();
        f6399z = this.f6401C.m12011f().m12035b();
        return (long) f6380I;
    }

    private void m9490s() {
        f6378A = new C1547g(this.f6413t, new C1425w(this));
        if (!this.f6400B.contains(f6378A)) {
            this.f6400B.add(f6378A);
        }
    }

    public void m9491a(int i) {
        this.f6401C.m12007b(i);
    }

    protected void m9492a(Message message) {
        super.m9382a(message);
        switch (message.what) {
            case f6388m /*2*/:
                if (f6378A != null) {
                    f6378A.m10134e();
                    f6378A.m10135f();
                }
                this.f6411P = 0;
                f6382f.m9419a(false, 0, 0, f6384i);
            case f6389n /*3*/:
                f6382f.m9419a(false, 0, 0, f6384i);
            case f6391p /*5*/:
                if (f6382f != null && message.arg2 == f6383h) {
                    f6382f.m9419a(false, (long) this.f6402D, (long) f6380I, f6386k);
                }
            case f6394s /*10*/:
                f6382f.m9419a(false, 0, 0, f6384i);
            default:
        }
    }

    public void m9493a(ac acVar) {
        this.f6412g = acVar;
    }

    public void m9494a(ad adVar) {
        if (C1189f.m8327a() != null) {
            ay.m9251a(C1189f.m8327a()).m9257b(new C1424v(this, adVar));
        }
    }

    public void m9495a(ah ahVar) {
        f6382f = ahVar;
    }

    public void m9496a(FirmwareInfo firmwareInfo) {
        this.f6401C.m12004a(firmwareInfo.getSysId(), Integer.valueOf(firmwareInfo.getVersion()).intValue());
        this.f6401C.m12003a(firmwareInfo.getSysId()).m12047b(Integer.valueOf(firmwareInfo.getVersion()).intValue());
    }

    public void m9497a(FirmwareInfo firmwareInfo, int i) {
        f6386k = i;
        this.f6413t = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        C1157c.m7938a().m7941a(f6387l, firmwareInfo.getSysId());
        m9491a(firmwareInfo.getSysId());
        m9499b(firmwareInfo.getSysId());
    }

    public void m9498b() {
        f6381O = (bi) f6399z.poll();
        if (f6381O != null && f6382f != null) {
            this.f6405G.m9569P().m9993a(f6381O.m9707a());
            if (!f6378A.m10131b()) {
                f6378A.m10132c();
            }
        }
    }

    public void m9499b(int i) {
        try {
            if (((int) m9489r()) > 0) {
                if (this.f6403E != null) {
                    this.f6403E.cancel();
                    this.f6403E = null;
                }
                f6379H = 1;
                f6398x = false;
                this.f6408L = new Timer();
                C1488o.f7054a.f6936d = (byte) i;
                C1488o.f7054a.f6937e = (byte) 1;
                this.f6405G.m9569P().m9993a(C1488o.f7054a.m9794a());
                this.f6408L.schedule(new ab(this, C1488o.f7054a.m9794a(), this.f6405G), 100, 2000);
                return;
            }
            Toast.makeText(this.f6404F, this.f6404F.getResources().getString(C1205R.string.firmware_file_not_exist), 1).show();
        } catch (Exception e) {
            Exception exception = e;
            f6382f.m9419a(false, 0, 0, f6384i);
            exception.printStackTrace();
        }
    }

    public void m9500b(UpdateVersonBean updateVersonBean, String str, ah ahVar) {
        m9384a(updateVersonBean, str, ahVar);
    }

    public boolean m9501c() {
        return this.f6405G != null ? this.f6405G.aa() : false;
    }

    public void m9502d() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String m9503e(UpdateVersonBean updateVersonBean) {
        return m9387d(updateVersonBean);
    }

    public void m9504e() {
        if (this.f6403E != null) {
            this.f6403E.cancel();
            this.f6403E = null;
        }
        if (this.f6408L != null) {
            this.f6408L.cancel();
            this.f6408L = null;
        }
        if (f6378A != null) {
            f6378A.m10134e();
            f6378A = null;
        }
        f6382f = null;
        f6386k = 1;
        if (this.f6400B != null && this.f6400B.size() > 0) {
            for (C1547g c1547g : this.f6400B) {
                if (c1547g != null) {
                    c1547g.m10134e();
                    c1547g.m10135f();
                }
            }
        }
    }

    public void m9505j() {
        if (f6378A != null) {
            f6378A.m10134e();
        }
        f6378A = null;
    }

    public void m9506k() {
        C1480g.f7039a.f6940d = (byte) 1;
        this.f6405G.m9569P().m9993a(C1480g.f7039a.m9796a());
    }

    public void m9507l() {
        this.f6405G.m9594b((C1234i) this);
    }

    public void m9508m() {
        try {
            UpgradeResultInfo a = ak.m9423a(this.f6404F);
            if (a != null) {
                C1189f.m8333c().m7930a("upgradeResultInfo", (Object) a);
            }
            if (C1189f.m8327a() != null) {
                ay.m9251a(C1189f.m8327a()).m9255a(new C1426x(this), a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        int i = 0;
        byte b;
        switch (aa.f6333b[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                au X = c1433a.m9577X();
                if (X.f7432d == 0) {
                    if (X.f7431c == 1 && !f6398x) {
                        f6398x = true;
                        f6379H = 1;
                        if (this.f6408L != null) {
                            this.f6408L.cancel();
                        }
                        this.f6403E = new Timer();
                        C1491r.f7059a.f6767d = (byte) X.f7430b;
                        C1491r.f7059a.f6768e = (byte) 0;
                        C1491r.f7059a.f6769f = (byte) 1;
                        c1433a.m9569P().m9993a(C1491r.f7059a.m9703a());
                        this.f6403E.schedule(new ab(this, C1491r.f7059a.m9703a(), c1433a), 100, 2000);
                    }
                    if (X.f7431c == f6388m && f6382f != null && !f6395u) {
                        C1493t.f7061a.f6791d = (byte) C1157c.m7938a().m7947b(f6387l);
                        C1493t.f7061a.f6792e = (byte) 1;
                        f6395u = true;
                        f6379H = 1;
                        if (this.f6403E != null) {
                            this.f6403E.cancel();
                            this.f6403E = new Timer();
                            this.f6403E.schedule(new ab(this, C1493t.f7061a.m9717a(), c1433a), 100, 1000);
                        }
                    }
                }
            case f6388m /*2*/:
                int a = c1433a.f6506b.m9849a();
                if (a == 51) {
                    this.f6414y.m8853i().setDvVersion(((C1575r) c1433a.f6506b).m10594c() + C2915a.f14760f);
                } else if (a == 50) {
                    C1564g c1564g = (C1564g) c1433a.f6506b;
                    switch (aa.f6332a[c1564g.m10548f().ordinal()]) {
                        case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                            if (this.f6412g != null) {
                                this.f6412g.m9416a(c1564g.m10547e());
                                if (c1564g.m10547e() == f6393r) {
                                    this.f6412g.m9417a(true);
                                }
                            }
                        case f6388m /*2*/:
                            if (this.f6412g != null) {
                                this.f6412g.m9417a(false);
                            }
                        default:
                    }
                }
            case f6389n /*3*/:
                if (f6398x) {
                    f6398x = false;
                    f6379H = 1;
                    if (this.f6403E != null) {
                        this.f6403E.cancel();
                    }
                    this.f6410N = c1433a.m9564K().m10488a();
                    if (C1491r.f7059a.f6767d == this.f6410N) {
                        b = c1433a.m9564K().m10490b();
                        if (b == null || b == (byte) 4) {
                            this.f6411P = 0;
                            if (f6378A == null) {
                                m9490s();
                                f6378A.m10133d();
                            }
                            m9498b();
                            return;
                        }
                        f6382f.m9419a(false, 0, 0, f6384i);
                    }
                }
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f6411P = 0;
                b = c1433a.m9563J().m10493b();
                if (b == null || b == (byte) 4) {
                    this.f6413t = C2799f.f14282t;
                    m9480a(c1433a.m9563J().f7480b);
                    return;
                }
                f6382f.m9419a(false, 0, 0, f6384i);
            case f6391p /*5*/:
                bd N = c1433a.m9567N();
                f6379H = 0;
                if (this.f6403E != null) {
                    this.f6403E.cancel();
                    this.f6403E = null;
                }
                if (N.f7473b == (byte) 1 && f6382f != null) {
                    if (this.f6407K) {
                        f6382f.m9419a(true, (long) f6380I, (long) f6380I, f6386k);
                        this.f6407K = false;
                    }
                    while (i < 4) {
                        C1493t.m9885a(c1433a, f6388m, C1157c.m7938a().m7947b(f6387l));
                        m9502d();
                        i++;
                    }
                }
            case Type.FLOAT /*6*/:
                m7685a().sendEmptyMessage(f6392q);
                ak.m11202b();
                f6395u = false;
                f6379H = 1;
                if (!f6396v) {
                    f6396v = true;
                    C1488o.f7054a.f6936d = c1433a.m9566M().f7472c;
                    C1488o.f7054a.f6937e = (byte) 2;
                    if (this.f6403E != null) {
                        this.f6403E.cancel();
                    }
                    this.f6403E = new Timer();
                    this.f6403E.schedule(new ab(this, C1488o.f7054a.m9794a(), c1433a), 100, 1000);
                }
            default:
        }
    }
}
