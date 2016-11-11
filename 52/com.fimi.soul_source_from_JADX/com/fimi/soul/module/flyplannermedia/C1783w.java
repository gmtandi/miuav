package com.fimi.soul.module.flyplannermedia;

import android.os.Handler;
import android.os.Message;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

/* renamed from: com.fimi.soul.module.flyplannermedia.w */
class C1783w extends Handler {
    final /* synthetic */ DroneOnlineFragment f8719a;

    C1783w(DroneOnlineFragment droneOnlineFragment) {
        this.f8719a = droneOnlineFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (Opcodes.IFEQ == message.what) {
            this.f8719a.m11404m().m8874s().m8780e();
        }
    }
}
