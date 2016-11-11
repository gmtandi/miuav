package com.p016a;

import android.content.Context;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.facebook.common.util.UriUtil;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.platformtools.PhoneUtil;
import com.tencent.mm.sdk.platformtools.Util;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.BufferRecycler;
import org.json.JSONObject;

/* renamed from: com.a.cm */
public class cm {
    public static final int[] f752a;
    static int f753b;
    private static volatile String f754c;
    private static Hashtable<String, Long> f755d;
    private static db f756e;
    private static Hashtable<String, String> f757f;
    private static TelephonyManager f758g;

    static {
        f754c = null;
        f755d = new Hashtable();
        f756e = new db();
        f757f = new Hashtable();
        f758g = null;
        f752a = new int[]{0, 0};
        f753b = 213891;
    }

    private cm() {
    }

    static int m1302a(int i) {
        int i2 = 0;
        int[] iArr = new int[32];
        int i3 = 0;
        while (i2 < 4) {
            iArr[i2] = (i >> (i2 * 8)) & Util.MASK_8BIT;
            iArr[i2] = ((iArr[i2] << 4) & 240) + ((iArr[i2] >> 4) & 15);
            i3 += (iArr[i2] & Util.MASK_8BIT) << ((3 - i2) * 8);
            i2++;
        }
        return f753b + i3;
    }

    private static int m1303a(int i, ct ctVar, String str, int[] iArr, int i2, int i3, String str2, int i4) {
        int i5 = i4 + 1;
        if (i5 > 25) {
            return -1;
        }
        int i6 = (((((i2 + i3) / 2) - i) / 16) * 16) + i;
        int a = cm.m1304a(ctVar, str, iArr, i6, str2);
        if (i2 == i6 && i6 == i3) {
            if (a != 0) {
                i2 = -1;
            }
            return i2;
        } else if (a == Integer.MAX_VALUE) {
            return -1;
        } else {
            if (a == 0) {
                return i6;
            }
            if (a < 0) {
                return cm.m1303a(i, ctVar, str, iArr, i2, i6, str2, i5);
            }
            return cm.m1303a(i, ctVar, str, iArr, i6 + 16, i3, str2, i5);
        }
    }

    private static int m1304a(ct ctVar, String str, int[] iArr, int i, String str2) {
        try {
            ctVar.m1386a((long) i);
            int i2;
            int i3;
            if (str2.equals(PhoneUtil.CELL_GSM)) {
                i2 = iArr[0];
                i3 = iArr[1];
                int d = ctVar.m1390d();
                int e = ctVar.m1391e();
                return i2 < d ? -1 : i2 > d ? 1 : i3 >= e ? i3 > e ? 1 : 0 : -1;
            } else if (str2.equals(PhoneUtil.CELL_CDMA)) {
                r4 = new int[]{iArr[0], iArr[1], iArr[2]};
                int[] iArr2 = new int[3];
                for (i2 = 0; i2 < 3; i2++) {
                    iArr2[i2] = ctVar.m1390d();
                    if (r4[i2] < iArr2[i2]) {
                        return -1;
                    }
                    if (r4[i2] > iArr2[i2]) {
                        return 1;
                    }
                }
                return 0;
            } else {
                if (str2.equals("wifi")) {
                    byte[] b = dn.m1525b(str);
                    int[] iArr3 = new int[6];
                    i3 = 0;
                    while (i3 < 6) {
                        iArr3[i3] = b[i3] < null ? b[i3] + Opcodes.ACC_NATIVE : b[i3];
                        i3++;
                    }
                    r4 = new int[6];
                    for (i2 = 0; i2 < 6; i2++) {
                        r4[i2] = ctVar.m1392f();
                        if (iArr3[i2] < r4[i2]) {
                            return -1;
                        }
                        if (iArr3[i2] > r4[i2]) {
                            return 1;
                        }
                    }
                    return 0;
                }
                return Integer.MAX_VALUE;
            }
        } catch (Throwable th) {
            ev.m1777a(th, "Off", "cmpItem");
        }
    }

