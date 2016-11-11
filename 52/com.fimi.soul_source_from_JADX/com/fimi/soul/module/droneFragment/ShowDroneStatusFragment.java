package com.fimi.soul.module.droneFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.ab;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.biz.p097e.C1328a;
import com.fimi.soul.biz.p103k.aq;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p110d.C1478e;
import com.fimi.soul.drone.p117h.C1560c;
import com.fimi.soul.drone.p117h.C1583z;
import com.fimi.soul.entity.BatteryOverDischange;
import com.fimi.soul.media.player.IMediaPlayer;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.utils.C1963c;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.EVview;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.Timer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p122a.p123a.C2915a;

public class ShowDroneStatusFragment extends Fragment implements C1234i {
    private static final int f8015L = 1;
    private static final double f8016O = 0.20000000298023224d;
    public static int f8017c;
    public static int f8018d;
    private TextView f8019A;
    private TextView f8020B;
    private TextView f8021C;
    private Context f8022D;
    private C1433a f8023E;
    private DroidPlannerApp f8024F;
    private boolean f8025G;
    private int f8026H;
    private EVview f8027I;
    private LinearLayout f8028J;
    private float f8029K;
    private boolean f8030M;
    private boolean f8031N;
    private BatteryOverDischange f8032P;
    private C1963c f8033Q;
    private Handler f8034R;
    private boolean f8035S;
    private double f8036T;
    private double f8037U;
    aq f8038a;
    Timer f8039b;
    boolean f8040e;
    private TextView f8041f;
    private TextView f8042g;
    private TextView f8043h;
    private TextView f8044i;
    private TextView f8045j;
    private TextView f8046k;
    private TextView f8047l;
    private TextView f8048m;
    private TextView f8049n;
    private TextView f8050o;
    private TextView f8051p;
    private TextView f8052q;
    private TextView f8053r;
    private TextView f8054s;
    private TextView f8055t;
    private TextView f8056u;
    private TextView f8057v;
    private TextView f8058w;
    private TextView f8059x;
    private TextView f8060y;
    private TextView f8061z;

    public ShowDroneStatusFragment() {
        this.f8025G = true;
        this.f8030M = false;
        this.f8031N = false;
        this.f8034R = new ak(this);
        this.f8035S = true;
        this.f8040e = false;
    }

    private double m10913a() {
        double[] dArr = new double[]{(((double) this.f8023E.m9619v().m10514a()) / 100.0d) + 2.0d, (((double) this.f8023E.m9619v().m10516b()) / 100.0d) + 2.0d, (((double) this.f8023E.m9619v().m10517c()) / 100.0d) + 2.0d, (((double) this.f8023E.m9619v().m10518d()) / 100.0d) + 2.0d};
        double d = 0.0d;
        int i = 0;
        while (i < 4) {
            double d2 = d;
            for (int i2 = i + f8015L; i2 < 4; i2 += f8015L) {
                if (Math.abs(dArr[i2] - dArr[i]) > d2) {
                    d2 = Math.abs(dArr[i2] - dArr[i]);
                }
            }
            i += f8015L;
            d = d2;
        }
        return d;
    }

    private void m10915a(String str, TextView textView) {
        if (str != null && !str.equals(textView.getText().toString())) {
            textView.setText(str);
        }
    }

    private String m10917b(int i) {
        return ab.m8016b((((double) i) / 100.0d) + 2.0d, 2);
    }

