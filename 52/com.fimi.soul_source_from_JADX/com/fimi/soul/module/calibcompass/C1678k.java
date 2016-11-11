package com.fimi.soul.module.calibcompass;

/* renamed from: com.fimi.soul.module.calibcompass.k */
class C1678k implements Runnable {
    final /* synthetic */ CaliCompassErrorFragment f7936a;

    C1678k(CaliCompassErrorFragment caliCompassErrorFragment) {
        this.f7936a = caliCompassErrorFragment;
    }

    public void run() {
        this.f7936a.getActivity().setResult(0);
        this.f7936a.getActivity().finish();
    }
}
