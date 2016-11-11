package com.fimi.soul.module.remote;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.view.percent.PercentRelativeLayout;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.ba;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.MidView;
import org.codehaus.jackson.smile.SmileConstants;

public class RemoteMidCalibrationFragment extends BaseRemoteFragment {
    private PercentRelativeLayout f8957f;
    private Button f8958g;
    private Button f8959h;
    private TextView f8960i;
    private TextView f8961j;
    private TextView f8962k;
    private MidView f8963l;
    private MidView f8964m;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.nextbutton:
                this.c.beginTransaction().hide(this.c.findFragmentById(C1205R.id.midcalibreation)).commit();
                this.c.beginTransaction().show(this.c.findFragmentById(C1205R.id.remote_roller_fragment)).commit();
            case C1205R.id.black_btn:
                this.d.m10837m();
                getActivity().finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.midcalibration, null);
        this.f8957f = (PercentRelativeLayout) inflate.findViewById(C1205R.id.heardView);
        this.f8963l = (MidView) inflate.findViewById(C1205R.id.leftremoteview);
        this.f8964m = (MidView) inflate.findViewById(C1205R.id.rightremoteview);
        this.f8961j = (TextView) inflate.findViewById(C1205R.id.showtitleone);
        this.f8962k = (TextView) inflate.findViewById(C1205R.id.showtitletwo);
        this.f8958g = (Button) this.f8957f.findViewById(C1205R.id.black_btn);
        this.f8958g.setOnClickListener(this);
        this.f8960i = (TextView) this.f8957f.findViewById(C1205R.id.tv_settingTitle);
        this.f8960i.setText(C1205R.string.calibration_remote);
        this.f8959h = (Button) inflate.findViewById(C1205R.id.nextbutton);
        this.f8959h.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f8960i, this.f8959h, this.f8961j, this.f8962k);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        if (isVisible()) {
            switch (C1829j.f9013a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    ba I = c1433a.m9562I();
                    this.f8964m.m12636a((float) I.m10448a(), (float) (100 - I.m10451b()));
                    this.f8963l.m12636a((float) I.m10455d(), (float) (100 - I.m10453c()));
                default:
            }
        }
    }
}
