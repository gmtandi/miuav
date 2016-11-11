package com.tencent.mm.sdk.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.Build;
import com.tencent.mm.sdk.platformtools.Log;
import java.util.HashMap;
import java.util.Map;

public class MMessage {

    public interface CallBack {
        void handleMessage(Intent intent);
    }

    public final class Receiver extends BroadcastReceiver {
        public static final Map<String, CallBack> callbacks;
        private final CallBack f11761o;

        static {
            callbacks = new HashMap();
        }

        public Receiver() {
            this(null);
        }

        public Receiver(CallBack callBack) {
            this.f11761o = callBack;
        }

        public static void registerCallBack(String str, CallBack callBack) {
            callbacks.put(str, callBack);
        }

        public static void unregisterCallBack(String str) {
            callbacks.remove(str);
        }

        public final void onReceive(Context context, Intent intent) {
            Log.m13539d("MicroMsg.SDK.MMessage", "receive intent=" + intent);
            if (this.f11761o != null) {
                this.f11761o.handleMessage(intent);
                Log.m13539d("MicroMsg.SDK.MMessage", "mm message self-handled");
                return;
            }
            CallBack callBack = (CallBack) callbacks.get(intent.getAction());
            if (callBack != null) {
                callBack.handleMessage(intent);
                Log.m13539d("MicroMsg.SDK.MMessage", "mm message handled");
            }
        }
    }

    public static void send(Context context, String str, String str2) {
        send(context, str, ConstantsMMessage.ACTION_MESSAGE, str2);
    }

    public static void send(Context context, String str, String str2, String str3) {
        send(context, str, str2, str3, null);
    }

    public static boolean send(Context context, String str, String str2, String str3, Bundle bundle) {
        String str4 = str + ".permission.MM_MESSAGE";
        Intent intent = new Intent(str2);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        String packageName = context.getPackageName();
        intent.putExtra(ConstantsMMessage.SDK_VERSION, Build.SDK_INT);
        intent.putExtra(ConstantsMMessage.APP_PACKAGE, packageName);
        intent.putExtra(ConstantsMMessage.CONTENT, str3);
        intent.putExtra(ConstantsMMessage.CHECK_SUM, MMessageUtil.m13519a(str3, packageName));
        context.sendBroadcast(intent, str4);
        Log.m13539d("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str4);
        return true;
    }
}
