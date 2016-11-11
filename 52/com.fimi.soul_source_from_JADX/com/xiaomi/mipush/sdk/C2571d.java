package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.tencent.connect.common.Constants;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.channel.commonutils.string.C2476d;
import com.xiaomi.mipush.sdk.PushMessageHandler.C2563a;
import com.xiaomi.push.service.C2655k;
import com.xiaomi.push.service.C2662r;
import com.xiaomi.xmpush.thrift.C2729a;
import com.xiaomi.xmpush.thrift.C2732c;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2742h;
import com.xiaomi.xmpush.thrift.C2746j;
import com.xiaomi.xmpush.thrift.C2748k;
import com.xiaomi.xmpush.thrift.C2750l;
import com.xiaomi.xmpush.thrift.C2754n;
import com.xiaomi.xmpush.thrift.C2760q;
import com.xiaomi.xmpush.thrift.C2764s;
import com.xiaomi.xmpush.thrift.C2768u;
import com.xiaomi.xmpush.thrift.C2772w;
import com.xiaomi.xmpush.thrift.C2773x;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;
import org.p122a.p123a.C2915a;
import org.p122a.p137b.C2478b;
import org.p122a.p137b.C3258g;

/* renamed from: com.xiaomi.mipush.sdk.d */
public class C2571d {
    private static C2571d f12879a;
    private static Queue<String> f12880c;
    private static String f12881d;
    private static String f12882e;
    private static String f12883f;
    private static Object f12884g;
    private Context f12885b;

