package com.fimi.soul.biz.p090b;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p100g.C1335b;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ao;
import com.fimi.soul.drone.p117h.ar;
import com.fimi.soul.drone.p117h.az;
import com.fimi.soul.drone.p117h.p118a.C1552a;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.module.droneFragment.FlyActionSettingFragment;
import com.fimi.soul.module.dronemanage.C1722i;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.module.setting.am;
import com.fimi.soul.utils.ad;
import com.fimi.soul.utils.aj;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.util.List;
import java.util.Observable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

/* renamed from: com.fimi.soul.biz.b.c */
public class C1244c extends Observable implements C1234i {
    private static final int f5643r = 11;
    private C1253k f5644a;
    private C1249h f5645b;
    private C1261s f5646c;
    private C1243b f5647d;
    private C1256n f5648e;
    private C1722i f5649f;
    private C1433a f5650g;
    private int f5651h;
    private int f5652i;
    private boolean f5653j;
    private boolean f5654k;
    private boolean f5655l;
    private boolean f5656m;
    private int f5657n;
    private boolean f5658o;
    private boolean f5659p;
    private FragmentManager f5660q;
    private Handler f5661s;

    public C1244c(C1433a c1433a, AMap aMap, Context context, FragmentManager fragmentManager) {
        this.f5653j = true;
        this.f5654k = true;
        this.f5655l = true;
        this.f5656m = true;
        this.f5657n = XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER;
        this.f5658o = true;
        this.f5661s = new C1245d(this);
        this.f5660q = fragmentManager;
        this.f5650g = c1433a;
        c1433a.m9590a((C1234i) this);
        this.f5644a = C1253k.m8598a(c1433a.f6507c);
        this.f5645b = new C1249h(aMap, context, c1433a);
        this.f5646c = new C1261s(aMap, context, c1433a);
        this.f5648e = new C1256n(aMap, context, c1433a);
        this.f5649f = new C1722i(aMap, context, c1433a);
        this.f5647d = new C1243b(aMap, context, c1433a);
    }

    private void m8555e() {
        if (this.f5651h == 4 && this.f5652i == 1 && !this.f5659p) {
            this.f5645b.m8588c();
            ak.m8083a(this.f5650g.f6507c, (int) C1205R.string.excute_direction_com, 3000);
            C1160b.m7953b(this.f5650g.f6507c).m7959a(this.f5650g.f6507c.getString(C1205R.string.excute_direction_com));
            this.f5645b.m8589d();
        }
        this.f5651h = this.f5652i;
    }

    private void m8556f() {
        if (this.f5651h == 10 && this.f5652i == 1 && !this.f5659p) {
            ak.m8083a(this.f5650g.f6507c, (int) C1205R.string.excute_takephoto_com, 3000);
            C1160b.m7953b(this.f5650g.f6507c).m7959a(this.f5650g.f6507c.getString(C1205R.string.excute_takephoto_com));
            C1253k.m8598a(this.f5650g.f6507c).m8600a(0);
        }
        this.f5651h = this.f5652i;
    }

    private void m8557g() {
        ao t = this.f5650g.m9617t();
        aj a = ad.m12227a((double) t.m10361e(), (double) t.m10360d());
        LatLng latLng = new LatLng(a.m12250a(), a.m12252b());
        if (this.f5644a.m8599a().get() == 1) {
            this.f5646c.m8630a(latLng);
        } else if (this.f5644a.m8599a().get() == 2) {
            this.f5645b.m8585a(latLng);
        } else if (this.f5644a.m8599a().get() == 3) {
            this.f5648e.m8612a(latLng);
        } else if (this.f5644a.m8599a().get() == 4) {
            this.f5647d.m8546b(latLng);
        }
    }

    private void m8558h() {
        int e = this.f5650g.m9618u().m10304e();
        if (this.f5651h == 6 && this.f5652i == 1) {
            this.f5646c.m8634c();
        } else if (this.f5651h == 7 && this.f5652i == 1 && this.f5650g.aa()) {
            this.f5650g.m9589a(C1584h.NOTIDRONEAIR);
        }
        this.f5651h = this.f5652i;
        if (this.f5652i == 6) {
            m8557g();
            if (!this.f5658o) {
                this.f5646c.m8629a(e);
            }
        }
    }

