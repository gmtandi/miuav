package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteMidcalingFragment extends BaseRemoteFragment {
    private RemoteEndCaliFragment f8965f;
    private TextView f8966g;
    private Button f8967h;

    public void m11568a() {
        m11554a(C1205R.id.midcalibrationing, C1205R.id.errorcalifragment, getString(C1205R.string.remote_cali_outtime), false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == C1205R.id.black_btn && isVisible() && this.e != null) {
            this.e.m11560a();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.remotecaliing, null);
        this.f8966g = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8966g.setText(C1205R.string.calireming);
        this.f8967h = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8967h.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8966g, this.f8967h);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (isVisible()) {
            switch (C1830k.f9014a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c1433a.m9594b((C1234i) this);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (isVisible()) {
                        C1683q.m10886a().m10887a("98");
                        if (c1433a.m9561H().m10512c() == 1 && c1433a.m9561H().m10511b() == 82) {
                            if (this.e != null) {
                                this.e.m11561a(C1205R.id.midcalibrationing, C1205R.id.endmidcalibration);
                            }
                            this.d.m10835k();
                        }
                    }
                    if (c1433a.m9561H().m10512c() == 3 && c1433a.m9561H().m10511b() == 86) {
                        m11568a();
                    }
                default:
            }
        }
    }
}
