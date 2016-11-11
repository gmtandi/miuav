package com.p016a;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.ad */
public class ad implements C0239w<gd> {
    private static String f518a;
    private static String f519b;
    private static String f520c;
    private static String f521d;
    private static String f522e;
    private static String f523f;
    private gd f524g;

    static {
        f518a = C0263y.f1322f;
        f519b = C0263y.f1323g;
        f520c = C0263y.f1327k;
        f521d = C0263y.f1324h;
        f522e = C0263y.f1325i;
        f523f = C0263y.f1326j;
    }

    public ad() {
        this.f524g = null;
    }

    public static String m986a(String str) {
        Map hashMap = new HashMap();
        hashMap.put(f518a, ah.m1011a(str));
        return C0261v.m2042a(hashMap);
    }

    private String m987a(String[] strArr) {
        String str = null;
        if (strArr != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append).append(";");
                }
                str = stringBuilder.toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    private String[] m988b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String m989c() {
        return f520c + "=1";
    }

    public ContentValues m990a() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f524g == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f518a, ah.m1011a(this.f524g.m1938a()));
                contentValues2.put(f519b, ah.m1011a(this.f524g.m1940b()));
                contentValues2.put(f520c, Boolean.valueOf(this.f524g.m1943e()));
                contentValues2.put(f521d, ah.m1011a(this.f524g.m1941c()));
                contentValues2.put(f523f, ah.m1011a(this.f524g.m1942d()));
                contentValues2.put(f522e, ah.m1011a(m987a(this.f524g.m1944f())));
                return contentValues2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                contentValues = contentValues2;
                th = th3;
                th.printStackTrace();
                return contentValues;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return contentValues;
        }
    }

    public /* synthetic */ Object m991a(Cursor cursor) {
        return m994b(cursor);
    }

    public void m992a(gd gdVar) {
        this.f524g = gdVar;
    }

    public /* synthetic */ void m993a(Object obj) {
        m992a((gd) obj);
    }

    public gd m994b(Cursor cursor) {
        boolean z = true;
        gd gdVar = null;
        try {
            String b = ah.m1012b(cursor.getString(1));
            String b2 = ah.m1012b(cursor.getString(2));
            String b3 = ah.m1012b(cursor.getString(3));
            String[] b4 = m988b(ah.m1012b(cursor.getString(4)));
            String b5 = ah.m1012b(cursor.getString(5));
            if (cursor.getInt(6) == 0) {
                z = false;
            }
            gdVar = new ge(b, b2, b3).m1953a(z).m1952a(b5).m1954a(b4).m1951a();
        } catch (fm e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return gdVar;
    }

    public String m995b() {
        return C0263y.f1317a;
    }
}
