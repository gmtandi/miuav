package com.fimi.soul.module.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;

public class FirstLeadFragment extends Fragment implements OnClickListener {
    private static final int f8723k = 1;
    private static final int f8724l = 2;
    private static final int f8725m = 3;
    private static final int f8726n = 4;
    private static final int f8727o = 5;
    private static final int f8728p = 6;
    private static final int f8729q = 7;
    private static final int f8730r = 8;
    private TextView f8731A;
    private TextView f8732B;
    private TextView f8733C;
    private TextView f8734D;
    private TextView f8735E;
    private TextView f8736F;
    private TextView f8737G;
    private TextView f8738H;
    private TextView f8739I;
    private TextView f8740J;
    RelativeLayout f8741a;
    RelativeLayout f8742b;
    RelativeLayout f8743c;
    RelativeLayout f8744d;
    RelativeLayout f8745e;
    RelativeLayout f8746f;
    RelativeLayout f8747g;
    RelativeLayout f8748h;
    PercentRelativeLayout f8749i;
    C1739a f8750j;
    private int f8751s;
    private SharedPreferences f8752t;
    private TextView f8753u;
    private TextView f8754v;
    private TextView f8755w;
    private TextView f8756x;
    private TextView f8757y;
    private TextView f8758z;

    public FirstLeadFragment() {
        this.f8751s = f8723k;
    }

    public C1739a m11465a() {
        return this.f8750j;
    }

    void m11466a(int i) {
        this.f8741a.setVisibility(f8730r);
        this.f8742b.setVisibility(f8730r);
        this.f8743c.setVisibility(f8730r);
        this.f8744d.setVisibility(f8730r);
        this.f8745e.setVisibility(f8730r);
        this.f8747g.setVisibility(f8730r);
        this.f8746f.setVisibility(f8730r);
        this.f8748h.setVisibility(f8730r);
        switch (i) {
            case f8723k /*1*/:
                this.f8742b.setVisibility(0);
            case f8724l /*2*/:
                this.f8746f.setVisibility(0);
            case f8725m /*3*/:
                this.f8747g.setVisibility(0);
            case f8726n /*4*/:
                this.f8745e.setVisibility(0);
            case f8727o /*5*/:
                this.f8744d.setVisibility(0);
            case f8728p /*6*/:
                this.f8743c.setVisibility(0);
            case f8729q /*7*/:
                this.f8741a.setVisibility(0);
            case f8730r /*8*/:
                this.f8748h.setVisibility(0);
            default:
        }
    }

    void m11467a(View view) {
        this.f8749i = (PercentRelativeLayout) view.findViewById(C1205R.id.root_layout);
        this.f8741a = (RelativeLayout) view.findViewById(C1205R.id.setting_layout);
        this.f8742b = (RelativeLayout) view.findViewById(C1205R.id.error_status_layout);
        this.f8743c = (RelativeLayout) view.findViewById(C1205R.id.baoxiang_layout);
        this.f8744d = (RelativeLayout) view.findViewById(C1205R.id.location_layout);
        this.f8745e = (RelativeLayout) view.findViewById(C1205R.id.controll_status_layout);
        this.f8746f = (RelativeLayout) view.findViewById(C1205R.id.battery_layout);
        this.f8747g = (RelativeLayout) view.findViewById(C1205R.id.gsp_layout);
        this.f8748h = (RelativeLayout) view.findViewById(C1205R.id.modle_layout);
        this.f8733C = (TextView) view.findViewById(C1205R.id.error_des);
        this.f8734D = (TextView) view.findViewById(C1205R.id.setting_des);
        this.f8735E = (TextView) view.findViewById(C1205R.id.battery_des);
        this.f8736F = (TextView) view.findViewById(C1205R.id.gsp_des);
        this.f8737G = (TextView) view.findViewById(C1205R.id.controller_des);
        this.f8738H = (TextView) view.findViewById(C1205R.id.location_des);
        this.f8740J = (TextView) view.findViewById(C1205R.id.modle_des);
        this.f8753u = (TextView) view.findViewById(C1205R.id.error_status_title);
        this.f8754v = (TextView) view.findViewById(C1205R.id.setting_title);
        this.f8755w = (TextView) view.findViewById(C1205R.id.battery_title);
        this.f8756x = (TextView) view.findViewById(C1205R.id.gsp_title);
        this.f8757y = (TextView) view.findViewById(C1205R.id.controll_status_title);
        this.f8758z = (TextView) view.findViewById(C1205R.id.location_title);
        this.f8732B = (TextView) view.findViewById(C1205R.id.modle_title);
        be.m12359a(getActivity().getAssets(), this.f8733C, this.f8735E, this.f8734D, this.f8738H, this.f8736F, this.f8739I, this.f8737G, this.f8735E, this.f8740J, this.f8753u, this.f8755w, this.f8754v, this.f8758z, this.f8756x, this.f8731A, this.f8757y, this.f8755w, this.f8732B);
        this.f8749i.setOnClickListener(this);
    }

    public void m11468a(C1739a c1739a) {
        this.f8750j = c1739a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.root_layout:
                switch (this.f8751s) {
                    case f8723k /*1*/:
                        this.f8751s = f8724l;
                        break;
                    case f8724l /*2*/:
                        this.f8751s = f8725m;
                        break;
                    case f8725m /*3*/:
                        this.f8751s = f8726n;
                        break;
                    case f8726n /*4*/:
                        this.f8751s = f8727o;
                        break;
                    case f8727o /*5*/:
                        this.f8751s = f8728p;
                        break;
                    case f8728p /*6*/:
                        this.f8751s = f8729q;
                        break;
                    case f8729q /*7*/:
                        this.f8751s = f8730r;
                        break;
                    case f8730r /*8*/:
                        if (this.f8750j != null) {
                            this.f8750j.m11325j();
                            break;
                        }
                        break;
                    default:
                        break;
                }
            case C1205R.id.error_status_layout:
                this.f8751s = f8724l;
                break;
            case C1205R.id.controll_status_layout:
                this.f8751s = f8727o;
                break;
            case C1205R.id.baoxiang_layout:
                this.f8751s = f8729q;
                break;
            case C1205R.id.location_layout:
                this.f8751s = f8728p;
                break;
            case C1205R.id.gsp_layout:
                this.f8751s = f8726n;
                break;
            case C1205R.id.setting_layout:
                this.f8751s = f8730r;
                break;
            case C1205R.id.battery_layout:
                this.f8751s = f8725m;
                break;
            case C1205R.id.modle_layout:
                if (this.f8750j != null) {
                    this.f8750j.m11325j();
                    break;
                }
                break;
        }
        m11466a(this.f8751s);
        this.f8752t.edit().putInt("curModel", this.f8751s).commit();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = View.inflate(getActivity(), C1205R.layout.lead_layout, null);
        m11467a(inflate);
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        this.f8752t = ay.m12293a(getActivity());
        this.f8751s = this.f8752t.getInt("curModel", f8723k);
        m11466a(this.f8751s);
    }
}
