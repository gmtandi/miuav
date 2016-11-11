package com.fimi.soul.biz.p100g;

import android.content.Context;
import android.os.Handler;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.entity.DroneModelBean;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.droneFragment.C1710x;
import com.fimi.soul.utils.au;
import java.lang.ref.WeakReference;
import java.util.Observer;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: com.fimi.soul.biz.g.c */
public class C1336c implements C1234i {
    private DroneModelBean f5957a;
    private C1341h f5958b;
    private C1433a f5959c;
    private int f5960d;
    private int f5961e;
    private int f5962f;
    private int f5963g;
    private au f5964h;
    private boolean f5965i;
    private WeakReference<Context> f5966j;
    private C1342i f5967k;
    private Handler f5968l;
    private Runnable f5969m;
    private C1253k f5970n;
    private boolean f5971o;
    private Observer f5972p;
    private int f5973q;

    public C1336c(C1341h c1341h, C1433a c1433a, Context context) {
        this.f5961e = 20;
        this.f5962f = 20;
        this.f5963g = 100;
        this.f5965i = true;
        this.f5968l = new Handler();
        this.f5969m = new C1337d(this);
        this.f5972p = new C1338e(this);
        this.f5958b = c1341h;
        this.f5957a = new DroneModelBean(c1433a);
        this.f5957a.addObserver(this.f5972p);
        this.f5959c = c1433a;
        this.f5966j = new WeakReference(context);
        this.f5967k = new C1342i(c1433a);
        this.f5970n = C1253k.m8598a(context);
    }

    private void m8966a(int i) {
        this.f5958b.m8985a(i);
    }

    private void m8968b(int i) {
        this.f5958b.m8987b(i);
    }

    private void m8972e() {
        if (this.f5959c.ah().m10172g().judgeNoAction()) {
            m8976h();
            m8977i();
        }
    }

    private void m8974f() {
        if (!this.f5959c.ah().m10172g().isEnterModel() || this.f5959c.m9602f().m10342k() || this.f5962f == 0) {
            if (this.f5973q != 0) {
                this.f5973q = 0;
            }
            this.f5958b.m8986a(0, 4);
            return;
        }
        this.f5973q++;
        if (this.f5973q > 3) {
            this.f5958b.m8986a(C1205R.string.exit, 0);
            this.f5973q = 0;
        }
    }

    private void m8975g() {
        if (this.f5959c.aa() || this.f5959c.ad()) {
            if (this.f5961e == 2 && !this.f5959c.aa() && this.f5960d == 1) {
                this.f5957a.setComTakeOFF(true);
                m8966a((int) C1205R.string.lightstreamfly);
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5961e == 1 && !this.f5959c.aa() && this.f5960d == 1) {
                this.f5957a.setComTakeOFF(true);
                m8966a((int) C1205R.string.gpsfly);
                this.f5971o = false;
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5961e == 0 && !this.f5959c.aa() && this.f5960d == 1) {
                this.f5957a.setComTakeOFF(true);
                m8966a((int) C1205R.string.attibase);
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5961e == 2 && this.f5959c.aa() && this.f5960d == 1) {
                m8966a((int) C1205R.string.lightstreamfling);
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5961e == 1 && this.f5959c.aa() && this.f5960d == 1) {
                m8966a((int) C1205R.string.gpsfling);
                m8968b((int) C1205R.drawable.normal_icon);
                m8978j();
            } else if (this.f5961e == 0 && this.f5959c.aa() && this.f5960d == 1) {
                m8966a((int) C1205R.string.attfling);
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5960d == 7) {
                m8966a((int) C1205R.string.tohome);
                m8968b((int) C1205R.drawable.sailround_icon);
                if (this.f5959c.m9602f().m10342k()) {
                    m8972e();
                }
            } else if (this.f5960d == 8) {
                m8972e();
                m8966a((int) C1205R.string.returntohome);
                m8968b((int) C1205R.drawable.sailround_icon);
                if (this.f5965i && this.f5964h == null) {
                    this.f5965i = false;
                    this.f5967k.m8992a(this.f5959c.f6507c.getResources().getString(C1205R.string.returntohome));
                    this.f5964h = new au((Context) this.f5966j.get(), new C1339f(this), 3);
                    this.f5964h.setCancelable(true);
                    this.f5964h.show();
                }
            } else if (this.f5960d == 3) {
                m8966a((int) C1205R.string.landing);
                m8968b((int) C1205R.drawable.landing_icon);
                m8972e();
            } else if (this.f5960d == 9) {
                m8972e();
                m8966a((int) C1205R.string.lowlanding);
                m8968b((int) C1205R.drawable.landing_icon);
            } else if (this.f5960d == 2) {
                m8966a((int) C1205R.string.take_off);
                m8968b((int) C1205R.drawable.takeoff_icon);
            } else if (this.f5960d == 4) {
                this.f5971o = true;
                if (this.f5962f == 4) {
                    m8966a((int) C1205R.string.stopflyto);
                    m8968b((int) C1205R.drawable.destination_icon);
                } else if (this.f5962f == 2) {
                    m8966a((int) C1205R.string.flyto);
                    m8968b((int) C1205R.drawable.destination_icon);
                }
            } else if (this.f5960d == 5) {
                if (this.f5962f == 4) {
                    m8966a((int) C1205R.string.stop_poi_fly);
                    m8968b((int) C1205R.drawable.detouringpoint_icon);
                } else if (this.f5962f == 2) {
                    m8966a((int) C1205R.string.interestFly);
                    m8968b((int) C1205R.drawable.detouringpoint_icon);
                }
            } else if (this.f5960d == 6) {
                this.f5971o = true;
                if (this.f5962f == 4) {
                    m8966a((int) C1205R.string.stopwaypoint);
                    m8968b((int) C1205R.drawable.icon_fly_airline);
                } else if (this.f5962f == 2) {
                    m8966a((int) C1205R.string.execuwaypoint);
                    m8968b((int) C1205R.drawable.icon_fly_airline);
                }
            } else if (this.f5960d != 10) {
                m8966a((int) C1205R.string.condrone);
                m8968b((int) C1205R.drawable.normal_icon);
            } else if (this.f5962f == 4) {
                m8966a((int) C1205R.string.stoptake_photo);
                m8968b((int) C1205R.mipmap.icon_fly_mode_selfie);
            } else if (this.f5962f == 2) {
                m8966a((int) C1205R.string.take_photo_fly);
                m8968b((int) C1205R.mipmap.icon_fly_mode_selfie);
            }
        } else if (this.f5959c.aj().m10321i() && this.f5959c.ah().m10165a() == null) {
            m8966a((int) C1205R.string.can_fly_by_hand);
            m8968b((int) C1205R.drawable.normal_icon);
        } else {
            m8966a((int) C1205R.string.self_error_result);
            m8968b((int) C1205R.drawable.notnormal_icon);
        }
    }

