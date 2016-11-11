package com.fimi.soul.module.calibcompass;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.p091a.C1664h;

public class BaseCaliCompassFragment extends Fragment implements OnClickListener, C1234i {
    protected Context f7857a;
    protected C1433a f7858b;
    protected FragmentManager f7859c;
    protected C1664h f7860d;
    protected C1667a f7861e;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7861e = (C1667a) activity;
        this.f7857a = activity.getApplicationContext();
        this.f7858b = ((DroidPlannerApp) activity.getApplication()).f5570a;
        this.f7859c = getFragmentManager();
        this.f7860d = C1664h.m10813a(this.f7858b);
    }

    public void onClick(View view) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDetach() {
        super.onDetach();
        if (this.f7861e != null) {
            this.f7861e = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
    }

    public void onStart() {
        super.onStart();
        this.f7858b.m9590a((C1234i) this);
    }

    public void onStop() {
        super.onStop();
        this.f7858b.m9594b((C1234i) this);
    }
}
