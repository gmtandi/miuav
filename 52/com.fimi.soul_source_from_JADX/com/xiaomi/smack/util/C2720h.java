package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.xiaomi.channel.commonutils.misc.C2467b;
import com.xiaomi.market.sdk.C2539l;
import com.xiaomi.push.providers.C2628a;
import com.xiaomi.push.service.XMPushService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.smack.util.h */
public class C2720h {
    private static C2467b f13437a;
    private static int f13438b;
    private static final Object f13439c;
    private static List<C2719a> f13440d;
    private static String f13441e;
    private static C2628a f13442f;

    /* renamed from: com.xiaomi.smack.util.h.a */
    class C2719a {
        public String f13431a;
        public long f13432b;
        public int f13433c;
        public int f13434d;
        public String f13435e;
        public long f13436f;

        public C2719a(String str, long j, int i, int i2, String str2, long j2) {
            this.f13431a = C2915a.f14760f;
            this.f13432b = 0;
            this.f13433c = -1;
            this.f13434d = -1;
            this.f13435e = C2915a.f14760f;
            this.f13436f = 0;
            this.f13431a = str;
            this.f13432b = j;
            this.f13433c = i;
            this.f13434d = i2;
            this.f13435e = str2;
            this.f13436f = j2;
        }

        public boolean m15364a(C2719a c2719a) {
            return TextUtils.equals(c2719a.f13431a, this.f13431a) && TextUtils.equals(c2719a.f13435e, this.f13435e) && c2719a.f13433c == this.f13433c && c2719a.f13434d == this.f13434d && Math.abs(c2719a.f13432b - this.f13432b) <= 5000;
        }
    }

    static {
        f13437a = new C2467b(true);
        f13438b = -1;
        f13439c = new Object();
        f13440d = Collections.synchronizedList(new ArrayList());
        f13441e = C2915a.f14760f;
        f13442f = null;
    }

    private static int m15365a(Context context) {
        if (f13438b == -1) {
            f13438b = C2720h.m15371b(context);
        }
        return f13438b;
    }

    public static int m15366a(String str) {
        try {
            return str.getBytes(C1142e.f5201a).length;
        } catch (UnsupportedEncodingException e) {
            return str.getBytes().length;
        }
    }

    public static void m15369a(XMPushService xMPushService, String str, long j, boolean z, long j2) {
        if (xMPushService != null && !TextUtils.isEmpty(str) && "com.xiaomi.xmsf".equals(xMPushService.getPackageName()) && !"com.xiaomi.xmsf".equals(str)) {
            int a = C2720h.m15365a((Context) xMPushService);
            if (-1 != a) {
                boolean isEmpty;
                synchronized (f13439c) {
                    isEmpty = f13440d.isEmpty();
                    C2720h.m15370a(new C2719a(str, j2, a, z ? 1 : 0, a == 0 ? C2720h.m15374c(xMPushService) : C2915a.f14760f, j));
                }
                if (isEmpty) {
                    f13437a.m14142a(new C2721i(xMPushService), 5000);
                }
            }
        }
    }

    private static void m15370a(C2719a c2719a) {
        for (C2719a c2719a2 : f13440d) {
            if (c2719a2.m15364a(c2719a)) {
                c2719a2.f13436f += c2719a.f13436f;
                return;
            }
        }
        f13440d.add(c2719a);
    }

    private static int m15371b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo == null ? -1 : activeNetworkInfo.getType();
            } catch (Exception e) {
                return -1;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    private static void m15373b(Context context, List<C2719a> list) {
        synchronized (C2628a.f13057a) {
            SQLiteDatabase writableDatabase = C2720h.m15375d(context).getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                for (C2719a c2719a : list) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(C2539l.PACKAGE_NAME, c2719a.f13431a);
                    contentValues.put("message_ts", Long.valueOf(c2719a.f13432b));
                    contentValues.put("network_type", Integer.valueOf(c2719a.f13433c));
                    contentValues.put("bytes", Long.valueOf(c2719a.f13436f));
                    contentValues.put("rcv", Integer.valueOf(c2719a.f13434d));
                    contentValues.put("imsi", c2719a.f13435e);
                    writableDatabase.insert("traffic", null, contentValues);
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
            }
        }
    }

    private static synchronized String m15374c(Context context) {
        String str;
        synchronized (C2720h.class) {
            if (TextUtils.isEmpty(f13441e)) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        f13441e = telephonyManager.getSubscriberId();
                    }
                } catch (Exception e) {
                }
                str = f13441e;
            } else {
                str = f13441e;
            }
        }
        return str;
    }

    private static C2628a m15375d(Context context) {
        if (f13442f != null) {
            return f13442f;
        }
        f13442f = new C2628a(context);
        return f13442f;
    }
}
