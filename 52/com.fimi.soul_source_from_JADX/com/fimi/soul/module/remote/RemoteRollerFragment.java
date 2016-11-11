package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.MidView;
import com.fimi.soul.view.RemoteRollerView;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;
import it.p074a.p075a.C2799f;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteRollerFragment extends BaseRemoteFragment {
    MidView f8982f;
    MidView f8983g;
    private TextView f8984h;
    private TextView f8985i;
    private TextView f8986j;
    private Button f8987k;
    private Button f8988l;
    private RemoteRollerView f8989m;
    private RemoteRollerView f8990n;
    private RemoteRollerView f8991o;
    private RemoteRollerView f8992p;

    public void m11573a() {
        if (this.b.m9570Q()) {
            if (this.b.aa()) {
                this.f8985i.setText(C1205R.string.calremotewaringfly);
            } else {
                this.f8985i.setText(C1205R.string.remotewaring);
            }
            this.f8987k.setEnabled(false);
            m11575a(this.f8987k, 77);
            this.f8986j.setVisibility(4);
        } else if (this.b.m9569P().m9995a()) {
            if (!this.f8987k.isEnabled()) {
                C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.reminder_calibration_canStart));
            }
            this.f8987k.setEnabled(true);
            m11575a(this.f8987k, C2799f.f14256F);
            this.f8985i.setText(C1205R.string.remote_roller_title_one);
            this.f8986j.setVisibility(0);
        } else {
            this.f8987k.setEnabled(false);
            m11575a(this.f8987k, 77);
            this.f8985i.setText(C1205R.string.disconremote);
            this.f8986j.setVisibility(4);
        }
    }

    public void m11574a(View view) {
        this.f8987k = (Button) view.findViewById(C1205R.id.nextbutton);
        this.f8987k.setOnClickListener(this);
        this.f8987k.setEnabled(false);
        this.f8989m = (RemoteRollerView) view.findViewById(C1205R.id.left_up_arrow);
        this.f8990n = (RemoteRollerView) view.findViewById(C1205R.id.left_down_arrow);
        this.f8991o = (RemoteRollerView) view.findViewById(C1205R.id.right_up_arrow);
        this.f8992p = (RemoteRollerView) view.findViewById(C1205R.id.right_down_arrow);
        this.f8989m.setInitView(0);
        this.f8990n.setInitView(1);
        this.f8991o.setInitView(2);
        this.f8992p.setInitView(3);
        this.f8989m.setSweep(TitleBar.TITLEBAR_HEIGHT);
        this.f8990n.setSweep(C2020f.f10933c);
        this.f8991o.setSweep(TitleBar.TITLEBAR_HEIGHT);
        this.f8992p.setSweep(C2020f.f10933c);
        this.f8984h.setText(C1205R.string.calibrate_remote);
        be.m12359a(getActivity().getAssets(), this.f8988l, this.f8984h, this.f8985i, this.f8986j, this.f8987k);
        m11573a();
    }

    public void m11575a(Button button, int i) {
        button.setTextColor(button.getTextColors().withAlpha(i));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.nextbutton:
                if (!this.b.m9569P().m9995a()) {
                    ak.m8085a(this.a, this.a.getString(C1205R.string.cali_fail_please_connect_remote), ak.f5302b);
                } else if (this.b.m9570Q()) {
                    ak.m8085a(this.a, this.a.getString(C1205R.string.close_plane_to_remote_cali), ak.f5302b);
                } else {
                    this.d.m10836l();
                    if (this.e != null) {
                        this.e.m11561a(C1205R.id.remote_roller_fragment, C1205R.id.midcalibrationing);
                    }
                }
            case C1205R.id.black_btn:
                this.d.m10837m();
                getActivity().finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_remote_roller, null);
        this.f8982f = (MidView) inflate.findViewById(C1205R.id.leftview).findViewById(C1205R.id.rightremoteview);
        this.f8983g = (MidView) inflate.findViewById(C1205R.id.rightview).findViewById(C1205R.id.rightremoteview);
        this.f8988l = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8988l.setOnClickListener(this);
        this.f8984h = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8985i = (TextView) inflate.findViewById(C1205R.id.showtitleone);
        this.f8986j = (TextView) inflate.findViewById(C1205R.id.showtitletwo);
        m11574a(inflate);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        float f = TitleBar.TITLEBAR_HEIGHT;
        float f2 = C2020f.f10933c;
        super.onDroneEvent(c1584h, c1433a);
        if (c1584h == C1584h.Remotecontrol || c1584h == C1584h.CLEANALLOBJ) {
            m11573a();
        }
        if (isVisible()) {
            switch (C1836q.f9020a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    int i;
                    int i2;
                    ba I = c1433a.m9562I();
                    this.f8983g.m12636a((float) I.m10448a(), (float) (100 - I.m10451b()));
                    this.f8982f.m12636a((float) I.m10455d(), (float) (100 - I.m10453c()));
                    byte f3 = I.m10459f() > (byte) 50 ? I.m10459f() : I.m10459f();
                    byte e = I.m10457e() > (byte) 50 ? I.m10457e() : I.m10457e();
                    if (f3 >= (byte) 97) {
                        i = 100;
                    } else {
                        byte b = f3;
                    }
                    if (e >= (byte) 97) {
                        i2 = 100;
                    } else {
                        f3 = e;
                    }
                    this.f8989m.setSweep(i > 50 ? (float) i : TitleBar.TITLEBAR_HEIGHT);
                    this.f8990n.setSweep(i < 50 ? (float) (50 - i) : C2020f.f10933c);
                    RemoteRollerView remoteRollerView = this.f8991o;
                    if (i2 > 50) {
                        f = (float) i2;
                    }
                    remoteRollerView.setSweep(f);
                    remoteRollerView = this.f8992p;
                    if (i2 < 50) {
                        f2 = (float) (50 - i2);
                    }
                    remoteRollerView.setSweep(f2);
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.f8987k.setEnabled(false);
    }
}
