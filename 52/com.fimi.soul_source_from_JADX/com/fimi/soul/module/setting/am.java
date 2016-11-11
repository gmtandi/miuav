package com.fimi.soul.module.setting;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.view.button.C1196c;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p103k.C1397v;
import com.fimi.soul.biz.p103k.ab;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.Setting;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.module.update.C1921n;
import com.fimi.soul.module.update.p121a.C1902b;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.Constants;
import java.util.List;
import org.p122a.p123a.C2915a;

public class am extends BaseAdapter implements C1196c {
    public static final int f9217A = 24;
    public static final int f9218B = 25;
    public static final int f9219C = 26;
    public static final int f9220D = 27;
    public static final int f9221E = 28;
    public static final int f9222F = 29;
    public static final int f9223G = 0;
    public static final int f9224H = 1;
    public static final int f9225I = 2;
    public static final int f9226J = 3;
    public static final String f9227L = "100";
    public static final int f9228a = 0;
    public static final int f9229b = 1;
    public static final int f9230c = 2;
    public static final int f9231d = 3;
    public static final int f9232e = 4;
    public static final int f9233f = 5;
    public static final int f9234g = 6;
    public static final int f9235h = 7;
    public static final int f9236i = 8;
    public static final int f9237j = 9;
    public static final int f9238k = 10;
    public static final int f9239l = 11;
    public static final int f9240m = 12;
    public static final int f9241n = 131;
    public static final int f9242o = 141;
    public static final int f9243p = 13;
    public static final int f9244q = 14;
    public static final int f9245r = 15;
    public static final int f9246s = 16;
    public static final int f9247t = 17;
    public static final int f9248u = 18;
    public static final int f9249v = 19;
    public static final int f9250w = 20;
    public static final int f9251x = 21;
    public static final int f9252y = 22;
    public static final int f9253z = 23;
    public ar f9254K;
    private Context f9255M;
    private List<Setting> f9256N;
    private C1433a f9257O;
    private aq f9258P;
    private C1902b f9259Q;
    private int f9260R;
    private C1397v f9261S;
    private ap f9262T;

    public am(Context context, C1433a c1433a) {
        this.f9260R = f9228a;
        this.f9255M = context;
        this.f9257O = c1433a;
        this.f9261S = C1397v.m9355a(context);
        this.f9259Q = (C1902b) C1189f.m8333c().m7926a(C1921n.f9846k, C1902b.class);
        if (this.f9259Q != null) {
            this.f9260R = this.f9259Q.m12012a();
        }
    }

