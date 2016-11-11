package com.fimi.soul.module.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p082c.C1157c;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.camera.C1276b;
import com.fimi.soul.biz.camera.C1313t;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.biz.p103k.C1397v;
import com.fimi.soul.biz.p103k.ab;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.biz.update.C1412i;
import com.fimi.soul.biz.update.C1417n;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.drone.p117h.C1560c;
import com.fimi.soul.entity.FirmwareInfo;
import com.fimi.soul.entity.Setting;
import com.fimi.soul.entity.UpdateVersonBean;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.module.calibcompass.CaliCompassActivity;
import com.fimi.soul.module.droneui.BatteryInfoActivity;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.insurance.ReceiveInsuranceActivity;
import com.fimi.soul.module.login.LoginActivity;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.module.paircode.PairCodeActivity;
import com.fimi.soul.module.remote.RemoteCalibration;
import com.fimi.soul.module.remote.RemoteModelActivity;
import com.fimi.soul.module.setting.GimalCalibration.GimalCalibrationActivity;
import com.fimi.soul.module.setting.newhand.C1873o;
import com.fimi.soul.module.setting.newhand.C1874p;
import com.fimi.soul.module.setting.newhand.GpsSettingActivity;
import com.fimi.soul.module.setting.newhand.NewHandActivity;
import com.fimi.soul.module.setting.newhand.ac;
import com.fimi.soul.module.setting.newhand.ae;
import com.fimi.soul.module.social.WebViewActivity;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.FindNewFirmwareAvtivity;
import com.fimi.soul.module.update.FindOnlineFirmwareAvtivity;
import com.fimi.soul.module.update.p121a.C1901a;
import com.fimi.soul.module.update.p121a.C1902b;
import com.fimi.soul.p087b.C1214c;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.aq;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.TokenBuffer.Segment;

public class MapSettingFragment extends Fragment implements OnItemClickListener, C1417n, C1234i, ap, aq, ar {
    private static final int f9126C = 0;
    private static final int f9127D = 2;
    private static final int f9128E = 1;
    private static final int f9129F = 3;
    private int f9130A;
    private boolean f9131B;
    private C1397v f9132G;
    private boolean f9133H;
    private int f9134I;
    private int f9135J;
    private Handler f9136K;
    public boolean f9137a;
    public C1412i f9138b;
    List<FirmwareInfo> f9139c;
    List<UpdateVersonBean> f9140d;
    ac f9141e;
    private Observer f9142f;
    private ListView f9143g;
    private am f9144h;
    private C1214c f9145i;
    private List<Setting> f9146j;
    private Setting f9147k;
    private Context f9148l;
    private al f9149m;
    private Button f9150n;
    private TextView f9151o;
    private C1433a f9152p;
    private C1664h f9153q;
    private PercentRelativeLayout f9154r;
    private Editor f9155s;
    private SharedPreferences f9156t;
    private C1313t f9157u;
    private ba f9158v;
    private boolean f9159w;
    private int f9160x;
    private int f9161y;
    private C1902b f9162z;

    public MapSettingFragment() {
        this.f9142f = new ab(this);
        this.f9146j = new ArrayList();
        this.f9137a = false;
        this.f9139c = null;
        this.f9159w = false;
        this.f9160x = f9126C;
        this.f9161y = f9126C;
        this.f9130A = f9126C;
        this.f9131B = false;
        this.f9136K = new aj(this);
    }

    private boolean m11634d() {
        return this.f9140d != null && this.f9140d.size() > 0;
    }

    private void m11636e() {
        if (this.f9137a) {
            this.f9151o.setText(C1205R.string.camera_setting);
            if (this.f9145i == null) {
                this.f9145i = new C1214c(getActivity());
            }
            this.f9145i.m8457a();
            this.f9143g.setAdapter(this.f9145i);
            this.f9143g.setOnItemClickListener(this.f9145i);
        } else if (this.f9144h == null || this.f9146j.size() <= 0) {
            this.f9151o.setText(C1205R.string.setting);
            this.f9144h = new am(this.f9148l, this.f9152p);
            this.f9144h.m11686a((aq) this);
            this.f9144h.m11687a((ar) this);
            this.f9144h.m11685a((ap) this);
            this.f9143g.setAdapter(this.f9144h);
            this.f9143g.setOverScrollMode(f9127D);
            this.f9143g.setOnItemClickListener(this);
            for (int i = f9126C; i < 30; i += f9128E) {
                this.f9147k = new Setting();
                this.f9147k.setId(i);
                this.f9147k.addObserver(this.f9142f);
                this.f9147k.setIsOPen(Boolean.valueOf(true));
                this.f9146j.add(this.f9147k);
            }
            m11638f();
            this.f9143g.setOnScrollListener(new ae(this));
        } else {
            this.f9143g.setAdapter(this.f9144h);
            this.f9143g.setOverScrollMode(f9127D);
            this.f9143g.setOnItemClickListener(this);
            m11638f();
            this.f9143g.setSelectionFromTop(this.f9160x, this.f9161y);
        }
    }