    /* renamed from: com.xiaomi.mipush.sdk.d.1 */
    /* synthetic */ class C25701 {
        static final /* synthetic */ int[] f12878a;

        static {
            f12878a = new int[C2729a.values().length];
            try {
                f12878a[C2729a.Registration.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f12878a[C2729a.UnRegistration.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f12878a[C2729a.SendMessage.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f12878a[C2729a.Subscription.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f12878a[C2729a.UnSubscription.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f12878a[C2729a.Command.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f12878a[C2729a.Notification.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    static {
        f12879a = null;
        f12881d = Constants.VIA_TO_TYPE_QQ_GROUP;
        f12882e = Constants.VIA_SSO_LOGIN;
        f12883f = Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP;
        f12884g = new Object();
    }

    private C2571d(Context context) {
        this.f12885b = context.getApplicationContext();
        if (this.f12885b == null) {
            this.f12885b = context;
        }
    }

    private C2563a m14645a(C2748k c2748k, boolean z, byte[] bArr) {
        Intent launchIntentForPackage;
        URISyntaxException e;
        Intent intent;
        MalformedURLException malformedURLException;
        C2563a c2563a = null;
        try {
            C2478b a = C2569c.m14641a(this.f12885b, c2748k);
            if (a == null) {
                C2463b.m14127c("receiving an un-recognized message. " + c2748k.f13740a);
                return null;
            }
            C2463b.m14126b("receive a message." + a);
            C2729a a2 = c2748k.m15561a();
            C2463b.m14123a("processing a message, action=" + a2);
            List list;
            switch (C25701.f12878a[a2.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C2754n c2754n = (C2754n) a;
                    if (c2754n.f13853f == 0) {
                        C2566a.m14615a(this.f12885b).m14624b(c2754n.f13855h, c2754n.f13856i);
                    }
                    if (TextUtils.isEmpty(c2754n.f13855h)) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(c2754n.f13855h);
                    }
                    c2563a = PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_REGISTER, list, c2754n.f13853f, c2754n.f13854g, null);
                    C2573e.m14652a(this.f12885b).m14671d();
                    return c2563a;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    if (((C2768u) a).f14081f == 0) {
                        C2566a.m14615a(this.f12885b).m14631h();
                        MiPushClient.clearExtras(this.f12885b);
                    }
                    PushMessageHandler.m14595a();
                    return null;
                case Type.BYTE /*3*/:
                    if (C2566a.m14615a(this.f12885b).m14635l()) {
                        C2463b.m14123a("receive a message in pause state. drop it");
                        return null;
                    }
                    C2760q c2760q = (C2760q) a;
                    C2732c l = c2760q.m15697l();
                    if (l == null) {
                        C2463b.m14127c("receive an empty message without push content, drop it");
                        return null;
                    }
                    if (z) {
                        MiPushClient.reportMessageClicked(this.f12885b, l.m15408b(), c2748k.m15587m());
                    }
                    if (!TextUtils.isEmpty(c2760q.m15695j()) && MiPushClient.aliasSetTime(this.f12885b, c2760q.m15695j()) < 0) {
                        MiPushClient.addAlias(this.f12885b, c2760q.m15695j());
                    } else if (!TextUtils.isEmpty(c2760q.m15693h()) && MiPushClient.topicSubscribedTime(this.f12885b, c2760q.m15693h()) < 0) {
                        MiPushClient.addTopic(this.f12885b, c2760q.m15693h());
                    }
                    String str = (c2748k.f13747h == null || c2748k.f13747h.m15457s() == null) ? null : (String) c2748k.f13747h.f13550j.get("jobkey");
                    if (TextUtils.isEmpty(str)) {
                        str = l.m15408b();
                    }
                    if (z || !C2571d.m14649a(this.f12885b, str)) {
                        Serializable generateMessage = PushMessageHelper.generateMessage(c2760q, c2748k.m15587m(), z);
                        if (generateMessage.getPassThrough() == 0 && !z && C2655k.m15050a(generateMessage.getExtra())) {
                            C2662r.m15078a(this.f12885b, c2748k, bArr);
                            return null;
                        }
                        C2463b.m14123a("receive a message, msgid=" + l.m15408b());
                        if (z && generateMessage.getExtra() != null && generateMessage.getExtra().containsKey("notify_effect")) {
                            Map extra = generateMessage.getExtra();
                            String str2 = (String) extra.get("notify_effect");
                            String str3 = "com.xiaomi.xmsf".equals(this.f12885b.getPackageName()) ? (String) extra.get("miui_package_name") : null;
                            if (f12881d.equals(str2)) {
                                try {
                                    PackageManager packageManager = this.f12885b.getPackageManager();
                                    if (TextUtils.isEmpty(str3)) {
                                        str3 = this.f12885b.getPackageName();
                                    }
                                    launchIntentForPackage = packageManager.getLaunchIntentForPackage(str3);
                                } catch (Exception e2) {
                                    C2463b.m14127c("Cause: " + e2.getMessage());
                                    launchIntentForPackage = null;
                                }
                            } else {
                                if (f12882e.equals(str2)) {
                                    if (extra.containsKey("intent_uri")) {
                                        str = (String) extra.get("intent_uri");
                                        if (str != null) {
                                            try {
                                                launchIntentForPackage = Intent.parseUri(str, 1);
                                                try {
                                                    if (TextUtils.isEmpty(str3)) {
                                                        str3 = this.f12885b.getPackageName();
                                                    }
                                                    launchIntentForPackage.setPackage(str3);
                                                } catch (URISyntaxException e3) {
                                                    e = e3;
                                                    C2463b.m14127c("Cause: " + e.getMessage());
                                                    if (launchIntentForPackage == null) {
                                                        return null;
                                                    }
                                                    if (!str2.equals(f12883f)) {
                                                        launchIntentForPackage.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                                    }
                                                    launchIntentForPackage.addFlags(268435456);
                                                    try {
                                                        if (this.f12885b.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                                                            return null;
                                                        }
                                                        this.f12885b.startActivity(launchIntentForPackage);
                                                        return null;
                                                    } catch (Exception e4) {
                                                        C2463b.m14127c("Cause: " + e4.getMessage());
                                                        return null;
                                                    }
                                                }
                                            } catch (URISyntaxException e5) {
                                                e = e5;
                                                launchIntentForPackage = null;
                                                C2463b.m14127c("Cause: " + e.getMessage());
                                                if (launchIntentForPackage == null) {
                                                    return null;
                                                }
                                                if (str2.equals(f12883f)) {
                                                    launchIntentForPackage.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                                }
                                                launchIntentForPackage.addFlags(268435456);
                                                if (this.f12885b.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                                                    return null;
                                                }
                                                this.f12885b.startActivity(launchIntentForPackage);
                                                return null;
                                            }
                                        }
                                        launchIntentForPackage = null;
                                    } else if (extra.containsKey("class_name")) {
                                        str = (String) extra.get("class_name");
                                        Intent intent2 = new Intent();
                                        if (TextUtils.isEmpty(str3)) {
                                            str3 = this.f12885b.getPackageName();
                                        }
                                        intent2.setComponent(new ComponentName(str3, str));
                                        try {
                                            if (extra.containsKey("intent_flag")) {
                                                intent2.setFlags(Integer.parseInt((String) extra.get("intent_flag")));
                                            }
                                        } catch (NumberFormatException e6) {
                                            C2463b.m14127c("Cause by intent_flag: " + e6.getMessage());
                                        }
                                        launchIntentForPackage = intent2;
                                    }
                                } else if (f12883f.equals(str2)) {
                                    str = (String) extra.get("web_uri");
                                    if (str != null) {
                                        str = str.trim().toLowerCase();
                                        str3 = (str.startsWith("http://") || str.startsWith("https://")) ? str : "http://" + str;
                                        try {
                                            str = new URL(str3).getProtocol();
                                            if (UriUtil.HTTP_SCHEME.equals(str) || UriUtil.HTTPS_SCHEME.equals(str)) {
                                                launchIntentForPackage = new Intent("android.intent.action.VIEW");
                                                try {
                                                    launchIntentForPackage.setData(Uri.parse(str3));
                                                } catch (MalformedURLException e7) {
                                                    MalformedURLException malformedURLException2 = e7;
                                                    intent = launchIntentForPackage;
                                                    malformedURLException = malformedURLException2;
                                                    C2463b.m14127c("Cause: " + malformedURLException.getMessage());
                                                    launchIntentForPackage = intent;
                                                    if (launchIntentForPackage == null) {
                                                        return null;
                                                    }
                                                    if (str2.equals(f12883f)) {
                                                        launchIntentForPackage.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                                    }
                                                    launchIntentForPackage.addFlags(268435456);
                                                    if (this.f12885b.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                                                        return null;
                                                    }
                                                    this.f12885b.startActivity(launchIntentForPackage);
                                                    return null;
                                                }
                                            }
                                            launchIntentForPackage = null;
                                        } catch (MalformedURLException e8) {
                                            malformedURLException = e8;
                                            Object obj = null;
                                            C2463b.m14127c("Cause: " + malformedURLException.getMessage());
                                            launchIntentForPackage = intent;
                                            if (launchIntentForPackage == null) {
                                                return null;
                                            }
                                            if (str2.equals(f12883f)) {
                                                launchIntentForPackage.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                                            }
                                            launchIntentForPackage.addFlags(268435456);
                                            if (this.f12885b.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                                                return null;
                                            }
                                            this.f12885b.startActivity(launchIntentForPackage);
                                            return null;
                                        }
                                    }
                                }
                                launchIntentForPackage = null;
                            }
                            if (launchIntentForPackage == null) {
                                return null;
                            }
                            if (str2.equals(f12883f)) {
                                launchIntentForPackage.putExtra(PushMessageHelper.KEY_MESSAGE, generateMessage);
                            }
                            launchIntentForPackage.addFlags(268435456);
                            if (this.f12885b.getPackageManager().resolveActivity(launchIntentForPackage, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                                return null;
                            }
                            this.f12885b.startActivity(launchIntentForPackage);
                            return null;
                        }
                        Serializable serializable = generateMessage;
                    } else {
                        C2463b.m14123a("drop a duplicate message, key=" + str);
                    }
                    if (c2748k.m15587m() != null || z) {
                        return c2563a;
                    }
                    m14648a(c2760q, c2748k.m15587m());
                    return c2563a;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    C2764s c2764s = (C2764s) a;
                    if (c2764s.f14012f == 0) {
                        MiPushClient.addTopic(this.f12885b, c2764s.m15735h());
                    }
                    if (TextUtils.isEmpty(c2764s.m15735h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(c2764s.m15735h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_SUBSCRIBE_TOPIC, list, c2764s.f14012f, c2764s.f14013g, c2764s.m15738k());
                case Type.INT /*5*/:
                    C2772w c2772w = (C2772w) a;
                    if (c2772w.f14143f == 0) {
                        MiPushClient.removeTopic(this.f12885b, c2772w.m15808h());
                    }
                    if (TextUtils.isEmpty(c2772w.m15808h())) {
                        list = null;
                    } else {
                        list = new ArrayList();
                        list.add(c2772w.m15808h());
                    }
                    return PushMessageHelper.generateCommandMessage(MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC, list, c2772w.f14143f, c2772w.f14144g, c2772w.m15811k());
                case Type.FLOAT /*6*/:
                    C2746j c2746j = (C2746j) a;
                    Object e9 = c2746j.m15549e();
                    list = c2746j.m15555k();
                    if (c2746j.f13712g == 0) {
                        if (TextUtils.equals(e9, MiPushClient.COMMAND_SET_ACCEPT_TIME) && list != null && list.size() > 1) {
                            MiPushClient.addAcceptTime(this.f12885b, (String) list.get(0), (String) list.get(1));
                            if ("00:00".equals(list.get(0)) && "00:00".equals(list.get(1))) {
                                C2566a.m14615a(this.f12885b).m14621a(true);
                            } else {
                                C2566a.m14615a(this.f12885b).m14621a(false);
                            }
                        } else if (TextUtils.equals(e9, MiPushClient.COMMAND_SET_ALIAS) && list != null && list.size() > 0) {
                            MiPushClient.addAlias(this.f12885b, (String) list.get(0));
                        } else if (TextUtils.equals(e9, MiPushClient.COMMAND_UNSET_ALIAS) && list != null && list.size() > 0) {
                            MiPushClient.removeAlias(this.f12885b, (String) list.get(0));
                        }
                    }
                    return PushMessageHelper.generateCommandMessage(e9, list, c2746j.f13712g, c2746j.f13713h, c2746j.m15557m());
                case Type.LONG /*7*/:
                    C2750l c2750l = (C2750l) a;
                    if ("registration id expired".equalsIgnoreCase(c2750l.f13779e)) {
                        MiPushClient.reInitialize(this.f12885b);
                        return null;
                    } else if (!"client_info_update_ok".equalsIgnoreCase(c2750l.f13779e) || c2750l.m15607h() == null || !c2750l.m15607h().containsKey("app_version")) {
                        return null;
                    } else {
                        C2566a.m14615a(this.f12885b).m14619a((String) c2750l.m15607h().get("app_version"));
                        return null;
                    }
                default:
                    return null;
            }
        } catch (Throwable e10) {
            C2463b.m14125a(e10);
            C2463b.m14127c("receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    public static C2571d m14646a(Context context) {
        if (f12879a == null) {
            f12879a = new C2571d(context);
        }
        return f12879a;
    }

    private void m14647a(C2748k c2748k) {
        C2734d m = c2748k.m15587m();
        C2478b c2742h = new C2742h();
        c2742h.m15505b(c2748k.m15582h());
        c2742h.m15499a(m.m15433b());
        c2742h.m15498a(m.m15440d());
        if (!TextUtils.isEmpty(m.m15444f())) {
            c2742h.m15508c(m.m15444f());
        }
        C2573e.m14652a(this.f12885b).m14667a(c2742h, C2729a.AckMessage, false, c2748k.m15587m());
    }

    private void m14648a(C2760q c2760q, C2734d c2734d) {
        C2478b c2742h = new C2742h();
        c2742h.m15505b(c2760q.m15690e());
        c2742h.m15499a(c2760q.m15688c());
        c2742h.m15498a(c2760q.m15697l().m15415g());
        if (!TextUtils.isEmpty(c2760q.m15693h())) {
            c2742h.m15508c(c2760q.m15693h());
        }
        if (!TextUtils.isEmpty(c2760q.m15695j())) {
            c2742h.m15510d(c2760q.m15695j());
        }
        C2573e.m14652a(this.f12885b).m14665a(c2742h, C2729a.AckMessage, c2734d);
    }

    private static boolean m14649a(Context context, String str) {
        boolean z = false;
        synchronized (f12884g) {
            SharedPreferences j = C2566a.m14615a(context).m14633j();
            if (f12880c == null) {
                String[] split = j.getString("pref_msg_ids", C2915a.f14760f).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                f12880c = new LinkedList();
                for (Object add : split) {
                    f12880c.add(add);
                }
            }
            if (f12880c.contains(str)) {
                z = true;
            } else {
                f12880c.add(str);
                if (f12880c.size() > 10) {
                    f12880c.poll();
                }
                String a = C2476d.m14167a(f12880c, MiPushClient.ACCEPT_TIME_SEPARATOR);
                Editor edit = j.edit();
                edit.putString("pref_msg_ids", a);
                edit.commit();
            }
        }
        return z;
    }

    public C2563a m14650a(Intent intent) {
        C2563a c2563a = null;
        String action = intent.getAction();
        C2463b.m14123a("receive an intent from server, action=" + action);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                C2463b.m14127c("receiving an empty message, drop");
            } else {
                C2748k c2748k = new C2748k();
                try {
                    C2773x.m15814a(c2748k, byteArrayExtra);
                    C2566a a = C2566a.m14615a(this.f12885b);
                    if (!(c2748k.m15561a() != C2729a.SendMessage || c2748k.m15587m() == null || a.m14635l() || booleanExtra)) {
                        m14647a(c2748k);
                    }
                    if (!a.m14632i() && c2748k.f13740a != C2729a.Registration) {
                        C2463b.m14127c("receive message without registration. need unregister or re-register!");
                    } else if (!a.m14632i() || !a.m14637n()) {
                        c2563a = m14645a(c2748k, booleanExtra, byteArrayExtra);
                    } else if (c2748k.f13740a == C2729a.UnRegistration) {
                        a.m14631h();
                        MiPushClient.clearExtras(this.f12885b);
                        PushMessageHandler.m14595a();
                    } else {
                        MiPushClient.unregisterPush(this.f12885b);
                    }
                } catch (Throwable e) {
                    C2463b.m14125a(e);
                } catch (Throwable e2) {
                    C2463b.m14125a(e2);
                }
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            c2563a = new MiPushCommandMessage();
            Object c2748k2 = new C2748k();
            try {
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    C2773x.m15814a(c2748k2, byteArrayExtra2);
                }
            } catch (C3258g e3) {
            }
            c2563a.setCommand(String.valueOf(c2748k2.m15561a()));
            c2563a.setResultCode((long) intent.getIntExtra("mipush_error_code", 0));
            c2563a.setReason(intent.getStringExtra("mipush_error_msg"));
            C2463b.m14127c("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
        }
        return c2563a;
    }
}
