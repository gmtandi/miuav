package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import com.xiaomi.channel.commonutils.logger.C2463b;
import com.xiaomi.xmpush.thrift.C2734d;
import com.xiaomi.xmpush.thrift.C2748k;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.service.r */
public class C2662r {
    public static long f13168a;
    private static LinkedList<Pair<Integer, String>> f13169b;

    static {
        f13168a = 0;
        f13169b = new LinkedList();
    }

    private static int m15071a(Context context, String str, String str2) {
        return str.equals(context.getPackageName()) ? context.getResources().getIdentifier(str2, "drawable", str) : 0;
    }

    private static Notification m15072a(Notification notification, String str) {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[]{str});
        } catch (Throwable e) {
            C2463b.m14125a(e);
        }
        return notification;
    }

    @SuppressLint({"NewApi"})
    private static Notification m15073a(Context context, C2748k c2748k, byte[] bArr, RemoteViews remoteViews) {
        C2734d m = c2748k.m15587m();
        Builder builder = new Builder(context);
        String[] a = C2662r.m15081a(context, m);
        builder.setContentTitle(a[0]);
        builder.setContentText(a[1]);
        if (remoteViews != null) {
            builder.setContent(remoteViews);
        } else if (VERSION.SDK_INT >= 16) {
            builder.setStyle(new BigTextStyle().bigText(a[1]));
        }
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(C2662r.m15074a(context, c2748k, m, bArr));
        int a2 = C2662r.m15071a(context, C2662r.m15077a(c2748k), "mipush_notification");
        int a3 = C2662r.m15071a(context, C2662r.m15077a(c2748k), "mipush_small_notification");
        if (a2 <= 0 || a3 <= 0) {
            builder.setSmallIcon(C2662r.m15088f(context, C2662r.m15077a(c2748k)));
        } else {
            builder.setLargeIcon(C2662r.m15075a(context, a2));
            builder.setSmallIcon(a3);
        }
        builder.setAutoCancel(true);
        long currentTimeMillis = System.currentTimeMillis();
        Map s = m.m15457s();
        if (s != null && s.containsKey("ticker")) {
            builder.setTicker((CharSequence) s.get("ticker"));
        }
        if (currentTimeMillis - f13168a > 10000) {
            f13168a = currentTimeMillis;
            int c = C2662r.m15087e(context, C2662r.m15077a(c2748k)) ? C2662r.m15085c(context, C2662r.m15077a(c2748k)) : m.f13546f;
            builder.setDefaults(c);
            if (!(s == null || (c & 1) == 0)) {
                String str = (String) s.get("sound_uri");
                if (!TextUtils.isEmpty(str) && str.startsWith("android.resource://" + C2662r.m15077a(c2748k))) {
                    builder.setDefaults(c ^ 1);
                    builder.setSound(Uri.parse(str));
                }
            }
        }
        return builder.getNotification();
    }

    private static PendingIntent m15074a(Context context, C2748k c2748k, C2734d c2734d, byte[] bArr) {
        if (c2734d == null || TextUtils.isEmpty(c2734d.f13547g)) {
            Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.setComponent(new ComponentName(c2748k.f13745f, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(c2734d.m15455q()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        }
        intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(c2734d.f13547g));
        intent.addFlags(268435456);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }

    private static Bitmap m15075a(Context context, int i) {
        return C2662r.m15076a(context.getResources().getDrawable(i));
    }

    public static Bitmap m15076a(Drawable drawable) {
        int i = 1;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    static String m15077a(C2748k c2748k) {
        if ("com.xiaomi.xmsf".equals(c2748k.f13745f)) {
            C2734d m = c2748k.m15587m();
            if (!(m == null || m.m15457s() == null)) {
                String str = (String) m.m15457s().get("miui_package_name");
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
        }
        return c2748k.f13745f;
    }

    public static void m15078a(Context context, C2748k c2748k, byte[] bArr) {
        Notification a;
        int c;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        C2734d m = c2748k.m15587m();
        RemoteViews b = C2662r.m15082b(context, c2748k, bArr);
        if (VERSION.SDK_INT >= 11) {
            a = C2662r.m15073a(context, c2748k, bArr, b);
        } else {
            Notification notification = new Notification(C2662r.m15088f(context, C2662r.m15077a(c2748k)), null, System.currentTimeMillis());
            String[] a2 = C2662r.m15081a(context, m);
            notification.setLatestEventInfo(context, a2[0], a2[1], C2662r.m15074a(context, c2748k, m, bArr));
            Map s = m.m15457s();
            if (s != null && s.containsKey("ticker")) {
                notification.tickerText = (CharSequence) s.get("ticker");
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - f13168a > 10000) {
                f13168a = currentTimeMillis;
                int i = m.f13546f;
                if (C2662r.m15087e(context, C2662r.m15077a(c2748k))) {
                    c = C2662r.m15085c(context, C2662r.m15077a(c2748k));
                } else {
                    c = i;
                }
                notification.defaults = c;
                if (!(s == null || (c & 1) == 0)) {
                    String str = (String) s.get("sound_uri");
                    if (!TextUtils.isEmpty(str) && str.startsWith("android.resource://" + C2662r.m15077a(c2748k))) {
                        notification.defaults = c ^ 1;
                        notification.sound = Uri.parse(str);
                    }
                }
            }
            notification.flags |= 16;
            if (b != null) {
                notification.contentView = b;
            }
            a = notification;
        }
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            C2662r.m15072a(a, C2662r.m15077a(c2748k));
        }
        c = m.m15455q() + ((C2662r.m15077a(c2748k).hashCode() / 10) * 10);
        notificationManager.notify(c, a);
        Pair pair = new Pair(Integer.valueOf(c), C2662r.m15077a(c2748k));
        synchronized (f13169b) {
            f13169b.add(pair);
            if (f13169b.size() > 100) {
                f13169b.remove();
            }
        }
    }

    public static void m15079a(Context context, String str, int i) {
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        ((NotificationManager) context.getSystemService("notification")).cancel(hashCode);
        synchronized (f13169b) {
            Iterator it = f13169b.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (hashCode == ((Integer) pair.first).intValue() && TextUtils.equals(str, (CharSequence) pair.second)) {
                    f13169b.remove(pair);
                    break;
                }
            }
        }
    }

    public static boolean m15080a(Context context, String str) {
        try {
            List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
            return (runningTasks == null || runningTasks.isEmpty()) ? false : ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName().equals(str);
        } catch (Throwable e) {
            C2463b.m14125a(e);
            return false;
        }
    }

    private static String[] m15081a(Context context, C2734d c2734d) {
        String h = c2734d.m15446h();
        String j = c2734d.m15448j();
        Map s = c2734d.m15457s();
        if (s != null) {
            int intValue = Float.valueOf((((float) context.getResources().getDisplayMetrics().widthPixels) / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            String str;
            if (intValue <= 320) {
                str = (String) s.get("title_short");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                CharSequence charSequence = (String) s.get("description_short");
                if (TextUtils.isEmpty(charSequence)) {
                    Object obj = j;
                }
                j = charSequence;
            } else if (intValue > 360) {
                str = (String) s.get("title_long");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                str = (String) s.get("description_long");
                if (!TextUtils.isEmpty(str)) {
                    j = str;
                }
            }
        }
        return new String[]{h, j};
    }

    private static RemoteViews m15082b(Context context, C2748k c2748k, byte[] bArr) {
        C2734d m = c2748k.m15587m();
        String a = C2662r.m15077a(c2748k);
        Map s = m.m15457s();
        if (s == null) {
            return null;
        }
        String str = (String) s.get("layout_name");
        String str2 = (String) s.get("layout_value");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(a);
            int identifier = resourcesForApplication.getIdentifier(str, "layout", a);
            if (identifier == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(a, identifier);
            try {
                JSONObject jSONObject;
                Iterator keys;
                JSONObject jSONObject2 = new JSONObject(str2);
                if (jSONObject2.has("text")) {
                    jSONObject = jSONObject2.getJSONObject("text");
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        CharSequence string = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, LocaleUtil.INDONESIAN, a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, string);
                        }
                    }
                }
                if (jSONObject2.has("image")) {
                    jSONObject = jSONObject2.getJSONObject("image");
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        String string2 = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, LocaleUtil.INDONESIAN, a);
                        int identifier2 = resourcesForApplication.getIdentifier(string2, "drawable", a);
                        if (identifier > 0) {
                            remoteViews.setImageViewResource(identifier, identifier2);
                        }
                    }
                }
                if (jSONObject2.has("time")) {
                    jSONObject2 = jSONObject2.getJSONObject("time");
                    keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        str2 = jSONObject2.getString(str);
                        if (str2.length() == 0) {
                            str2 = "yy-MM-dd hh:mm";
                        }
                        identifier = resourcesForApplication.getIdentifier(str, LocaleUtil.INDONESIAN, a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, new SimpleDateFormat(str2).format(new Date(System.currentTimeMillis())));
                        }
                    }
                }
                return remoteViews;
            } catch (Throwable e) {
                C2463b.m14125a(e);
                return null;
            }
        } catch (Throwable e2) {
            C2463b.m14125a(e2);
            return null;
        }
    }

    public static void m15083b(Context context, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        synchronized (f13169b) {
            Iterator it = ((LinkedList) f13169b.clone()).iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (TextUtils.equals((CharSequence) pair.second, str)) {
                    notificationManager.cancel(((Integer) pair.first).intValue());
                    f13169b.remove(pair);
                }
            }
        }
    }

    static void m15084b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    static int m15085c(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    static void m15086d(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    static boolean m15087e(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    private static int m15088f(Context context, String str) {
        int a = C2662r.m15071a(context, str, "mipush_notification");
        int a2 = C2662r.m15071a(context, str, "mipush_small_notification");
        if (a <= 0) {
            a = a2 > 0 ? a2 : context.getApplicationInfo().icon;
        }
        return (a != 0 || VERSION.SDK_INT < 9) ? a : context.getApplicationInfo().logo;
    }
}
