package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.MessageHandleService.C2561a;
import com.xiaomi.mipush.sdk.MessageHandleService.ReceiverCallback;

public abstract class PushMessageReceiver extends BroadcastReceiver implements ReceiverCallback {
    public final void onReceive(Context context, Intent intent) {
        MessageHandleService.addJob(new C2561a(intent, this));
        try {
            context.startService(new Intent(context, MessageHandleService.class));
        } catch (Exception e) {
        }
    }
}
