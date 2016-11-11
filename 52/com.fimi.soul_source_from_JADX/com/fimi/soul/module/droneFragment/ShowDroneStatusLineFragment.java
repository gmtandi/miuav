package com.fimi.soul.module.droneFragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p100g.C1334a;
import com.fimi.soul.biz.p100g.C1336c;
import com.fimi.soul.biz.p100g.C1341h;
import com.fimi.soul.biz.p100g.C1345l;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.aa;
import com.fimi.soul.entity.ErrorMode;
import com.fimi.soul.media.player.FermiPlayerFullActivity;
import com.fimi.soul.module.calibcompass.CaliCompassActivity;
import com.fimi.soul.module.droneui.C1746g;
import com.fimi.soul.module.login.LoginActivity;
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.module.remote.RemoteCalibration;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.utils.bi;
import com.fimi.soul.view.AutoScrollTextView;
import com.fimi.soul.view.C2045z;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Timer;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class ShowDroneStatusLineFragment extends Fragment implements Callback, OnClickListener, C1341h, C1234i {
    public static boolean f8062a;
    private boolean f8063A;
    private Timer f8064B;
    private Button f8065C;
    private ImageView f8066D;
    private C1336c f8067E;
    private C1345l f8068F;
    private C2045z f8069G;
    private C1334a f8070H;
    private String f8071I;
    private Observer f8072J;
    private ErrorMode f8073K;
    private boolean f8074L;
    int f8075b;
    int f8076c;
    long f8077d;
    boolean f8078e;
    int f8079f;
    int f8080g;
    private DroidPlannerApp f8081h;
    private C1433a f8082i;
    private C1746g f8083j;
    private Context f8084k;
    private TextView f8085l;
    private TextView f8086m;
    private AutoScrollTextView f8087n;
    private Button f8088o;
    private ImageView f8089p;
    private ImageView f8090q;
    private ImageView f8091r;
    private ImageView f8092s;
    private ImageView f8093t;
    private ImageView f8094u;
    private final int f8095v;
    private final int f8096w;
    private final int f8097x;
    private Handler f8098y;
    private C1664h f8099z;

    static {
        f8062a = false;
    }

    public ShowDroneStatusLineFragment() {
        this.f8095v = 0;
        this.f8096w = 1;
        this.f8097x = 2;
        this.f8098y = new Handler(this);
        this.f8063A = false;
        this.f8072J = new ao(this);
        this.f8075b = 0;
        this.f8076c = 0;
        this.f8077d = 0;
        this.f8078e = false;
        this.f8079f = 0;
        this.f8080g = 0;
    }

    private void m10927a(int i, int i2, int i3) {
        if (this.f8083j != null && this.f8083j.isShowing()) {
            this.f8083j.dismiss();
            this.f8083j = null;
        }
        this.f8083j = new C1746g(getActivity(), getString(i), getString(i2), i3, 3, false, new ar(this));
        this.f8083j.setCancelable(true);
        this.f8083j.show();
    }

    @TargetApi(16)
    private void m10928a(aa aaVar) {
        int i;
        int i2;
        int i3 = C1205R.string.sky_BATTERY_ERROR_result;
        int i4 = 0;
        if (aaVar.m10183H()) {
            i = C1205R.string.sky_LOWPOWER;
            i2 = C1205R.string.sky_LOWPOWER_title;
            i3 = C1205R.string.sky_LOWPOWER_result;
            i4 = C1205R.drawable.low_battery;
        } else if (aaVar.m10211g() || (this.f8082i.m9604g() != null && this.f8082i.m9604g().m10159f() == 3)) {
            i = C1205R.string.sky_no_fly_error;
            i2 = C1205R.string.sky_no_fly_title;
            i3 = C1205R.string.sky_no_fly_result;
            i4 = C1205R.drawable.no_fly_zone;
        } else if (aaVar.m10217m()) {
            i2 = C1205R.string.sky_BATTERY_ERROR_title;
            i4 = C1205R.drawable.battery_abnormal;
            i = C1205R.string.sky_BATTERY_ERROR;
        } else if (aaVar.m10210f() && !this.f8082i.ah().m10172g().isLightStream()) {
            i2 = C1205R.string.sky_GPS_ERROR_title;
            i3 = C1205R.string.sky_GPS_ERROR_result;
            i = C1205R.string.sky_GPS_ERROR;
            i4 = C1205R.drawable.plane_fall_bg;
        } else if (this.f8082i.ah().m10172g().isLightStream() && aaVar.m10220p()) {
            i2 = C1205R.string.sky_VPS_ERROR_title;
            i3 = C1205R.string.sky_VPS_ERROR_result;
            i = C1205R.string.sky_VPS_ERROR;
            i4 = C1205R.drawable.plane_fall_bg;
        } else if (aaVar.m10182G()) {
            i = C1205R.string.sky_LP_RETURN;
            i3 = C1205R.string.fault_back_battery_des;
            i2 = 0;
        } else if (aaVar.m10185J() && this.f8082i.aa()) {
            i2 = C1205R.string.sky_BATTERY_ERR_title;
            i4 = C1205R.drawable.battery_abnormal;
            i = C1205R.string.sky_BATTERY_ERROR;
        } else if (aaVar.m10181F()) {
            i = C1205R.string.sky_BAT_light_LOWPOWER;
            i3 = C1205R.string.fault_back_battery_des;
            i2 = 0;
        } else if (aaVar.m10187L()) {
            i4 = C1205R.drawable.img_warning_rocker;
            i2 = C1205R.string.stick_move_warning_title;
            i3 = C1205R.string.stick_move_warning_result;
            i = C1205R.string.stick_move_warning_des;
        } else {
            this.f8076c = 0;
            return;
        }
        if (!(this.f8076c == i || i4 == 0)) {
            m10927a(i2, i3, i4);
        }
        this.f8076c = i;
    }

    private float m10930b(String str) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        float f = getResources().getDisplayMetrics().density;
        TextPaint textPaint = new TextPaint();
        Rect rect = new Rect();
        textPaint.setTextSize(13.0f);
        textPaint.getTextBounds(str, 0, str.length(), rect);
        return f * ((float) rect.width());
    }

    private void m10932b() {
        this.f8093t.setVisibility(0);
    }

    private void m10933b(int i, int i2) {
        this.f8088o.setVisibility(i2);
        if (!getString(i).equals(this.f8088o.getText().toString())) {
            this.f8088o.setText(i);
        }
        this.f8091r.setVisibility(i2);
        this.f8088o.setEnabled(true);
        this.f8070H.m8961a(true);
    }

    private void m10935c() {
        this.f8093t.setVisibility(4);
    }

    private void m10936c(int i, int i2) {
        this.f8088o.setVisibility(i2);
        if (!C2915a.f14760f.equals(this.f8088o.getText().toString())) {
            this.f8088o.setText(C2915a.f14760f);
        }
        this.f8091r.setVisibility(i2);
        this.f8088o.setEnabled(false);
        this.f8070H.m8961a(false);
    }

    private void m10937c(boolean z) {
        int i = 0;
        this.f8065C.setVisibility(z ? 0 : 4);
        this.f8070H.m8961a(z);
        ImageView imageView = this.f8066D;
        if (!z) {
            i = 4;
        }
        imageView.setVisibility(i);
    }

    private boolean m10938c(String str) {
        if (str == null || !str.contains(":")) {
            return false;
        }
        try {
            Integer.valueOf(str.split(":")[0]).intValue();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void m10940d() {
        if (!isAdded()) {
            return;
        }
        if (this.f8082i.m9570Q()) {
            this.f8073K = this.f8068F.m9003a(this.f8082i);
            ArrayList lightErrorList = this.f8073K.getLightErrorList();
            if (lightErrorList.size() <= this.f8075b) {
                this.f8075b = 0;
            }
            if (this.f8082i.aa()) {
                if (this.f8082i.ab().m10186K()) {
                    this.f8080g++;
                    if (this.f8080g >= 3) {
                        m10927a(C1205R.string.title_sky_bat_lowpower_dangerous, C1205R.string.des_sky_bat_lowpower_dangerous, C1205R.drawable.plane_fall_bg);
                        C1160b.m7953b(C1189f.m8334d()).m7959a(C1189f.m8334d().getString(C1205R.string.sky_bat_lowpower_dangerous));
                        bi.m12400a(C1189f.m8334d(), 2000);
                        this.f8080g = 0;
                    }
                } else if (this.f8082i.ab().m10183H()) {
                    this.f8080g++;
                    if (this.f8080g >= 7) {
                        m10927a(C1205R.string.title_sky_landing_BAT_LOWPOWER, C1205R.string.des_sky_landing_BAT_LOWPOWER, C1205R.drawable.plane_fall_bg);
                        C1160b.m7953b(C1189f.m8334d()).m7959a(C1189f.m8334d().getString(C1205R.string.sky_landing_BAT_LOWPOWER));
                        bi.m12400a(C1189f.m8334d(), 2000);
                        this.f8080g = 0;
                    }
                }
            }
            if (lightErrorList == null || lightErrorList.size() <= 0) {
                m10942d(C2915a.f14760f);
                m10935c();
            } else {
                m10932b();
                this.f8087n.setTextColor(getResources().getColor(C1205R.color.warmtextcl));
                m10942d(getText(((Integer) lightErrorList.get(this.f8075b)).intValue()).toString());
            }
            this.f8075b++;
            if ((this.f8073K.isCompassFault() || this.f8073K.isMiddleFault()) && !this.f8082i.aa()) {
                m10937c(true);
                this.f8065C.setText(getString(C1205R.string.platform_correct));
                this.f8094u.setVisibility(4);
                return;
            }
            m10937c(false);
            if (this.f8073K.getSeriousErrorList().size() <= 0 || this.f8088o.getVisibility() == 0) {
                this.f8094u.setVisibility(4);
                return;
            } else {
                this.f8094u.setVisibility(0);
                return;
            }
        }
        C1160b.m7953b(C1189f.m8334d()).m7960b();
    }

    private void m10941d(int i) {
        if (!isAdded()) {
            return;
        }
        if (getString(C1205R.string.self_error_result).equals(this.f8085l.getText().toString())) {
            this.f8089p.setImageResource(C1205R.drawable.notnormal_icon);
            this.f8089p.setTag(Integer.valueOf(C1205R.drawable.notnormal_icon));
        } else if (this.f8089p.getTag() == null) {
            this.f8089p.setImageResource(i);
            this.f8089p.setTag(Integer.valueOf(i));
        } else if (i != ((Integer) this.f8089p.getTag()).intValue()) {
            this.f8089p.setImageResource(i);
            this.f8089p.setTag(Integer.valueOf(i));
        }
    }

    private void m10942d(String str) {
        if (str != null && !str.equals(this.f8087n.getText().toString())) {
            this.f8087n.setText(str, BufferType.NORMAL);
        }
    }

    private void m10944e(int i) {
        if (!this.f8098y.hasMessages(0) && !getString(i).equals(this.f8087n.getText())) {
            Message obtain = Message.obtain();
            obtain.what = 0;
            obtain.obj = Integer.valueOf(i);
            this.f8098y.sendMessageDelayed(obtain, 2500);
        }
    }

    private void m10946f(int i) {
        if (i != 0 && !getString(i).equals(this.f8085l.getText().toString())) {
            this.f8085l.setText(i);
        }
    }

    public int m10950a() {
        return this.f8079f;
    }

    public void m10951a(int i) {
        m10946f(i);
    }

    public void m10952a(int i, int i2) {
        if (i > 0) {
            m10933b(i, i2);
        } else {
            m10936c(i, i2);
        }
    }

    public void m10953a(String str) {
        if (m10938c(str)) {
            if (getActivity() != null) {
                be.m12368b(getActivity().getAssets(), this.f8086m);
            }
            this.f8086m.setTextSize(1, 16.5f);
            this.f8086m.setPadding(0, 0, 0, 0);
            this.f8090q.setVisibility(0);
        } else {
            if (getActivity() != null) {
                be.m12359a(getActivity().getAssets(), this.f8086m);
            }
            this.f8086m.setTextSize(1, 13.0f);
            this.f8086m.setTextColor(-1);
            this.f8086m.setPadding(0, (int) ((14.0f * getResources().getDisplayMetrics().density) / C2020f.f10931a), 0, 0);
            this.f8090q.setVisibility(4);
        }
        this.f8086m.setText(str);
    }

    public void m10954a(boolean z) {
        if (z && System.currentTimeMillis() - this.f8077d >= 800) {
            this.f8077d = System.currentTimeMillis();
            if (this.f8078e) {
                this.f8078e = false;
                this.f8090q.setImageDrawable(null);
            } else {
                this.f8078e = true;
                this.f8090q.setImageDrawable(getResources().getDrawable(C1205R.drawable.record_time_red_dot));
            }
            this.f8086m.setAlpha(C2020f.f10933c);
            this.f8090q.invalidate();
        }
        if (!z) {
            this.f8090q.setImageDrawable(getResources().getDrawable(C1205R.drawable.gray_point));
            this.f8086m.setAlpha(0.3f);
        }
    }

    public void m10955b(int i) {
        m10941d(i);
    }

    public void m10956b(boolean z) {
        this.f8074L = z;
    }

    public void m10957c(int i) {
        if (i > 0) {
            this.f8079f = i;
            m10953a(getString(i));
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            if (this.f8082i.m9570Q()) {
                m10942d(getString(((Integer) message.obj).intValue()));
            }
        } else if (message.what == 1) {
            m10940d();
        } else if (message.what == 2) {
            m10941d((int) C1205R.drawable.normal_icon);
            this.f8085l.setText(getString(C1205R.string.self_vps_ok_result));
        }
        return false;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8081h = (DroidPlannerApp) activity.getApplication();
        this.f8082i = this.f8081h.f5570a;
        this.f8084k = activity;
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1205R.id.fault_bnt:
                this.f8073K = this.f8068F.m9003a(this.f8082i);
                this.f8069G.m13049a(this.f8073K.getSeriousErrorList(), view, (int) getResources().getDimension(C1205R.dimen.popuwindows_xoff), (int) getResources().getDimension(C1205R.dimen.popuwindows_yoff));
            case C1205R.id.dronemoelbutton:
                if (this.f8082i.m9569P().m9995a() && this.f8082i.m9570Q()) {
                    this.f8099z.m10828d();
                    this.f8082i.m9589a(C1584h.NOTIDRONEAIR);
                    return;
                }
                Editor edit = ay.m12293a(this.f8084k).edit();
                edit.putBoolean(C1236a.f5588L, true);
                edit.commit();
                intent = new Intent(this.f8084k, LoginActivity.class);
                intent.putExtra("login", false);
                startActivity(intent);
            case C1205R.id.compass_calibration:
                if (this.f8073K != null && this.f8073K.isMiddleFault()) {
                    startActivity(new Intent(getActivity(), RemoteCalibration.class));
                } else if (this.f8073K != null && this.f8073K.isCompassFault()) {
                    intent = new Intent(getActivity(), CaliCompassActivity.class);
                    intent.putExtra(C1236a.f5594R, true);
                    startActivity(intent);
                }
            case C1205R.id.camertime:
                if (this.f8082i.m9575V() && !this.f8074L) {
                    startActivity(new Intent(this.f8084k, FermiPlayerFullActivity.class));
                }
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f8068F = C1345l.m9000a();
        this.f8070H = new C1334a();
        this.f8070H.addObserver(this.f8072J);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.showstatusline, null);
        this.f8085l = (TextView) inflate.findViewById(C1205R.id.dronestuts);
        this.f8085l.getPaint().setFakeBoldText(true);
        this.f8093t = (ImageView) inflate.findViewById(C1205R.id.divide_line);
        this.f8087n = (AutoScrollTextView) inflate.findViewById(C1205R.id.dronerrorcode);
        this.f8086m = (TextView) inflate.findViewById(C1205R.id.camertime);
        this.f8086m.setOnClickListener(this);
        this.f8088o = (Button) inflate.findViewById(C1205R.id.dronemoelbutton);
        this.f8088o.setOnClickListener(this);
        this.f8065C = (Button) inflate.findViewById(C1205R.id.compass_calibration);
        this.f8065C.setOnClickListener(this);
        this.f8089p = (ImageView) inflate.findViewById(C1205R.id.headview);
        this.f8090q = (ImageView) inflate.findViewById(C1205R.id.camericon);
        this.f8091r = (ImageView) inflate.findViewById(C1205R.id.linestart);
        this.f8092s = (ImageView) inflate.findViewById(C1205R.id.lineend);
        this.f8066D = (ImageView) inflate.findViewById(C1205R.id.linecalibration);
        this.f8094u = (ImageView) inflate.findViewById(C1205R.id.fault_bnt);
        this.f8094u.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8085l, this.f8087n, this.f8088o);
        be.m12368b(getActivity().getAssets(), this.f8086m);
        this.f8099z = C1664h.m10813a(this.f8082i);
        if (this.f8082i.m9575V()) {
            m10957c((int) C1205R.string.tf_normal);
        } else {
            m10957c((int) C1205R.string.fault_camera);
        }
        this.f8069G = new C2045z(getActivity(), (int) getResources().getDimension(C1205R.dimen.popuwindows_w), 400);
        f8062a = true;
        this.f8087n.m12543a(0.0f);
        this.f8087n.setEnabled(false);
        this.f8087n.addTextChangedListener(new ap(this));
        this.f8064B = new Timer();
        this.f8064B.schedule(new aq(this), 0, 3000);
        if (!(this.f8082i.m9570Q() || this.f8082i.m9569P().m9995a())) {
            m10933b((int) C1205R.string.connect_rn, 0);
            this.f8085l.setText(C1205R.string.connectdefault);
        }
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f8070H.deleteObserver(this.f8072J);
        this.f8082i.m9594b((C1234i) this);
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (c1433a.m9570Q() && c1433a.m9569P().m9995a()) {
            this.f8063A = true;
            if (getResources().getColor(C1205R.color.white) != this.f8085l.getCurrentTextColor()) {
                this.f8085l.setTextColor(getResources().getColor(C1205R.color.white));
            }
            switch (as.f8187a[c1584h.ordinal()]) {
                case Type.BYTE /*3*/:
                    aa ab = c1433a.ab();
                    if (ab.m10202a() < 0 && (c1433a.m9604g() == null || c1433a.m9604g().m10159f() != 3)) {
                        this.f8076c = 0;
                        this.f8094u.setVisibility(4);
                        return;
                    } else if (c1433a.aa()) {
                        m10928a(ab);
                        return;
                    } else {
                        this.f8076c = 0;
                        return;
                    }
                default:
                    return;
            }
        }
        switch (as.f8187a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                if (!c1433a.m9570Q()) {
                    com.fimi.soul.utils.aa.m12215d();
                    this.f8085l.setTextColor(getResources().getColor(C1205R.color.white));
                    m10941d((int) C1205R.drawable.notnormal_icon);
                    this.f8085l.setText(C1205R.string.uncondrone);
                    this.f8088o.setVisibility(4);
                    if (!(this.f8073K == null || this.f8073K.isMiddleFault())) {
                        m10937c(false);
                    }
                    this.f8091r.setVisibility(4);
                    break;
                }
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                com.fimi.soul.utils.aa.m12215d();
                this.f8085l.setText(C1205R.string.dis_connect_phone);
                m10942d(C2915a.f14760f);
                m10941d((int) C1205R.drawable.notnormal_red_icon);
                this.f8085l.setTextColor(getResources().getColor(C1205R.color.errortextcl));
                this.f8088o.setVisibility(0);
                m10937c(false);
                m10933b((int) C1205R.string.connectagain, 0);
                this.f8091r.setVisibility(0);
                if (this.f8063A && c1433a.aa()) {
                    this.f8063A = false;
                    C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.dis_connect_phone));
                    break;
                }
        }
        if (this.f8073K != null && this.f8073K.getLightErrorList().size() == 0) {
            m10942d(C2915a.f14760f);
            m10935c();
            this.f8087n.m12543a(0.0f);
            this.f8087n.m12544b();
        }
        this.f8094u.setVisibility(4);
        this.f8079f = 0;
        this.f8076c = 0;
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        if (this.f8067E == null) {
            this.f8067E = new C1336c(this, this.f8082i, getActivity());
        }
        this.f8082i.m9590a((C1234i) this);
        this.f8067E.m8980b();
        this.f8067E.m8982d();
        if (this.f8082i.m9575V() && getString(C1205R.string.fault_camera).equalsIgnoreCase(this.f8086m.getText().toString())) {
            m10953a(getString(C1205R.string.tf_normal));
        }
    }

    public void onStop() {
        super.onStop();
        this.f8067E.m8981c();
    }
}
