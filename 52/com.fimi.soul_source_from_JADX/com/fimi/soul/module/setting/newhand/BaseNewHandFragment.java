package com.fimi.soul.module.setting.newhand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.utils.be;

public abstract class BaseNewHandFragment extends Fragment implements OnClickListener {
    protected Context f9314a;
    protected FragmentManager f9315b;
    private TextView f9316c;

    protected abstract View m11745a(LayoutInflater layoutInflater);

    protected abstract void m11746a();

    protected abstract void m11747a(View view);

    protected abstract String m11748b();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9314a = activity.getApplicationContext();
        this.f9315b = getFragmentManager();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.newhand_skip_tv:
                C1189f.m8333c().m7933a(C1236a.f5621s, true);
                if (C1189f.m8333c().m7937d(C1236a.f5588L)) {
                    getActivity().finish();
                    return;
                }
                startActivity(new Intent(getActivity(), FlightActivity.class));
                getActivity().finish();
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View a = m11745a(layoutInflater);
        m11747a(a);
        m11746a();
        this.f9316c = (TextView) a.findViewById(C1205R.id.newhand_skip_tv);
        this.f9316c.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f9316c);
        return a;
    }
}
