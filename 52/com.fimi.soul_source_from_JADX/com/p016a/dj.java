package com.p016a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.json.JSONObject;
import org.p122a.p123a.C2915a;

/* renamed from: com.a.dj */
public class dj {
    private static Context f879a;
    private static String f880b;
    private static String f881c;
    private static String f882d;
    private static String f883e;
    private static String f884f;
    private static String f885g;
    private static boolean f886h;
    private static long f887i;
    private static long f888j;
    private static boolean f889k;
    private static int f890l;
    private static boolean f891m;
    private static int f892n;
    private static boolean f893o;
    private static String f894p;
    private static String f895q;
    private static int f896r;
    private static long f897s;
    private static String f898t;
    private static int f899u;
    private static long f900v;
    private static String f901w;
    private static String f902x;
    private static String f903y;
    private static boolean f904z;

    static {
        f880b = "\u63d0\u793a\u4fe1\u606f";
        f881c = "\u786e\u8ba4";
        f882d = "\u53d6\u6d88";
        f883e = C2915a.f14760f;
        f884f = C2915a.f14760f;
        f885g = C2915a.f14760f;
        f886h = false;
        f887i = 0;
        f888j = 0;
        f889k = false;
        f890l = 0;
        f891m = false;
        f892n = 0;
        f893o = false;
        f894p = Constants.VIA_TO_TYPE_QQ_GROUP;
        f895q = Constants.VIA_TO_TYPE_QQ_GROUP;
        f896r = -1;
        f897s = 0;
        f898t = Constants.VIA_RESULT_SUCCESS;
        f899u = -1;
        f900v = 0;
        f903y = Constants.VIA_RESULT_SUCCESS;
        f904z = false;
    }

