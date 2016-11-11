package com.fimi.soul.module.setting.newhand;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.button.C1196c;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseActivity;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.setting.C1886p;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.p121a.C1902b;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.at;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class GpsSettingActivity extends BaseActivity implements OnClickListener, C1196c, C1234i {
    private static final int f9317B = 1;
    private static final int f9318C = 2;
    private static final int f9319D = 3;
    private static final int f9320E = 4;
    private static final int f9321F = 1000;
    private static final float f9322G = 0.3f;
    private static final float f9323H = 1.0f;
    private static final int f9324I = 300;
    private static final int f9325J = 3000;
    public static final String f9326b = "newhand_mode";
    public static final String f9327c = "optical_flow_mode";
    public static final String f9328d = "force_attitude_mode";
    private boolean f9329A;
    private boolean f9330K;
    private boolean f9331L;
    private boolean f9332M;
    private Handler f9333N;
    TextView f9334a;
    private RelativeLayout f9335e;
    private RelativeLayout f9336f;
    private RelativeLayout f9337g;
    private TextView f9338h;
    private TextView f9339i;
    private TextView f9340j;
    private TextView f9341k;
    private TextView f9342l;
    private TextView f9343m;
    private ImageView f9344n;
    private ImageView f9345o;
    private SwitchButton f9346p;
    private Context f9347q;
    private ac f9348r;
    private C1902b f9349s;
    private boolean f9350t;
    private C1433a f9351u;
    private boolean f9352v;
    private int f9353w;
    private int f9354x;
    private int f9355y;
    private int f9356z;

    public GpsSettingActivity() {
        this.f9352v = false;
        this.f9329A = false;
        this.f9330K = false;
        this.f9331L = false;
        this.f9332M = false;
        this.f9333N = new C1860b(this);
    }

    private void m11751a() {
        this.f9335e = (RelativeLayout) findViewById(C1205R.id.flight_speed_rl);
        this.f9336f = (RelativeLayout) findViewById(C1205R.id.flight_distance_rl);
        this.f9337g = (RelativeLayout) findViewById(C1205R.id.flight_back_height_rl);
        this.f9335e.setOnClickListener(this);
        this.f9336f.setOnClickListener(this);
        this.f9337g.setOnClickListener(this);
        this.f9338h = (TextView) findViewById(C1205R.id.flight_speed_setting_coontent_tv);
        this.f9339i = (TextView) findViewById(C1205R.id.flight_distance_settig_coontent_tv);
        this.f9343m = (TextView) findViewById(C1205R.id.flight_distance_settig_child_coontent_tv);
        this.f9340j = (TextView) findViewById(C1205R.id.flight_back_height_coontent_tv);
        this.f9341k = (TextView) findViewById(C1205R.id.flight_speed_setting_delcare);
        this.f9342l = (TextView) findViewById(C1205R.id.flight_back_height_setting_delcare);
        ((TextView) findViewById(C1205R.id.tv_settingTitle)).setText(getString(C1205R.string.setting_gps_mode));
        this.f9334a = (TextView) findViewById(C1205R.id.is_connect_tv);
        be.m12359a(getAssets(), this.f9338h, this.f9339i, this.f9343m, this.f9340j, this.f9341k, this.f9342l, r0, this.f9334a);
        this.f9344n = (ImageView) findViewById(C1205R.id.flight_speed_setting_more_iv);
        this.f9345o = (ImageView) findViewById(C1205R.id.flight_back_height_setting_more_iv);
        this.f9346p = (SwitchButton) findViewById(C1205R.id.flight_distance_switch_btn);
        this.f9346p.setOnSwitchListener(this);
        this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
        findViewById(C1205R.id.black_btn).setOnClickListener(new C1861c(this));
    }

    @TargetApi(16)
    private void m11752a(boolean z) {
        float f = z ? f9322G : f9323H;
        this.f9338h.setAlpha(f);
        this.f9341k.setAlpha(f);
        this.f9344n.setAlpha(f);
        this.f9339i.setAlpha(f);
        this.f9343m.setAlpha(f);
        this.f9346p.setAlpha(f);
        this.f9340j.setAlpha(f);
        this.f9342l.setAlpha(f);
        this.f9345o.setAlpha(f);
        if (z) {
            this.f9335e.setBackgroundColor(getResources().getColor(C1205R.color.list_nomal));
            this.f9336f.setBackgroundColor(getResources().getColor(C1205R.color.list_nomal));
            this.f9337g.setBackgroundColor(getResources().getColor(C1205R.color.list_nomal));
            return;
        }
        this.f9335e.setBackground(getResources().getDrawable(C1205R.drawable.list_setting_selector));
        this.f9336f.setBackground(getResources().getDrawable(C1205R.drawable.list_setting_selector));
        this.f9337g.setBackground(getResources().getDrawable(C1205R.drawable.list_setting_selector));
    }

    public void m11765a(int i, boolean z) {
        if (!z) {
            this.f9355y = i;
            C1189f.m8335e().m8029c(this.f9355y + C2915a.f14760f);
        }
        if (i <= 6) {
            this.f9341k.setText(i + this.f9347q.getString(C1205R.string.speed_unit_m) + this.f9347q.getString(C1205R.string.setting_speed_low));
        } else if (i <= 6 || i > 10) {
            this.f9341k.setText(i + this.f9347q.getString(C1205R.string.speed_unit_m) + this.f9347q.getString(C1205R.string.setting_speed_hight));
        } else {
            this.f9341k.setText(i + this.f9347q.getString(C1205R.string.speed_unit_m) + this.f9347q.getString(C1205R.string.setting_speed_standard));
        }
    }

    public void m11766a(View view, boolean z) {
        if (view.getId() == C1205R.id.flight_distance_switch_btn) {
            if (z) {
                this.f9346p.m8371a(false, false);
            } else {
                this.f9346p.m8371a(true, false);
            }
            if (!this.f9352v) {
                this.f9348r.m11828a(this.f9356z, new C1867i(this));
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.flight_speed_rl:
                if (!this.f9352v) {
                    this.f9348r.m11834b(this.f9355y, new C1862d(this));
                }
            case C1205R.id.flight_distance_rl:
                if (!this.f9352v) {
                    this.f9348r.m11828a(this.f9356z, new C1863e(this));
                }
            case C1205R.id.flight_back_height_rl:
                if (!this.f9352v) {
                    if (!this.f9350t && this.f9353w < C1886p.f9611g) {
                        ak.m8085a(this.f9347q, this.f9347q.getString(C1205R.string.please_update_the_last_flight_version), 0);
                    } else if (!this.f9351u.m9570Q()) {
                        ak.m8083a(this.f9347q, (int) C1205R.string.set_new_hand_fail, ak.f5302b);
                    } else if (this.f9351u.aa()) {
                        ak.m8085a(this.f9347q, this.f9347q.getString(C1205R.string.not_set_flight_back_height), ak.f5302b);
                    } else {
                        new at(this.f9347q).m12775c(this.f9347q.getString(C1205R.string.return_height)).m12771b((int) Opcodes.ISHL).m12766a(30).m12768a(this.f9347q.getString(C1205R.string.distance_unit_m)).m12772b(this.f9347q.getString(C1205R.string.dialog_height_unit)).m12776d(this.f9347q.getResources().getColor(C1205R.color.dialog_ensure_hot)).m12774c(Integer.parseInt(C1189f.m8335e().m8034e())).m12769a(this.f9347q.getString(C1205R.string.cancel), new C1866h(this)).m12773b(this.f9347q.getString(C1205R.string.ensure), new C1865g(this)).m12767a(new C1864f(this)).m12765a().show();
                    }
                }
            default:
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1205R.layout.activity_gps_setting);
        getWindow().setFlags(SmileConstants.TOKEN_PREFIX_TINY_UNICODE, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
        m11751a();
        this.f9347q = this;
        this.f9351u = ((DroidPlannerApp) getApplication()).f5570a;
        if (getIntent().getBooleanExtra(f9326b, true)) {
            m11765a(6, true);
            this.f9342l.setText(30 + getString(C1205R.string.distance_unit_m));
        }
        if (this.f9351u.m9570Q()) {
            this.f9334a.setVisibility(f9320E);
            if (getIntent().getBooleanExtra(f9326b, true) || getIntent().getBooleanExtra(f9328d, false)) {
                m11752a(true);
                this.f9352v = true;
                return;
            }
            return;
        }
        this.f9334a.setVisibility(0);
        m11752a(true);
        this.f9352v = true;
        this.f9329A = true;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        int g;
        switch (C1868j.f9508a[c1584h.ordinal()]) {
            case f9317B /*1*/:
                if (c1433a.aj().m10315c() == 51) {
                    if (c1433a.aj().m10317e() != (byte) 2) {
                        return;
                    }
                    if (c1433a.aj().m10320h() == null) {
                        ak.m8082a(this.f9347q, (int) C1205R.string.set_model_success);
                        if (c1433a.aj().m10318f() == 7) {
                            this.f9333N.sendEmptyMessageDelayed(f9318C, 1000);
                            if (c1433a.aj().m10319g() == C1873o.ak) {
                                this.f9346p.m8371a(true, true);
                                this.f9343m.setText(C1205R.string.gps_setting_distance_unlimt);
                                this.f9356z = C1873o.ak;
                                return;
                            }
                            this.f9346p.m8371a(false, true);
                            this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
                            this.f9356z = C2799f.f14263a;
                            return;
                        } else if (c1433a.aj().m10318f() == (byte) 3) {
                            this.f9333N.sendEmptyMessageDelayed(f9319D, 1000);
                            m11765a(c1433a.aj().m10319g(), false);
                            return;
                        } else {
                            return;
                        }
                    }
                    ak.m8082a(this.f9347q, (int) C1205R.string.set_model_fail);
                } else if (c1433a.aj().m10315c() != 34 || c1433a.aj().m10317e() != (byte) 2) {
                } else {
                    if (c1433a.aj().m10318f() == 7) {
                        this.f9332M = true;
                        g = c1433a.aj().m10319g();
                        if (g != 0) {
                            C1189f.m8335e().m8023a(g + C2915a.f14760f);
                        }
                        if (g == C1873o.ak) {
                            this.f9346p.m8371a(true, true);
                            this.f9343m.setText(C1205R.string.gps_setting_distance_unlimt);
                            this.f9356z = C1873o.ak;
                            return;
                        }
                        this.f9346p.m8371a(false, true);
                        this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
                        this.f9356z = C2799f.f14263a;
                    } else if (c1433a.aj().m10318f() == (byte) 3) {
                        this.f9331L = true;
                        g = c1433a.aj().m10319g();
                        if (g != 0) {
                            m11765a(g, false);
                        } else {
                            m11765a(10, false);
                        }
                    }
                }
            case f9318C /*2*/:
                if (c1433a.m9612o().m10619b() == C1886p.f9608d) {
                    if (c1433a.m9612o().m10623d() == C1886p.f9610f) {
                        ak.m8082a(this.f9347q, (int) C1205R.string.set_model_success);
                        g = (int) c1433a.m9612o().m10621c();
                        C1189f.m8335e().m8027b(g + C2915a.f14760f);
                        this.f9354x = g;
                        this.f9342l.setText(this.f9354x + this.f9347q.getString(C1205R.string.distance_unit_m));
                    } else {
                        ak.m8082a(this.f9347q, (int) C1205R.string.set_model_fail);
                    }
                    this.f9333N.sendEmptyMessageDelayed(f9317B, 1000);
                } else if (c1433a.m9612o().m10619b() == C1886p.f9609e) {
                    this.f9330K = true;
                    if (c1433a.m9612o().m10623d() == C1886p.f9610f) {
                        g = (int) c1433a.m9612o().m10621c();
                        C1189f.m8335e().m8027b(g + C2915a.f14760f);
                        this.f9354x = g;
                        this.f9342l.setText(this.f9354x + this.f9347q.getString(C1205R.string.distance_unit_m));
                    }
                }
            case f9319D /*3*/:
            case f9320E /*4*/:
                if (c1433a.m9570Q()) {
                    if (getIntent().getBooleanExtra(f9326b, true) || getIntent().getBooleanExtra(f9328d, false)) {
                        m11752a(true);
                        this.f9352v = true;
                    } else {
                        m11752a(false);
                        this.f9352v = false;
                    }
                    this.f9334a.setVisibility(f9320E);
                    if (this.f9329A) {
                        this.f9329A = false;
                        this.f9348r.m11832b();
                        this.f9348r.m11825a();
                        this.f9333N.sendEmptyMessageDelayed(f9318C, 300);
                        this.f9333N.sendEmptyMessageDelayed(f9319D, 300);
                        this.f9333N.sendEmptyMessageDelayed(f9318C, 600);
                        this.f9333N.sendEmptyMessageDelayed(f9319D, 600);
                        if (this.f9350t || this.f9353w >= C1886p.f9611g) {
                            C1886p.m11892a(c1433a).m11895a();
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.f9342l.setText(C1873o.an);
                this.f9341k.setText(C1873o.an);
                this.f9346p.m8371a(false, false);
                this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
                this.f9334a.setVisibility(0);
                m11752a(true);
                this.f9352v = true;
                this.f9329A = true;
            default:
        }
    }

    public void onStart() {
        super.onStart();
        this.f9351u.m9590a((C1234i) this);
        this.f9349s = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        this.f9350t = C1325k.m8930a().m8942g();
        if (this.f9350t || this.f9349s == null || this.f9349s.m12012a() >= C1873o.aa) {
            this.f9348r = new C1874p(this.f9351u, this.f9347q);
        } else {
            this.f9348r = new ae(this.f9351u, this.f9347q);
        }
        if (this.f9349s != null) {
            this.f9353w = this.f9349s.m12012a();
        }
        this.f9348r.m11832b();
        this.f9348r.m11825a();
        this.f9333N.sendEmptyMessageDelayed(f9318C, 300);
        this.f9333N.sendEmptyMessageDelayed(f9319D, 300);
        this.f9333N.sendEmptyMessageDelayed(f9318C, 600);
        this.f9333N.sendEmptyMessageDelayed(f9319D, 600);
        this.f9333N.sendEmptyMessageDelayed(f9318C, 900);
        this.f9333N.sendEmptyMessageDelayed(f9319D, 900);
        this.f9333N.sendEmptyMessageDelayed(f9318C, 1200);
        this.f9333N.sendEmptyMessageDelayed(f9319D, 1200);
        if (this.f9350t || this.f9353w >= C1886p.f9611g) {
            C1886p.m11892a(this.f9351u).m11895a();
            this.f9333N.sendEmptyMessageDelayed(f9317B, 300);
            this.f9333N.sendEmptyMessageDelayed(f9317B, 600);
            this.f9333N.sendEmptyMessageDelayed(f9317B, 900);
            this.f9333N.sendEmptyMessageDelayed(f9317B, 1200);
            this.f9330K = false;
        } else {
            this.f9330K = true;
        }
        this.f9355y = Integer.parseInt(C1189f.m8335e().m8035f());
        m11765a(this.f9355y, true);
        this.f9342l.setText(C1189f.m8335e().m8034e() + this.f9347q.getString(C1205R.string.distance_unit_m));
        this.f9356z = Integer.parseInt(C1189f.m8335e().m8032d());
        if (this.f9356z == C1873o.ak) {
            this.f9346p.m8371a(true, false);
            this.f9343m.setText(C1205R.string.gps_setting_distance_unlimt);
        } else {
            this.f9346p.m8371a(false, false);
            this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
        }
        if (this.f9351u.m9570Q()) {
            this.f9333N.sendEmptyMessageDelayed(f9320E, 3000);
            return;
        }
        this.f9342l.setText(C1873o.an);
        this.f9341k.setText(C1873o.an);
        this.f9346p.m8371a(false, false);
        this.f9343m.setText(C1205R.string.gps_setting_distance_limit_500m);
    }

    public void onStop() {
        super.onStop();
    }
}
