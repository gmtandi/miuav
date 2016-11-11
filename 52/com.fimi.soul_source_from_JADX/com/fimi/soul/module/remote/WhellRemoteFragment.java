package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.RemoteRollerView;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.open.yyb.TitleBar;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;

public class WhellRemoteFragment extends BaseRemoteFragment {
    private TextView f8993f;
    private TextView f8994g;
    private TextView f8995h;
    private Button f8996i;
    private Button f8997j;
    private RemoteRollerView f8998k;
    private RemoteRollerView f8999l;
    private RemoteRollerView f9000m;
    private RemoteRollerView f9001n;
    private boolean f9002o;
    private final int f9003p;
    private Handler f9004q;

    public WhellRemoteFragment() {
        this.f9003p = 1;
        this.f9004q = new C1837r(this);
    }

    public void m11577a(boolean z, String str) {
        if (this.f9004q.hasMessages(1)) {
            this.f9004q.removeMessages(1);
        }
        if (z) {
            m11554a(C1205R.id.cariremotecomple, C1205R.id.errorcalifragment, C2915a.f14760f, z);
        } else {
            m11554a(C1205R.id.cariremotecomple, C1205R.id.errorcalifragment, str, z);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.nextbutton:
                this.d.m10840p();
            case C1205R.id.black_btn:
                if (isVisible() && this.e != null) {
                    this.e.m11560a();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.whellcalicomplete, null);
        this.f8995h = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8995h.setText(C1205R.string.calireming);
        this.f8997j = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8997j.setOnClickListener(this);
        this.f8993f = (TextView) inflate.findViewById(C1205R.id.showtitleone);
        this.f8994g = (TextView) inflate.findViewById(C1205R.id.showtitletwo);
        this.f8996i = (Button) inflate.findViewById(C1205R.id.nextbutton);
        this.f8996i.setOnClickListener(this);
        this.f8998k = (RemoteRollerView) inflate.findViewById(C1205R.id.left_up_arrow);
        this.f8999l = (RemoteRollerView) inflate.findViewById(C1205R.id.left_down_arrow);
        this.f9000m = (RemoteRollerView) inflate.findViewById(C1205R.id.right_up_arrow);
        this.f9001n = (RemoteRollerView) inflate.findViewById(C1205R.id.right_down_arrow);
        this.f8998k.setInitView(0);
        this.f8999l.setInitView(1);
        this.f9000m.setInitView(2);
        this.f9001n.setInitView(3);
        this.f8998k.setSweep(TitleBar.TITLEBAR_HEIGHT);
        this.f8999l.setSweep(C2020f.f10933c);
        this.f9000m.setSweep(TitleBar.TITLEBAR_HEIGHT);
        this.f9001n.setSweep(C2020f.f10933c);
        be.m12359a(getActivity().getAssets(), this.f8996i, this.f8993f, this.f8994g);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        if (isVisible()) {
            switch (C1838s.f9022a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c1433a.m9594b((C1234i) this);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    C1683q.m10886a().m10887a("98");
                    if (c1433a.m9561H().m10511b() == (byte) 86 && c1433a.m9561H().m10512c() == (byte) 2 && c1433a.m9561H().m10513d() == (byte) 1) {
                        m11577a(true, C2915a.f14760f);
                    } else if (c1433a.m9561H().m10512c() >= (byte) 2 && c1433a.m9561H().m10511b() == 85 && c1433a.m9561H().m10513d() == (byte) 2) {
                        this.f9002o = true;
                        m11577a(false, getString(C1205R.string.remote_cali_rocker_failue));
                    } else if (c1433a.m9561H().m10512c() >= (byte) 2 && c1433a.m9561H().m10511b() == (byte) 86 && c1433a.m9561H().m10513d() == (byte) 2) {
                        this.f9002o = true;
                        m11577a(false, getString(C1205R.string.remote_cali_roller_failue));
                    } else if (c1433a.m9561H().m10512c() == 3 && c1433a.m9561H().m10511b() == (byte) 86) {
                        m11577a(false, getString(C1205R.string.remote_cali_outtime));
                    }
                case Type.BYTE /*3*/:
                    ba I = c1433a.m9562I();
                    int f = I.m10459f() > (byte) 50 ? I.m10459f() : I.m10459f();
                    int e = I.m10457e() > (byte) 50 ? I.m10457e() : I.m10457e();
                    this.f8998k.setSweep(f > 50 ? (float) f : TitleBar.TITLEBAR_HEIGHT);
                    this.f8999l.setSweep(f < 50 ? (float) (50 - f) : C2020f.f10933c);
                    this.f9000m.setSweep(e > 50 ? (float) e : TitleBar.TITLEBAR_HEIGHT);
                    this.f9001n.setSweep(e < 50 ? (float) (50 - e) : C2020f.f10933c);
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            this.f9002o = false;
        } else {
            this.f9004q.sendEmptyMessageDelayed(1, 2000);
        }
    }
}