    private void m10918b() {
        if (this.f8036T > 0.0d) {
            int g = this.f8023E.m9619v().m10521g();
            if (g < 0) {
                g = 0;
            }
            f8017c = (int) ((((double) g) / this.f8036T) * 100.0d);
            f8018d = (int) ((((double) g) / this.f8037U) * 100.0d);
            if (f8017c >= 0) {
                if (f8017c > 100) {
                    this.f8035S = true;
                    this.f8036T = 0.0d;
                    return;
                }
                if (f8018d <= 0 || f8018d >= 30) {
                    this.f8047l.setTextColor(getResources().getColor(C1205R.color.changebetery));
                    this.f8043h.setTextColor(getResources().getColor(C1205R.color.changebetery));
                    this.f8051p.setTextColor(getResources().getColor(C1205R.color.changebetery));
                    this.f8052q.setTextColor(getResources().getColor(C1205R.color.changebetery));
                } else {
                    this.f8047l.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                    this.f8043h.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                    this.f8051p.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                    this.f8052q.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                }
                this.f8026H += f8015L;
                if (this.f8026H % 3 == 0) {
                    if (this.f8025G) {
                        this.f8025G = false;
                    } else {
                        this.f8025G = true;
                    }
                }
                if (this.f8025G) {
                    if (f8018d <= 30) {
                        this.f8043h.setText(f8018d + C2915a.f14760f);
                    } else {
                        this.f8043h.setText(f8017c + C2915a.f14760f);
                    }
                    this.f8052q.setVisibility(8);
                    this.f8051p.setVisibility(0);
                    m10915a("%", this.f8051p);
                    m10915a(getString(C1205R.string.electricity), this.f8047l);
                    this.f8051p.setTextSize(16.0f);
                } else {
                    short j = this.f8023E.m9619v().m10524j();
                    if (j >= (short) 0 && j <= (short) 35) {
                        m10915a(j + C2915a.f14760f, this.f8043h);
                        this.f8047l.setText(C1205R.string.endurance);
                        this.f8052q.setVisibility(0);
                        this.f8051p.setVisibility(8);
                        m10915a(getString(C1205R.string.minute), this.f8052q);
                        this.f8052q.setTextSize(11.0f);
                    } else {
                        return;
                    }
                }
                this.f8023E.m9589a(C1584h.NOTIFYPBATTERY);
            }
        }
    }

    private float m10919c(int i) {
        return (i < 0 || i > 78) ? (i <= 78 || i > Opcodes.IFGE) ? (i <= Opcodes.IFGE || i > 234) ? (i <= 234 || i > 312) ? (i <= 312 || i > 390) ? (i <= 390 || i > 468) ? (i <= 468 || i > 546) ? (i <= 546 || i > 624) ? (i <= 624 || i > IMediaPlayer.MEDIA_INFO_BUFFERING_END) ? (i <= IMediaPlayer.MEDIA_INFO_BUFFERING_END || i > 780) ? (i <= 780 || i > 858) ? (i <= 858 || i > 936) ? (i <= 936 || i > SmileConstants.MAX_SHARED_STRING_VALUES) ? 0.0f : 2.0f : 1.7f : 1.3f : C2020f.f10933c : 0.7f : 0.3f : 0.0f : -0.3f : -0.7f : GroundOverlayOptions.NO_DIMENSION : -1.3f : -1.7f : -2.0f;
    }

    public void m10922a(int i) {
        this.f8019A.setVisibility(i);
        this.f8061z.setVisibility(i);
    }

