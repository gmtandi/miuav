package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.MidView;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class CarliRemoteFragment extends BaseRemoteFragment {
    private Button f8922f;
    private Button f8923g;
    private TextView f8924h;
    private TextView f8925i;
    private TextView f8926j;
    private MidView f8927k;
    private MidView f8928l;

    public void m11555a() {
        m11554a(C1205R.id.caliremotestart, C1205R.id.errorcalifragment, getString(C1205R.string.remote_cali_outtime), false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.nextbutton:
                this.d.m10839o();
                if (this.e != null) {
                    this.e.m11561a(C1205R.id.caliremotestart, C1205R.id.cariremotecomple);
                }
            case C1205R.id.black_btn:
                if (isVisible() && this.e != null) {
                    this.e.m11560a();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.caliremotebegin, null);
        this.f8926j = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8926j.setText(C1205R.string.calireming);
        this.f8923g = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8923g.setOnClickListener(this);
        this.f8927k = (MidView) inflate.findViewById(C1205R.id.leftcircle).findViewById(C1205R.id.rightremoteview);
        this.f8928l = (MidView) inflate.findViewById(C1205R.id.rightcircle).findViewById(C1205R.id.rightremoteview);
        this.f8924h = (TextView) inflate.findViewById(C1205R.id.showtitleone);
        this.f8925i = (TextView) inflate.findViewById(C1205R.id.showtitletwo);
        this.f8922f = (Button) inflate.findViewById(C1205R.id.nextbutton);
        this.f8922f.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8922f, this.f8924h, this.f8925i, this.f8926j);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (isVisible()) {
            switch (C1821b.f9005a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c1433a.m9594b((C1234i) this);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    ba I = c1433a.m9562I();
                    this.f8928l.m12636a((float) I.m10448a(), (float) (100 - I.m10451b()));
                    this.f8927k.m12636a((float) I.m10455d(), (float) (100 - I.m10453c()));
                case Type.BYTE /*3*/:
                    if (c1433a.m9561H().m10512c() != 3) {
                        return;
                    }
                    if (c1433a.m9561H().m10511b() == 86 || c1433a.m9561H().m10511b() == 85) {
                        m11555a();
                    }
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && isVisible()) {
            C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.calirockertitle));
        }
    }
}
