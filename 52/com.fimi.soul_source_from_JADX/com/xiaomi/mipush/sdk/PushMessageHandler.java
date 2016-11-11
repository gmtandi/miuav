package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.mipush.sdk.MiPushClient.MiPushClientCallback;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PushMessageHandler extends IntentService {
    private static List<MiPushClientCallback> f12860a;

    /* renamed from: com.xiaomi.mipush.sdk.PushMessageHandler.a */
    interface C2563a extends Serializable {
    }

    static {
        f12860a = new ArrayList();
    }

    public PushMessageHandler() {
        super("mipush message handler");
    }

    protected static void m14595a() {
        synchronized (f12860a) {
            f12860a.clear();
        }
    }

    public static void m14596a(long j, String str, String str2) {
        synchronized (f12860a) {
            for (MiPushClientCallback onInitializeResult : f12860a) {
                onInitializeResult.onInitializeResult(j, str, str2);
            }
        }
    }

    public static void m14597a(Context context, MiPushMessage miPushMessage) {
        synchronized (f12860a) {
            for (MiPushClientCallback miPushClientCallback : f12860a) {
                if (m14602a(miPushMessage.getCategory(), miPushClientCallback.getCategory())) {
                    miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    miPushClientCallback.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    public static void m14598a(Context context, C2563a c2563a) {
        String str = null;
        if (c2563a instanceof MiPushMessage) {
            m14597a(context, (MiPushMessage) c2563a);
        } else if (c2563a instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) c2563a;
            String command = miPushCommandMessage.getCommand();
            List commandArguments;
            if (MiPushClient.COMMAND_REGISTER.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                if (!(commandArguments == null || commandArguments.isEmpty())) {
                    str = (String) commandArguments.get(0);
                }
                m14596a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command) || MiPushClient.COMMAND_UNSET_ALIAS.equals(command) || MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
                m14600a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                m14599a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                m14603b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            }
        }
    }

    protected static void m14599a(Context context, String str, long j, String str2, String str3) {
        synchronized (f12860a) {
            for (MiPushClientCallback miPushClientCallback : f12860a) {
                if (m14602a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    protected static void m14600a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (f12860a) {
            for (MiPushClientCallback miPushClientCallback : f12860a) {
                if (m14602a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    protected static void m14601a(MiPushClientCallback miPushClientCallback) {
        synchronized (f12860a) {
            if (!f12860a.contains(miPushClientCallback)) {
                f12860a.add(miPushClientCallback);
            }
        }
    }

    protected static boolean m14602a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    protected static void m14603b(Context context, String str, long j, String str2, String str3) {
        synchronized (f12860a) {
            for (MiPushClientCallback miPushClientCallback : f12860a) {
                if (m14602a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    public static boolean m14604b() {
        return f12860a.isEmpty();
    }

    protected void onHandleIntent(Intent intent) {
        if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
            if (C2566a.m14615a((Context) this).m14632i()) {
                C2573e.m14652a((Context) this).m14661a();
            }
        } else if (1 != PushMessageHelper.getPushMode(this)) {
            Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent2.setPackage(getPackageName());
            intent2.putExtras(intent);
            sendBroadcast(intent2);
        } else if (m14604b()) {
            C2463b.m14127c("receive a message before application calling initialize");
        } else {
            C2563a a = C2571d.m14646a((Context) this).m14650a(intent);
            if (a != null) {
                m14598a((Context) this, a);
            }
        }
    }
}