    public void m8559a() {
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (j != null) {
            switch (j.getModelType()) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (2 == j.getType()) {
                        this.f5646c.m8632a(j);
                        return;
                    }
                    C1247f.m8565k().m8583l();
                    this.f5650g.m9589a(C1584h.CLEARDATA);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    this.f5645b.m8587b();
                case Type.BYTE /*3*/:
                    this.f5648e.m8622i();
                default:
            }
        }
    }

    public void m8560a(LatLng latLng, int i) {
        if (C1247f.m8565k().m8576d()) {
            Marker c = C1247f.m8565k().m8575c();
            if (c != null) {
                c.hideInfoWindow();
                FlyActionBean flyActionBean = (FlyActionBean) c.getObject();
                if (flyActionBean.getModelType() == 1) {
                    c.setIcon(C1255m.m8606a(this.f5650g.f6507c, C1205R.drawable.icon_fly_waypoint_blue, flyActionBean.getHeight(), false));
                }
                C1247f.m8565k().m8569a(null);
                C1247f.m8565k().m8573a(false);
            }
        } else if (this.f5644a.m8599a().get() == 1) {
            this.f5646c.m8631a(latLng, i);
        } else if (this.f5644a.m8599a().get() == 2) {
            this.f5645b.m8586a(latLng, i);
        } else if (this.f5644a.m8599a().get() != 3) {
        }
    }

    public void m8561a(Marker marker) {
        FlyActionSettingFragment flyActionSettingFragment = (FlyActionSettingFragment) this.f5660q.findFragmentById(C1205R.id.flyaction);
        FlyActionBean flyActionBean = (FlyActionBean) marker.getObject();
        List<Marker> e = C1247f.m8565k().m8577e();
        if (e != null && flyActionBean != null) {
            C1247f.m8565k().m8571a(flyActionBean);
            for (Marker marker2 : e) {
                FlyActionBean flyActionBean2 = (FlyActionBean) marker2.getObject();
                if (marker2.getObject().equals(flyActionBean)) {
                    if (flyActionSettingFragment != null) {
                        if (flyActionSettingFragment.isVisible()) {
                            this.f5650g.m9589a(C1584h.SHOWHEIGHTVIEW);
                            if (flyActionBean.getModelType() == 1) {
                                flyActionBean2.setDrawableRes(C1205R.drawable.icon_fly_waypoint_red);
                                marker.setIcon(C1255m.m8606a(this.f5650g.f6507c, C1205R.drawable.icon_fly_waypoint_red, flyActionBean2.getHeight(), true));
                                C1247f.m8565k().m8573a(true);
                                C1247f.m8565k().m8571a(flyActionBean2);
                                C1247f.m8565k().m8569a(marker2);
                            }
                        } else if (flyActionBean.getModelType() == 1) {
                            this.f5646c.m8636e();
                        } else if (flyActionBean.getModelType() == 2) {
                            marker.setTitle(this.f5650g.f6507c.getString(C1205R.string.delete_marker));
                            marker.showInfoWindow();
                        } else if (flyActionBean.getModelType() == 3) {
                            marker.setTitle(this.f5650g.f6507c.getString(C1205R.string.delete_marker));
                            marker.showInfoWindow();
                        }
                    }
                } else if (flyActionSettingFragment != null && flyActionSettingFragment.isVisible() && 2 == flyActionBean.getType()) {
                    marker2.setIcon(C1255m.m8606a(this.f5650g.f6507c, C1205R.drawable.icon_fly_waypoint_blue, flyActionBean2.getHeight(), false));
                    flyActionBean2.setDrawableRes(C1205R.drawable.icon_fly_waypoint_blue);
                }
            }
        }
    }

    public void m8562b() {
        if (this.f5644a.m8599a().get() == 1) {
            this.f5646c.m8635d();
        } else if (this.f5644a.m8599a().get() != 2 && this.f5644a.m8599a().get() == 3) {
        }
    }

    public void m8563c() {
        this.f5649f.m11247b();
        this.f5653j = true;
        this.f5654k = true;
        this.f5655l = true;
        this.f5656m = true;
    }

    public void m8564d() {
        this.f5650g.m9590a((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1246e.f5663a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                this.f5647d.m8547c();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f5648e.m8610a();
            case Type.BYTE /*3*/:
                this.f5653j = true;
                C1247f.m8565k().m8583l();
                this.f5646c.m8633b();
                this.f5645b.m8587b();
                this.f5648e.m8622i();
                this.f5647d.m8547c();
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                this.f5646c.m8636e();
            case Type.INT /*5*/:
                this.f5659p = true;
                if (this.f5661s.hasMessages(f5643r)) {
                    this.f5661s.removeMessages(f5643r);
                    this.f5661s.sendEmptyMessageDelayed(f5643r, 2000);
                } else {
                    this.f5661s.sendEmptyMessageDelayed(f5643r, 2000);
                }
                az n = c1433a.m9611n();
                if (n.m10440e() == 51) {
                    List e = C1247f.m8565k().m8577e();
                    if (e != null && e.size() == 0) {
                        aj a = ad.m12227a((double) n.m10443h(), n.m10433b());
                        this.f5645b.m8586a(new LatLng(a.m12250a(), a.m12252b()), C1205R.drawable.img_fly_flag_blue);
                    }
                    this.f5645b.m8584a();
                }
                C1335b.m8963a(n.m10438d());
                C1664h.m10813a(c1433a).m10827c(Opcodes.I2L);
            case Type.FLOAT /*6*/:
                this.f5658o = true;
                if (!this.f5661s.hasMessages(this.f5657n)) {
                    this.f5661s.sendEmptyMessageDelayed(this.f5657n, 3000);
                }
                this.f5646c.m8628a();
            case Type.LONG /*7*/:
                this.f5652i = c1433a.ah().m10170e();
            case Type.DOUBLE /*8*/:
            case Type.ARRAY /*9*/:
                this.f5646c.m8633b();
                this.f5645b.m8587b();
                this.f5648e.m8622i();
                C1247f.m8565k().m8583l();
                c1433a.m9589a(C1584h.CLEARPOIDATA);
            case Type.OBJECT /*10*/:
                this.f5658o = true;
                if (!this.f5661s.hasMessages(this.f5657n)) {
                    this.f5661s.sendEmptyMessageDelayed(this.f5657n, 3000);
                }
                this.f5646c.m8628a();
            case f5643r /*11*/:
                if (this.f5644a.m8599a().get() == 1) {
                    if (this.f5653j && C1247f.m8565k().m8580h().size() < 1 && this.f5652i == 6) {
                        this.f5653j = false;
                        c1433a.m9589a(C1584h.READWAYPOINT);
                        return;
                    }
                    m8558h();
                } else if (this.f5644a.m8599a().get() == 2) {
                    if (this.f5654k && C1247f.m8565k().m8580h().size() < 1 && this.f5652i == 4) {
                        this.f5654k = false;
                        c1433a.m9589a(C1584h.READFLYTO);
                        return;
                    }
                    if (this.f5652i == 4) {
                        m8557g();
                        this.f5645b.m8591f();
                    }
                    m8555e();
                } else if (this.f5644a.m8599a().get() == 3) {
                    if (this.f5655l && C1247f.m8565k().m8580h().size() < 1 && this.f5652i == 5) {
                        this.f5655l = false;
                        c1433a.m9589a(C1584h.READPOI);
                    } else if (this.f5652i == 5) {
                        m8557g();
                    }
                } else if (this.f5644a.m8599a().get() != 4) {
                    this.f5651h = 0;
                } else if (this.f5656m && this.f5647d.m8551g() && this.f5652i == 10) {
                    this.f5656m = false;
                    C1664h.m10813a(c1433a).m10817a((byte) 19, (byte) 0, (short) 0, (short) 0, (byte) 0, (short) 0, 0.0f, 0.0f);
                } else {
                    if (this.f5652i == 10) {
                        m8557g();
                    }
                    m8556f();
                }
            case Opcodes.FCONST_1 /*12*/:
            case Opcodes.FCONST_2 /*13*/:
                this.f5646c.m8637f();
                this.f5645b.m8592g();
                this.f5648e.m8618e();
            case Opcodes.DCONST_0 /*14*/:
                this.f5646c.m8638g();
                this.f5645b.m8593h();
                this.f5648e.m8619f();
            case Opcodes.DCONST_1 /*15*/:
                this.f5646c.m8639h();
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                this.f5659p = true;
                this.f5645b.m8590e();
            case Opcodes.SIPUSH /*17*/:
                m8559a();
            case Opcodes.LDC /*18*/:
                ar p = c1433a.m9613p();
                if (p.m10384j() == 22 && C1247f.m8565k().m8580h().size() < 1 && this.f5652i == 5) {
                    FlyActionBean flyActionBean = new FlyActionBean();
                    flyActionBean.setYaw_mode(p.m10376b() / 10);
                    aj a2 = ad.m12227a((double) p.m10382h(), (double) p.m10383i());
                    flyActionBean.setLatLng(new LatLng(a2.m12250a(), a2.m12252b()));
                    flyActionBean.setDrawableRes(C1205R.drawable.img_fly_flag_blue);
                    flyActionBean.setCanclick(true);
                    flyActionBean.setType(1);
                    flyActionBean.setModelType(3);
                    flyActionBean.setStyleInfo(2);
                    flyActionBean.setStart_point_agle((short) ((int) (((double) p.m10378d()) * 1.412d)));
                    flyActionBean.setSpeek(p.m10379e() / 10);
                    flyActionBean.setRidus(p.m10380f() / 10);
                    flyActionBean.setPara1(p.m10377c());
                    flyActionBean.setHeight(((int) p.m10381g()) / 10);
                    this.f5648e.m8614a(flyActionBean);
                } else {
                    this.f5648e.m8620g();
                }
                c1433a.m9589a(C1584h.CLOSECARMERPOI);
            case am.f9249v /*19*/:
                this.f5648e.m8619f();
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                C1552a an = c1433a.an();
                if (an.m10147j() == 18) {
                    this.f5659p = true;
                    if (this.f5661s.hasMessages(f5643r)) {
                        this.f5661s.removeMessages(f5643r);
                        this.f5661s.sendEmptyMessageDelayed(f5643r, 2000);
                        return;
                    }
                    this.f5661s.sendEmptyMessageDelayed(f5643r, 2000);
                    return;
                }
                aj a3 = ad.m12227a((double) an.m10140c(), (double) an.m10142e());
                this.f5647d.m8544a(new LatLng(a3.m12250a(), a3.m12252b()));
                this.f5647d.m8545b();
            case Opcodes.ILOAD /*21*/:
                this.f5659p = true;
            case Opcodes.LLOAD /*22*/:
                this.f5647d.m8547c();
            case Opcodes.FLOAD /*23*/:
                this.f5647d.m8543a();
            case Opcodes.DLOAD /*24*/:
                this.f5647d.m8545b();
            default:
        }
    }
}
