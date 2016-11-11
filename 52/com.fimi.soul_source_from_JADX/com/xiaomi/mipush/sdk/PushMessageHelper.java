package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2760q;
import java.util.List;

public class PushMessageHelper {
    public static final String KEY_COMMAND = "key_command";
    public static final String KEY_MESSAGE = "key_message";
    public static final int MESSAGE_COMMAND = 3;
    public static final int MESSAGE_QUIT = 4;
    public static final int MESSAGE_RAW = 1;
    public static final int MESSAGE_SENDMESSAGE = 2;
    public static final String MESSAGE_TYPE = "message_type";
    public static final int PUSH_MODE_BROADCAST = 2;
    public static final int PUSH_MODE_CALLBACK = 1;
    private static int pushMode;

    static {
        pushMode = 0;
    }

    public static MiPushCommandMessage generateCommandMessage(String str, List<String> list, long j, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage generateMessage(C2760q c2760q, C2734d c2734d, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(c2760q.m15688c());
        if (!TextUtils.isEmpty(c2760q.m15695j())) {
            miPushMessage.setMessageType(PUSH_MODE_CALLBACK);
            miPushMessage.setAlias(c2760q.m15695j());
        } else if (TextUtils.isEmpty(c2760q.m15693h())) {
            miPushMessage.setMessageType(0);
        } else {
            miPushMessage.setMessageType(PUSH_MODE_BROADCAST);
            miPushMessage.setTopic(c2760q.m15693h());
        }
        miPushMessage.setCategory(c2760q.m15701p());
        if (c2760q.m15697l() != null) {
            miPushMessage.setContent(c2760q.m15697l().m15413e());
        }
        if (c2734d != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(c2734d.m15433b());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(c2734d.m15444f());
            }
            miPushMessage.setDescription(c2734d.m15448j());
            miPushMessage.setTitle(c2734d.m15446h());
            miPushMessage.setNotifyType(c2734d.m15450l());
            miPushMessage.setNotifyId(c2734d.m15455q());
            miPushMessage.setPassThrough(c2734d.m15453o());
            miPushMessage.setExtra(c2734d.m15457s());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static int getPushMode(Context context) {
        if (pushMode == 0) {
            if (isUseCallbackPushMode(context)) {
                setPushMode(PUSH_MODE_CALLBACK);
            } else {
                setPushMode(PUSH_MODE_BROADCAST);
            }
        }
        return pushMode;
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return isIntentAvailable(context, intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_COMMAND);
        intent.putExtra(KEY_COMMAND, miPushCommandMessage);
        context.sendBroadcast(intent);
    }

    public static void sendQuitMessageBroadcast(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_QUIT);
        context.sendBroadcast(intent);
    }

    private static void setPushMode(int i) {
        pushMode = i;
    }
}
