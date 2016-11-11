package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.MidView;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteBeginMidCaFragment extends BaseRemoteFragment {
    public DroidPlannerApp f8941f;
    private TextView f8942g;
    private Button f8943h;
    private Button f8944i;
    private MidView f8945j;
    private MidView f8946k;
    private C1433a f8947l;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8941f = (DroidPlannerApp) activity.getApplication();
        this.f8947l = this.f8941f.f5570a;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.nextbutton:
                if (!this.f8947l.m9569P().m9995a()) {
                    ak.m8085a(this.a, this.a.getString(C1205R.string.cali_fail_please_connect_remote), ak.f5302b);
                } else if (this.f8947l.m9570Q()) {
                    ak.m8085a(this.a, this.a.getString(C1205R.string.close_plane_to_remote_cali), ak.f5302b);
                } else {
                    this.d.m10836l();
                    this.c.beginTransaction().hide(this.c.findFragmentById(C1205R.id.beginmidcalibration)).commit();
                    this.c.beginTransaction().show(this.c.findFragmentById(C1205R.id.midcalibrationing)).commit();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.beginmidcalibration, null);
        this.f8942g = (TextView) inflate.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f8942g.setText(C1205R.string.calireming);
        this.f8944i = (Button) inflate.findViewById(C1205R.id.headview).findViewById(C1205R.id.back_btn);
        this.f8945j = (MidView) inflate.findViewById(C1205R.id.rightVew);
        this.f8946k = (MidView) inflate.findViewById(C1205R.id.leftView);
        this.f8943h = (Button) inflate.findViewById(C1205R.id.nextbutton);
        this.f8943h.setOnClickListener(this);
        this.f8944i.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8942g, this.f8944i, this.f8943h);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (isVisible()) {
            switch (C1823d.f9007a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    if (isVisible()) {
                        ba I = c1433a.m9562I();
                        this.f8945j.m12636a((float) I.m10448a(), (float) (100 - I.m10451b()));
                        this.f8946k.m12636a((float) I.m10455d(), (float) (100 - I.m10453c()));
                    }
                default:
            }
        }
    }
}
