package com.fimi.soul.module.remote;

import android.content.Intent;

/* renamed from: com.fimi.soul.module.remote.c */
class C1822c implements Runnable {
    final /* synthetic */ ErrorCaliBretionFragment f9006a;

    C1822c(ErrorCaliBretionFragment errorCaliBretionFragment) {
        this.f9006a = errorCaliBretionFragment;
    }

    public void run() {
        this.f9006a.d.m10837m();
        this.f9006a.getActivity().sendBroadcast(new Intent("com.fimi.soul.control"));
        this.f9006a.f8929f.m12299b(true);
        this.f9006a.getActivity().finish();
    }
}
