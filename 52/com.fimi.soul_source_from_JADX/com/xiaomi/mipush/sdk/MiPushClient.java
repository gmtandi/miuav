package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.misc.C2464a;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.push.service.C2662r;
import com.xiaomi.push.service.C2671x;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import com.xiaomi.push.service.receivers.PingReceiver;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2744i;
import com.xiaomi.xmpush.thrift.C2750l;
import com.xiaomi.xmpush.thrift.C2752m;
import com.xiaomi.xmpush.thrift.C2762r;
import com.xiaomi.xmpush.thrift.C2766t;
import com.xiaomi.xmpush.thrift.C2770v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C2478b;

public abstract class MiPushClient {
    public static final String ACCEPT_TIME_SEPARATOR = ",";
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static String COMMAND_SUBSCRIBE_TOPIC = null;
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static String COMMAND_UNSUBSCRIBE_TOPIC;
    private static boolean awakeService;
    private static Context sContext;
    private static long sCurMsgId;

    @Deprecated
    public abstract class MiPushClientCallback {
        private String category;

        protected String getCategory() {
            return this.category;
        }

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }

        protected void setCategory(String str) {
            this.category = str;
        }
    }

    /* renamed from: com.xiaomi.mipush.sdk.MiPushClient.a */
    public class C2562a extends Exception {
        private PackageItemInfo f12859a;

        public C2562a(String str, PackageItemInfo packageItemInfo) {
            super(str);
            this.f12859a = packageItemInfo;
        }
    }

    static {
        COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
        COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
        awakeService = true;
        if (C2464a.f12421b || C2464a.f12424e || C2464a.f12422c || C2464a.f12426g) {
            C2463b.m14117a(0);
        }
        sCurMsgId = System.currentTimeMillis();
    }

    private static boolean acceptTimeSet(Context context, String str, String str2) {
        return TextUtils.equals(context.getSharedPreferences("mipush_extra", 0).getString("accept_time", C2915a.f14760f), str + ACCEPT_TIME_SEPARATOR + str2);
    }

    static synchronized void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putString("accept_time", str + ACCEPT_TIME_SEPARATOR + str2).commit();
        }
    }

    static synchronized void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    static synchronized void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    public static long aliasSetTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("alias_" + str, -1);
    }

    private static void awakePushServices(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        if (System.currentTimeMillis() - MiStatInterface.MAX_UPLOAD_INTERVAL >= sharedPreferences.getLong("wake_up", 0)) {
            sharedPreferences.edit().putLong("wake_up", System.currentTimeMillis()).commit();
            new Thread(new C2567b(context)).start();
        }
    }

    public static void checkManifest(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4100);
            checkReceivers(context);
            checkServices(context, packageInfo);
            checkPermissions(context, packageInfo);
        } catch (Throwable e) {
            C2463b.m14125a(e);
        }
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("param " + str + " is not nullable");
        }
    }

    private static void checkPermissions(Context context, PackageInfo packageInfo) {
        int i;
        Set hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(new String[]{"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", context.getPackageName() + ".permission.MIPUSH_RECEIVE", "android.permission.ACCESS_WIFI_STATE", "android.permission.READ_PHONE_STATE", "android.permission.GET_TASKS", "android.permission.VIBRATE"}));
        if (packageInfo.permissions != null) {
            for (PermissionInfo permissionInfo : packageInfo.permissions) {
                if (r4.equals(permissionInfo.name)) {
                    i = 1;
                    break;
                }
            }
        }
        i = 0;
        if (i == 0) {
            throw new C2562a(String.format("<permission android:name=\"%1$s\" /> is undefined.", new Object[]{r4}), null);
        }
        if (packageInfo.requestedPermissions != null) {
            for (CharSequence charSequence : packageInfo.requestedPermissions) {
                if (!TextUtils.isEmpty(charSequence) && hashSet.contains(charSequence)) {
                    hashSet.remove(charSequence);
                    if (hashSet.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!hashSet.isEmpty()) {
            throw new C2562a(String.format("<use-permission android:name=\"%1$s\" /> is missing.", new Object[]{hashSet.iterator().next()}), null);
        }
    }

    private static void checkReceivers(Context context) {
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent intent = new Intent(NetworkStateReceiver.f9876a);
        intent.setPackage(packageName);
        findAndCheckReceiverInfo(packageManager, intent, NetworkStatusReceiver.class, new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(true)});
        intent = new Intent(C2671x.f13213l);
        intent.setPackage(packageName);
        findAndCheckReceiverInfo(packageManager, intent, PingReceiver.class, new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(packageName);
        boolean z2 = false;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, Opcodes.ACC_ENUM)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null) {
                try {
                    if (!TextUtils.isEmpty(activityInfo.name) && PushMessageReceiver.class.isAssignableFrom(Class.forName(activityInfo.name)) && activityInfo.enabled) {
                        z = true;
                        if (z) {
                            break;
                        }
                        z2 = z;
                    }
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                    z = z2;
                }
            }
            z = false;
            if (z) {
                break;
            }
            z2 = z;
        }
        z = z2;
        if (!z) {
            throw new C2562a("Receiver: none of the subclasses of PushMessageReceiver is enabled or defined.", null);
        }
    }

    private static void checkServices(Context context, PackageInfo packageInfo) {
        Map hashMap = new HashMap();
        hashMap.put(XMPushService.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        hashMap.put(PushMessageHandler.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(true)});
        hashMap.put(MessageHandleService.class.getCanonicalName(), new Boolean[]{Boolean.valueOf(true), Boolean.valueOf(false)});
        if (packageInfo.services != null) {
            for (PackageItemInfo packageItemInfo : packageInfo.services) {
                if (!TextUtils.isEmpty(packageItemInfo.name) && hashMap.containsKey(packageItemInfo.name)) {
                    Boolean[] boolArr = (Boolean[]) hashMap.remove(packageItemInfo.name);
                    if (boolArr[0].booleanValue() != packageItemInfo.enabled) {
                        throw new C2562a(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{packageItemInfo.name, boolArr[0]}), packageItemInfo);
                    } else if (boolArr[1].booleanValue() != packageItemInfo.exported) {
                        throw new C2562a(String.format("Wrong attribute: %n    <service android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{packageItemInfo.name, boolArr[1]}), packageItemInfo);
                    } else if (hashMap.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!hashMap.isEmpty()) {
            throw new C2562a(String.format("<service android:name=\"%1$s\" /> is missing or disabled.", new Object[]{hashMap.keySet().iterator().next()}), null);
        }
    }

    protected static void clearExtras(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("wake_up", 0);
        Editor edit = sharedPreferences.edit();
        edit.clear();
        if (j > 0) {
            edit.putLong("wake_up", j);
        }
        edit.commit();
    }

    public static void clearLocalNotificationType(Context context) {
        C2573e.m14652a(context).m14672e();
    }

    public static void clearNotification(Context context) {
        if (shouldUseMIUIPush(context)) {
            C2573e.m14652a(context).m14662a(-1);
        } else {
            C2662r.m15083b(context, context.getPackageName());
        }
    }

    public static void clearNotification(Context context, int i) {
        if (shouldUseMIUIPush(context)) {
            C2573e.m14652a(context).m14662a(i);
        } else {
            C2662r.m15079a(context, context.getPackageName(), i);
        }
    }

    private static void findAndCheckReceiverInfo(PackageManager packageManager, Intent intent, Class<?> cls, Boolean[] boolArr) {
        int i;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, Opcodes.ACC_ENUM)) {
            PackageItemInfo packageItemInfo = resolveInfo.activityInfo;
            if (packageItemInfo != null && cls.getCanonicalName().equals(packageItemInfo.name)) {
                if (boolArr[0].booleanValue() != packageItemInfo.enabled) {
                    throw new C2562a(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:enabled should be %<b.", new Object[]{packageItemInfo.name, boolArr[0]}), packageItemInfo);
                } else if (boolArr[1].booleanValue() != packageItemInfo.exported) {
                    throw new C2562a(String.format("Wrong attribute: %n    <receiver android:name=\"%1$s\" .../> android:exported should be %<b.", new Object[]{packageItemInfo.name, boolArr[1]}), packageItemInfo);
                } else {
                    i = 1;
                    if (i == 0) {
                        throw new C2562a(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
                    }
                }
            }
        }
        i = 0;
        if (i == 0) {
            throw new C2562a(String.format("<receiver android:name=\"%1$s\" /> is missing or disabled.", new Object[]{cls.getCanonicalName()}), null);
        }
    }

    protected static synchronized String generatePacketID() {
        String str;
        synchronized (MiPushClient.class) {
            str = C2476d.m14165a(4) + sCurMsgId;
            sCurMsgId++;
        }
        return str;
    }

    public static List<String> getAllAlias(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring("alias_".length()));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        List<String> arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_")) {
                arrayList.add(str.substring("topic_".length()));
            }
        }
        return arrayList;
    }

    public static String getRegId(Context context) {
        return C2566a.m14615a(context).m14632i() ? C2566a.m14615a(context).m14628e() : null;
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        boolean z = false;
        checkNotNull(context, "context");
        checkNotNull(str, "appID");
        checkNotNull(str2, "appToken");
        try {
            sContext = context.getApplicationContext();
            if (sContext == null) {
                sContext = context;
            }
            if (miPushClientCallback != null) {
                PushMessageHandler.m14601a(miPushClientCallback);
            }
            if (C2566a.m14615a(sContext).m14636m() != Constants.m14592a()) {
                z = true;
            }
            if (z || !C2566a.m14615a(sContext).m14623a(str, str2) || C2566a.m14615a(sContext).m14637n()) {
                String a = C2476d.m14165a(6);
                C2566a.m14615a(sContext).m14631h();
                C2566a.m14615a(sContext).m14618a(Constants.m14592a());
                C2566a.m14615a(sContext).m14620a(str, str2, a);
                clearExtras(sContext);
                C2752m c2752m = new C2752m();
                c2752m.m15613a(generatePacketID());
                c2752m.m15618b(str);
                c2752m.m15625e(str2);
                c2752m.m15623d(context.getPackageName());
                c2752m.m15627f(a);
                c2752m.m15621c(C2566a.m14616a(context, context.getPackageName()));
                C2573e.m14652a(sContext).m14663a(c2752m, z);
            } else {
                if (1 == PushMessageHelper.getPushMode(context)) {
                    checkNotNull(miPushClientCallback, "callback");
                    miPushClientCallback.onInitializeResult(0, null, C2566a.m14615a(context).m14628e());
                } else {
                    List arrayList = new ArrayList();
                    arrayList.add(C2566a.m14615a(context).m14628e());
                    PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(COMMAND_REGISTER, arrayList, 0, null, null));
                }
                C2573e.m14652a(context).m14661a();
                if (C2566a.m14615a(sContext).m14622a()) {
                    C2478b c2750l = new C2750l();
                    c2750l.m15597b(C2566a.m14615a(context).m14626c());
                    c2750l.m15601c("client_info_update");
                    c2750l.m15591a(generatePacketID());
                    c2750l.f13782h = new HashMap();
                    c2750l.f13782h.put("app_version", C2566a.m14616a(sContext, sContext.getPackageName()));
                    CharSequence g = C2566a.m14615a(sContext).m14630g();
                    if (!TextUtils.isEmpty(g)) {
                        c2750l.f13782h.put("deviceid", g);
                    }
                    C2573e.m14652a(context).m14667a(c2750l, C2729a.Notification, false, null);
                }
            }
            if (awakeService) {
                awakePushServices(sContext);
            }
        } catch (Throwable th) {
            C2463b.m14125a(th);
        }
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    static void reInitialize(Context context) {
        if (C2566a.m14615a(context).m14632i()) {
            String a = C2476d.m14165a(6);
            String c = C2566a.m14615a(context).m14626c();
            String d = C2566a.m14615a(context).m14627d();
            C2566a.m14615a(context).m14631h();
            C2566a.m14615a(context).m14620a(c, d, a);
            C2752m c2752m = new C2752m();
            c2752m.m15613a(generatePacketID());
            c2752m.m15618b(c);
            c2752m.m15625e(d);
            c2752m.m15627f(a);
            c2752m.m15623d(context.getPackageName());
            c2752m.m15621c(C2566a.m14616a(context, context.getPackageName()));
            C2573e.m14652a(context).m14663a(c2752m, false);
        }
    }

    public static void registerPush(Context context, String str, String str2) {
        initialize(context, str, str2, null);
    }

    static synchronized void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("alias_" + str).commit();
        }
    }

    static synchronized void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().remove("topic_" + str).commit();
        }
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        C2734d c2734d = new C2734d();
        c2734d.m15424a(miPushMessage.getMessageId());
        c2734d.m15432b(miPushMessage.getTopic());
        c2734d.m15441d(miPushMessage.getDescription());
        c2734d.m15437c(miPushMessage.getTitle());
        c2734d.m15436c(miPushMessage.getNotifyId());
        c2734d.m15423a(miPushMessage.getNotifyType());
        c2734d.m15431b(miPushMessage.getPassThrough());
        c2734d.m15425a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), c2734d);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        reportMessageClicked(context, str, null);
    }

    static void reportMessageClicked(Context context, String str, C2734d c2734d) {
        if (C2566a.m14615a(context).m14625b()) {
            C2478b c2750l = new C2750l();
            c2750l.m15597b(C2566a.m14615a(context).m14626c());
            c2750l.m15601c("bar:click");
            c2750l.m15591a(str);
            c2750l.m15592a(false);
            C2573e.m14652a(context).m14667a(c2750l, C2729a.Notification, false, c2734d);
        }
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 23, 59, str);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = (long) (((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER) / 60);
        long j = ((((long) ((i * 60) + i2)) + rawOffset) + 1440) % 1440;
        rawOffset = ((rawOffset + ((long) ((i3 * 60) + i4))) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(j / 60), Long.valueOf(j % 60)}));
        arrayList.add(String.format("%1$02d:%2$02d", new Object[]{Long.valueOf(rawOffset / 60), Long.valueOf(rawOffset % 60)}));
        if (!acceptTimeSet(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            setCommand(context, COMMAND_SET_ACCEPT_TIME, arrayList, str);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.m14600a(context, str, COMMAND_SET_ACCEPT_TIME, 0, null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ACCEPT_TIME, arrayList, 0, null, null));
        }
    }

    public static void setAlias(Context context, String str, String str2) {
        setCommand(context, COMMAND_SET_ALIAS, str, str2);
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (!COMMAND_SET_ALIAS.equalsIgnoreCase(str) || System.currentTimeMillis() - aliasSetTime(context, str2) >= MiStatInterface.MAX_UPLOAD_INTERVAL) {
            if (!COMMAND_UNSET_ALIAS.equalsIgnoreCase(str) || aliasSetTime(context, str2) >= 0) {
                setCommand(context, str, arrayList, str3);
            } else {
                C2463b.m14123a("Don't cancel alias for " + arrayList + " is unseted");
            }
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.m14600a(context, str3, str, 0, null, arrayList);
        } else {
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SET_ALIAS, arrayList, 0, null, null));
        }
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (!TextUtils.isEmpty(C2566a.m14615a(context).m14626c())) {
            C2478b c2744i = new C2744i();
            c2744i.m15520a(generatePacketID());
            c2744i.m15525b(C2566a.m14615a(context).m14626c());
            c2744i.m15528c(str);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c2744i.m15530d((String) it.next());
            }
            c2744i.m15534f(str2);
            c2744i.m15532e(context.getPackageName());
            C2573e.m14652a(context).m14665a(c2744i, C2729a.Command, null);
        }
    }

    public static void setLocalNotificationType(Context context, int i) {
        C2573e.m14652a(context).m14668b(i & -1);
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return C2573e.m14652a(context).m14669b();
    }

    public static void subscribe(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(C2566a.m14615a(context).m14626c())) {
            if (System.currentTimeMillis() - topicSubscribedTime(context, str) > MiStatInterface.MAX_UPLOAD_INTERVAL) {
                C2478b c2762r = new C2762r();
                c2762r.m15705a(generatePacketID());
                c2762r.m15710b(C2566a.m14615a(context).m14626c());
                c2762r.m15713c(str);
                c2762r.m15715d(context.getPackageName());
                c2762r.m15717e(str2);
                C2573e.m14652a(context).m14665a(c2762r, C2729a.Subscription, null);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.m14599a(context, str2, 0, null, str);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(str);
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(COMMAND_SUBSCRIBE_TOPIC, arrayList, 0, null, null));
            }
        }
    }

    public static long topicSubscribedTime(Context context, String str) {
        return context.getSharedPreferences("mipush_extra", 0).getLong("topic_" + str, -1);
    }

    public static void unregisterPush(Context context) {
        if (C2566a.m14615a(context).m14625b()) {
            C2766t c2766t = new C2766t();
            c2766t.m15742a(generatePacketID());
            c2766t.m15747b(C2566a.m14615a(context).m14626c());
            c2766t.m15750c(C2566a.m14615a(context).m14628e());
            c2766t.m15754e(C2566a.m14615a(context).m14627d());
            c2766t.m15752d(context.getPackageName());
            C2573e.m14652a(context).m14664a(c2766t);
            PushMessageHandler.m14595a();
            C2566a.m14615a(context).m14634k();
            clearExtras(context);
        }
    }

    public static void unsetAlias(Context context, String str, String str2) {
        setCommand(context, COMMAND_UNSET_ALIAS, str, str2);
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (!C2566a.m14615a(context).m14625b()) {
            return;
        }
        if (topicSubscribedTime(context, str) < 0) {
            C2463b.m14123a("Don't cancel subscribe for " + str + " is unsubscribed");
            return;
        }
        C2478b c2770v = new C2770v();
        c2770v.m15778a(generatePacketID());
        c2770v.m15783b(C2566a.m14615a(context).m14626c());
        c2770v.m15786c(str);
        c2770v.m15788d(context.getPackageName());
        c2770v.m15790e(str2);
        C2573e.m14652a(context).m14665a(c2770v, C2729a.UnSubscription, null);
    }
}
