package com.fimi.soul.module.calibcompass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class CaliCompassErrorVertialFragment extends BaseCaliCompassFragment {
    private TextView f7909f;
    private TextView f7910g;
    private Button f7911h;

    private void m10876a(View view) {
        this.f7909f = (TextView) view.findViewById(C1205R.id.title);
        this.f7910g = (TextView) view.findViewById(C1205R.id.descompass);
        this.f7911h = (Button) view.findViewById(C1205R.id.againcali);
        this.f7911h.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f7909f, this.f7910g, this.f7911h);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.againcali:
                getActivity().finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.cali_compass_error_vertical, null);
        m10876a(inflate);
        return inflate;
    }
}