    private void m8976h() {
        this.f5959c.m9589a(C1584h.CLOSEFLYACTIONFRAGMENT);
    }

    private void m8977i() {
        this.f5971o = false;
        this.f5959c.m9589a(C1584h.NOTIDRONEFLOOR);
    }

    private void m8978j() {
        if (!this.f5971o) {
            this.f5971o = true;
            this.f5959c.m9589a(C1584h.NOTIDRONEAIR);
        }
    }

    public void m8979a() {
        C1710x.m11160a(false);
        this.f5968l.postDelayed(this.f5969m, 5000);
    }

    public void m8980b() {
        C1710x.m11160a(true);
    }

    public void m8981c() {
        if (this.f5967k != null) {
            this.f5967k.m8991a();
        }
        this.f5959c.m9594b((C1234i) this);
    }

    public void m8982d() {
        if (this.f5967k != null) {
            this.f5967k.m8993b();
        }
        this.f5959c.m9590a((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (c1433a.m9570Q() && c1433a.m9569P().m9995a()) {
            switch (C1340g.f5977a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    C1683q.m10886a().m10887a("147");
                case Type.BYTE /*3*/:
                    C1683q.m10886a().m10887a("146");
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    C1683q.m10886a().m10887a("145");
                    c1433a.m9589a(C1584h.NOTIDRONEFLOOR);
                case Type.INT /*5*/:
                    C1683q.m10886a().m10887a("144");
                    c1433a.m9589a(C1584h.NOTIDRONEFLOOR);
                case Type.FLOAT /*6*/:
                    this.f5960d = c1433a.ah().m10170e();
                    this.f5961e = c1433a.ah().m10169d();
                    this.f5962f = c1433a.ah().m10171f();
                    this.f5957a.setFlightModel(this.f5960d, this.f5961e);
                    m8974f();
                    if (this.f5960d != 8) {
                        this.f5965i = true;
                    }
                    if (C1710x.m11165d().get()) {
                        m8975g();
                    }
                    if (this.f5963g == 5 && this.f5960d != 5) {
                        this.f5967k.m8992a(c1433a.f6507c.getString(C1205R.string.poi_point_com));
                    }
                    if (this.f5963g != 10 && this.f5960d == 10) {
                        c1433a.m9589a(C1584h.ENTRYTAKEPHOTOMODEL);
                    }
                    this.f5963g = this.f5960d;
                default:
            }
        }
    }
}