    private void m11638f() {
        ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(true));
        ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
        ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
        this.f9139c = C1901a.m12002a().m12009d();
        if (ay.m12293a(this.f9148l).contains("isfirstloading")) {
            ((Setting) this.f9146j.get(28)).setIsOPen(Boolean.valueOf(true));
            ((Setting) this.f9146j.get(28)).setContent(C1236a.m8533b(this.f9148l).getName());
        } else {
            ((Setting) this.f9146j.get(28)).setIsOPen(Boolean.valueOf(false));
        }
        if (this.f9156t.getInt(RemoteModelActivity.f8968a, f9126C) == 0) {
            ((Setting) this.f9146j.get(12)).setContent(getResources().getString(C1205R.string.modelcannal));
        } else {
            ((Setting) this.f9146j.get(12)).setContent(getResources().getString(C1205R.string.modelcannaljp));
        }
        if (!(this.f9152p.m9569P().m9995a() && this.f9152p.m9570Q())) {
            ((Setting) this.f9146j.get(f9128E)).setIsOPen(Boolean.valueOf(true));
        }
        this.f9140d = C1901a.m12002a().m12010e();
        m11640g();
        this.f9144h.m11688a(this.f9146j);
    }

    private void m11640g() {
        ((Setting) this.f9146j.get(f9127D)).setIsOPen(Boolean.valueOf(true));
        if (m11634d() || (this.f9139c != null && this.f9139c.size() > 0)) {
            ((Setting) this.f9146j.get(f9127D)).setDisplayTv(true);
        } else {
            ((Setting) this.f9146j.get(f9127D)).setDisplayTv(false);
        }
    }

    public void m11642a() {
        if (this.f9141e == null) {
            return;
        }
        if (this.f9133H || this.f9162z == null || this.f9162z.m12012a() >= C1873o.aa) {
            this.f9141e = new C1874p(this.f9152p, this.f9148l);
            this.f9141e.m11836d();
            return;
        }
        this.f9141e = new ae(this.f9152p, this.f9148l);
        this.f9141e.m11836d();
    }

    public void m11643a(int i) {
        if (i == 4) {
            this.f9141e.m11830a(this.f9146j);
        } else if (i == f9127D) {
            this.f9141e.m11831a(this.f9146j, this.f9132G);
        }
    }

    public void m11644a(SwitchButton switchButton) {
        this.f9141e.m11829a(switchButton, (Setting) this.f9146j.get(5));
        this.f9144h.notifyDataSetChanged();
    }

    public void m11645a(String str) {
        ((Setting) this.f9146j.get(26)).setIsOPen(Boolean.valueOf(false));
        ((Setting) this.f9146j.get(26)).setContent(str);
        this.f9144h.m11688a(this.f9146j);
        this.f9139c = C1901a.m12002a().m12009d();
    }

    public void m11646b() {
    }

    public boolean m11647c() {
        if (this.f9137a) {
            this.f9137a = false;
            this.f9157u.m8873r().m8752e();
            m11636e();
            return false;
        }
        this.f9149m.m11326c();
        return true;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9148l = activity;
        this.f9149m = (al) activity;
        this.f9152p = ((DroidPlannerApp) activity.getApplication()).f5570a;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.activity_general_setting, viewGroup, false);
        this.f9154r = (PercentRelativeLayout) inflate.findViewById(C1205R.id.heardView);
        this.f9150n = (Button) this.f9154r.findViewById(C1205R.id.black_btn);
        this.f9143g = (ListView) inflate.findViewById(C1205R.id.setting_lv);
        this.f9151o = (TextView) this.f9154r.findViewById(C1205R.id.tv_settingTitle);
        this.f9156t = ay.m12293a(getActivity());
        this.f9155s = this.f9156t.edit();
        this.f9157u = (C1313t) C1276b.m8680a().m8699d();
        this.f9150n.setOnClickListener(new ac(this));
        this.f9138b = new C1412i(this.f9148l);
        this.f9138b.m9461a((C1417n) this);
        this.f9138b.m9459a();
        this.f9158v = ba.m9259a(this.f9148l);
        this.f9132G = C1397v.m9355a(this.f9148l);
        if (be.m12370b(this.f9148l) && this.f9152p.m9570Q()) {
            this.f9132G.m9358a(C1236a.m8533b(this.f9148l), C1397v.f6276a, new ad(this));
        }
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (!c1433a.m9569P().m9995a() || !c1433a.m9570Q()) {
            ((Setting) this.f9146j.get(f9128E)).setIsOPen(Boolean.valueOf(true));
            switch (ak.f9216a[c1584h.ordinal()]) {
                case f9128E /*1*/:
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    m11640g();
                    ((Setting) this.f9146j.get(f9128E)).setIsOPen(Boolean.valueOf(true));
                    break;
                default:
                    break;
            }
        }
        switch (ak.f9216a[c1584h.ordinal()]) {
            case f9128E /*1*/:
                ((Setting) this.f9146j.get(f9128E)).setIsOPen(Boolean.valueOf(false));
                break;
            case f9127D /*2*/:
                if (!this.f9133H && this.f9130A < C1873o.aa) {
                    if (c1433a.aj().m10315c() == 51 || c1433a.aj().m10315c() == 85) {
                        if (c1433a.aj().m10320h() == null) {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_success);
                            if (c1433a.aj().m10316d() == null) {
                                ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(true));
                            } else {
                                ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                                ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                                ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(false));
                            }
                            this.f9144h.notifyDataSetChanged();
                        } else if (c1433a.aj().m10320h() != 16) {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_fail);
                        }
                    }
                    if (c1433a.aj().m10320h() == 16) {
                        if (c1433a.aj().m10316d() == null) {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(true));
                        } else {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(false));
                        }
                        this.f9144h.notifyDataSetChanged();
                        break;
                    }
                }
                if (c1433a.aj().m10315c() == 51) {
                    if (C1189f.m8331b().m8343b() != null && C1189f.m8331b().m8343b().isShowing()) {
                        C1189f.m8331b().m8348c();
                    }
                    if (c1433a.aj().m10317e() == (byte) 1) {
                        if (c1433a.aj().m10320h() == null) {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_success);
                            this.f9136K.sendEmptyMessageDelayed(f9128E, 1000);
                        } else {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_fail);
                        }
                        if (c1433a.aj().m10316d() == null) {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(true));
                            ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                            ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                        } else {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(false));
                        }
                        this.f9144h.notifyDataSetChanged();
                    }
                    if (c1433a.aj().m10317e() == f9129F) {
                        if (c1433a.aj().m10320h() == null) {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_success);
                            this.f9136K.sendEmptyMessageDelayed(f9128E, 1000);
                        } else {
                            ak.m8082a(this.f9148l, (int) C1205R.string.set_model_fail);
                        }
                        if (c1433a.aj().m10318f() == f9127D) {
                            if (c1433a.aj().m10319g() == f9128E) {
                                ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                                ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(true));
                            } else {
                                ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                            }
                        }
                        if (c1433a.aj().m10318f() == 4) {
                            if (c1433a.aj().m10319g() == f9128E) {
                                ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                                ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(true));
                            } else {
                                ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                            }
                        }
                        this.f9144h.notifyDataSetChanged();
                    }
                }
                if (c1433a.aj().m10315c() == 34 && (c1433a.aj().m10317e() == (byte) 1 || c1433a.aj().m10317e() == f9129F)) {
                    if (c1433a.aj().m10317e() == (byte) 1) {
                        if (c1433a.aj().m10316d() == null) {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(true));
                            ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                            ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                        } else {
                            ((Setting) this.f9146j.get(5)).setIsOPen(Boolean.valueOf(false));
                        }
                    }
                    if ((c1433a.aj().m10319g() & f9128E) == f9128E) {
                        ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                    }
                    if ((c1433a.aj().m10319g() & f9127D) == f9127D) {
                        ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(false));
                        ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(true));
                    } else {
                        ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                    }
                    if ((c1433a.aj().m10319g() & 4) == 4) {
                        ((Setting) this.f9146j.get(8)).setIsOPen(Boolean.valueOf(true));
                        ((Setting) this.f9146j.get(7)).setIsOPen(Boolean.valueOf(false));
                    }
                    this.f9144h.notifyDataSetChanged();
                    break;
                }
                break;
            case f9129F /*3*/:
                C1560c H = c1433a.m9561H();
                if (!(H == null || H.m10511b() != 115 || H.f7499d == null)) {
                    switch (H.f7499d) {
                        default:
                            this.f9144h.notifyDataSetChanged();
                            break;
                    }
                }
        }
        if (c1584h == C1584h.backControl && c1433a.m9561H().m10511b() == 114) {
            C1683q.m10886a().m10887a("98");
            if (c1433a.m9561H().m10513d() == f9127D) {
                this.f9155s.putInt(RemoteModelActivity.f8968a, f9128E);
                ((Setting) this.f9146j.get(12)).setContent(getResources().getString(C1205R.string.modelcannaljp));
            } else if (c1433a.m9561H().m10513d() == (byte) 1) {
                this.f9155s.putInt(RemoteModelActivity.f8968a, f9126C);
                ((Setting) this.f9146j.get(12)).setContent(getResources().getString(C1205R.string.modelcannal));
            }
            this.f9155s.commit();
            if (this.f9144h != null) {
                this.f9144h.m11682a(12, this.f9143g.getChildAt(12));
            }
        }
        switch (ak.f9216a[c1584h.ordinal()]) {
            case f9128E /*1*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (!c1433a.m9570Q()) {
                    this.f9131B = true;
                } else if (this.f9131B) {
                    this.f9131B = false;
                    this.f9141e.m11836d();
                }
            default:
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Editor edit;
        Intent intent;
        SwitchButton switchButton;
        switch (i) {
            case f9128E /*1*/:
                if (((Setting) this.f9146j.get(f9128E)).getIsOPen().booleanValue()) {
                    edit = ay.m12293a(this.f9148l).edit();
                    edit.putBoolean(C1236a.f5588L, true);
                    edit.commit();
                    intent = new Intent(this.f9148l, LoginActivity.class);
                    intent.putExtra("login", false);
                    startActivityForResult(intent, f9126C);
                }
            case f9127D /*2*/:
                if (!((Setting) this.f9146j.get(f9127D)).getIsOPen().booleanValue()) {
                    return;
                }
                if (m11634d()) {
                    C1157c.m7938a().m7945a(UpdateVersonBean.SP_NEED_DOWN_FIRMWWARES, this.f9140d);
                    startActivity(new Intent(this.f9148l, FindOnlineFirmwareAvtivity.class));
                } else if (this.f9139c == null || this.f9139c.size() <= 0) {
                    ak.m8082a(this.f9148l, (int) C1205R.string.version_tip);
                } else {
                    startActivity(new Intent(this.f9148l, FindNewFirmwareAvtivity.class));
                }
            case f9129F /*3*/:
                intent = new Intent(getActivity(), CaliCompassActivity.class);
                intent.putExtra(C1236a.f5594R, true);
                startActivity(intent);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                edit = ay.m12293a(this.f9148l).edit();
                edit.putBoolean(C1236a.f5588L, true);
                edit.commit();
                startActivity(new Intent(this.f9148l, NewHandActivity.class));
            case Type.INT /*5*/:
                this.f9141e.m11829a((SwitchButton) view.findViewById(C1205R.id.switch_btn), (Setting) this.f9146j.get(5));
                this.f9144h.notifyDataSetChanged();
            case Type.FLOAT /*6*/:
                if (!((Setting) this.f9146j.get(5)).getIsOPen().booleanValue()) {
                    Intent intent2 = new Intent(this.f9148l, GpsSettingActivity.class);
                    intent2.putExtra(GpsSettingActivity.f9326b, ((Setting) this.f9146j.get(5)).getIsOPen());
                    intent2.putExtra(GpsSettingActivity.f9327c, ((Setting) this.f9146j.get(7)).getIsOPen());
                    intent2.putExtra(GpsSettingActivity.f9328d, ((Setting) this.f9146j.get(8)).getIsOPen());
                    this.f9148l.startActivity(intent2);
                }
            case Type.LONG /*7*/:
                this.f9141e.m11831a(this.f9146j, this.f9132G);
            case Type.DOUBLE /*8*/:
                this.f9141e.m11830a(this.f9146j);
            case Type.ARRAY /*9*/:
                switchButton = (SwitchButton) view.findViewById(C1205R.id.switch_btn);
                if (C1189f.m8335e().m8031c()) {
                    C1189f.m8335e().m8024a(false);
                    switchButton.m8371a(false, true);
                } else {
                    C1189f.m8335e().m8024a(true);
                    switchButton.m8371a(true, true);
                }
                ((FlightActivity) this.f9148l).m11346h();
            case Opcodes.T_LONG /*11*/:
                startActivity(new Intent(this.f9148l, RemoteCalibration.class));
            case Opcodes.FCONST_1 /*12*/:
                if (this.f9152p.m9570Q()) {
                    ak.m8085a(this.f9148l, this.f9148l.getString(C1205R.string.close_plane_switch_rocket), ak.f5302b);
                } else {
                    startActivity(new Intent(this.f9148l, RemoteModelActivity.class));
                }
            case Opcodes.DCONST_0 /*14*/:
                startActivity(new Intent(this.f9148l, BatteryInfoActivity.class));
            case Segment.TOKENS_PER_SEGMENT /*16*/:
                startActivity(new Intent(this.f9148l, GimalCalibrationActivity.class));
            case Opcodes.LDC /*18*/:
                this.f9137a = true;
                m11636e();
            case Util.MAX_ACCOUNT_LENGTH /*20*/:
                switchButton = (SwitchButton) view.findViewById(C1205R.id.switch_btn);
                if (ab.m9180a(this.f9148l).m9190e()) {
                    switchButton.m8371a(false, true);
                    ab.m9180a(this.f9148l).m9191f(false);
                    PreferenceManager.getDefaultSharedPreferences(this.f9148l).edit().putInt(C1543c.f7244s, f9128E).commit();
                    return;
                }
                switchButton.m8371a(true, true);
                ab.m9180a(this.f9148l).m9191f(true);
                PreferenceManager.getDefaultSharedPreferences(this.f9148l).edit().putInt(C1543c.f7244s, f9127D).commit();
            case Opcodes.LLOAD /*22*/:
                startActivity(new Intent(this.f9148l, FlyRecordActivity.class));
            case Opcodes.FLOAD /*23*/:
                startActivity(new Intent(this.f9148l, FlyLogsActivity.class));
            case Opcodes.DLOAD /*24*/:
                intent = new Intent(this.f9148l, WebViewActivity.class);
                intent.putExtra(SocialConstants.PARAM_URL, ReceiveInsuranceActivity.f8720a);
                intent.putExtra(SocialConstants.PARAM_TITLE, getString(C1205R.string.xiaomi_insurance));
                startActivity(intent);
            case Opcodes.ALOAD /*25*/:
                startActivity(new Intent(this.f9148l, UserFeedBackActivity.class));
            case am.f9219C /*26*/:
                ak.m8085a(this.f9148l, this.f9148l.getString(C1205R.string.checking_update), ak.f5302b);
                this.f9138b.m9460a((int) f9128E);
                this.f9138b.m9462a(new ai(this));
            case am.f9220D /*27*/:
                startActivity(new Intent(this.f9148l, AboutActivity.class));
            case am.f9221E /*28*/:
                if (ay.m12293a(this.f9148l).contains("isfirstloading")) {
                    new aq(this.f9148l).m12748a(this.f9148l.getString(C1205R.string.log_out)).m12754c(17).m12747a(getResources().getColor(C1205R.color.dialog_ensure_hot)).m12753b(this.f9148l.getString(C1205R.string.exit), new ag(this)).m12749a(this.f9148l.getString(C1205R.string.cancel), new af(this)).m12746a().show();
                    return;
                }
                edit = ay.m12293a(this.f9148l).edit();
                edit.putBoolean(C1236a.f5588L, true);
                edit.commit();
                intent = new Intent(this.f9148l, LoginActivity.class);
                intent.putExtra("login", true);
                startActivity(intent);
            case Opcodes.LXOR /*131*/:
                startActivity(new Intent(this.f9148l, PairCodeActivity.class));
            default:
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        m11636e();
        this.f9152p.m9590a((C1234i) this);
        this.f9153q = C1664h.m10813a(this.f9152p);
        this.f9153q.m10831g();
        this.f9162z = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        this.f9133H = C1325k.m8930a().m8942g();
        if (this.f9133H || this.f9162z == null || this.f9162z.m12012a() >= C1873o.aa) {
            this.f9141e = new C1874p(this.f9152p, this.f9148l);
            this.f9141e.m11836d();
        } else {
            this.f9141e = new ae(this.f9152p, this.f9148l);
            this.f9141e.m11836d();
        }
        this.f9136K.sendEmptyMessageDelayed(f9128E, 500);
        this.f9136K.sendEmptyMessageDelayed(f9128E, 1000);
        if (this.f9162z != null) {
            this.f9130A = this.f9162z.m12012a();
            this.f9144h.m11681a(this.f9130A);
        }
    }

    public void onStop() {
        super.onStop();
        this.f9152p.m9594b((C1234i) this);
        if (this.f9138b != null && this.f9138b.m9464b() != null) {
            this.f9138b.m9462a(null);
        }
    }
}
