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
import com.fimi.soul.module.calibcompass.C1683q;
import com.fimi.soul.utils.be;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteEndCaliFragment extends BaseRemoteFragment {
    private Button f8953f;
    private TextView f8954g;
    private Button f8955h;
    private Handler f8956i;

    public RemoteEndCaliFragment() {
        this.f8956i = new C1827h(this);
    }

    public void m11567a() {
        m11554a(C1205R.id.endmidcalibration, C1205R.id.errorcalifragment, getString(C1205R.string.remote_cali_outtime), false);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.black_btn:
                if (isVisible() && this.e != null) {
                    this.e.m11560a();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.endremotecalibation, null);
        this.f8954g = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8954g.setText(C1205R.string.calireming);
        this.f8955h = (Button) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f8955h.setOnClickListener(this);
        this.f8953f = (Button) inflate.findViewById(C1205R.id.nextbutton);
        this.f8953f.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8954g, this.f8955h, this.f8953f);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (isVisible()) {
            switch (C1828i.f9012a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    c1433a.m9594b((C1234i) this);
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (isVisible()) {
                        C1683q.m10886a().m10887a("98");
                        if (c1433a.m9561H().m10512c() == 3 && c1433a.m9561H().m10511b() == 86) {
                            m11567a();
                        }
                    }
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            this.f8956i.sendEmptyMessageDelayed(0, 2000);
        }
    }
}
