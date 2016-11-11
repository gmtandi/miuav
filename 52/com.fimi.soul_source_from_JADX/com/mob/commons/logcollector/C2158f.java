package com.mob.commons.logcollector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.MobLog;
import com.tencent.open.SocialConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.logcollector.f */
public class C2158f {
    public static synchronized long m13211a(Context context, long j, String str, int i, String str2) {
        long j2;
        synchronized (C2158f.class) {
            if (TextUtils.isEmpty(str)) {
                j2 = -1;
            } else {
                C2154b a = C2154b.m13178a(context);
                ContentValues contentValues = new ContentValues();
                contentValues.put("exception_time", Long.valueOf(j));
                contentValues.put("exception_msg", str.toString());
                contentValues.put("exception_level", Integer.valueOf(i));
                contentValues.put("exception_md5", str2);
                j2 = a.m13181a("table_exception", contentValues);
            }
        }
        return j2;
    }

    public static synchronized long m13212a(Context context, ArrayList<String> arrayList) {
        long j;
        synchronized (C2158f.class) {
            if (arrayList == null) {
                j = 0;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < arrayList.size(); i++) {
                    stringBuilder.append("'");
                    stringBuilder.append((String) arrayList.get(i));
                    stringBuilder.append("'");
                    stringBuilder.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                MobLog.getInstance().m743i("delete COUNT == %s", Integer.valueOf(C2154b.m13178a(context).m13180a("table_exception", "exception_md5 in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null)));
                j = (long) C2154b.m13178a(context).m13180a("table_exception", "exception_md5 in ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 1) + " )", null);
            }
        }
        return j;
    }

    private static synchronized ArrayList<C2157e> m13213a(Context context, String str, String[] strArr) {
        ArrayList<C2157e> arrayList;
        synchronized (C2158f.class) {
            arrayList = new ArrayList();
            C2157e c2157e = new C2157e();
            C2154b a = C2154b.m13178a(context);
            String str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception group by exception_md5 having max(_id)";
            if (!(TextUtils.isEmpty(str) || strArr == null || strArr.length <= 0)) {
                str2 = " select exception_md5, exception_level, exception_time, exception_msg, sum(exception_counts) from table_exception where " + str + " group by exception_md5 having max(_id)";
            }
            Cursor a2 = a.m13182a(str2, strArr);
            while (a2 != null && a2.moveToNext()) {
                c2157e.f11346b.add(a2.getString(0));
                HashMap hashMap = new HashMap();
                hashMap.put(SocialConstants.PARAM_TYPE, Integer.valueOf(a2.getInt(1)));
                hashMap.put("errat", Long.valueOf(a2.getLong(2)));
                hashMap.put(SocialConstants.PARAM_SEND_MSG, Base64.encodeToString(a2.getString(3).getBytes(), 2));
                hashMap.put("times", Integer.valueOf(a2.getInt(4)));
                c2157e.f11345a.add(hashMap);
                if (c2157e.f11346b.size() == 50) {
                    arrayList.add(c2157e);
                    c2157e = new C2157e();
                    break;
                }
            }
            a2.close();
            if (c2157e.f11346b.size() != 0) {
                arrayList.add(c2157e);
            }
        }
        return arrayList;
    }

    public static synchronized ArrayList<C2157e> m13214a(Context context, String[] strArr) {
        ArrayList<C2157e> a;
        synchronized (C2158f.class) {
            String str = "exception_level = ?";
            if (strArr == null || strArr.length <= 0) {
                str = null;
                strArr = null;
            }
            a = C2154b.m13178a(context).m13179a("table_exception") > 0 ? C2158f.m13213a(context, str, strArr) : new ArrayList();
        }
        return a;
    }
}