    private void m11679a(as asVar, LayoutParams layoutParams) {
        asVar.f9274i.setBackgroundResource(C1205R.drawable.list_setting_selector);
        asVar.f9266a.setVisibility(f9232e);
        asVar.f9271f.setVisibility(f9232e);
        asVar.f9267b.setVisibility(f9232e);
        asVar.f9275j.setVisibility(f9232e);
        asVar.f9273h.setVisibility(f9236i);
        asVar.f9272g.setVisibility(f9232e);
        asVar.f9269d.setVisibility(f9232e);
        asVar.f9270e.setVisibility(f9232e);
        asVar.f9277l.setVisibility(f9236i);
        asVar.f9268c.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_content));
        asVar.f9269d.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_content));
        asVar.f9270e.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_content_child));
        asVar.f9272g.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_declare));
        asVar.f9267b.setAlpha(C2020f.f10933c);
        asVar.f9275j.setAlpha(C2020f.f10933c);
        asVar.f9271f.setAlpha(C2020f.f10933c);
        layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_height);
        asVar.f9274i.setLayoutParams(layoutParams);
    }

    public void m11681a(int i) {
        this.f9260R = i;
    }

    public void m11682a(int i, View view) {
        if (view != null && (view.getTag() instanceof as)) {
            ((as) view.getTag()).f9272g.setText(((Setting) this.f9256N.get(i)).getContent());
        }
    }

    public void m11683a(View view, boolean z) {
        SwitchButton switchButton;
        switch (((Integer) view.getTag()).intValue()) {
            case f9233f /*5*/:
                this.f9258P.m11626a((SwitchButton) view.findViewWithTag(view.getTag()));
            case f9235h /*7*/:
                switchButton = (SwitchButton) view.findViewWithTag(view.getTag());
                if (((Setting) this.f9256N.get(f9235h)).getIsOPen().booleanValue()) {
                    switchButton.m8371a(true, false);
                } else {
                    switchButton.m8371a(false, false);
                }
                this.f9254K.m11627a(f9230c);
            case f9236i /*8*/:
                switchButton = (SwitchButton) view.findViewWithTag(view.getTag());
                if (((Setting) this.f9256N.get(f9236i)).getIsOPen().booleanValue()) {
                    switchButton.m8371a(true, false);
                } else {
                    switchButton.m8371a(false, false);
                }
                if (!((Setting) this.f9256N.get(f9233f)).getIsOPen().booleanValue()) {
                    this.f9254K.m11627a(f9232e);
                }
            case f9237j /*9*/:
                if (C1189f.m8335e().m8031c()) {
                    C1189f.m8335e().m8024a(false);
                } else {
                    C1189f.m8335e().m8024a(true);
                }
                ((FlightActivity) this.f9255M).m11346h();
            case f9250w /*20*/:
                if (ab.m9180a(this.f9255M).m9190e()) {
                    ab.m9180a(this.f9255M).m9191f(false);
                    PreferenceManager.getDefaultSharedPreferences(this.f9255M).edit().putInt(C1543c.f7244s, f9229b).commit();
                    return;
                }
                ab.m9180a(this.f9255M).m9191f(true);
                PreferenceManager.getDefaultSharedPreferences(this.f9255M).edit().putInt(C1543c.f7244s, f9230c).commit();
            default:
        }
    }

    void m11684a(TextView textView) {
        if (textView.isSelected()) {
            textView.setTextColor(this.f9255M.getResources().getColor(C1205R.color.onmarker_color));
        } else {
            textView.setTextColor(this.f9255M.getResources().getColor(C1205R.color.white_half));
        }
    }

    public void m11685a(ap apVar) {
        this.f9262T = apVar;
    }

    public void m11686a(aq aqVar) {
        this.f9258P = aqVar;
    }

    public void m11687a(ar arVar) {
        this.f9254K = arVar;
    }

    public void m11688a(List<Setting> list) {
        this.f9256N = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return f9222F;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        as asVar;
        if (view == null) {
            as asVar2 = new as();
            view = asVar2.m11689a(viewGroup);
            view.setTag(asVar2);
            asVar = asVar2;
        } else {
            asVar = (as) view.getTag();
        }
        if (this.f9256N != null) {
            m11679a(asVar, view.getLayoutParams());
            LayoutParams layoutParams;
            switch (i) {
                case f9228a /*0*/:
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.flight);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9229b /*1*/:
                    if (!((Setting) this.f9256N.get(f9229b)).getIsOPen().booleanValue()) {
                        asVar.f9268c.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                    }
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.connect_aircraft);
                    asVar.f9268c.setText(C1205R.string.connect_plane);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9230c /*2*/:
                    if (!((Setting) this.f9256N.get(f9230c)).getIsOPen().booleanValue()) {
                        asVar.f9268c.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                    }
                    if (((Setting) this.f9256N.get(f9230c)).getIsOPen().booleanValue()) {
                        if (((Setting) this.f9256N.get(f9230c)).isDisplayTv()) {
                            asVar.f9272g.setVisibility(f9228a);
                            asVar.f9272g.setText(this.f9255M.getResources().getString(C1205R.string.new_firmware_upgrade));
                            asVar.f9273h.setVisibility(f9228a);
                        } else {
                            asVar.f9273h.setVisibility(f9232e);
                            asVar.f9272g.setVisibility(f9232e);
                        }
                    }
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.firmware_upgrade);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9268c.setText(C1205R.string.refreshupdate);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9231d /*3*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.compass_calibration);
                    asVar.f9268c.setText(C1205R.string.compass_calibration);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9232e /*4*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.new_hand_guide);
                    asVar.f9268c.setText(C1205R.string.new_hand_guide);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9233f /*5*/:
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9269d.setVisibility(f9228a);
                    asVar.f9270e.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9269d.setText(C1205R.string.new_hand_mode);
                    asVar.f9270e.setText(C1205R.string.newhand_mode_child);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.control_mode);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9275j.setVisibility(f9228a);
                    if (((Setting) this.f9256N.get(f9233f)).getIsOPen().booleanValue()) {
                        asVar.f9275j.m8371a(true, false);
                    } else {
                        asVar.f9275j.m8371a(false, false);
                    }
                    asVar.f9275j.setAlpha(C2020f.f10933c);
                    asVar.f9275j.setTag(Integer.valueOf(f9233f));
                    asVar.f9275j.setOnSwitchListener(this);
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_switch);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9234g /*6*/:
                    if (((Setting) this.f9256N.get(f9233f)).getIsOPen().booleanValue()) {
                        asVar.f9269d.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9270e.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9271f.setAlpha(0.3f);
                        asVar.f9267b.setAlpha(0.3f);
                    }
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9269d.setVisibility(f9228a);
                    asVar.f9270e.setVisibility(f9228a);
                    asVar.f9269d.setText(C1205R.string.gps_mode);
                    asVar.f9270e.setText(C1205R.string.need_to_unlock_the_newhand_mode);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_gps);
                    asVar.f9267b.setVisibility(f9228a);
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_switch);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    break;
                case f9235h /*7*/:
                    if (((Setting) this.f9256N.get(f9233f)).getIsOPen().booleanValue() || ((Setting) this.f9256N.get(f9236i)).getIsOPen().booleanValue()) {
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                        asVar.f9269d.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9270e.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9275j.setAlpha(0.3f);
                    } else {
                        asVar.f9267b.setAlpha(C2020f.f10933c);
                        asVar.f9271f.setAlpha(C2020f.f10933c);
                        asVar.f9275j.setAlpha(C2020f.f10933c);
                    }
                    asVar.f9275j.setTag(Integer.valueOf(f9235h));
                    asVar.f9275j.setOnSwitchListener(this);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9269d.setVisibility(f9228a);
                    asVar.f9270e.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9269d.setText(C1205R.string.optical_flow_mode);
                    asVar.f9270e.setText(C1205R.string.need_to_unlock_the_newhand_mode);
                    asVar.f9275j.setVisibility(f9228a);
                    if (((Setting) this.f9256N.get(f9235h)).getIsOPen().booleanValue()) {
                        asVar.f9275j.m8371a(true, false);
                    } else {
                        asVar.f9275j.m8371a(false, false);
                    }
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_location_mode);
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_switch);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9236i /*8*/:
                    if (((Setting) this.f9256N.get(f9233f)).getIsOPen().booleanValue()) {
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                        asVar.f9269d.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9270e.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9275j.setAlpha(0.3f);
                    } else {
                        asVar.f9267b.setAlpha(C2020f.f10933c);
                        asVar.f9271f.setAlpha(C2020f.f10933c);
                        asVar.f9275j.setAlpha(C2020f.f10933c);
                    }
                    asVar.f9269d.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9270e.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9269d.setText(C1205R.string.forced_attitude_mode);
                    asVar.f9270e.setText(C1205R.string.need_to_unlock_the_newhand_mode);
                    asVar.f9275j.setVisibility(f9228a);
                    if (((Setting) this.f9256N.get(f9236i)).getIsOPen().booleanValue()) {
                        asVar.f9275j.m8371a(true, false);
                    } else {
                        asVar.f9275j.m8371a(false, false);
                    }
                    asVar.f9275j.setTag(Integer.valueOf(f9236i));
                    asVar.f9275j.setOnSwitchListener(this);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_posture_mode);
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_switch);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9237j /*9*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.flight_parameter);
                    asVar.f9268c.setText(C1205R.string.show_more_patter);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9275j.setVisibility(f9228a);
                    if (C1189f.m8335e().m8031c()) {
                        asVar.f9275j.m8371a(true, false);
                    } else {
                        asVar.f9275j.m8371a(false, false);
                    }
                    asVar.f9275j.setOnSwitchListener(this);
                    asVar.f9275j.setTag(Integer.valueOf(f9237j));
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9238k /*10*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.remotecontrol);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9239l /*11*/:
                    asVar.f9276k.setVisibility(f9232e);
                    if (!((Setting) this.f9256N.get(f9239l)).getIsOPen().booleanValue()) {
                        asVar.f9268c.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                    }
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.the_remote_control_of_calibration);
                    asVar.f9268c.setText(C1205R.string.calibration_remote);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9240m /*12*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.switch_the_remote_control);
                    asVar.f9268c.setText(C1205R.string.switch_remote_mode);
                    asVar.f9272g.setVisibility(f9228a);
                    asVar.f9272g.setText(((Setting) this.f9256N.get(f9240m)).getContent());
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9243p /*13*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.battery);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9244q /*14*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_battery_parameters);
                    asVar.f9268c.setText(C1205R.string.battery_info);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9245r /*15*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.gimbal);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9246s /*16*/:
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.gimbal_calibration);
                    asVar.f9268c.setText(C1205R.string.gimbal_calibration);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9247t /*17*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.camera);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9248u /*18*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.camera_settings);
                    asVar.f9268c.setText(C1205R.string.camera_setting);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9249v /*19*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.map);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9250w /*20*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.satellite_map);
                    asVar.f9268c.setText(C1205R.string.sattlite_map);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9275j.setVisibility(f9228a);
                    if (ab.m9180a(this.f9255M).m9190e()) {
                        asVar.f9275j.m8371a(true, false);
                    } else {
                        asVar.f9275j.m8371a(false, false);
                    }
                    asVar.f9275j.setOnSwitchListener(this);
                    asVar.f9275j.setTag(Integer.valueOf(f9250w));
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9251x /*21*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9228a);
                    asVar.f9266a.setText(C1205R.string.account);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9252y /*22*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_fly_record);
                    asVar.f9268c.setText(C1205R.string.flight_record);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9253z /*23*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_fly_log);
                    asVar.f9268c.setText(C1205R.string.fly_logs);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9217A /*24*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_mi_insurance);
                    asVar.f9268c.setText(C1205R.string.xiaomi_insurance);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9218B /*25*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.user_feedback);
                    asVar.f9268c.setText(C1205R.string.user_feedback);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9219C /*26*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.check_update);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9268c.setText(C1205R.string.checkupdate);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    if (!((Setting) this.f9256N.get(f9219C)).getIsOPen().booleanValue()) {
                        asVar.f9273h.setVisibility(f9228a);
                        asVar.f9272g.setVisibility(f9228a);
                        asVar.f9272g.setText(this.f9255M.getString(C1205R.string.have_update) + ((Setting) this.f9256N.get(f9219C)).getContent());
                        break;
                    }
                    break;
                case f9220D /*27*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.about_icon);
                    asVar.f9268c.setText(C1205R.string.about);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9221E /*28*/:
                    asVar.f9276k.setVisibility(f9232e);
                    if (((Setting) this.f9256N.get(f9221E)).getIsOPen().booleanValue()) {
                        asVar.f9272g.setText(((Setting) this.f9256N.get(f9221E)).getContent());
                        asVar.f9271f.setVisibility(f9232e);
                    } else {
                        asVar.f9272g.setText(C1205R.string.exited);
                        asVar.f9271f.setVisibility(f9228a);
                    }
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.mi_id);
                    asVar.f9268c.setText(C1205R.string.xiaomi_account);
                    asVar.f9272g.setVisibility(f9228a);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9222F /*29*/:
                    asVar.f9276k.setVisibility(f9232e);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9232e);
                    asVar.f9275j.setVisibility(f9232e);
                    asVar.f9273h.setVisibility(f9232e);
                    asVar.f9272g.setVisibility(f9232e);
                    asVar.f9269d.setVisibility(f9232e);
                    asVar.f9270e.setVisibility(f9232e);
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9277l.setVisibility(f9236i);
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_bottom);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                    break;
                case f9241n /*131*/:
                    asVar.f9276k.setVisibility(f9232e);
                    if (!((Setting) this.f9256N.get(f9239l)).getIsOPen().booleanValue()) {
                        asVar.f9268c.setTextColor(this.f9255M.getResources().getColor(C1205R.color.setting_ash));
                        asVar.f9274i.setBackgroundResource(C1205R.color.list_nomal);
                        asVar.f9267b.setAlpha(0.3f);
                        asVar.f9271f.setAlpha(0.3f);
                    }
                    asVar.f9271f.setVisibility(f9228a);
                    asVar.f9268c.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_match_code);
                    asVar.f9268c.setText(C1205R.string.pair_setting_item);
                    asVar.f9277l.setVisibility(f9236i);
                    break;
                case f9242o /*141*/:
                    asVar.f9268c.setVisibility(f9232e);
                    asVar.f9269d.setVisibility(f9228a);
                    asVar.f9270e.setVisibility(f9228a);
                    asVar.f9266a.setVisibility(f9232e);
                    asVar.f9269d.setText(C1205R.string.right_scroll_mode);
                    asVar.f9270e.setText(C1205R.string.right_scroll_mode_des);
                    asVar.f9267b.setBackgroundResource(C1205R.drawable.icon_right_pulley_mode);
                    asVar.f9267b.setVisibility(f9228a);
                    asVar.f9275j.setVisibility(f9236i);
                    asVar.f9271f.setVisibility(f9232e);
                    asVar.f9277l.setVisibility(f9228a);
                    String str = C2915a.f14760f;
                    if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(str)) {
                        asVar.f9278m.setSelected(true);
                        asVar.f9279n.setSelected(false);
                    } else if (Constants.VIA_SSO_LOGIN.equals(str)) {
                        asVar.f9279n.setSelected(true);
                        asVar.f9278m.setSelected(false);
                    }
                    m11684a(asVar.f9278m);
                    m11684a(asVar.f9279n);
                    asVar.f9278m.setOnClickListener(new ao(this, asVar.f9278m, asVar.f9279n));
                    asVar.f9279n.setOnClickListener(new ao(this, asVar.f9278m, asVar.f9279n));
                    layoutParams = view.getLayoutParams();
                    layoutParams.height = (int) this.f9255M.getResources().getDimension(C1205R.dimen.setting_adapt_switch);
                    asVar.f9274i.setLayoutParams(layoutParams);
                    break;
                default:
                    break;
            }
        }
        return view;
    }
}
