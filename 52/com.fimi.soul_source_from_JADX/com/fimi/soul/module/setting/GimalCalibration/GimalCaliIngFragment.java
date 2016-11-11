package com.fimi.soul.module.setting.GimalCalibration;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.view.progress.ProgressView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import org.p122a.p123a.C2915a;

public class GimalCaliIngFragment extends Fragment implements OnClickListener {
    private static final int f9075i = 100;
    private TextView f9076a;
    private TextView f9077b;
    private TextView f9078c;
    private TextView f9079d;
    private TextView f9080e;
    private Button f9081f;
    private ProgressView f9082g;
    private C1839a f9083h;

    private void m11586a(View view) {
        this.f9076a = (TextView) view.findViewById(C1205R.id.heardView).findViewById(C1205R.id.tv_settingTitle);
        this.f9076a.setText(C1205R.string.gc_calbrating_titling);
        this.f9077b = (TextView) view.findViewById(C1205R.id.process);
        this.f9078c = (TextView) view.findViewById(C1205R.id.processsign);
        this.f9079d = (TextView) view.findViewById(C1205R.id.desTitle);
        this.f9080e = (TextView) view.findViewById(C1205R.id.desTips);
        this.f9081f = (Button) view.findViewById(C1205R.id.heardView).findViewById(C1205R.id.black_btn);
        this.f9081f.setOnClickListener(this);
        this.f9082g = (ProgressView) view.findViewById(C1205R.id.pro_calibration);
        this.f9082g.setMaxCount(100.0f);
        be.m12359a(getActivity().getAssets(), this.f9076a, this.f9079d, this.f9081f);
        be.m12368b(getActivity().getAssets(), this.f9077b, this.f9078c);
    }

    public void m11587a(int i) {
        this.f9077b.setText(i + C2915a.f14760f);
        this.f9082g.setCurrentCount((float) i);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9083h = (C1839a) activity;
    }

    public void onClick(View view) {
        if (view.getId() == C1205R.id.black_btn && this.f9083h != null) {
            this.f9083h.m11588a();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.gimalcaliing, null);
        m11586a(inflate);
        return inflate;
    }

    public void onDetach() {
        super.onDetach();
        if (this.f9083h != null) {
            this.f9083h = null;
        }
    }
}
