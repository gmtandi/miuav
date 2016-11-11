package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;
import com.amap.api.services.core.ad.C0452a;

public class ao implements ap<ad> {
    private static String f3013a;
    private static String f3014b;
    private static String f3015c;
    private static String f3016d;
    private static String f3017e;
    private static String f3018f;
    private ad f3019g;

    static {
        f3013a = ah.f2989f;
        f3014b = ah.f2990g;
        f3015c = ah.f2994k;
        f3016d = ah.f2991h;
        f3017e = ah.f2992i;
        f3018f = ah.f2993j;
    }

    public ao() {
        this.f3019g = null;
    }

    public static String m4548a(String str) {
        return f3013a + "='" + at.m4573a(str) + "'";
    }

    private String m4549a(String[] strArr) {
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

    private String[] m4550b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String m4551c() {
        return f3015c + "=1";
    }

    public ad m4552a(Cursor cursor) {
        boolean z = true;
        ad adVar = null;
        try {
            String b = at.m4574b(cursor.getString(1));
            String b2 = at.m4574b(cursor.getString(2));
            String b3 = at.m4574b(cursor.getString(3));
            String[] b4 = m4550b(at.m4574b(cursor.getString(4)));
            String b5 = at.m4574b(cursor.getString(5));
            if (cursor.getInt(6) == 0) {
                z = false;
            }
            adVar = new C0452a(b, b2, b3).m4491a(z).m4490a(b5).m4492a(b4).m4493a();
        } catch (C0495v e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return adVar;
    }

    public String m4553a() {
        return ah.f2984a;
    }

    public void m4554a(ad adVar) {
        this.f3019g = adVar;
    }

    public ContentValues m4556b() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f3019g == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f3013a, at.m4573a(this.f3019g.m4494a()));
                contentValues2.put(f3014b, at.m4573a(this.f3019g.m4495b()));
                contentValues2.put(f3015c, Boolean.valueOf(this.f3019g.m4498e()));
                contentValues2.put(f3016d, at.m4573a(this.f3019g.m4496c()));
                contentValues2.put(f3018f, at.m4573a(this.f3019g.m4497d()));
                contentValues2.put(f3017e, at.m4573a(m4549a(this.f3019g.m4499f())));
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

    public /* synthetic */ Object m4557b(Cursor cursor) {
        return m4552a(cursor);
    }
}
