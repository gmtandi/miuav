package com.fimi.soul.module.droneFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.fimi.kernel.p084e.ak;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.biz.p090b.C1247f;
import com.fimi.soul.biz.p090b.C1253k;
import com.fimi.soul.biz.p090b.C1255m;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.ad;
import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class FlyActionSettingFragment extends Fragment implements OnClickListener, C1234i, C1684l {
    private Button f7950a;
    private Button f7951b;
    private TextView f7952c;
    private TextView f7953d;
    private TextView f7954e;
    private SeekBar f7955f;
    private AMap f7956g;
    private C1433a f7957h;
    private PercentRelativeLayout f7958i;
    private C1253k f7959j;
    private Button f7960k;
    private SharedPreferences f7961l;
    private C1697k f7962m;
    private C1707u f7963n;
    private ImageButton f7964o;
    private boolean f7965p;
    private Dialog f7966q;

    private void m10894a(View view) {
        this.f7964o = (ImageButton) view.findViewById(C1205R.id.toggleaction);
        this.f7964o.setOnClickListener(this);
        this.f7960k = (Button) view.findViewById(C1205R.id.delete_operaActon);
        this.f7960k.setOnClickListener(this);
        this.f7958i = (PercentRelativeLayout) view.findViewById(C1205R.id.changgeHeight_view);
        this.f7950a = (Button) view.findViewById(C1205R.id.fly_action_cancal_btn);
        this.f7950a.setOnClickListener(this);
        this.f7951b = (Button) view.findViewById(C1205R.id.fly_action_execute_btn);
        this.f7951b.setOnClickListener(this);
        this.f7952c = (TextView) view.findViewById(C1205R.id.title);
        this.f7953d = (TextView) view.findViewById(C1205R.id.height);
        this.f7954e = (TextView) view.findViewById(C1205R.id.heightvalue);
        if (this.f7955f == null) {
            this.f7955f = (SeekBar) view.findViewById(C1205R.id.height_seebar);
            this.f7955f.setOnSeekBarChangeListener(this);
            this.f7955f.setMax(Opcodes.ISHL);
            this.f7955f.setProgress(30);
            this.f7954e.setText("30m");
        }
        be.m12359a(getActivity().getAssets(), this.f7950a, this.f7951b, this.f7952c, this.f7953d, this.f7960k);
        be.m12368b(getActivity().getAssets(), this.f7954e);
    }

    private void m10895a(String str) {
        if (this.f7966q != null && this.f7966q.isShowing()) {
            this.f7966q.dismiss();
            this.f7966q = null;
        }
        ad adVar = new ad(getActivity());
        adVar.m12735a(str);
        adVar.m12736a(getString(C1205R.string.finish), new C1695i(this));
        this.f7966q = adVar.m12733a();
        this.f7966q.setCanceledOnTouchOutside(false);
        this.f7966q.show();
    }

    private void m10897b() {
        this.f7957h.m9589a(C1584h.DISPLAYLIMITCIRCLE);
        this.f7957h.m9589a(C1584h.CLEARTAKEPHOTO);
    }

    private void m10898c() {
        if (this.f7959j.m8599a().get() == 1) {
            this.f7952c.setText(C1205R.string.settingwaypoint);
            this.f7960k.setText(C1205R.string.delete_wraypoint);
            this.f7962m.m11126e();
            this.f7964o.setVisibility(8);
            if (!this.f7961l.getBoolean(C1543c.bC, false)) {
                m10895a(getString(C1205R.string.fly_action_tip));
            }
        } else if (this.f7959j.m8599a().get() == 2) {
            this.f7952c.setText(C1205R.string.settingtargetplace);
            this.f7960k.setText(C1205R.string.delete_flytopoint);
            this.f7962m.m11126e();
            this.f7964o.setVisibility(8);
            if (!this.f7961l.getBoolean(C1543c.bD, false)) {
                m10895a(getString(C1205R.string.fly_action_flyto_tip));
            }
        } else if (this.f7959j.m8599a().get() == 3) {
            this.f7952c.setText(C1205R.string.set_interest_point);
            this.f7964o.setVisibility(0);
            if (this.f7961l.getBoolean(C1543c.bE, false)) {
                this.f7962m.m11119a();
            } else {
                m10895a(getString(C1205R.string.fly_action_poi_tip));
            }
        }
        this.f7955f.setProgress(30);
        C1247f.m8565k().m8567a(30);
        C1247f.m8565k().m8583l();
        this.f7957h.m9589a(C1584h.CLEARDATA);
        if (this.f7958i.isShown()) {
            this.f7958i.setVisibility(4);
        }
    }

    public void m10899a() {
        this.f7962m.m11125d();
        this.f7962m.m11126e();
        this.f7963n.m11157b();
        this.f7964o.setImageResource(C1205R.drawable.switch_action_poi);
        this.f7965p = false;
        C1247f.m8565k().m8583l();
        C1253k.m8598a(getActivity().getApplicationContext()).m8600a(0);
        this.f7957h.m9589a(C1584h.CANCALFLYACTION);
    }

    public void m10900a(SeekBar seekBar) {
    }

    public void m10901a(SeekBar seekBar, int i, boolean z) {
        if (i < 5) {
            i = 5;
        }
        this.f7954e.setText(i + "m");
        FlyActionBean j = C1247f.m8565k().m8582j();
        if (j != null) {
            C1247f.m8565k().m8567a(i);
            j.setHeight(i);
            for (Marker marker : C1247f.m8565k().m8577e()) {
                if (marker.getObject().equals(j)) {
                    marker.setIcon(C1255m.m8606a(this.f7957h.f6507c, j.getDrawableRes(), j.getHeight(), true));
                    return;
                }
            }
        }
    }

    public void m10902b(SeekBar seekBar) {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7957h = ((DroidPlannerApp) activity.getApplication()).f5570a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.fly_action_cancal_btn:
                m10899a();
            case C1205R.id.fly_action_execute_btn:
                if (this.f7957h.ah().m10172g().isLightStream()) {
                    ak.m8083a(getActivity(), (int) C1205R.string.in_light_stream, 3000);
                } else if (this.f7957h.ah().m10172g().isEnforcementAtti()) {
                    ak.m8083a(getActivity(), (int) C1205R.string.in_Atti, 3000);
                } else if (this.f7959j.m8599a().get() == 1) {
                    this.f7957h.m9589a(C1584h.UPWAYPOINT);
                } else if (this.f7959j.m8599a().get() == 2) {
                    this.f7957h.m9589a(C1584h.ASSIGNWAYPOINT);
                } else if (this.f7959j.m8599a().get() == 3) {
                    this.f7957h.m9589a(C1584h.INTERESTWAYPOINT);
                }
            case C1205R.id.delete_operaActon:
                this.f7957h.m9589a(C1584h.DELETE_WAYPOINT);
            case C1205R.id.toggleaction:
                if (this.f7965p) {
                    this.f7965p = false;
                    this.f7963n.m11157b();
                    this.f7964o.setImageResource(C1205R.drawable.switch_action_poi);
                } else if (this.f7957h.m9575V()) {
                    this.f7965p = true;
                    this.f7963n.m11156a();
                    this.f7964o.setImageResource(C1205R.drawable.switch_poi_map);
                } else {
                    ak.m8083a(this.f7957h.f6507c, (int) C1205R.string.disconnect_camer, 3000);
                }
            default:
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7959j = C1253k.m8598a(this.f7957h.f6507c);
        this.f7961l = com.fimi.kernel.p084e.ad.m8019a(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fly_drone_action, null);
        this.f7962m = new C1697k(inflate, this.f7957h);
        this.f7963n = new C1707u(inflate, this.f7957h.f6507c);
        m10894a(inflate);
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        if (this.f7962m != null) {
            this.f7962m = null;
        }
        if (this.f7963n != null) {
            this.f7963n = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        switch (C1696j.f8285a[c1584h.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                m10899a();
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                this.f7965p = false;
                this.f7963n.m11157b();
                this.f7964o.setImageResource(C1205R.drawable.switch_action_poi);
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                if (this.f7962m != null) {
                    this.f7962m.m11121a(false);
                }
            case Type.INT /*5*/:
                if (!this.f7958i.isShown()) {
                    this.f7958i.setVisibility(0);
                }
                FlyActionBean j = C1247f.m8565k().m8582j();
                if (j != null) {
                    this.f7955f.setProgress(j.getHeight());
                }
            case Type.FLOAT /*6*/:
                if (this.f7958i.isShown()) {
                    this.f7958i.setVisibility(4);
                }
            case Type.LONG /*7*/:
                if (isVisible() && c1433a.ah().m10172g().judgeNoAction()) {
                    m10898c();
                    m10897b();
                }
            case Type.DOUBLE /*8*/:
                this.f7962m.m11128g();
            case Type.ARRAY /*9*/:
                if (this.f7962m != null) {
                    this.f7962m.m11125d();
                }
            case Type.OBJECT /*10*/:
                if (this.f7962m != null) {
                    this.f7962m.m11125d();
                }
            case Opcodes.T_LONG /*11*/:
                this.f7963n.m11157b();
                this.f7962m.m11131j();
                this.f7964o.setImageResource(C1205R.drawable.switch_action_poi);
                this.f7965p = false;
            default:
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!isAdded()) {
            return;
        }
        if (z) {
            if (this.f7966q != null) {
                this.f7966q.dismiss();
            }
            this.f7965p = false;
            this.f7963n.m11157b();
            this.f7964o.setImageResource(C1205R.drawable.switch_action_poi);
            return;
        }
        m10898c();
        m10897b();
    }

    public void onResume() {
        super.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onStart() {
        super.onStart();
        this.f7957h.m9590a((C1234i) this);
    }

    public void onStop() {
        super.onStop();
        this.f7957h.m9594b((C1234i) this);
    }
}
