package com.fimi.soul.receiver;

import android.content.Context;
import com.fimi.kernel.p083d.C1160b;

public class SpeekSignReceiver extends NetworkStateReceiver {
    public void m12099a(C1933a c1933a, Context context) {
        if (c1933a == C1933a.Wifi || c1933a == C1933a.Mobile) {
            C1160b a = C1160b.m7951a(context);
            if (!a.m7965d()) {
                a.m7957a();
            }
        }
    }
}
