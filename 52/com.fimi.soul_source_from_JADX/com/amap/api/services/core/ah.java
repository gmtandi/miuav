package com.amap.api.services.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ah extends SQLiteOpenHelper {
    static final String f2984a;
    static final String f2985b;
    static final String f2986c;
    static final String f2987d;
    static final String f2988e;
    static final String f2989f;
    static final String f2990g;
    static final String f2991h;
    static final String f2992i;
    static final String f2993j;
    static final String f2994k;
    static final String f2995l;
    static final String f2996m;
    static final String f2997n;
    static final String f2998o;
    static final String f2999p;
    static final String f3000q;
    private static final String f3001r;
    private static final String f3002s;
    private static final String f3003t;

    static {
        f2984a = "a";
        f2985b = "b";
        f2986c = "c";
        f2987d = "d";
        f2988e = "e";
        f2989f = "a1";
        f2990g = "a2";
        f2991h = "a3";
        f2992i = "a4";
        f2993j = "a5";
        f2994k = "a6";
        f2995l = "b1";
        f2996m = "b2";
        f2997n = "b3";
        f2998o = "c1";
        f2999p = "c2";
        f3000q = "c3";
        f3001r = "CREATE TABLE IF NOT EXISTS " + f2984a + " (_id integer primary key autoincrement, " + f2989f + "  varchar(20), " + f2990g + " varchar(10)," + f2991h + " varchar(50)," + f2992i + " varchar(100)," + f2993j + " varchar(20)," + f2994k + " integer);";
        f3002s = "CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement," + f2995l + " varchar(40), " + f2996m + " integer," + f2997n + "  integer," + f2989f + "  varchar(20));";
        f3003t = "CREATE TABLE IF NOT EXISTS " + f2988e + " (_id integer primary key autoincrement," + f2998o + " integer," + f2999p + " integer," + f3000q + " integer);";
    }

    public ah(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(f3001r);
            sQLiteDatabase.execSQL(String.format(f3002s, new Object[]{f2985b}));
            sQLiteDatabase.execSQL(String.format(f3002s, new Object[]{f2986c}));
            sQLiteDatabase.execSQL(String.format(f3002s, new Object[]{f2987d}));
            sQLiteDatabase.execSQL(f3003t);
        } catch (Throwable th) {
            ay.m4590a(th, "DB", "onCreate");
            th.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
