package com.p016a;

import android.database.sqlite.SQLiteDatabase;

/* renamed from: com.a.y */
public class C0263y implements C0240u {
    static final String f1317a;
    public static final String f1318b;
    public static final String f1319c;
    public static final String f1320d;
    static final String f1321e;
    static final String f1322f;
    static final String f1323g;
    static final String f1324h;
    static final String f1325i;
    static final String f1326j;
    static final String f1327k;
    static final String f1328l;
    static final String f1329m;
    static final String f1330n;
    static final String f1331o;
    static final String f1332p;
    static final String f1333q;
    private static final String f1334r;
    private static final String f1335s;
    private static final String f1336t;
    private static C0263y f1337u;

    static {
        f1317a = "a";
        f1318b = "b";
        f1319c = "c";
        f1320d = "d";
        f1321e = "e";
        f1322f = "a1";
        f1323g = "a2";
        f1324h = "a3";
        f1325i = "a4";
        f1326j = "a5";
        f1327k = "a6";
        f1328l = "b1";
        f1329m = "b2";
        f1330n = "b3";
        f1331o = "c1";
        f1332p = "c2";
        f1333q = "c3";
        f1334r = "CREATE TABLE IF NOT EXISTS " + f1317a + " (_id integer primary key autoincrement, " + f1322f + "  varchar(20), " + f1323g + " varchar(10)," + f1324h + " varchar(50)," + f1325i + " varchar(100)," + f1326j + " varchar(20)," + f1327k + " integer);";
        f1335s = "CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement," + f1328l + " varchar(40), " + f1329m + " integer," + f1330n + "  integer," + f1322f + "  varchar(20));";
        f1336t = "CREATE TABLE IF NOT EXISTS " + f1321e + " (_id integer primary key autoincrement," + f1331o + " integer," + f1332p + " integer," + f1333q + " integer);";
    }

    private C0263y() {
    }

    public static synchronized C0263y m2053c() {
        C0263y c0263y;
        synchronized (C0263y.class) {
            if (f1337u == null) {
                f1337u = new C0263y();
            }
            c0263y = f1337u;
        }
        return c0263y;
    }

    public String m2054a() {
        return "logdb.db";
    }

    public void m2055a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f1334r);
            sQLiteDatabase.execSQL(String.format(f1335s, new Object[]{f1318b}));
            sQLiteDatabase.execSQL(String.format(f1335s, new Object[]{f1319c}));
            sQLiteDatabase.execSQL(String.format(f1335s, new Object[]{f1320d}));
            sQLiteDatabase.execSQL(f1336t);
        } catch (Throwable th) {
            C0247g.m1917a(th, "DB", "onCreate");
        }
    }

    public void m2056a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public int m2057b() {
        return 1;
    }
}