    private static String m1461a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        String str2 = C2915a.f14760f;
        try {
            return (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) ? str2 : jSONObject.optString(str);
        } catch (Throwable th) {
            ev.m1777a(th, "AuthUtil", "parse2String");
            return str2;
        }
    }

    public static synchronized void m1462a(String str) {
        synchronized (dj.class) {
            f903y = str;
        }
    }

    public static boolean m1463a() {
        return f886h;
    }

    public static synchronized boolean m1464a(Context context) {
        boolean z;
        synchronized (dj.class) {
            f879a = context;
            z = false;
            try {
                fr a = fq.m1860a(context, ev.m1772a("2.4.1"), "callamappro;fast;sdkupdate;sdkcoordinate;locate;opflag;exception;amappushflag");
                if (a != null) {
                    z = dj.m1465a(a);
                }
            } catch (Throwable th) {
                ev.m1777a(th, "AuthUtil", "getConfig");
            }
        }
        return z;
    }

    private static boolean m1465a(fr frVar) {
        JSONObject jSONObject = frVar.f1223b;
        if (jSONObject != null && jSONObject.has("opflag")) {
            f894p = jSONObject.getString("opflag");
        }
        jSONObject = frVar.f1224c;
        if (jSONObject != null) {
            if (jSONObject.has("callamapflag")) {
                f895q = jSONObject.getString("callamapflag");
            }
            if (jSONObject.has("count")) {
                f896r = jSONObject.getInt("count");
            }
            if (jSONObject.has("nowtime")) {
                f897s = jSONObject.getLong("nowtime");
            }
            if (!(f896r == -1 || f897s == 0)) {
                if (!dn.m1508a(f897s, dm.m1495b(f879a, "pref", "nowtime", 0))) {
                    dj.m1468b(f879a);
                }
            }
        }
        jSONObject = frVar.f1222a;
        if (jSONObject != null) {
            if (jSONObject.has("amappushflag")) {
                f898t = jSONObject.getString("amappushflag");
            }
            if (jSONObject.has("count")) {
                f899u = jSONObject.getInt("count");
            }
            if (jSONObject.has("nowtime")) {
                f900v = jSONObject.getLong("nowtime");
            }
            if (!(f899u == -1 || f900v == 0)) {
                if (!dn.m1508a(f900v, dm.m1495b(f879a, "pref", "pushSerTime", 0))) {
                    dj.m1470c(f879a);
                }
            }
        }
        jSONObject = frVar.f1227f;
        if (jSONObject != null) {
            if (jSONObject.has("f")) {
                f903y = dj.m1461a(jSONObject, "f");
                if (Constants.VIA_TO_TYPE_QQ_GROUP.equals(f903y)) {
                    long b = dm.m1495b(f879a, "abcd", "abc", 0);
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - b > Util.MILLSECONDS_OF_HOUR) {
                        dm.m1492a(f879a, "abcd", "abc", elapsedRealtime);
                    }
                    if (elapsedRealtime > b && elapsedRealtime - b < Util.MILLSECONDS_OF_HOUR) {
                        f903y = Constants.VIA_RESULT_SUCCESS;
                    }
                    if (elapsedRealtime < b) {
                        f903y = Constants.VIA_RESULT_SUCCESS;
                        dm.m1492a(f879a, "abcd", "abc", elapsedRealtime);
                    }
                } else {
                    f903y = Constants.VIA_RESULT_SUCCESS;
                }
            }
            if (jSONObject.has("a")) {
                f880b = dj.m1461a(jSONObject, "a");
            }
            if (jSONObject.has("o")) {
                f881c = dj.m1461a(jSONObject, "o");
            }
            if (jSONObject.has("c")) {
                f882d = dj.m1461a(jSONObject, "c");
            }
            if (jSONObject.has("i")) {
                f883e = dj.m1461a(jSONObject, "i");
            }
            if (jSONObject.has("u")) {
                f884f = dj.m1461a(jSONObject, "u");
            }
            if (jSONObject.has("t")) {
                f885g = dj.m1461a(jSONObject, "t");
            }
            if ((C2915a.f14760f.equals(f883e) || f883e == null) && (C2915a.f14760f.equals(f884f) || f884f == null)) {
                f903y = Constants.VIA_RESULT_SUCCESS;
            }
        }
        gd a = ev.m1772a("2.4.1");
        fu fuVar = frVar.f1229h;
        if (fuVar != null) {
            Object obj = fuVar.f1236b;
            Object obj2 = fuVar.f1235a;
            Object obj3 = fuVar.f1237c;
            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj3)) {
                new ai(f879a, null, a).m1024a();
            } else {
                new ai(f879a, new aj(obj2, obj, obj3), a).m1024a();
            }
        } else {
            new ai(f879a, null, a).m1024a();
        }
        ft ftVar = frVar.f1230i;
        if (ftVar != null) {
            f901w = ftVar.f1233a;
            f902x = ftVar.f1234b;
            if (!(TextUtils.isEmpty(f901w) || TextUtils.isEmpty(f902x))) {
                new gb(f879a, "loc", f901w, f902x).m1930a();
            }
        }
        fs fsVar = frVar.f1228g;
        if (fsVar != null) {
            boolean z = fsVar.f1231a;
            gd a2 = ev.m1772a("2.4.1");
            a2.m1939a(z);
            C0248h.m1976a(f879a, a2);
        }
        JSONObject jSONObject2 = frVar.f1225d;
        if (jSONObject2 == null) {
            return true;
        }
        dk b2 = dj.m1467b(jSONObject2, "fs");
        if (b2 != null) {
            f889k = b2.f907c.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
            try {
                f890l = Integer.parseInt(b2.f906b);
            } catch (Throwable th) {
                ev.m1777a(th, "AuthUtil", "loadconfig");
                f903y = Constants.VIA_RESULT_SUCCESS;
                return false;
            }
        }
        b2 = dj.m1467b(jSONObject2, "us");
        if (b2 != null) {
            f891m = b2.f907c.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
            f893o = !b2.f905a.equals(Constants.VIA_RESULT_SUCCESS);
            try {
                f892n = Integer.parseInt(b2.f906b);
            } catch (Throwable th2) {
                ev.m1777a(th2, "AuthUtil", "loadconfig part1");
            }
        }
        dk b3 = dj.m1467b(jSONObject2, "rs");
        if (b3 == null) {
            return true;
        }
        f886h = b3.f907c.equals(Constants.VIA_TO_TYPE_QQ_GROUP);
        if (f886h) {
            f888j = dn.m1519b();
        }
        try {
            f887i = (long) (Integer.parseInt(b3.f906b) * XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            return true;
        } catch (Throwable th22) {
            ev.m1777a(th22, "AuthUtil", "loadconfig part");
            return true;
        }
    }

    public static long m1466b() {
        return f887i;
    }

    private static dk m1467b(JSONObject jSONObject, String str) {
        dk dkVar;
        Throwable th;
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                    if (jSONObject2 != null) {
                        dkVar = new dk();
                        try {
                            if (jSONObject2.has("b")) {
                                dkVar.f905a = dj.m1461a(jSONObject2, "b");
                            }
                            if (jSONObject2.has("t")) {
                                dkVar.f906b = dj.m1461a(jSONObject2, "t");
                            }
                            if (!jSONObject2.has("st")) {
                                return dkVar;
                            }
                            dkVar.f907c = dj.m1461a(jSONObject2, "st");
                            return dkVar;
                        } catch (Throwable th2) {
                            th = th2;
                            ev.m1777a(th, "AuthUtil", "getLocateObj");
                            return dkVar;
                        }
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                dkVar = null;
                th = th4;
                ev.m1777a(th, "AuthUtil", "getLocateObj");
                return dkVar;
            }
        }
        return null;
    }

    private static void m1468b(Context context) {
        Editor edit;
        try {
            edit = context.getSharedPreferences("pref", 0).edit();
            if (f897s == 0) {
                edit.remove("nowtime");
            } else {
                edit.putLong("nowtime", f897s);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AuthUtil", "resetPrefsBind");
            return;
        }
        if (f896r == -1) {
            edit.remove("count");
        } else {
            edit.putInt("count", 0);
        }
        dm.m1493a(edit);
    }

    public static long m1469c() {
        return f888j;
    }

    private static void m1470c(Context context) {
        Editor edit;
        try {
            edit = context.getSharedPreferences("pref", 0).edit();
            if (f900v == 0) {
                edit.remove("pushSerTime");
            } else {
                edit.putLong("pushSerTime", f900v);
            }
        } catch (Throwable th) {
            ev.m1777a(th, "AuthUtil", "resetPrefsBind");
            return;
        }
        if (f899u == -1) {
            edit.remove("pushCount");
        } else {
            edit.putInt("pushCount", 0);
        }
        dm.m1493a(edit);
    }

    public static boolean m1471d() {
        return f889k;
    }

    public static int m1472e() {
        return f890l;
    }

    public static boolean m1473f() {
        return f891m;
    }

    public static int m1474g() {
        return f892n;
    }

    public static boolean m1475h() {
        return f893o;
    }

    public static boolean m1476i() {
        boolean equals = Constants.VIA_TO_TYPE_QQ_GROUP.equals(f894p);
        cf.f742a = equals;
        return equals;
    }

    public static String m1477j() {
        return f880b;
    }

    public static String m1478k() {
        return f881c;
    }

    public static String m1479l() {
        return f882d;
    }

    public static String m1480m() {
        return f883e;
    }

    public static String m1481n() {
        return f884f;
    }

    public static String m1482o() {
        return f885g;
    }

    public static boolean m1483p() {
        if (!Constants.VIA_TO_TYPE_QQ_GROUP.equals(f895q)) {
            return false;
        }
        if (f896r == -1 || f897s == 0) {
            return true;
        }
        if (dn.m1508a(f897s, dm.m1495b(f879a, "pref", "nowtime", 0))) {
            int b = dm.m1494b(f879a, "pref", "count", 0);
            if (b >= f896r) {
                return false;
            }
            dm.m1491a(f879a, "pref", "count", b + 1);
            return true;
        }
        dj.m1468b(f879a);
        dm.m1491a(f879a, "pref", "count", 1);
        return true;
    }

    public static boolean m1484q() {
        if (!Constants.VIA_TO_TYPE_QQ_GROUP.equals(f898t)) {
            return false;
        }
        if (f899u == -1 || f900v == 0) {
            return true;
        }
        if (dn.m1508a(f900v, dm.m1495b(f879a, "pref", "pushSerTime", 0))) {
            int b = dm.m1494b(f879a, "pref", "pushCount", 0);
            if (b >= f899u) {
                return false;
            }
            dm.m1491a(f879a, "pref", "pushCount", b + 1);
            return true;
        }
        dj.m1470c(f879a);
        dm.m1491a(f879a, "pref", "pushCount", 1);
        return true;
    }

    public static synchronized boolean m1485r() {
        boolean equals;
        synchronized (dj.class) {
            equals = Constants.VIA_TO_TYPE_QQ_GROUP.equals(f903y);
        }
        return equals;
    }
}
