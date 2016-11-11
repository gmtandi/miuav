package com.fimi.soul.module.remote;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1234i;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.module.p091a.C1664h;

public class BaseRemoteFragment extends Fragment implements OnClickListener, C1234i {
    protected Context f8917a;
    protected C1433a f8918b;
    protected FragmentManager f8919c;
    protected C1664h f8920d;
    protected C1820a f8921e;

    protected void m11554a(int i, int i2, String str, boolean z) {
        if (this.f8919c.findFragmentById(C1205R.id.errorcalifragment).isHidden() && this.f8921e != null) {
            this.f8921e.m11562a(i, i2, str, z);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8921e = (C1820a) activity;
        this.f8917a = activity.getApplicationContext();
        this.f8918b = ((DroidPlannerApp) activity.getApplication()).f5570a;
        this.f8919c = getFragmentManager();
        this.f8920d = C1664h.m10813a(this.f8918b);
    }

    public void onClick(View view) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f8918b.m9594b((C1234i) this);
    }

    public void onDetach() {
        super.onDetach();
        if (this.f8921e != null) {
            this.f8921e = null;
        }
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
    }

    public void onStart() {
        super.onStart();
        this.f8918b.m9590a((C1234i) this);
    }

    public void onStop() {
        super.onStop();
    }
}
