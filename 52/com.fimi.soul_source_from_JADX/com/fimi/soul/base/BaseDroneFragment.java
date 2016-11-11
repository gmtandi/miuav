package com.fimi.soul.base;

import android.app.Activity;
import com.fimi.kernel.BaseFragment;
import com.fimi.soul.drone.C1433a;

public abstract class BaseDroneFragment extends BaseFragment {
    private DroidPlannerApp f5565a;

    public DroidPlannerApp m8506d() {
        return this.f5565a;
    }

    protected C1433a m8507e() {
        return m8506d().f5570a;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f5565a = (DroidPlannerApp) activity.getApplication();
    }
}