    private static int m1305a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("cgi")) {
            return 9;
        }
        String[] split = str.split("#");
        return split.length == 7 ? 1 : split.length == 8 ? 2 : 9;
    }

    private static AmapLoc m1306a(Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2, int i, int i2) {
        String str;
        ArrayList a;
        AmapLoc amapLoc;
        cb cbVar = new cb();
        if (!hashtable.isEmpty()) {
            for (Entry value : hashtable.entrySet()) {
                str = (String) value.getValue();
                int i3 = str.contains("access") ? 1 : 0;
                if (str.contains("|")) {
                    try {
                        cbVar.m1279a(i3 != 0 ? 1 : 2, str.substring(0, str.lastIndexOf("|")));
                    } catch (Throwable th) {
                        ev.m1777a(th, "Off", "calLoc part3");
                    }
                }
            }
        }
        if (!hashtable2.isEmpty()) {
            for (Entry value2 : hashtable2.entrySet()) {
                str = (String) value2.getValue();
                if (str.contains("|")) {
                    try {
                        cbVar.m1279a(0, str.substring(0, str.lastIndexOf("|")));
                    } catch (Throwable th2) {
                        ev.m1777a(th2, "Off", "calLoc part2");
                    }
                }
            }
        }
        try {
            a = cbVar.m1278a((double) i2, (double) i);
        } catch (Throwable th22) {
            ev.m1777a(th22, "Off", "calLoc part4");
            a = null;
        }
        if (a == null || a.isEmpty()) {
            amapLoc = null;
        } else {
            ce ceVar = (ce) a.get(0);
            amapLoc = null == null ? new AmapLoc() : null;
            amapLoc.m5328c("network");
            amapLoc.m5319b(ceVar.f736a);
            amapLoc.m5311a(ceVar.f737b);
            amapLoc.m5312a((float) ceVar.f738c);
            amapLoc.m5344k(ceVar.f739d);
            amapLoc.m5370x(Constants.VIA_RESULT_SUCCESS);
            amapLoc.m5314a(dn.m1502a());
            a.clear();
        }
        if (!dn.m1512a(amapLoc)) {
            return null;
        }
        amapLoc.m5333f(UriUtil.LOCAL_FILE_SCHEME);
        return amapLoc;
    }

    public static AmapLoc m1307a(double[] dArr, String str, String str2, String str3, int i, Context context, int[] iArr) {
        int i2;
        Throwable e;
        int i3;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str2.contains(GeocodeSearch.GPS)) {
            return null;
        }
        int a = cm.m1305a(str2);
        String a2 = cm.m1308a(a, str2);
        Hashtable hashtable = new Hashtable();
        cm.m1312a(a, str2, str3, hashtable);
        Hashtable hashtable2 = new Hashtable();
        cm.m1313a(str3, hashtable2);
        StringBuilder c = cm.m1323c();
        String[] a3 = dArr == null ? cm.m1320a(0.0d, 0.0d, str) : cm.m1320a(dArr[0], dArr[1], str);
        int length = a3.length / 2;
        if (1 > i || i > 3) {
            i = 1;
        }
        ev.f1156o = hashtable.size();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i6 < a3.length && ev.f1155n) {
            if ((i6 >= length || (i5 <= 0 && !hashtable.isEmpty())) && ((i6 < length || (i4 <= 0 && !hashtable2.isEmpty())) && i6 >= length && i5 > 0)) {
            }
            if (i == 1) {
                if (i6 != 0) {
                }
            } else if (i == 2) {
                if (i6 > 8 && i6 < 25) {
                    i2 = i4;
                    i4 = i5;
                    i5 = i2;
                    i6++;
                    i2 = i5;
                    i5 = i4;
                    i4 = i2;
                } else if (i6 > 33) {
                }
            }
            String stringBuilder = c.toString();
            if (i6 < length) {
                switch (a) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        iArr[0] = 0;
                        iArr[1] = 0;
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        break;
                    default:
                        iArr[0] = 0;
                        iArr[1] = 0;
                        break;
                }
                stringBuilder = stringBuilder + a2 + File.separator;
                stringBuilder = a3[i6].startsWith("-") ? stringBuilder + a3[i6].substring(0, 4) + MiPushClient.ACCEPT_TIME_SEPARATOR : stringBuilder + a3[i6].substring(0, 3) + MiPushClient.ACCEPT_TIME_SEPARATOR;
                int indexOf = a3[i6].indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1;
                stringBuilder = a3[i6].substring(indexOf, indexOf + 1).startsWith("-") ? stringBuilder + a3[i6].substring(indexOf, indexOf + 4) : stringBuilder + a3[i6].substring(indexOf, indexOf + 3);
                String str4 = (stringBuilder + File.separator) + a3[i6];
                if (str4.equals(f754c)) {
                    i2 = i4;
                    i4 = i5;
                    i5 = i2;
                } else {
                    ct ctVar;
                    db dbVar = f756e;
                    ct ctVar2 = (ct) dbVar.m1434b(str4);
                    Object obj = null;
                    File file = new File(str4);
                    if (ctVar2 != null) {
                        obj = 1;
                        ctVar = ctVar2;
                    } else if (!file.getParentFile().exists()) {
                        i2 = i4;
                        i4 = i5;
                        i5 = i2;
                    } else if (file.isDirectory()) {
                        i2 = i4;
                        i4 = i5;
                        i5 = i2;
                    } else if (file.exists()) {
                        cu cuVar = new cu();
                        try {
                            ctVar2 = new ct(file, cuVar);
                        } catch (Throwable e2) {
                            ev.m1777a(e2, "Off", "search part1");
                            i2 = i4;
                            i4 = i5;
                            i5 = i2;
                        } catch (Throwable e22) {
                            Throwable th = e22;
                            ctVar2 = null;
                            ev.m1777a(th, "Off", "search part3");
                        }
                        ctVar = ctVar2;
                    } else {
                        i2 = i4;
                        i4 = i5;
                        i5 = i2;
                    }
                    int i7 = 0;
                    if (ctVar == null) {
                        i2 = i4;
                        i4 = i5;
                        i5 = i2;
                    } else {
                        try {
                            ctVar.m1386a(0);
                            long c2 = ctVar.m1389c();
                            if (i6 < length) {
                                i7 = ctVar.m1390d();
                            }
                            try {
                                long g = ctVar.m1393g();
                                int i8 = 8;
                                if (i6 < length) {
                                    i8 = ((i7 * 4) + 2) + 8;
                                }
                                if (c2 + 7776000000L < dn.m1502a()) {
                                    if (ctVar != null) {
                                        if (obj != null) {
                                            try {
                                                dbVar.m1436c(str4);
                                            } catch (Throwable e222) {
                                                ev.m1777a(e222, "Off", "search part6");
                                            }
                                        } else {
                                            ctVar.m1388b();
                                        }
                                    }
                                    file.delete();
                                    f755d.remove(a3[i6]);
                                    i2 = i4;
                                    i4 = i5;
                                    i5 = i2;
                                } else {
                                    if (g > 8) {
                                        if ((g - ((long) i8)) % 16 == 0) {
                                            double[] a4;
                                            Entry entry;
                                            Object obj2 = (i6 >= length || hashtable.isEmpty() || i5 >= ev.f1156o) ? null : 1;
                                            Object obj3 = (i6 < length || hashtable2.isEmpty() || i4 >= 15) ? null : 1;
                                            if (obj2 != null) {
                                                try {
                                                    i3 = i5;
                                                    for (Entry entry2 : hashtable.entrySet()) {
                                                        try {
                                                            a4 = cm.m1319a(i8, ctVar, ((String) entry2.getKey()).toString(), 0);
                                                            if (a4 != null) {
                                                                i3++;
                                                                hashtable.put(((String) entry2.getKey()).toString(), ((a4[0] + "|" + a4[1]) + "|" + a4[2] + "|") + ((String) hashtable.get(((String) entry2.getKey()).toString())));
                                                                if (i3 >= ev.f1156o) {
                                                                }
                                                            }
                                                            i3 = i3;
                                                        } catch (Throwable th2) {
                                                            e222 = th2;
                                                        }
                                                    }
                                                } catch (Throwable th3) {
                                                    e222 = th3;
                                                    i3 = i5;
                                                    ev.m1777a(e222, "Off", "search part8");
                                                    i5 = i3;
                                                    if (ctVar != null) {
                                                        if (!ctVar.m1387a()) {
                                                            try {
                                                                ctVar.m1388b();
                                                                i2 = i4;
                                                                i4 = i5;
                                                                i5 = i2;
                                                            } catch (Throwable e2222) {
                                                                ev.m1777a(e2222, "Off", "search part9");
                                                                i2 = i4;
                                                                i4 = i5;
                                                                i5 = i2;
                                                            }
                                                            i6++;
                                                            i2 = i5;
                                                            i5 = i4;
                                                            i4 = i2;
                                                        } else if (obj == null) {
                                                            dbVar.m1435b(str4, ctVar);
                                                        }
                                                    }
                                                    i2 = i4;
                                                    i4 = i5;
                                                    i5 = i2;
                                                    i6++;
                                                    i2 = i5;
                                                    i5 = i4;
                                                    i4 = i2;
                                                }
                                            } else {
                                                i3 = i5;
                                            }
                                            if (obj3 != null) {
                                                Iterator it = hashtable2.entrySet().iterator();
                                                while (it != null && it.hasNext()) {
                                                    entry2 = (Entry) it.next();
                                                    a4 = cm.m1319a(i8, ctVar, ((String) entry2.getKey()).toString(), 1);
                                                    if (a4 != null) {
                                                        i4++;
                                                        hashtable2.put(((String) entry2.getKey()).toString(), ((a4[0] + "|" + a4[1]) + "|" + a4[2] + "|") + ((String) hashtable2.get(((String) entry2.getKey()).toString())));
                                                        if (i4 >= 15) {
                                                        }
                                                    }
                                                    i4 = i4;
                                                }
                                            }
                                            i5 = i3;
                                            if (ctVar != null) {
                                                if (!ctVar.m1387a()) {
                                                    ctVar.m1388b();
                                                    i2 = i4;
                                                    i4 = i5;
                                                    i5 = i2;
                                                } else if (obj == null) {
                                                    dbVar.m1435b(str4, ctVar);
                                                }
                                            }
                                        }
                                    }
                                    if (ctVar != null) {
                                        try {
                                            ctVar.m1388b();
                                        } catch (Throwable e22222) {
                                            ev.m1777a(e22222, "Off", "search part7");
                                        }
                                    }
                                    file.delete();
                                    f755d.remove(a3[i6]);
                                    i2 = i4;
                                    i4 = i5;
                                    i5 = i2;
                                }
                            } catch (Throwable e222222) {
                                ev.m1777a(e222222, "Off", "search part5");
                                if (obj != null) {
                                    dbVar.m1436c(str4);
                                }
                                i2 = i4;
                                i4 = i5;
                                i5 = i2;
                            }
                        } catch (Throwable e2222222) {
                            ev.m1777a(e2222222, "Off", "search part4");
                            if (obj != null) {
                                dbVar.m1436c(str4);
                            }
                            i2 = i4;
                            i4 = i5;
                            i5 = i2;
                        }
                    }
                }
                i6++;
                i2 = i5;
                i5 = i4;
                i4 = i2;
            }
            i2 = i4;
            i4 = i5;
            i5 = i2;
            i6++;
            i2 = i5;
            i5 = i4;
            i4 = i2;
        }
        c.delete(0, c.length());
        AmapLoc a5 = cm.m1306a(hashtable, hashtable2, iArr[0], iArr[1]);
        return !dn.m1512a(a5) ? null : a5;
    }

    private static String m1308a(int i, String str) {
        String[] split = str.split("#");
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return split[1] + "_" + split[2];
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return split[3];
            default:
                return null;
        }
    }

    private static String m1309a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        StringBuilder c = cm.m1323c();
        int indexOf;
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                c.append(cm.m1308a(cm.m1305a(str), str)).append(File.separator);
                if (str2.startsWith("-")) {
                    c.append(str2.substring(0, 4));
                } else {
                    c.append(str2.substring(0, 3));
                }
                c.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                indexOf = str2.indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1;
                if (str2.substring(indexOf, indexOf + 1).startsWith("-")) {
                    c.append(str2.substring(indexOf, indexOf + 4));
                } else {
                    c.append(str2.substring(indexOf, indexOf + 3));
                }
                c.append(File.separator).append(str2);
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                c.append("wifi").append(File.separator);
                c.append(str2.substring(0, 3)).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                indexOf = str2.indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1;
                c.append(str2.substring(indexOf, indexOf + 3));
                c.append(File.separator).append(str2);
                break;
            default:
                return null;
        }
        return c.toString();
    }

    public static ArrayList<String> m1310a(String str, boolean z) {
        ArrayList<String> arrayList = null;
        if (f757f.isEmpty()) {
            return null;
        }
        int a = cm.m1305a(str);
        String[] split = str.split("#");
        switch (a) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                for (String str2 : f757f.keySet()) {
                    ArrayList<String> arrayList2;
                    if (((String) f757f.get(str2)).contains(MiPushClient.ACCEPT_TIME_SEPARATOR + split[3] + MiPushClient.ACCEPT_TIME_SEPARATOR)) {
                        arrayList2 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList2.add(str2);
                        if (z) {
                            return arrayList2;
                        }
                    } else {
                        arrayList2 = arrayList;
                    }
                    arrayList = arrayList2;
                }
                return arrayList;
            default:
                return null;
        }
    }

    public static void m1311a() {
        f756e.m1432a();
        f755d.clear();
        f757f.clear();
        f752a[0] = 0;
        f752a[1] = 0;
    }

    private static void m1312a(int i, String str, String str2, Hashtable<String, String> hashtable) {
        String[] split = str.split("#");
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                hashtable.put(split[3] + "|" + split[4], "access");
                if (!TextUtils.isEmpty(str2) && 0 < str2.split("#").length) {
                }
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                hashtable.put(split[3] + "|" + split[4] + "|" + split[5], "access");
            default:
        }
    }

    private static void m1313a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = new String[2];
            for (String str2 : str.split("#")) {
                if (str2.contains(MiPushClient.ACCEPT_TIME_SEPARATOR)) {
                    String[] split = str2.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    hashtable.put(split[0], split[1]);
                }
            }
        }
    }

    private static boolean m1314a(Context context, String str, int i, boolean z, boolean z2) {
        int i2;
        boolean z3;
        if (z) {
            if (i < 25) {
                i2 = 1;
            } else {
                z3 = false;
            }
        } else if (i == 1) {
            i2 = 1;
        } else {
            z3 = false;
        }
        if (!str.contains("cgi") && r0 != 0) {
            return false;
        }
        if ((!str.contains("wifi") && r0 == 0) || f752a[1] > BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN) {
            return false;
        }
        NetworkInfo c = dn.m1528c(context);
        if (dd.m1441a(c) == -1) {
            return false;
        }
        if (c.getType() != 1 && z2) {
            return false;
        }
        if (!(c.getType() == 1 || z2 || f758g != null)) {
            f758g = (TelephonyManager) dn.m1503a(context, "phone");
        }
        return true;
    }

    public static boolean m1315a(Context context, String str, String str2, int i, int i2, boolean z, boolean z2) {
        if (!cm.m1314a(context, str, i, false, z)) {
            return false;
        }
        if (i2 == 0) {
            return cm.m1316a(context, str, str2, i, z2);
        }
        int i3 = i2 == 1 ? 8 : 24;
        String[] a = cm.m1320a(0.0d, 0.0d, str2);
        for (int i4 = 1; i4 < i3; i4++) {
            cm.m1316a(context, str, a[i4], i, z2);
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m1316a(android.content.Context r11, java.lang.String r12, java.lang.String r13, int r14, boolean r15) {
        /*
        r0 = 2;
        r6 = new java.lang.String[r0];
        r0 = com.p016a.cm.m1318a(r12, r13, r14, r6);
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r1 = 0;
    L_0x000a:
        return r1;
    L_0x000b:
        r0 = f755d;
        r1 = 1;
        r1 = r6[r1];
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x003c;
    L_0x0016:
        r0 = f755d;
        r1 = 1;
        r1 = r6[r1];
        r0 = r0.get(r1);
        r0 = (java.lang.Long) r0;
        r0 = r0.longValue();
        r2 = com.p016a.dn.m1502a();
        r0 = r2 - r0;
        r2 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0034;
    L_0x0032:
        r1 = 0;
        goto L_0x000a;
    L_0x0034:
        r0 = f755d;
        r1 = 1;
        r1 = r6[r1];
        r0.remove(r1);
    L_0x003c:
        r1 = 0;
        r4 = 0;
        r3 = 0;
        r2 = 0;
        com.p016a.dn.m1519b();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = new java.util.HashMap;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0.<init>();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r5 = "v";
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7 = java.lang.String.valueOf(r7);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0.put(r5, r7);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r5 = com.p016a.dd.m1442a(r11);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r7 = "https://offline.aps.amap.com/LoadOfflineData/getData";
        r8 = 0;
        r8 = r6[r8];	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r9 = "UTF-8";
        r8 = r8.getBytes(r9);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r2 = r5.m1444a(r11, r7, r0, r8);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        if (r2 != 0) goto L_0x0081;
    L_0x0068:
        if (r3 == 0) goto L_0x006d;
    L_0x006a:
        r3.close();	 Catch:{ Throwable -> 0x0313 }
    L_0x006d:
        if (r4 == 0) goto L_0x0072;
    L_0x006f:
        r4.close();	 Catch:{ Throwable -> 0x031d }
    L_0x0072:
        if (r2 == 0) goto L_0x000a;
    L_0x0074:
        r2.disconnect();	 Catch:{ Throwable -> 0x0078 }
        goto L_0x000a;
    L_0x0078:
        r0 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        com.p016a.ev.m1777a(r0, r2, r3);
        goto L_0x000a;
    L_0x0081:
        r0 = r2.getResponseCode();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r5) goto L_0x0202;
    L_0x0089:
        r5 = 0;
        r0 = r2.getHeaderFields();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = r0.entrySet();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r7 = r0.iterator();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
    L_0x0096:
        r0 = r7.hasNext();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        if (r0 == 0) goto L_0x03c9;
    L_0x009c:
        r0 = r7.next();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r8 = "code";
        r9 = r0.getKey();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r8 = r8.equals(r9);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        if (r8 == 0) goto L_0x0096;
    L_0x00ae:
        r0 = r0.getValue();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = (java.util.List) r0;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r5 = 0;
        r0 = r0.get(r5);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = (java.lang.String) r0;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
    L_0x00bf:
        r5 = 260; // 0x104 float:3.64E-43 double:1.285E-321;
        if (r0 != r5) goto L_0x01e8;
    L_0x00c3:
        r0 = 1;
        r0 = r6[r0];	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        f754c = r0;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0 = 1;
        r5 = r2.getInputStream();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x03b7, SocketException -> 0x039c, SocketTimeoutException -> 0x037c, EOFException -> 0x0364, Throwable -> 0x034c, all -> 0x033b }
        r4.<init>(r5);	 Catch:{ UnknownHostException -> 0x03b7, SocketException -> 0x039c, SocketTimeoutException -> 0x037c, EOFException -> 0x0364, Throwable -> 0x034c, all -> 0x033b }
        r3 = new java.io.File;	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r7 = 1;
        r7 = r6[r7];	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r3.<init>(r7);	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r7 = r3.exists();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        if (r7 == 0) goto L_0x00e6;
    L_0x00e0:
        r7 = r3.delete();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        if (r7 == 0) goto L_0x013e;
    L_0x00e6:
        if (r0 == 0) goto L_0x03c6;
    L_0x00e8:
        r0 = com.p016a.ev.f1155n;	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        if (r0 == 0) goto L_0x03c6;
    L_0x00ec:
        r0 = r3.getParentFile();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r7 = r0.exists();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        if (r7 != 0) goto L_0x00f9;
    L_0x00f6:
        r0.mkdirs();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
    L_0x00f9:
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r7 = new java.io.FileOutputStream;	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r7.<init>(r3);	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r3 = new java.io.BufferedOutputStream;	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r3.<init>(r7, r0);	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r7 = new byte[r0];	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
    L_0x0107:
        r8 = 0;
        r8 = r4.read(r7, r8, r0);	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r9 = -1;
        if (r8 == r9) goto L_0x0140;
    L_0x010f:
        r9 = 0;
        r3.write(r7, r9, r8);	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        goto L_0x0107;
    L_0x0114:
        r0 = move-exception;
        r3 = r4;
        r4 = r5;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x011a:
        r5 = "Off";
        r6 = "c 2 part2";
        com.p016a.ev.m1777a(r1, r5, r6);	 Catch:{ all -> 0x0347 }
        if (r3 == 0) goto L_0x0126;
    L_0x0123:
        r3.close();	 Catch:{ Throwable -> 0x02a8 }
    L_0x0126:
        if (r4 == 0) goto L_0x012b;
    L_0x0128:
        r4.close();	 Catch:{ Throwable -> 0x02b2 }
    L_0x012b:
        if (r2 == 0) goto L_0x0130;
    L_0x012d:
        r2.disconnect();	 Catch:{ Throwable -> 0x02bc }
    L_0x0130:
        r1 = f754c;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x013b;
    L_0x0138:
        r1 = 0;
        f754c = r1;
    L_0x013b:
        r1 = r0;
        goto L_0x000a;
    L_0x013e:
        r0 = 0;
        goto L_0x00e6;
    L_0x0140:
        r3.flush();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r3.close();	 Catch:{ UnknownHostException -> 0x0114, SocketException -> 0x03a3, SocketTimeoutException -> 0x0383, EOFException -> 0x036b, Throwable -> 0x0353 }
        r0 = 1;
        r1 = f755d;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = 1;
        r3 = r6[r3];	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r8 = com.p016a.dn.m1502a();	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r1.put(r3, r7);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r8 = 0;
        r1 = "yyyyMMdd";
        r1 = com.p016a.dn.m1504a(r8, r1);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r7 = 0;
        r3 = r3[r7];	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = r1.equals(r3);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        if (r3 == 0) goto L_0x019f;
    L_0x016e:
        r1 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = 1;
        r7 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r8 = 1;
        r7 = r7[r8];	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r7 = r7 + 1;
        r1[r3] = r7;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
    L_0x017a:
        r1 = r4;
        r3 = r5;
        r4 = r0;
    L_0x017d:
        if (r15 == 0) goto L_0x0185;
    L_0x017f:
        r0 = 1;
        r0 = r6[r0];	 Catch:{ UnknownHostException -> 0x03be, SocketException -> 0x03a9, SocketTimeoutException -> 0x038c, EOFException -> 0x0374, Throwable -> 0x035c, all -> 0x0342 }
        com.p016a.cm.m1321b(r0);	 Catch:{ UnknownHostException -> 0x03be, SocketException -> 0x03a9, SocketTimeoutException -> 0x038c, EOFException -> 0x0374, Throwable -> 0x035c, all -> 0x0342 }
    L_0x0185:
        r0 = r4;
    L_0x0186:
        if (r1 == 0) goto L_0x018b;
    L_0x0188:
        r1.close();	 Catch:{ Throwable -> 0x0327 }
    L_0x018b:
        if (r3 == 0) goto L_0x0190;
    L_0x018d:
        r3.close();	 Catch:{ Throwable -> 0x0331 }
    L_0x0190:
        if (r2 == 0) goto L_0x0130;
    L_0x0192:
        r2.disconnect();	 Catch:{ Throwable -> 0x0196 }
        goto L_0x0130;
    L_0x0196:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
    L_0x019b:
        com.p016a.ev.m1777a(r1, r2, r3);
        goto L_0x0130;
    L_0x019f:
        r3 = f752a;	 Catch:{ Throwable -> 0x01b4, UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371 }
        r7 = 0;
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x01b4, UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371 }
        r3[r7] = r1;	 Catch:{ Throwable -> 0x01b4, UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371 }
    L_0x01a8:
        r1 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = 1;
        r7 = 1;
        r1[r3] = r7;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        goto L_0x017a;
    L_0x01af:
        r1 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x011a;
    L_0x01b4:
        r1 = move-exception;
        r3 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r7 = 0;
        r8 = 0;
        r3[r7] = r8;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = f752a;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r7 = 1;
        r8 = 0;
        r3[r7] = r8;	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        r3 = "Off";
        r7 = "c 2 part1";
        com.p016a.ev.m1777a(r1, r3, r7);	 Catch:{ UnknownHostException -> 0x01af, SocketException -> 0x01c9, SocketTimeoutException -> 0x0389, EOFException -> 0x0371, Throwable -> 0x0359 }
        goto L_0x01a8;
    L_0x01c9:
        r1 = move-exception;
    L_0x01ca:
        r3 = "Off";
        r6 = "c 2 part3";
        com.p016a.ev.m1777a(r1, r3, r6);	 Catch:{ all -> 0x033f }
        if (r4 == 0) goto L_0x01d6;
    L_0x01d3:
        r4.close();	 Catch:{ Throwable -> 0x02c3 }
    L_0x01d6:
        if (r5 == 0) goto L_0x01db;
    L_0x01d8:
        r5.close();	 Catch:{ Throwable -> 0x02cd }
    L_0x01db:
        if (r2 == 0) goto L_0x0130;
    L_0x01dd:
        r2.disconnect();	 Catch:{ Throwable -> 0x01e2 }
        goto L_0x0130;
    L_0x01e2:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019b;
    L_0x01e8:
        r0 = com.p016a.ev.f1155n;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        if (r0 == 0) goto L_0x01fc;
    L_0x01ec:
        r0 = f755d;	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r5 = 1;
        r5 = r6[r5];	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r8 = com.p016a.dn.m1502a();	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
        r0.put(r5, r7);	 Catch:{ UnknownHostException -> 0x03b1, SocketException -> 0x0394, SocketTimeoutException -> 0x020b, EOFException -> 0x0230, Throwable -> 0x0255, all -> 0x027a }
    L_0x01fc:
        r10 = r3;
        r3 = r4;
        r4 = r1;
        r1 = r10;
        goto L_0x017d;
    L_0x0202:
        r5 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r0 != r5) goto L_0x0206;
    L_0x0206:
        r0 = r1;
        r1 = r3;
        r3 = r4;
        goto L_0x0186;
    L_0x020b:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0211:
        r3 = "Off";
        r6 = "c 2 part4";
        com.p016a.ev.m1777a(r1, r3, r6);	 Catch:{ all -> 0x033f }
        if (r4 == 0) goto L_0x021d;
    L_0x021a:
        r4.close();	 Catch:{ Throwable -> 0x02d7 }
    L_0x021d:
        if (r5 == 0) goto L_0x0222;
    L_0x021f:
        r5.close();	 Catch:{ Throwable -> 0x02e1 }
    L_0x0222:
        if (r2 == 0) goto L_0x0130;
    L_0x0224:
        r2.disconnect();	 Catch:{ Throwable -> 0x0229 }
        goto L_0x0130;
    L_0x0229:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019b;
    L_0x0230:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0236:
        r3 = "Off";
        r6 = "c 2 part5";
        com.p016a.ev.m1777a(r1, r3, r6);	 Catch:{ all -> 0x033f }
        if (r4 == 0) goto L_0x0242;
    L_0x023f:
        r4.close();	 Catch:{ Throwable -> 0x02eb }
    L_0x0242:
        if (r5 == 0) goto L_0x0247;
    L_0x0244:
        r5.close();	 Catch:{ Throwable -> 0x02f5 }
    L_0x0247:
        if (r2 == 0) goto L_0x0130;
    L_0x0249:
        r2.disconnect();	 Catch:{ Throwable -> 0x024e }
        goto L_0x0130;
    L_0x024e:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019b;
    L_0x0255:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x025b:
        r3 = "Off";
        r6 = "c 2 part6";
        com.p016a.ev.m1777a(r1, r3, r6);	 Catch:{ all -> 0x033f }
        if (r4 == 0) goto L_0x0267;
    L_0x0264:
        r4.close();	 Catch:{ Throwable -> 0x02ff }
    L_0x0267:
        if (r5 == 0) goto L_0x026c;
    L_0x0269:
        r5.close();	 Catch:{ Throwable -> 0x0309 }
    L_0x026c:
        if (r2 == 0) goto L_0x0130;
    L_0x026e:
        r2.disconnect();	 Catch:{ Throwable -> 0x0273 }
        goto L_0x0130;
    L_0x0273:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019b;
    L_0x027a:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
    L_0x027d:
        if (r4 == 0) goto L_0x0282;
    L_0x027f:
        r4.close();	 Catch:{ Throwable -> 0x028d }
    L_0x0282:
        if (r5 == 0) goto L_0x0287;
    L_0x0284:
        r5.close();	 Catch:{ Throwable -> 0x0296 }
    L_0x0287:
        if (r2 == 0) goto L_0x028c;
    L_0x0289:
        r2.disconnect();	 Catch:{ Throwable -> 0x029f }
    L_0x028c:
        throw r0;
    L_0x028d:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0282;
    L_0x0296:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0287;
    L_0x029f:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        com.p016a.ev.m1777a(r1, r2, r3);
        goto L_0x028c;
    L_0x02a8:
        r1 = move-exception;
        r3 = "Off";
        r5 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r5);
        goto L_0x0126;
    L_0x02b2:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x012b;
    L_0x02bc:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019b;
    L_0x02c3:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x01d6;
    L_0x02cd:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x01db;
    L_0x02d7:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x021d;
    L_0x02e1:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0222;
    L_0x02eb:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0242;
    L_0x02f5:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0247;
    L_0x02ff:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0267;
    L_0x0309:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x026c;
    L_0x0313:
        r0 = move-exception;
        r3 = "Off";
        r5 = "c 2 part7";
        com.p016a.ev.m1777a(r0, r3, r5);
        goto L_0x006d;
    L_0x031d:
        r0 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r0, r3, r4);
        goto L_0x0072;
    L_0x0327:
        r1 = move-exception;
        r4 = "Off";
        r5 = "c 2 part7";
        com.p016a.ev.m1777a(r1, r4, r5);
        goto L_0x018b;
    L_0x0331:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.p016a.ev.m1777a(r1, r3, r4);
        goto L_0x0190;
    L_0x033b:
        r0 = move-exception;
        r4 = r3;
        goto L_0x027d;
    L_0x033f:
        r0 = move-exception;
        goto L_0x027d;
    L_0x0342:
        r0 = move-exception;
        r4 = r1;
        r5 = r3;
        goto L_0x027d;
    L_0x0347:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        goto L_0x027d;
    L_0x034c:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x025b;
    L_0x0353:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x025b;
    L_0x0359:
        r1 = move-exception;
        goto L_0x025b;
    L_0x035c:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x025b;
    L_0x0364:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0236;
    L_0x036b:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0236;
    L_0x0371:
        r1 = move-exception;
        goto L_0x0236;
    L_0x0374:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x0236;
    L_0x037c:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0211;
    L_0x0383:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0211;
    L_0x0389:
        r1 = move-exception;
        goto L_0x0211;
    L_0x038c:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x0211;
    L_0x0394:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01ca;
    L_0x039c:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01ca;
    L_0x03a3:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01ca;
    L_0x03a9:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x01ca;
    L_0x03b1:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x011a;
    L_0x03b7:
        r0 = move-exception;
        r4 = r5;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x011a;
    L_0x03be:
        r0 = move-exception;
        r10 = r0;
        r0 = r4;
        r4 = r3;
        r3 = r1;
        r1 = r10;
        goto L_0x011a;
    L_0x03c6:
        r0 = r1;
        goto L_0x017a;
    L_0x03c9:
        r0 = r5;
        goto L_0x00bf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.cm.a(android.content.Context, java.lang.String, java.lang.String, int, boolean):boolean");
    }

    public static boolean m1317a(String str, String str2, int i, int i2) {
        int i3 = 0;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (i2 == 0) {
            String a = cm.m1309a(str, str2, i);
            if (a != null) {
                File file = new File(a);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                if (f757f.containsKey(a)) {
                    f757f.remove(a);
                }
                if (f755d.containsKey(a)) {
                    f755d.remove(a);
                }
            }
            return true;
        } else if (i2 != 1 && i2 != 2) {
            return false;
        } else {
            String[] a2 = cm.m1320a(0.0d, 0.0d, str2);
            int i4 = i2 == 1 ? 9 : i2 == 2 ? 25 : 0;
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                i3 = 25;
            }
            Hashtable hashtable = f757f;
            Hashtable hashtable2 = f755d;
            for (i3 = 
            /* Method generation error in method: com.a.cm.a(java.lang.String, java.lang.String, int, int):boolean
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_3 'i3' int) = (r0_0 'i3' int), (r0_7 'i3' int) binds: {(r0_0 'i3' int)=B:22:0x0052, (r0_7 'i3' int)=B:42:0x0091} in method: com.a.cm.a(java.lang.String, java.lang.String, int, int):boolean
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:225)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:128)
	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:146)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:124)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:177)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:324)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:263)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:226)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:116)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:81)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
Caused by: jadx.core.utils.exceptions.CodegenException: Unknown instruction: PHI in method: com.a.cm.a(java.lang.String, java.lang.String, int, int):boolean
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:512)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:219)
	... 26 more
 */

            private static boolean m1318a(String str, String str2, int i, String[] strArr) {
                Object a;
                RandomAccessFile randomAccessFile;
                Throwable th;
                String str3;
                String str4;
                JSONObject jSONObject;
                Throwable th2;
                long j = 0;
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return false;
                }
                if (strArr == null || strArr.length != 2) {
                    return false;
                }
                StringBuilder c = cm.m1323c();
                int indexOf;
                switch (i) {
                    case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                        a = cm.m1308a(cm.m1305a(str), str);
                        c.append(a).append(File.separator);
                        if (str2.startsWith("-")) {
                            c.append(str2.substring(0, 4));
                        } else {
                            c.append(str2.substring(0, 3));
                        }
                        c.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        indexOf = str2.indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1;
                        if (str2.substring(indexOf, indexOf + 1).startsWith("-")) {
                            c.append(str2.substring(indexOf, indexOf + 4));
                        } else {
                            c.append(str2.substring(indexOf, indexOf + 3));
                        }
                        c.append(File.separator).append(str2);
                        break;
                    case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                        a = "wifi";
                        c.append(a).append(File.separator);
                        c.append(str2.substring(0, 3)).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                        indexOf = str2.indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR) + 1;
                        c.append(str2.substring(indexOf, indexOf + 3));
                        c.append(File.separator).append(str2);
                        break;
                    default:
                        return false;
                }
                strArr[1] = c.toString();
                c.delete(0, c.length());
                File file = new File(strArr[1]);
                if (file.exists() && file.isFile()) {
                    RandomAccessFile randomAccessFile2 = null;
                    try {
                        randomAccessFile = new RandomAccessFile(file, "r");
                        try {
                            randomAccessFile.seek(0);
                            j = randomAccessFile.readLong();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th3) {
                                    th = th3;
                                    str3 = "Off";
                                    str4 = "getRequestParams part3";
                                    ev.m1777a(th, str3, str4);
                                    jSONObject = new JSONObject();
                                    jSONObject.put("v", String.valueOf(C2020f.f10933c));
                                    jSONObject.put("geohash", str2);
                                    jSONObject.put("t", String.valueOf(j));
                                    jSONObject.put(SocialConstants.PARAM_TYPE, a);
                                    jSONObject.put("imei", ev.f1143b);
                                    jSONObject.put("imsi", ev.f1144c);
                                    jSONObject.put("src", ev.f1146e);
                                    jSONObject.put("license", ev.f1147f);
                                    strArr[0] = jSONObject.toString();
                                    return true;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            th = e;
                            try {
                                ev.m1777a(th, "Off", "getRequestParams part1");
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str3 = "Off";
                                        str4 = "getRequestParams part3";
                                        ev.m1777a(th, str3, str4);
                                        jSONObject = new JSONObject();
                                        jSONObject.put("v", String.valueOf(C2020f.f10933c));
                                        jSONObject.put("geohash", str2);
                                        jSONObject.put("t", String.valueOf(j));
                                        jSONObject.put(SocialConstants.PARAM_TYPE, a);
                                        jSONObject.put("imei", ev.f1143b);
                                        jSONObject.put("imsi", ev.f1144c);
                                        jSONObject.put("src", ev.f1146e);
                                        jSONObject.put("license", ev.f1147f);
                                        strArr[0] = jSONObject.toString();
                                        return true;
                                    }
                                }
                                jSONObject = new JSONObject();
                                jSONObject.put("v", String.valueOf(C2020f.f10933c));
                                jSONObject.put("geohash", str2);
                                jSONObject.put("t", String.valueOf(j));
                                jSONObject.put(SocialConstants.PARAM_TYPE, a);
                                jSONObject.put("imei", ev.f1143b);
                                jSONObject.put("imsi", ev.f1144c);
                                jSONObject.put("src", ev.f1146e);
                                jSONObject.put("license", ev.f1147f);
                                strArr[0] = jSONObject.toString();
                                return true;
                            } catch (Throwable th5) {
                                th2 = th5;
                                randomAccessFile2 = randomAccessFile;
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (Throwable th6) {
                                        ev.m1777a(th6, "Off", "getRequestParams part3");
                                    }
                                }
                                throw th2;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            randomAccessFile2 = randomAccessFile;
                            try {
                                ev.m1777a(th, "Off", "getRequestParams part2");
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (Throwable th8) {
                                        th = th8;
                                        str3 = "Off";
                                        str4 = "getRequestParams part3";
                                        ev.m1777a(th, str3, str4);
                                        jSONObject = new JSONObject();
                                        jSONObject.put("v", String.valueOf(C2020f.f10933c));
                                        jSONObject.put("geohash", str2);
                                        jSONObject.put("t", String.valueOf(j));
                                        jSONObject.put(SocialConstants.PARAM_TYPE, a);
                                        jSONObject.put("imei", ev.f1143b);
                                        jSONObject.put("imsi", ev.f1144c);
                                        jSONObject.put("src", ev.f1146e);
                                        jSONObject.put("license", ev.f1147f);
                                        strArr[0] = jSONObject.toString();
                                        return true;
                                    }
                                }
                                jSONObject = new JSONObject();
                                jSONObject.put("v", String.valueOf(C2020f.f10933c));
                                jSONObject.put("geohash", str2);
                                jSONObject.put("t", String.valueOf(j));
                                jSONObject.put(SocialConstants.PARAM_TYPE, a);
                                jSONObject.put("imei", ev.f1143b);
                                jSONObject.put("imsi", ev.f1144c);
                                jSONObject.put("src", ev.f1146e);
                                jSONObject.put("license", ev.f1147f);
                                strArr[0] = jSONObject.toString();
                                return true;
                            } catch (Throwable th9) {
                                th2 = th9;
                                if (randomAccessFile2 != null) {
                                    randomAccessFile2.close();
                                }
                                throw th2;
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        th = e2;
                        randomAccessFile = null;
                        ev.m1777a(th, "Off", "getRequestParams part1");
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        jSONObject = new JSONObject();
                        jSONObject.put("v", String.valueOf(C2020f.f10933c));
                        jSONObject.put("geohash", str2);
                        jSONObject.put("t", String.valueOf(j));
                        jSONObject.put(SocialConstants.PARAM_TYPE, a);
                        jSONObject.put("imei", ev.f1143b);
                        jSONObject.put("imsi", ev.f1144c);
                        jSONObject.put("src", ev.f1146e);
                        jSONObject.put("license", ev.f1147f);
                        strArr[0] = jSONObject.toString();
                        return true;
                    } catch (Throwable th10) {
                        th = th10;
                        ev.m1777a(th, "Off", "getRequestParams part2");
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        jSONObject = new JSONObject();
                        jSONObject.put("v", String.valueOf(C2020f.f10933c));
                        jSONObject.put("geohash", str2);
                        jSONObject.put("t", String.valueOf(j));
                        jSONObject.put(SocialConstants.PARAM_TYPE, a);
                        jSONObject.put("imei", ev.f1143b);
                        jSONObject.put("imsi", ev.f1144c);
                        jSONObject.put("src", ev.f1146e);
                        jSONObject.put("license", ev.f1147f);
                        strArr[0] = jSONObject.toString();
                        return true;
                    }
                }
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("v", String.valueOf(C2020f.f10933c));
                    jSONObject.put("geohash", str2);
                    jSONObject.put("t", String.valueOf(j));
                    jSONObject.put(SocialConstants.PARAM_TYPE, a);
                    jSONObject.put("imei", ev.f1143b);
                    jSONObject.put("imsi", ev.f1144c);
                    jSONObject.put("src", ev.f1146e);
                    jSONObject.put("license", ev.f1147f);
                } catch (Throwable th22) {
                    ev.m1777a(th22, "Off", "getRequestParams part4");
                }
                strArr[0] = jSONObject.toString();
                return true;
            }

            private static double[] m1319a(int i, ct ctVar, String str, int i2) {
                int[] iArr;
                int i3;
                String str2;
                Throwable th;
                if (i2 == 0) {
                    String[] split = str.split("\\|");
                    iArr = new int[split.length];
                    for (i3 = 0; i3 < split.length; i3++) {
                        iArr[i3] = Integer.parseInt(split[i3]);
                    }
                    str2 = split.length == 2 ? PhoneUtil.CELL_GSM : PhoneUtil.CELL_CDMA;
                } else {
                    str2 = "wifi";
                    iArr = null;
                }
                double[] dArr;
                try {
                    if (ctVar.m1393g() > ((long) i)) {
                        ctVar.m1386a((long) i);
                        i3 = cm.m1303a(i, ctVar, str, iArr, i, ((int) ctVar.m1393g()) - 16, str2, 0);
                        if (i3 != -1) {
                            ctVar.m1386a((long) (i3 + 6));
                            dArr = new double[3];
                            try {
                                dArr[0] = ((double) cm.m1302a(ctVar.m1391e())) / 1000000.0d;
                                dArr[1] = ((double) cm.m1302a(ctVar.m1391e())) / 1000000.0d;
                                dArr[2] = (double) ctVar.m1390d();
                                return !dn.m1507a(dArr[1]) ? null : !dn.m1523b(dArr[0]) ? null : dArr;
                            } catch (Throwable th2) {
                                th = th2;
                                ev.m1777a(th, "Off", "binS");
                                return dArr;
                            }
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    dArr = null;
                    ev.m1777a(th, "Off", "binS");
                    return dArr;
                }
            }

            public static String[] m1320a(double d, double d2, String str) {
                String a;
                int i;
                String[] strArr = new String[50];
                if (TextUtils.isEmpty(str)) {
                    a = cl.m1298a(d, d2);
                    str = cl.m1298a(d, d2);
                } else {
                    a = str;
                }
                strArr[0] = a;
                strArr[25] = str;
                String[] a2 = cl.m1301a(a);
                for (i = 1; i < 25; i++) {
                    strArr[i] = a2[i - 1];
                }
                a2 = cl.m1301a(str);
                for (i = 26; i < 50; i++) {
                    strArr[i] = a2[i - 26];
                }
                return strArr;
            }

            private static void m1321b(String str) {
                RandomAccessFile randomAccessFile;
                Throwable th;
                String str2;
                String str3;
                if (!f757f.containsKey(str) || TextUtils.isEmpty((CharSequence) f757f.get(str))) {
                    File file = new File(str);
                    if (file.exists() && file.isFile()) {
                        try {
                            randomAccessFile = new RandomAccessFile(file, "r");
                            try {
                                randomAccessFile.seek(8);
                                int readUnsignedShort = randomAccessFile.readUnsignedShort();
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < readUnsignedShort; i++) {
                                    int readInt = randomAccessFile.readInt();
                                    if (stringBuilder.indexOf(MiPushClient.ACCEPT_TIME_SEPARATOR + readInt) == -1) {
                                        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(readInt);
                                    }
                                    if (i == readUnsignedShort - 1) {
                                        stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                                    }
                                }
                                f757f.put(str, stringBuilder.toString());
                                stringBuilder.delete(0, stringBuilder.length());
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        str2 = "Off";
                                        str3 = "loadFcFea part3";
                                        ev.m1777a(th, str2, str3);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                th = e;
                                try {
                                    ev.m1777a(th, "Off", "loadFcFea part1");
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Throwable th3) {
                                            th = th3;
                                            str2 = "Off";
                                            str3 = "loadFcFea part3";
                                            ev.m1777a(th, str2, str3);
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Throwable th5) {
                                            ev.m1777a(th5, "Off", "loadFcFea part3");
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                ev.m1777a(th, "Off", "loadFcFea part2");
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th7) {
                                        th = th7;
                                        str2 = "Off";
                                        str3 = "loadFcFea part3";
                                        ev.m1777a(th, str2, str3);
                                    }
                                }
                            }
                        } catch (FileNotFoundException e2) {
                            th = e2;
                            randomAccessFile = null;
                            ev.m1777a(th, "Off", "loadFcFea part1");
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            randomAccessFile = null;
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            throw th;
                        }
                    }
                }
            }

            public static boolean m1322b() {
                return !f757f.isEmpty();
            }

            private static StringBuilder m1323c() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dn.m1535e());
                stringBuilder.append("offline").append(File.separator);
                stringBuilder.append(dn.m1542j()).append(File.separator).append("s").append(File.separator);
                return stringBuilder;
            }
        }
