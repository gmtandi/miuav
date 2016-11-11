package com.fimi.soul.module.update;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.fimi.soul.module.droneui.FlightActivity;

/* renamed from: com.fimi.soul.module.update.a */
class C1909a extends Handler {
    final /* synthetic */ AutoSelfErrorFrgment f9813a;

    C1909a(AutoSelfErrorFrgment autoSelfErrorFrgment) {
        this.f9813a = autoSelfErrorFrgment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f9813a.getActivity() != null) {
            this.f9813a.startActivity(new Intent(this.f9813a.getActivity(), FlightActivity.class));
            this.f9813a.getActivity().finish();
        }
    }
}
