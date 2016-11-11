package com.p016a;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.aa */
public class aa implements C0239w<ab> {
    private static final String f506a;
    private static final String f507b;
    private static final String f508c;
    private static final String f509d;
    private ab f510e;
    private int f511f;

    static {
        f506a = C0263y.f1328l;
        f507b = C0263y.f1329m;
        f508c = C0263y.f1330n;
        f509d = C0263y.f1322f;
    }

    public aa(int i) {
        this.f510e = null;
        this.f511f = i;
    }

    public static String m967a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(f507b).append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String m968a(String str) {
        Map hashMap = new HashMap();
        hashMap.put(f506a, str);
        return C0261v.m2042a(hashMap);
    }

    public ContentValues m969a() {
        Throwable th;
        ContentValues contentValues = null;
        try {
            if (this.f510e == null) {
                return null;
            }
            ContentValues contentValues2 = new ContentValues();
            try {
                contentValues2.put(f506a, this.f510e.m978b());
                contentValues2.put(f507b, Integer.valueOf(this.f510e.m975a()));
                contentValues2.put(f509d, ah.m1011a(this.f510e.m981c()));
                contentValues2.put(f508c, Integer.valueOf(this.f510e.m982d()));
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

    public /* synthetic */ Object m970a(Cursor cursor) {
        return m973b(cursor);
    }

    public void m971a(ab abVar) {
        this.f510e = abVar;
    }

    public /* synthetic */ void m972a(Object obj) {
        m971a((ab) obj);
    }

    public ab m973b(Cursor cursor) {
        Throwable th;
        ab abVar = null;
        if (cursor == null) {
            return null;
        }
        try {
            String string = cursor.getString(1);
            int i = cursor.getInt(2);
            String string2 = cursor.getString(4);
            int i2 = cursor.getInt(3);
            ab abVar2 = new ab();
            try {
                abVar2.m977a(string);
                abVar2.m976a(i);
                abVar2.m980b(ah.m1012b(string2));
                abVar2.m979b(i2);
                return abVar2;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                abVar = abVar2;
                th = th3;
                th.printStackTrace();
                return abVar;
            }
        } catch (Throwable th4) {
            th = th4;
            th.printStackTrace();
            return abVar;
        }
    }

    public String m974b() {
        return C0255n.m2029a(this.f511f);
    }
}