    public void m10923a(boolean z) {
        if (z) {
            this.f8028J.setVisibility(8);
            this.f8020B.setVisibility(8);
            this.f8021C.setVisibility(8);
            this.f8040e = z;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8022D = activity.getApplicationContext();
        this.f8024F = (DroidPlannerApp) activity.getApplication();
        this.f8038a = aq.m9219a(getActivity());
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.showdronestates, null);
        if (C1189f.m8335e().m8031c()) {
            inflate.findViewById(C1205R.id.bottom_state_rl).setVisibility(0);
        } else {
            inflate.findViewById(C1205R.id.bottom_state_rl).setVisibility(4);
        }
        this.f8053r = (TextView) inflate.findViewById(C1205R.id.deslevelspeek);
        this.f8054s = (TextView) inflate.findViewById(C1205R.id.levelspeek);
        this.f8027I = (EVview) inflate.findViewById(C1205R.id.ev_v);
        this.f8055t = (TextView) inflate.findViewById(C1205R.id.desverticalspeek);
        this.f8056u = (TextView) inflate.findViewById(C1205R.id.verticalspeek);
        this.f8057v = (TextView) inflate.findViewById(C1205R.id.desremotesign);
        this.f8058w = (TextView) inflate.findViewById(C1205R.id.remotesign);
        this.f8059x = (TextView) inflate.findViewById(C1205R.id.desimagetransmission);
        this.f8060y = (TextView) inflate.findViewById(C1205R.id.imagetransmission);
        this.f8061z = (TextView) inflate.findViewById(C1205R.id.network_text);
        this.f8019A = (TextView) inflate.findViewById(C1205R.id.network);
        this.f8020B = (TextView) inflate.findViewById(C1205R.id.ev_text);
        this.f8021C = (TextView) inflate.findViewById(C1205R.id.ev_value_tv);
        this.f8028J = (LinearLayout) inflate.findViewById(C1205R.id.ev_layout);
        this.f8045j = (TextView) inflate.findViewById(C1205R.id.texthight);
        this.f8045j.getPaint().setFakeBoldText(true);
        this.f8046k = (TextView) inflate.findViewById(C1205R.id.textdistance);
        this.f8046k.getPaint().setFakeBoldText(true);
        this.f8047l = (TextView) inflate.findViewById(C1205R.id.textpower);
        this.f8047l.getPaint().setFakeBoldText(true);
        this.f8049n = (TextView) inflate.findViewById(C1205R.id.texthightbelowafter);
        this.f8048m = (TextView) inflate.findViewById(C1205R.id.textsign);
        this.f8048m.getPaint().setFakeBoldText(true);
        this.f8044i = (TextView) inflate.findViewById(C1205R.id.textsignbelow);
        this.f8050o = (TextView) inflate.findViewById(C1205R.id.textdistancenum);
        this.f8051p = (TextView) inflate.findViewById(C1205R.id.textpowernumbelow);
        this.f8052q = (TextView) inflate.findViewById(C1205R.id.textpowernumbelowsec);
        this.f8041f = (TextView) inflate.findViewById(C1205R.id.texthightbelow);
        this.f8041f.getPaint().setFakeBoldText(true);
        this.f8043h = (TextView) inflate.findViewById(C1205R.id.textpowernum);
        this.f8043h.getPaint().setFakeBoldText(true);
        this.f8042g = (TextView) inflate.findViewById(C1205R.id.textdistancenumbelow);
        this.f8042g.getPaint().setFakeBoldText(true);
        this.f8044i = (TextView) inflate.findViewById(C1205R.id.textsignbelow);
        this.f8044i.getPaint().setFakeBoldText(true);
        be.m12368b(getActivity().getAssets(), this.f8041f, this.f8042g, this.f8043h, this.f8049n, this.f8050o, this.f8051p, this.f8054s, this.f8056u);
        be.m12359a(getActivity().getAssets(), this.f8044i, this.f8045j, this.f8046k, this.f8047l, this.f8048m, this.f8052q, this.f8053r, this.f8055t, this.f8057v, this.f8059x, this.f8058w, this.f8060y, this.f8061z, this.f8019A);
        this.f8023E = this.f8024F.f5570a;
        this.f8038a.m9224a(new al(this));
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f8023E.m9594b((C1234i) this);
        this.f8038a.m9223a();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        int i = 0;
        if (c1433a.m9570Q() && c1433a.m9569P().m9995a()) {
            double a;
            byte a2;
            switch (an.f8182a[c1584h.ordinal()]) {
                case f8015L /*1*/:
                    if (c1433a.m9570Q() && this.f8035S) {
                        C1478e.m9859a(c1433a);
                    }
                    a = ab.m8015a(c1433a.m9614q().m10272a() / 100.0d, f8015L);
                    double a3 = ab.m8015a(c1433a.m9614q().m10277b() / 100.0d, f8015L);
                    m10915a(a + "m/s", this.f8054s);
                    m10915a(a3 + "m/s", this.f8056u);
                    if (!c1433a.m9570Q()) {
                        this.f8031N = false;
                    }
                    if (!this.f8031N && m10913a() > f8016O) {
                        if (this.f8033Q == null) {
                            this.f8033Q = C1963c.m12455a(this.f8022D);
                        }
                        this.f8032P = new BatteryOverDischange();
                        this.f8032P.setVoltage(ab.m8016b((((double) (((c1433a.m9619v().m10514a() + c1433a.m9619v().m10516b()) + c1433a.m9619v().m10517c()) + c1433a.m9619v().m10518d())) / 100.0d) + 8.0d, 2));
                        short g = c1433a.m9619v().m10521g();
                        if (g >= (short) 0) {
                            short s = g;
                        }
                        this.f8032P.setBatteryCurrent(i + C2915a.f14760f);
                        this.f8032P.setTemperature((c1433a.m9619v().m10523i() - 60) + C2915a.f14760f);
                        this.f8036T = (double) c1433a.m9623z().m10530e();
                        this.f8037U = (double) c1433a.m9623z().m10529d();
                        this.f8032P.setBatteryFull((this.f8037U > this.f8036T ? (int) this.f8037U : (int) this.f8036T) + C2915a.f14760f);
                        f8017c = (int) ((((double) i) / this.f8036T) * 100.0d);
                        f8018d = (int) ((((double) i) / this.f8037U) * 100.0d);
                        if (f8018d <= 30) {
                            this.f8032P.setBatteryLevel(f8018d + "%");
                        } else {
                            this.f8032P.setBatteryLevel(f8017c + "%");
                        }
                        this.f8032P.setUserId(C1236a.m8533b(this.f8022D).getUserID());
                        this.f8032P.setBatteryId(C1901a.m12002a().m12003a(5).m12059i() == null ? Constants.VIA_RESULT_SUCCESS : C1901a.m12002a().m12003a(5).m12059i());
                        this.f8032P.setBatteryLevel(c1433a.m9619v().m10524j() + C2915a.f14760f);
                        this.f8032P.setBatteryOne(m10917b(c1433a.m9619v().m10514a()));
                        this.f8032P.setBatteryTwo(m10917b(c1433a.m9619v().m10516b()));
                        this.f8032P.setBatteryThree(m10917b(c1433a.m9619v().m10517c()));
                        this.f8032P.setBatteryFour(m10917b(c1433a.m9619v().m10518d()));
                        this.f8032P.setBatteryRecyle(c1433a.m9623z().m10528c() + C2915a.f14760f);
                        this.f8032P.setVersion(C1901a.m12002a().m12003a(5).m12046b() + C2915a.f14760f);
                        this.f8032P.setAppType(Constants.VIA_RESULT_SUCCESS);
                        new C1328a(getActivity()).m8950a(this.f8032P);
                        this.f8031N = true;
                        return;
                    }
                    return;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    a2 = c1433a.m9560G().m10408a();
                    if (30 <= a2 && a2 < 80) {
                        m10915a(getString(C1205R.string.remotrmid), this.f8058w);
                        return;
                    } else if (a2 >= 80) {
                        m10915a(getString(C1205R.string.strong), this.f8058w);
                        return;
                    } else if (5 >= a2 || a2 >= 30) {
                        m10915a(getString(C1205R.string.nothing), this.f8058w);
                        return;
                    } else {
                        m10915a(getString(C1205R.string.remotelow), this.f8058w);
                        return;
                    }
                case Type.BYTE /*3*/:
                    a = (c1433a.m9617t().m10362f() - 677216.0d) / 10.0d;
                    if (!c1433a.aa()) {
                        a = 0.0d;
                    }
                    if (a < 3000.0d) {
                        if (a >= 100.0d || a < 0.0d) {
                            if (a < -999.0d) {
                                a = -999.0d;
                            }
                            m10915a(a < 100.0d ? ab.m8015a(a, f8015L) + C2915a.f14760f : ((int) a) + C2915a.f14760f, this.f8041f);
                        } else {
                            m10915a(ab.m8015a(a, f8015L) + C2915a.f14760f, this.f8041f);
                        }
                    }
                    a = c1433a.m9617t().m10363g();
                    if (!c1433a.aa()) {
                        a = 0.0d;
                    }
                    if (a > -50.0d && a < 10000.0d && !c1433a.ah().m10172g().isLightStream()) {
                        if (a >= 100.0d) {
                            m10915a(((int) a) + C2915a.f14760f, this.f8042g);
                        } else {
                            m10915a(ab.m8015a(a, f8015L) + C2915a.f14760f, this.f8042g);
                        }
                    }
                    C1325k a4 = C1325k.m8930a();
                    int f = a4.m8941f();
                    if (!C1325k.m8930a().m8943h() || f >= 50 || a >= 300.0d || c1433a.ah().m10172g().isLightStream()) {
                        this.f8030M = false;
                    } else if (!this.f8030M) {
                        this.f8034R.sendEmptyMessage(f8015L);
                        this.f8030M = true;
                    }
                    if (!a4.m8944i() && !a4.m8943h()) {
                        m10915a(getString(C1205R.string.nothing), this.f8060y);
                    } else if (30 <= f && f < 60) {
                        m10915a(getString(C1205R.string.remotrmid), this.f8060y);
                    } else if (f >= 60) {
                        m10915a(getString(C1205R.string.strong), this.f8060y);
                    } else if (f <= 0 || f >= 30) {
                        m10915a(getString(C1205R.string.nothing), this.f8060y);
                    } else {
                        m10915a(getString(C1205R.string.remotelow), this.f8060y);
                    }
                    if (c1433a.ah().m10172g().isLightStream()) {
                        m10915a(getString(C1205R.string.nothing), this.f8044i);
                        return;
                    }
                    a2 = c1433a.m9617t().m10359c();
                    if (a2 > 13) {
                        m10915a(a2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, this.f8044i);
                    } else if ((byte) 8 < a2 && a2 <= 13) {
                        m10915a(a2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, this.f8044i);
                    } else if (a2 > (byte) 8 || a2 <= 4) {
                        m10915a(getString(C1205R.string.nothing), this.f8044i);
                    } else {
                        m10915a(a2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, this.f8044i);
                    }
                    if (a2 <= (byte) 8) {
                        this.f8044i.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                        this.f8048m.setTextColor(getResources().getColor(C1205R.color.changebeterylow));
                        return;
                    }
                    this.f8044i.setTextColor(getResources().getColor(C1205R.color.changebetery));
                    this.f8048m.setTextColor(getResources().getColor(C1205R.color.changebetery));
                    return;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    this.f8035S = false;
                    this.f8036T = (double) c1433a.m9623z().m10530e();
                    this.f8037U = (double) c1433a.m9623z().m10529d();
                    return;
                case Type.INT /*5*/:
                    m10918b();
                    return;
                case Type.FLOAT /*6*/:
                    C1560c H = c1433a.m9561H();
                    if (H != null && H.m10511b() == 115) {
                        switch (H.f7499d) {
                            case f8015L /*1*/:
                                if (!this.f8040e) {
                                    m10923a(false);
                                    return;
                                }
                                return;
                            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                                m10923a(true);
                                return;
                            default:
                                return;
                        }
                    }
                    return;
                case Type.LONG /*7*/:
                    C1583z e = c1433a.m9600e();
                    if (e.m10633c() == 2) {
                        int d = e.m10635d() & Util.MASK_8BIT;
                        int e2 = e.m10637e() & Util.MASK_8BIT;
                        float c = m10919c((e2 << 8) | d);
                        if (this.f8029K == c || (e2 == Util.MASK_8BIT && d == Util.MASK_8BIT)) {
                            if (this.f8039b == null) {
                                this.f8039b = new Timer();
                            }
                            this.f8039b.schedule(new am(this), 3000);
                            return;
                        }
                        this.f8029K = c;
                        this.f8021C.setText(String.valueOf(c));
                        this.f8027I.m12579a(c);
                        if (this.f8028J.getVisibility() == 8 && !this.f8040e) {
                            this.f8021C.setVisibility(0);
                            this.f8020B.setVisibility(0);
                            this.f8028J.setVisibility(0);
                        }
                        if (this.f8039b != null) {
                            this.f8039b.cancel();
                            this.f8039b = null;
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
        if (!c1433a.m9569P().m9995a()) {
            this.f8052q.setVisibility(8);
            this.f8051p.setVisibility(0);
            m10915a("0.0", this.f8042g);
            m10915a("0.0", this.f8041f);
            m10915a(Constants.VIA_RESULT_SUCCESS, this.f8043h);
            m10915a("0.0m/s", this.f8054s);
            m10915a("0.0m/s", this.f8056u);
            m10915a(getString(C1205R.string.electricity), this.f8047l);
            m10915a("%", this.f8051p);
            m10915a(getString(C1205R.string.nothing), this.f8044i);
            m10915a(getString(C1205R.string.nothing), this.f8058w);
            m10915a(getString(C1205R.string.nothing), this.f8060y);
        }
        if (!c1433a.m9570Q()) {
            this.f8036T = 0.0d;
            this.f8035S = true;
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        this.f8023E.m9590a((C1234i) this);
    }

    public void onStop() {
        super.onStop();
    }
}
